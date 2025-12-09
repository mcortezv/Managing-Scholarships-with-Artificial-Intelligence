package controles;

import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.BecaDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import excepciones.NegociosEvaluarSolicitudesException;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import objetosNegocioGobierno.adaptadores.ResolucionAdaptador;
import objetosNegocioGobierno.adaptadores.SolicitudAdaptador;
import objetosNegocioGobierno.bo.interfaces.IBecaBO;
import objetosNegocioGobierno.bo.interfaces.IResolucionBO;
import objetosNegocioGobierno.bo.interfaces.ISolicitudBO;

import java.time.LocalDate;
import java.util.List;

/**
 * Control de negocio para evaluación de solicitudes
 * @author Cortez, Manuel
 */
public class ControlEvaluarSolicitudes {
    private final IResolucionBO resolucionBO;
    private final ISolicitudBO solicitudBO;
    private final IBecaBO becaBO;

    public ControlEvaluarSolicitudes(IResolucionBO resolucionBO, ISolicitudBO solicitudBO, IBecaBO becaBO) {
        this.resolucionBO = resolucionBO;
        this.solicitudBO = solicitudBO;
        this.becaBO = becaBO;
    }

    /**
     * Obtiene el listado de becas disponibles con solicitudes
     */
    public List<BecaDTO> obtenerListadoBecas() {
        try {
            return becaBO.obtenerListadoBecas();
        } catch (Exception ex) {
            throw new NegociosEvaluarSolicitudesException(
                    "Error al obtener listado de becas: " + ex.getMessage());
        }
    }

    /**
     * Obtiene las solicitudes filtradas por tipo de beca
     * @param tipoBeca Tipo de beca a filtrar
     * @return Lista de solicitudes
     */
    public List<SolicitudDTO> obtenerSolicitudes(String tipoBeca) {
        try {
            validarTipoBeca(tipoBeca);
            return solicitudBO.obtenerListadoSolicitudes(tipoBeca);
        } catch (Exception ex) {
            throw new NegociosEvaluarSolicitudesException(
                    "Error al obtener solicitudes: " + ex.getMessage());
        }
    }

    /**
     * Realiza evaluación automática usando ML
     * @param solicitudDTO Solicitud a evaluar
     * @return ResolucionDTO con la predicción
     */
    public ResolucionDTO evaluacionAutomatica(SolicitudDTO solicitudDTO) {
        try {
            // Validaciones de negocio
            validarSolicitudParaEvaluacion(solicitudDTO);

            // Convertir a entidad y DTO de infraestructura
            Solicitud solicitud = SolicitudAdaptador.toEntity(solicitudDTO);
            SolicitudDTOGobierno solicitudInfraestructuraDTO = SolicitudAdaptador.toInfraestructuraDTO(solicitud);

            // Llamar al modelo de ML
            ResolucionDTOGobierno resolucionInfraestructuraDTO = resolucionBO.crearResolucionAutomatica(solicitudInfraestructuraDTO);

            // Validar que la resolución sea coherente
            validarResolucion(resolucionInfraestructuraDTO);

            // Convertir de vuelta a DTO de presentación
            Resolucion resolucion = ResolucionAdaptador.toEntity(resolucionInfraestructuraDTO);
            return ResolucionAdaptador.toDTO(resolucion);

        } catch (Exception ex) {
            throw new NegociosEvaluarSolicitudesException(
                    "Error en evaluación automática: " + ex.getMessage());
        }
    }

    /**
     * Resuelve una solicitud de forma manual
     * @param resolucionDTO Resolución manual del evaluador
     * @return true si se guardó correctamente
     */
    public boolean resolverSolicitudManual(ResolucionDTO resolucionDTO) {
        try {
            // Validaciones de negocio
            validarResolucionManual(resolucionDTO);

            // Validar que la solicitud esté en estado ACTIVA
            if (!resolucionDTO.getSolicitud().getEstado().equals("ACTIVA")) {
                throw new NegociosEvaluarSolicitudesException(
                        "Solo se pueden evaluar solicitudes en estado ACTIVA");
            }

            // Determinar nuevo estado según decisión
            String nuevoEstado = resolucionDTO.getDecision();

            // Cambiar estado de la solicitud
            if (cambiarEstadoSolicitud((int) resolucionDTO.getSolicitud().getId(), nuevoEstado)) {
                // Guardar la resolución
                return resolucionBO.resolver(ResolucionAdaptador.toEntity(resolucionDTO));
            }

            return false;
        } catch (Exception ex) {
            throw new NegociosEvaluarSolicitudesException(
                    "Error al resolver solicitud manual: " + ex.getMessage());
        }
    }

    /**
     * Resuelve y guarda una resolución (automática o manual)
     * @param resolucionDTO Resolución a guardar
     * @return true si se guardó correctamente
     */
    public boolean resolver(ResolucionDTO resolucionDTO) {
        try {
            validarResolucionParaGuardar(resolucionDTO);

            String nuevoEstado = resolucionDTO.getDecision();

            if (cambiarEstadoSolicitud((int) resolucionDTO.getSolicitud().getId(), nuevoEstado)) {
                return resolucionBO.resolver(ResolucionAdaptador.toEntity(resolucionDTO));
            }

            return false;
        } catch (Exception ex) {
            throw new NegociosEvaluarSolicitudesException(
                    "Error al resolver solicitud: " + ex.getMessage());
        }
    }

