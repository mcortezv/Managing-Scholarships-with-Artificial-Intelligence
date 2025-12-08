package controles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import excepciones.InfraestructuraModelMLException;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

/**
 * Control para interactuar con la API de Machine Learning
 * @author Cortez, Manuel
 */
public class ControlModeloML {
    private static final String API_URL = "http://0.0.0.0:8000/predecir";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ControlModeloML() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Genera una predicción de resolución usando el modelo de ML
     * @param solicitud Solicitud a evaluar
     * @return ResolucionDTOGobierno con la predicción
     */
    public ResolucionDTOGobierno predecir(SolicitudDTOGobierno solicitud) {
        try {
            // Construir el JSON de entrada para la API
            String jsonInput = construirJsonSolicitud(solicitud);

            // Crear la petición HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInput))
                    .build();

            // Enviar petición y obtener respuesta
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            // Verificar código de respuesta
            if (response.statusCode() != 200) {
                throw new InfraestructuraModelMLException(
                        "Error en API de ML. Código: " + response.statusCode());
            }

            // Parsear respuesta JSON
            return parsearRespuesta(response.body(), solicitud);

        } catch (Exception ex) {
            throw new InfraestructuraModelMLException(
                    "Error al llamar a la API de ML: " + ex.getMessage());
        }
    }

    /**
     * Construye el JSON esperado por la API de ML
     */
    private String construirJsonSolicitud(SolicitudDTOGobierno solicitud) {
        try {
            // Mapear los datos de la solicitud al formato esperado por la API
            var solicitudML = new SolicitudMLRequest(
                    solicitud.getHistorialAcademico().getPromedio(),
                    solicitud.getHistorialAcademico().getPorcentajeBajas(),
                    solicitud.getHistorialAcademico().getIndiceReprobacion(),
                    solicitud.getHistorialAcademico().getSemestre(),
                    mapearCarrera(solicitud.getHistorialAcademico().getCarrera()),
                    solicitud.getHistorialAcademico().getCargaAcademica(),
                    solicitud.getInformacionSocioeconomica().getIngresoTotalFamilarMensual().doubleValue(),
                    mapearTipoVivienda(solicitud.getInformacionSocioeconomica().getTipoVivienda()),
                    solicitud.getInformacionSocioeconomica().getTrabajo() ? 1 : 0,
                    solicitud.getInformacionSocioeconomica().getDeudas() ? 1 : 0,
                    mapearTipoBeca(solicitud.getBeca().getTipo()),
                    solicitud.getBeca().getBecasDisponibles()
            );

            return objectMapper.writeValueAsString(solicitudML);
        } catch (Exception ex) {
            throw new InfraestructuraModelMLException(
                    "Error al construir JSON de solicitud: " + ex.getMessage());
        }
    }

    /**
     * Parsea la respuesta JSON de la API a ResolucionDTOGobierno
     */
    private ResolucionDTOGobierno parsearRespuesta(String jsonResponse,
                                                   SolicitudDTOGobierno solicitud) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);

            ResolucionDTOGobierno resolucion = new ResolucionDTOGobierno();
            resolucion.setSolicitud(solicitud);
            resolucion.setDecision(mapearDecision(root.get("resolution").asText()));
            resolucion.setMotivo(root.get("reason").asText());
            resolucion.setFechaEvaluacion(LocalDate.now());

            // Logging de confianza (opcional)
            double porcentaje = root.get("percentage").asDouble();
            System.out.println("Predicción con confianza: " + porcentaje + "%");

            return resolucion;
        } catch (Exception ex) {
            throw new InfraestructuraModelMLException(
                    "Error al parsear respuesta de ML: " + ex.getMessage());
        }
    }

    /**
     * Mapea el tipo de carrera al formato esperado por la API
     */
    private String mapearCarrera(String carrera) {
        switch (carrera.toUpperCase()) {
            case "INGENIERIA": return "Engineering";
            case "LICENCIATURA": return "Bachelor";
            case "MAESTRIA": return "Masters";
            case "DOCTORADO": return "Doctorate";
            default: return "Bachelor";
        }
    }

    /**
     * Mapea el tipo de vivienda al formato esperado por la API
     */
    private String mapearTipoVivienda(String tipoVivienda) {
        switch (tipoVivienda.toUpperCase()) {
            case "CASA_PROPIA": return "Own House";
            case "DEPARTAMENTO": return "Apartment";
            case "RESIDENCIA": return "Residence";
            case "VIVIENDA_IMPROVISADA": return "Improvised Housing";
            default: return "Apartment";
        }
    }

    /**
     * Mapea el tipo de beca al formato esperado por la API
     * IMPORTANTE: La API usa los mismos nombres para todas las becas
     */
    private String mapearTipoBeca(String tipoBeca) {
        // Según la estructura de la API, todas las becas usan el mismo nombre
        // Solo se diferencian por sus requisitos
        switch (tipoBeca.toUpperCase()) {
            case "EXCELENCIA_ACADEMICA": return "Academic Excellence";
            case "ESCASOS_RECURSOS": return "Low Income";
            case "CONSTANCIA": return "Persistence";
            case "ESTUDIANTE_TRABAJO": return "Working Student";
            default: return tipoBeca; // Usar el nombre original si no coincide
        }
    }

    /**
     * Mapea la decisión de la API al enum del sistema
     */
    private String mapearDecision(String resolutionAPI) {
        // La API devuelve: 0 (Rechazada), 1 (Aceptada), 2 (Devuelta)
        switch (resolutionAPI) {
            case "0": return "RECHAZADA";
            case "1": return "ACEPTADA";
            case "2": return "DEVUELTA";
            default: return "RECHAZADA";
        }
    }

    /**
     * Clase interna para la solicitud a la API de ML
     */
    private static class SolicitudMLRequest {
        public double student_gpa;
        public double dropout_rate;
        public double failure_rate;
        public int semester;
        public String degree_program;
        public double course_load;
        public double total_household_income;
        public String housing_type;
        public int employment;
        public int debts;
        public String category;
        public int number_of_scholarships_awarded;

        public SolicitudMLRequest(double student_gpa, double dropout_rate,
                                  double failure_rate, int semester, String degree_program,
                                  double course_load, double total_household_income,
                                  String housing_type, int employment, int debts,
                                  String category, int number_of_scholarships_awarded) {
            this.student_gpa = student_gpa;
            this.dropout_rate = dropout_rate;
            this.failure_rate = failure_rate;
            this.semester = semester;
            this.degree_program = degree_program;
            this.course_load = course_load;
            this.total_household_income = total_household_income;
            this.housing_type = housing_type;
            this.employment = employment;
            this.debts = debts;
            this.category = category;
            this.number_of_scholarships_awarded = number_of_scholarships_awarded;
        }
    }
}