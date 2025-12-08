package controles;

import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.Decision;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import excepciones.NegociosModificarResolucionesException;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import objetosNegocioGobierno.adaptadores.ResolucionAdaptador;
import objetosNegocioGobierno.adaptadores.SolicitudAdaptador;
import objetosNegocioGobierno.bo.interfaces.IResolucionBO;
import objetosNegocioGobierno.bo.interfaces.ISolicitudBO;

import java.time.LocalDate;
import java.util.List;

/**
 * Control de negocio para modificación de resoluciones
 * @author Cortez, Manuel
 */
public class ControlModificarResolucion {
    private final IResolucionBO resolucionBO;
    private final ISolicitudBO solicitudBO;

    public ControlModificarResolucion(IResolucionBO resolucionBO, ISolicitudBO solicitudBO) {
        this.resolucionBO = resolucionBO;
        this.solicitudBO = solicitudBO;
    }

    /**
     * Valida si una convocatoria está disponible
     * @param idConvocatoria ID de la convocatoria
     * @return true si está disponible
     */
    public boolean validarConvocatoriaDisp(int idConvocatoria) {
        try {
            // Validar que el ID sea válido
            if (idConvocatoria <= 0) {
                throw new NegociosModificarResolucionesException(
                        "ID de convocatoria inválido");
            }

            // Aquí irían validaciones adicionales como:
            // - Verificar que la convocatoria existe
            // - Verificar que está en periodo de modificación
            // - Verificar que no está cerrada

            return true;
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException(
                    "Error al validar convocatoria: " + ex.getMessage());
        }
    }

    /**
     * Busca una resolución por filtros específicos
     * @param tipoFiltro Tipo de filtro (MATRICULA, NOMBRE, ID_SOLICITUD)
     * @param filtro Valor del filtro
     * @return ResolucionDTO encontrada
     */
    public ResolucionDTO buscarResolucion(String tipoFiltro, String filtro) {
        try {
            // Validar parámetros de búsqueda
            validarParametrosBusqueda(tipoFiltro, filtro);

            // Buscar resolución
            Resolucion resolucion = resolucionBO.obtenerResolucionPorFiltro(tipoFiltro, filtro);

            if (resolucion == null) {
                throw new NegociosModificarResolucionesException(
                        "No se encontró resolución con el filtro proporcionado");
            }

            // Validar que la resolución sea modificable
            validarResolucionModificable(resolucion);

            return ResolucionAdaptador.toDTO(resolucion);

        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException(
                    "Error al buscar resolución: " + ex.getMessage());
        }
    }

    /**
     * Genera una resolución automática para re-evaluar
     * @param solicitudDTO Solicitud a re-evaluar
     * @return ResolucionDTO con la nueva predicción
     */
    public ResolucionDTO resolverAutomatico(SolicitudDTO solicitudDTO) {
        try {
            // Validar que la solicitud pueda ser re-evaluada
            validarSolicitudParaReevaluacion(solicitudDTO);

            // Convertir a entidad y DTO de infraestructura
            Solicitud solicitud = SolicitudAdaptador.toEntity(solicitudDTO);
            SolicitudDTOGobierno solicitudInfraestructuraDTO =
                    SolicitudAdaptador.toInfraestructuraDTO(solicitud);

            // Llamar al modelo de ML
            ResolucionDTOGobierno resolucionInfraestructuraDTO =
                    resolucionBO.crearResolucionAutomatica(solicitudInfraestructuraDTO);

            // Validar coherencia
            if (resolucionInfraestructuraDTO == null) {
                throw new NegociosModificarResolucionesException(
                        "Error al generar resolución automática");
            }

            // Convertir de vuelta a DTO
            Resolucion resolucion = ResolucionAdaptador.toEntity(resolucionInfraestructuraDTO);
            return ResolucionAdaptador.toDTO(resolucion);

        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException(
                    "Error al resolver automático: " + ex.getMessage());
        }
    }

