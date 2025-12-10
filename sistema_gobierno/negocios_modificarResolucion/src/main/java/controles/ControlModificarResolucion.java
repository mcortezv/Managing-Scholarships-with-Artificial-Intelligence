package controles;
import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.Decision;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import excepciones.NegociosModificarResolucionesException;
import gobierno.ResolucionDTOGobierno;
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
     * Busca una resolución por filtros específicos
     * @param tipoFiltro Tipo de filtro (MATRICULA, NOMBRE, ID_SOLICITUD)
     * @param filtro Valor del filtro
     * @return ResolucionDTO encontrada
     */
    public ResolucionDTO buscarResolucion(String tipoFiltro, String filtro) {
        try {
            validarParametrosBusqueda(tipoFiltro, filtro);

            // Buscar resolución
            ResolucionDTO resolucion = resolucionBO.obtenerResolucionPorFiltro(tipoFiltro, filtro);

            if (resolucion == null) {
                throw new NegociosModificarResolucionesException("No se encontró resolución con el filtro proporcionado");
            }

            validarResolucionModificable(resolucion);

            return resolucion;

        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException("Error al buscar resolución: " + ex.getMessage());
        }
    }

    /**
     * Genera una resolución automática para re-evaluar
     * @param solicitudDTO Solicitud a re-evaluar
     * @return ResolucionDTO con la nueva predicción
     */
    public ResolucionDTO resolverAutomatico(SolicitudDTO solicitudDTO) {
        try {
            // Validar que la solicitud pueda ser reevaludada
            validarSolicitudParaReevaluacion(solicitudDTO);

            // Llamar al modelo de ML
            ResolucionDTOGobierno resolucionInfraestructuraDTO = resolucionBO.crearResolucionAutomatica(solicitudDTO);

            // Validar coherencia
            if (resolucionInfraestructuraDTO == null) {
                throw new NegociosModificarResolucionesException("Error al generar resolución automática");
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


            // Cambiar estado de la solicitud
            if (cambiarEstadoSolicitud(resolucionDTO.getSolicitud())) {
                return resolucionBO.actualizarResolucion(resolucionDTO);
            }

            return false;
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException("Error al resolver manual: " + ex.getMessage());
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

            // Cambiar estado de la solicitud si cambió la decisión
            if (cambiarEstadoSolicitud(resolucionDTO.getSolicitud())) {
                return resolucionBO.actualizarResolucion(resolucionDTO);
            }

            return false;
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException("Error al modificar resolución: " + ex.getMessage());
        }
    }

    /**
     * Cambia el estado de una solicitud según su resolución
     */
    public boolean cambiarEstadoSolicitud(SolicitudDTO solicitud) {
        try {
            if (solicitud == null) {
                throw new NegociosModificarResolucionesException("La solicitud no puede ser nula");
            }

            return solicitudBO.cambiarEstado(Math.toIntExact(solicitud.getIdSolicitud()), EstadoSolicitud.valueOf(solicitud.getEstado()));
        } catch (Exception ex) {
            throw new NegociosModificarResolucionesException("Error al cambiar estado de solicitud: " + ex.getMessage());
        }
    }

    /**
     * Valida los parámetros de búsqueda
     */
    private void validarParametrosBusqueda(String tipoFiltro, String filtro) {
        if (tipoFiltro == null || tipoFiltro.trim().isEmpty()) {
            throw new NegociosModificarResolucionesException("El tipo de filtro no puede ser nulo o vacío");
        }

        if (filtro == null || filtro.trim().isEmpty()) {
            throw new NegociosModificarResolucionesException("El filtro no puede ser nulo o vacío");
        }

        List<String> tiposFiltroValidos = List.of("MATRICULA", "NOMBRE", "ID_SOLICITUD");
        if (!tiposFiltroValidos.contains(tipoFiltro.toUpperCase())) {
            throw new NegociosModificarResolucionesException("Tipo de filtro inválido: " + tipoFiltro);
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
            throw new NegociosModificarResolucionesException("La matrícula debe ser un número válido");
        }
    }

    /**
     * Valida formato de nombre
     */
    private void validarNombre(String nombre) {
        if (nombre.length() < 3) {
            throw new NegociosModificarResolucionesException("El nombre debe tener al menos 3 caracteres");
        }
    }

    /**
     * Valida formato de ID de solicitud
     */
    private void validarIdSolicitud(String idSolicitud) {
        try {
            Long.parseLong(idSolicitud);
        } catch (NumberFormatException ex) {
            throw new NegociosModificarResolucionesException("El ID de solicitud debe ser un número válido");
        }
    }

    /**
     * Valida que una resolución sea modificable
     */
    private void validarResolucionModificable(ResolucionDTO resolucion) {
        if (resolucion == null) {
            throw new NegociosModificarResolucionesException("La resolución no puede ser nula");
        }
    }

    /**
     * Valida que una solicitud pueda ser reevaluada
     */
    private void validarSolicitudParaReevaluacion(SolicitudDTO solicitud) {
        if (solicitud == null) {
            throw new NegociosModificarResolucionesException("La solicitud no puede ser nula");
        }

        // Validar que tenga información completa
        if (solicitud.getHistorialAcademico() == null) {
            throw new NegociosModificarResolucionesException("La solicitud debe tener historial académico");
        }

        if (solicitud.getInformacionSocioeconomica() == null) {
            throw new NegociosModificarResolucionesException("La solicitud debe tener información socioeconómica");
        }

        // Validar que no esté en estado ACTIVA (debe haber sido evaluada previamente)
        if (solicitud.getEstado().equals("ACTIVA")) {
            throw new NegociosModificarResolucionesException("No se puede re-evaluar una solicitud en estado ACTIVA");
        }
    }

    /**
     * Valida una resolución manual
     */
    private void validarResolucionManual(ResolucionDTO resolucion) {
        if (resolucion == null) {
            throw new NegociosModificarResolucionesException("La resolución no puede ser nula");
        }
        if (resolucion.getSolicitud() == null) {
            throw new NegociosModificarResolucionesException("La resolución debe estar asociada a una solicitud");
        }
        if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
            throw new NegociosModificarResolucionesException("Debe especificar una decisión");
        }
        List<String> decisionesValidas = List.of("ACEPTADA", "RECHAZADA", "DEVUELTA");
        if (!decisionesValidas.contains(resolucion.getDecision().toUpperCase())) {
            throw new NegociosModificarResolucionesException("Decisión inválida: " + resolucion.getDecision());
        }
        if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
            throw new NegociosModificarResolucionesException("Debe especificar un motivo");
        }
        if (resolucion.getMotivo().length() < 10) {
            throw new NegociosModificarResolucionesException("El motivo debe tener al menos 10 caracteres");
        }
        if (resolucion.getMotivo().length() > 500) {
            throw new NegociosModificarResolucionesException("El motivo no puede exceder 500 caracteres");
        }
        if (resolucion.getFechaEvaluacion() == null) {
            throw new NegociosModificarResolucionesException("Debe especificar una fecha de evaluación");
        }
        if (resolucion.getFechaEvaluacion().isAfter(LocalDate.now())) {
            throw new NegociosModificarResolucionesException("La fecha de evaluación no puede ser futura");
        }
    }

    /**
     * Valida la modificación de una resolución
     */
    private void validarModificacionResolucion(ResolucionDTO resolucion) {
        validarResolucionManual(resolucion);

        // Verificar que la resolución exista
        if (resolucion.getSolicitud().getIdSolicitud() <= 0) {
            throw new NegociosModificarResolucionesException("La solicitud debe tener un ID válido");
        }
    }
}
