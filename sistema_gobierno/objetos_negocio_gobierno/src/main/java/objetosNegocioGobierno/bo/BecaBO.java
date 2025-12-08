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
 * Business Object para gestión de becas
 * Implementa validaciones de reglas de negocio
 *
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
            // Obtener becas que tienen solicitudes
            List<Beca> becas = becaDAO.obtenerBecasConSolicitudes();

            if (becas == null || becas.isEmpty()) {
                throw new BecaBOException(
                        "No hay becas con solicitudes disponibles para evaluación");
            }

            // Filtrar solo becas activas
            List<BecaDTO> resultado = new ArrayList<>();
            LocalDate fechaActual = LocalDate.now();

            for (Beca beca : becas) {
                // Validar que la beca esté en periodo activo
                if (validarBecaActiva(beca, fechaActual)) {
                    resultado.add(BecaAdaptador.toDTO(beca));
                }
            }

            if (resultado.isEmpty()) {
                throw new BecaBOException(
                        "No hay becas activas con solicitudes en este momento");
            }

            return resultado;

        } catch (BecaBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BecaBOException(
                    "Error al obtener listado de becas: " + ex.getMessage());
        }
    }

    /**
     * Valida si una beca está activa según su periodo
     */
    private boolean validarBecaActiva(Beca beca, LocalDate fechaActual) {
        // Validar que la beca tenga fechas configuradas
        if (beca.getFechaInicio() == null || beca.getFechaFin() == null) {
            return false; // No se puede evaluar sin fechas
        }

        // Validar que estemos dentro del periodo de la convocatoria
        // O dentro del periodo de evaluación (hasta fecha de resultados + 30 días)
        boolean dentroConvocatoria =
                !fechaActual.isBefore(beca.getFechaInicio()) &&
                        !fechaActual.isAfter(beca.getFechaFin());

        boolean dentroEvaluacion = false;
        if (beca.getFechaResultados() != null) {
            LocalDate fechaLimiteEvaluacion = beca.getFechaResultados().plusDays(30);
            dentroEvaluacion = !fechaActual.isAfter(fechaLimiteEvaluacion);
        }

        // La beca está activa si está dentro de convocatoria o dentro del periodo de evaluación
        return dentroConvocatoria || dentroEvaluacion;
    }

    /**
     * Valida los requisitos de una beca
     */
    private void validarRequisitos(Beca beca) {
        if (beca.getRequisitos() == null) {
            throw new BecaBOException(
                    "La beca debe tener requisitos definidos");
        }

        // Validar rangos de requisitos
        if (beca.getRequisitos().getPromedioMinimo() < 0 ||
                beca.getRequisitos().getPromedioMinimo() > 100) {
            throw new BecaBOException(
                    "El promedio mínimo debe estar entre 0 y 100");
        }

        if (beca.getRequisitos().getIngresoFamiliarMaximo() < 0) {
            throw new BecaBOException(
                    "El ingreso familiar máximo no puede ser negativo");
        }

        if (beca.getRequisitos().getCargaAcademica() < 0) {
            throw new BecaBOException(
                    "La carga académica no puede ser negativa");
        }
    }
}