    /**
     * Crea o actualiza una resolución manual
     * @param resolucionDTO Resolución manual del evaluador
     * @return true si se guardó correctamente
     */
    public boolean resolverManual(ResolucionDTO resolucionDTO) {
        try {
            // Validar resolución manual
            validarResolucionManual(resolucionDTO);

            // Crear la entidad de resolución
            Solicitud solicitud = SolicitudAdaptador.toEntity(resolucionDTO.getSolicitud());
            Decision decision = Decision.valueOf(resolucionDTO.getDecision());
            String motivo = resolucionDTO.getMotivo();
            LocalDate fechaEvaluacion = resolucionDTO.getFechaEvaluacion();

            Resolucion resolucion = resolucionBO.crearResolucion(
                    solicitud, decision, motivo, fechaEvaluacion);

            // Cambiar estado de la solicitud
            if (cambiarEstadoSolicitud(resolucion.getSolicitud())) {
                return resolucionBO.actualizarResolucion(resolucion);
            }

            return false;
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException(
                    "Error al resolver manual: " + ex.getMessage());
        }
    }

    /**
     * Modifica una resolución existente
     * @param resolucionDTO Resolución modificada
     * @return true si se actualizó correctamente
     */
    public boolean modificarResolucion(ResolucionDTO resolucionDTO) {
        try {
            // Validar que la resolución exista y sea modificable
            validarModificacionResolucion(resolucionDTO);

            // Convertir a entidad
            Resolucion resolucion = ResolucionAdaptador.toEntity(resolucionDTO);

            // Cambiar estado de la solicitud si cambió la decisión
            if (cambiarEstadoSolicitud(resolucion.getSolicitud())) {
                return resolucionBO.actualizarResolucion(resolucion);
            }

            return false;
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException(
                    "Error al modificar resolución: " + ex.getMessage());
        }
    }

    /**
     * Cambia el estado de una solicitud según su resolución
     */
    public boolean cambiarEstadoSolicitud(Solicitud solicitud) {
        try {
            if (solicitud == null) {
                throw new NegociosModificarResolucionesException(
                        "La solicitud no puede ser nula");
            }

            return solicitudBO.cambiarEstado((int) solicitud.getId(), solicitud.getEstado());
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException(
                    "Error al cambiar estado de solicitud: " + ex.getMessage());
        }
    }

    // ============= VALIDACIONES DE REGLAS DE NEGOCIO =============

    /**
     * Valida los parámetros de búsqueda
     */
    private void validarParametrosBusqueda(String tipoFiltro, String filtro) {
        if (tipoFiltro == null || tipoFiltro.trim().isEmpty()) {
            throw new NegociosModificarResolucionesException(
                    "El tipo de filtro no puede ser nulo o vacío");
        }

        if (filtro == null || filtro.trim().isEmpty()) {
            throw new NegociosModificarResolucionesException(
                    "El filtro no puede ser nulo o vacío");
        }

        List<String> tiposFiltroValidos = List.of("MATRICULA", "NOMBRE", "ID_SOLICITUD");
        if (!tiposFiltroValidos.contains(tipoFiltro.toUpperCase())) {
            throw new NegociosModificarResolucionesException(
                    "Tipo de filtro inválido: " + tipoFiltro);
        }

        // Validación específica según tipo de filtro
        switch (tipoFiltro.toUpperCase()) {
            case "MATRICULA":
                validarMatricula(filtro);
                break;
            case "NOMBRE":
                validarNombre(filtro);
                break;
            case "ID_SOLICITUD":
                validarIdSolicitud(filtro);
                break;
        }
    }

    /**
     * Valida formato de matrícula
     */
    private void validarMatricula(String matricula) {
        try {
            Long.parseLong(matricula);
        } catch (NumberFormatException ex) {
            throw new NegociosModificarResolucionesException(
                    "La matrícula debe ser un número válido");
        }
    }

    /**
     * Valida formato de nombre
     */
    private void validarNombre(String nombre) {
        if (nombre.length() < 3) {
            throw new NegociosModificarResolucionesException(
                    "El nombre debe tener al menos 3 caracteres");
        }
    }

    /**
     * Valida formato de ID de solicitud
     */
    private void validarIdSolicitud(String idSolicitud) {
        try {
            Long.parseLong(idSolicitud);
        } catch (NumberFormatException ex) {
            throw new NegociosModificarResolucionesException(
                    "El ID de solicitud debe ser un número válido");
        }
    }

