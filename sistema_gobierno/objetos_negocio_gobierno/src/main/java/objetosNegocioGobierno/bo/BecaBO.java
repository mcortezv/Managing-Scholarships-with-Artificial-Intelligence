package objetosNegocioGobierno.bo;

import datosGobierno.adaptadoresGobierno.BecaAdaptador;
import datosGobierno.daoGobierno.interfacesGobierno.IBecaDAO;
import datosGobierno.dominioGobierno.Beca;
import dtoGobierno.BecaDTO;
import objetosNegocioGobierno.bo.excepciones.BecaBOException;
import objetosNegocioGobierno.bo.interfaces.IBecaBO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Business Object corregido para gestión de becas
 * @author Cortez, Manuel
 */
public class BecaBO implements IBecaBO {
    private final IBecaDAO becaDAO;

    public BecaBO(IBecaDAO becaDAO) {
        if (becaDAO == null) {
            throw new IllegalArgumentException("BecaDAO no puede ser nulo");
        }
        this.becaDAO = becaDAO;
    }

    @Override
    public List<BecaDTO> obtenerListadoBecas() {
        try {
            System.out.println("DEBUG BecaBO: Obteniendo becas con solicitudes...");

            // Obtener becas que tienen solicitudes
            List<Beca> becas = becaDAO.obtenerBecasConSolicitudes();

            System.out.println("DEBUG BecaBO: Becas obtenidas del DAO: " +
                    (becas != null ? becas.size() : "null"));

            if (becas == null || becas.isEmpty()) {
                System.out.println("DEBUG BecaBO: No hay becas con solicitudes");
                throw new BecaBOException(
                        "No hay becas con solicitudes disponibles para evaluación");
            }

            // Convertir a DTO (sin filtrar por fechas, ya que solo queremos
            // becas que tengan solicitudes activas)
            List<BecaDTO> resultado = new ArrayList<>();
            for (Beca beca : becas) {
                BecaDTO dto = BecaAdaptador.toDTO(beca);
                resultado.add(dto);
                System.out.println("DEBUG BecaBO: Agregando beca: " + beca.getNombre() +
                        " (" + beca.getTipo() + ")");
            }

            System.out.println("DEBUG BecaBO: Total becas a retornar: " + resultado.size());

            if (resultado.isEmpty()) {
                throw new BecaBOException(
                        "No hay becas con solicitudes disponibles");
            }

            return resultado;

        } catch (BecaBOException ex) {
            System.err.println("ERROR BecaBO: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("ERROR BecaBO: " + ex.getMessage());
            ex.printStackTrace();
            throw new BecaBOException(
                    "Error al obtener listado de becas: " + ex.getMessage());
        }
    }

    /**
     * Valida si una beca está activa según su periodo
     * NOTA: Este método ya no se usa en obtenerListadoBecas() porque
     * ahora filtramos solo por existencia de solicitudes
     */
    private boolean validarBecaActiva(Beca beca, LocalDate fechaActual) {
        if (beca.getFechaInicio() == null || beca.getFechaFin() == null) {
            return true; // Si no tiene fechas, la consideramos activa
        }

        boolean dentroConvocatoria =
                !fechaActual.isBefore(beca.getFechaInicio()) &&
                        !fechaActual.isAfter(beca.getFechaFin());

        boolean dentroEvaluacion = false;
        if (beca.getFechaResultados() != null) {
            LocalDate fechaLimiteEvaluacion = beca.getFechaResultados().plusDays(30);
            dentroEvaluacion = !fechaActual.isAfter(fechaLimiteEvaluacion);
        }

        return dentroConvocatoria || dentroEvaluacion;
    }
}