    /**
     * Cambia el estado de una solicitud
     */
    public boolean cambiarEstadoSolicitud(int id, String nuevoEstado) {
        try {
            validarCambioEstado(nuevoEstado);
            return solicitudBO.cambiarEstado(id, EstadoSolicitud.valueOf(nuevoEstado));
        } catch (Exception ex) {
            throw new NegociosEvaluarSolicitudesException(
                    "Error al cambiar estado: " + ex.getMessage());
        }
    }

    // ============= VALIDACIONES DE REGLAS DE NEGOCIO =============

    /**
     * Valida que el tipo de beca sea válido
     */
    private void validarTipoBeca(String tipoBeca) {
        if (tipoBeca == null || tipoBeca.trim().isEmpty()) {
            throw new NegociosEvaluarSolicitudesException("El tipo de beca no puede ser nulo o vacío");
        }

        List<String> tiposValidos = List.of(
                "EXCELENCIA_ACADEMICA", "ESCASOS_RECURSOS",
                "CONSTANCIA", "ESTUDIANTE_TRABAJO"
        );

        if (!tiposValidos.contains(tipoBeca.toUpperCase())) {
            throw new NegociosEvaluarSolicitudesException("Tipo de beca inválido: " + tipoBeca);
        }
    }

    /**
     * Valida que la solicitud esté lista para evaluación
     */
    private void validarSolicitudParaEvaluacion(SolicitudDTO solicitud) {
        if (solicitud == null) {
            throw new NegociosEvaluarSolicitudesException("La solicitud no puede ser nula");
        }

        if (!solicitud.getEstado().equals("ACTIVA")) {
            throw new NegociosEvaluarSolicitudesException(
                    "Solo se pueden evaluar solicitudes en estado ACTIVA");
        }

        // Validar que tenga información completa
        if (solicitud.getHistorialAcademico() == null) {
            throw new NegociosEvaluarSolicitudesException(
                    "La solicitud debe tener historial académico");
        }

        if (solicitud.getInformacionSocioeconomica() == null) {
            throw new NegociosEvaluarSolicitudesException(
                    "La solicitud debe tener información socioeconómica");
        }

        if (solicitud.getBeca() == null) {
            throw new NegociosEvaluarSolicitudesException(
                    "La solicitud debe estar asociada a una beca");
        }

        // Validar promedio válido
        double promedio = solicitud.getHistorialAcademico().getPromedio();
        if (promedio < 0 || promedio > 100) {
            throw new NegociosEvaluarSolicitudesException(
                    "El promedio debe estar entre 0 y 100");
        }
    }

    /**
     * Valida que la resolución sea coherente
     */
    private void validarResolucion(ResolucionDTOGobierno resolucion) {
        if (resolucion == null) {
            throw new NegociosEvaluarSolicitudesException("La resolución no puede ser nula");
        }

        if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
            throw new NegociosEvaluarSolicitudesException("La decisión no puede ser nula");
        }

        if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
            throw new NegociosEvaluarSolicitudesException("El motivo no puede ser nulo");
        }
    }

    /**
     * Valida una resolución manual
     */
    private void validarResolucionManual(ResolucionDTO resolucion) {
        if (resolucion == null) {
            throw new NegociosEvaluarSolicitudesException("La resolución no puede ser nula");
        }

        if (resolucion.getSolicitud() == null) {
            throw new NegociosEvaluarSolicitudesException(
                    "La resolución debe estar asociada a una solicitud");
        }

        if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
            throw new NegociosEvaluarSolicitudesException("Debe especificar una decisión");
        }

        List<String> decisionesValidas = List.of("ACEPTADA", "RECHAZADA", "DEVUELTA");
        if (!decisionesValidas.contains(resolucion.getDecision().toUpperCase())) {
            throw new NegociosEvaluarSolicitudesException("Decisión inválida: " + resolucion.getDecision());
        }

        if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
            throw new NegociosEvaluarSolicitudesException("Debe especificar un motivo");
        }

        if (resolucion.getMotivo().length() < 10) {
            throw new NegociosEvaluarSolicitudesException(
                    "El motivo debe tener al menos 10 caracteres");
        }

        if (resolucion.getFechaEvaluacion() == null) {
            throw new NegociosEvaluarSolicitudesException("Debe especificar una fecha de evaluación");
        }

        // Validar que la fecha no sea futura
        if (resolucion.getFechaEvaluacion().isAfter(LocalDate.now())) {
            throw new NegociosEvaluarSolicitudesException(
                    "La fecha de evaluación no puede ser futura");
        }
    }

    /**
     * Valida una resolución antes de guardarla
     */
    private void validarResolucionParaGuardar(ResolucionDTO resolucion) {
        validarResolucionManual(resolucion);

        // Validación adicional: verificar que no exista ya una resolución para esta solicitud
        // (esto depende de tu regla de negocio - comentado por si no aplica)
        /*
        if (resolucionBO.obtenerPorSolicitud(resolucion.getSolicitud().getId()) != null) {
            throw new NegociosEvaluarSolicitudesException(
                "Ya existe una resolución para esta solicitud");
        }
        */
    }

    /**
     * Valida el cambio de estado
     */
    private void validarCambioEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            throw new NegociosEvaluarSolicitudesException("El nuevo estado no puede ser nulo");
        }

        List<String> estadosValidos = List.of("ACTIVA", "ACEPTADA", "RECHAZADA", "DEVUELTA");
        if (!estadosValidos.contains(nuevoEstado.toUpperCase())) {
            throw new NegociosEvaluarSolicitudesException("Estado inválido: " + nuevoEstado);
        }
    }
}