    /**
     * Valida que una resolución sea modificable
     */
    private void validarResolucionModificable(Resolucion resolucion) {
        if (resolucion == null) {
            throw new NegociosModificarResolucionesException(
                    "La resolución no puede ser nula");
        }

        // Validar que no haya pasado el periodo de modificación
        LocalDate fechaEvaluacion = resolucion.getFechaEvaluacion();
        LocalDate fechaLimite = fechaEvaluacion.plusDays(30); // 30 días para modificar

        if (LocalDate.now().isAfter(fechaLimite)) {
            throw new NegociosModificarResolucionesException(
                    "El periodo de modificación ha expirado (30 días desde la evaluación)");
        }

        // Validar que la beca aún esté en periodo de resultados
        LocalDate fechaResultados = resolucion.getSolicitud().getBeca().getFechaResultados();
        if (fechaResultados != null && LocalDate.now().isAfter(fechaResultados.plusDays(15))) {
            throw new NegociosModificarResolucionesException(
                    "No se pueden modificar resoluciones después de 15 días de publicados los resultados");
        }
    }

    /**
     * Valida que una solicitud pueda ser re-evaluada
     */
    private void validarSolicitudParaReevaluacion(SolicitudDTO solicitud) {
        if (solicitud == null) {
            throw new NegociosModificarResolucionesException(
                    "La solicitud no puede ser nula");
        }

        // Validar que tenga información completa
        if (solicitud.getHistorialAcademico() == null) {
            throw new NegociosModificarResolucionesException(
                    "La solicitud debe tener historial académico");
        }

        if (solicitud.getInformacionSocioeconomica() == null) {
            throw new NegociosModificarResolucionesException(
                    "La solicitud debe tener información socioeconómica");
        }

        // Validar que no esté en estado ACTIVA (debe haber sido evaluada previamente)
        if (solicitud.getEstado().equals("ACTIVA")) {
            throw new NegociosModificarResolucionesException(
                    "No se puede re-evaluar una solicitud en estado ACTIVA");
        }
    }

    /**
     * Valida una resolución manual
     */
    private void validarResolucionManual(ResolucionDTO resolucion) {
        if (resolucion == null) {
            throw new NegociosModificarResolucionesException(
                    "La resolución no puede ser nula");
        }

        if (resolucion.getSolicitud() == null) {
            throw new NegociosModificarResolucionesException(
                    "La resolución debe estar asociada a una solicitud");
        }

        if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
            throw new NegociosModificarResolucionesException(
                    "Debe especificar una decisión");
        }

        List<String> decisionesValidas = List.of("ACEPTADA", "RECHAZADA", "DEVUELTA");
        if (!decisionesValidas.contains(resolucion.getDecision().toUpperCase())) {
            throw new NegociosModificarResolucionesException(
                    "Decisión inválida: " + resolucion.getDecision());
        }

        if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
            throw new NegociosModificarResolucionesException(
                    "Debe especificar un motivo");
        }

        if (resolucion.getMotivo().length() < 10) {
            throw new NegociosModificarResolucionesException(
                    "El motivo debe tener al menos 10 caracteres");
        }

        if (resolucion.getMotivo().length() > 500) {
            throw new NegociosModificarResolucionesException(
                    "El motivo no puede exceder 500 caracteres");
        }

        if (resolucion.getFechaEvaluacion() == null) {
            throw new NegociosModificarResolucionesException(
                    "Debe especificar una fecha de evaluación");
        }

        if (resolucion.getFechaEvaluacion().isAfter(LocalDate.now())) {
            throw new NegociosModificarResolucionesException(
                    "La fecha de evaluación no puede ser futura");
        }
    }

    /**
     * Valida la modificación de una resolución
     */
    private void validarModificacionResolucion(ResolucionDTO resolucion) {
        validarResolucionManual(resolucion);

        // Validación adicional: verificar que la resolución exista
        if (resolucion.getSolicitud().getId() <= 0) {
            throw new NegociosModificarResolucionesException(
                    "La solicitud debe tener un ID válido");
        }

        // Validar que haya una razón válida para la modificación
        if (!resolucion.getMotivo().toLowerCase().contains("modificación") &&
                !resolucion.getMotivo().toLowerCase().contains("corrección") &&
                !resolucion.getMotivo().toLowerCase().contains("revisión")) {

            throw new NegociosModificarResolucionesException(
                    "El motivo debe explicar claramente la razón de la modificación");
        }
    }
}