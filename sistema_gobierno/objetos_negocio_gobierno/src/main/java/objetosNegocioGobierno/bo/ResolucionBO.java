package objetosNegocioGobierno.bo;
import datosGobierno.daoGobierno.interfacesGobierno.IResolucionDAO;
import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.Decision;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import interfaces.IFachadaModeloML;
import objetosNegocioGobierno.bo.excepciones.ResolucionBOException;
import objetosNegocioGobierno.bo.interfaces.IResolucionBO;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Business Object para gestión de resoluciones
 * Implementa validaciones de reglas de negocio
 *
 * @author Cortez, Manuel
 */
public class ResolucionBO implements IResolucionBO {
    private final IResolucionDAO resolucionDAO;
    private final IFachadaModeloML fachadaModeloML;
    private static final int MOTIVO_MIN_LENGTH = 10;
    private static final int MOTIVO_MAX_LENGTH = 500;
    private static final int DIAS_MODIFICACION_LIMITE = 30;
    private static final int DIAS_POST_RESULTADOS_LIMITE = 15;

    /**
     * Instantiates a new Resolucion bo.
     *
     * @param resolucionDAO   the resolucion dao
     * @param fachadaModeloML the fachada modelo ml
     */
    public ResolucionBO(IResolucionDAO resolucionDAO, IFachadaModeloML fachadaModeloML) {
        if (resolucionDAO == null) {
            throw new IllegalArgumentException("ResolucionDAO no puede ser nulo");
        }
        if (fachadaModeloML == null) {
            throw new IllegalArgumentException("FachadaModeloML no puede ser nulo");
        }
        this.resolucionDAO = resolucionDAO;
        this.fachadaModeloML = fachadaModeloML;
    }

    @Override
    public Resolucion crearResolucion(Solicitud solicitud, Decision decision, String motivo, LocalDate fechaEvaluacion) {
        try {
            // Validaciones de reglas de negocio
            validarSolicitudParaResolucion(solicitud);
            validarDecision(decision);
            validarMotivo(motivo);
            validarFechaEvaluacion(fechaEvaluacion);

            // Crear resolución
            Resolucion resolucion = new Resolucion();
            resolucion.setSolicitud(solicitud);
            resolucion.setDecision(decision);
            resolucion.setMotivo(motivo.trim());
            resolucion.setFechaEvaluacion(fechaEvaluacion);

            return resolucion;

        } catch (ResolucionBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResolucionBOException("Error al crear resolución: " + ex.getMessage());
        }
    }

    @Override
    public boolean resolver(Resolucion resolucion) {
        try {
            validarResolucionCompleta(resolucion);

            // Verificar que no exista ya una resolución para esta solicitud
            verificarResolucionNoExistente(resolucion.getSolicitud().getId());

            // Actualizar estado de solicitud según decisión
            actualizarEstadoSolicitud(resolucion);

            // Guardar resolución
            return resolucionDAO.guardar(resolucion);

        } catch (ResolucionBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResolucionBOException("Error al resolver solicitud: " + ex.getMessage());
        }
    }

    @Override
    public ResolucionDTOGobierno crearResolucionAutomatica(SolicitudDTOGobierno solicitud) {
        try {
            if (solicitud == null) {
                throw new ResolucionBOException("La solicitud no puede ser nula");
            }

            // Validar que la solicitud esté en estado ACTIVA
            if (!"ACTIVA".equals(solicitud.getEstado())) {
                throw new ResolucionBOException("Solo se pueden evaluar automáticamente solicitudes en estado ACTIVA");
            }

            // Validar datos requeridos para ML
            validarDatosParaML(solicitud);

            // Llamar a la fachada de ML
            ResolucionDTOGobierno resolucion = fachadaModeloML.generarPrediccion(solicitud);

            if (resolucion == null) {
                throw new ResolucionBOException("El modelo de ML no pudo generar una predicción");
            }

            // Validar que la resolución generada sea coherente
            validarResolucionML(resolucion);

            return resolucion;

        } catch (ResolucionBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResolucionBOException("Error al crear resolución automática: " + ex.getMessage());
        }
    }

    @Override
    public Resolucion obtenerResolucion(int id) {
        try {
            if (id <= 0) {
                throw new ResolucionBOException("ID de resolución inválido");
            }

            Resolucion resolucion = resolucionDAO.obtenerPorId(id);

            if (resolucion == null) {
                throw new ResolucionBOException("No se encontró resolución con ID: " + id);
            }

            return resolucion;

        } catch (ResolucionBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResolucionBOException("Error al obtener resolución: " + ex.getMessage());
        }
    }

    @Override
    public Resolucion obtenerResolucionPorFiltro(String tipoFiltro, String filtro) {
        try {
            validarParametrosBusqueda(tipoFiltro, filtro);

            Resolucion resolucion = resolucionDAO.obtenerPorFiltro(tipoFiltro, filtro);

            if (resolucion == null) {
                throw new ResolucionBOException("No se encontró resolución con " + tipoFiltro + ": " + filtro);
            }

            return resolucion;

        } catch (ResolucionBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResolucionBOException("Error al buscar resolución: " + ex.getMessage());
        }
    }

    @Override
    public boolean actualizarResolucion(Resolucion resolucion) {
        try {
            validarResolucionCompleta(resolucion);
            validarResolucionModificable(resolucion);

            // Actualizar estado de solicitud según nueva decisión
            actualizarEstadoSolicitud(resolucion);

            return resolucionDAO.actualizar(resolucion);

        } catch (ResolucionBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResolucionBOException("Error al actualizar resolución: " + ex.getMessage());
        }
    }

    /**
     * Valida que una solicitud sea válida para crear resolución
     */
    private void validarSolicitudParaResolucion(Solicitud solicitud) {
        if (solicitud == null) {
            throw new ResolucionBOException("La solicitud no puede ser nula");
        }

        if (solicitud.getId() <= 0) {
            throw new ResolucionBOException("La solicitud debe tener un ID válido");
        }

        if (solicitud.getEstudiante() == null) {
            throw new ResolucionBOException("La solicitud debe tener un estudiante asociado");
        }

        if (solicitud.getBeca() == null) {
            throw new ResolucionBOException("La solicitud debe tener una beca asociada");
        }
    }

    /**
     * Valida que la decisión sea válida
     */
    private void validarDecision(Decision decision) {
        if (decision == null) {
            throw new ResolucionBOException("La decisión no puede ser nula");
        }

        List<Decision> decisionesValidas = Arrays.asList(Decision.ACEPTADA, Decision.RECHAZADA, Decision.DEVUELTA);

        if (!decisionesValidas.contains(decision)) {
            throw new ResolucionBOException("Decisión inválida: " + decision);
        }
    }

    /**
     * Valida el motivo de la resolución
     */
    private void validarMotivo(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new ResolucionBOException("El motivo no puede estar vacío");
        }

        if (motivo.trim().length() < MOTIVO_MIN_LENGTH) {
            throw new ResolucionBOException(String.format("El motivo debe tener al menos %d caracteres", MOTIVO_MIN_LENGTH));
        }

        if (motivo.trim().length() > MOTIVO_MAX_LENGTH) {
            throw new ResolucionBOException(String.format("El motivo no puede exceder %d caracteres", MOTIVO_MAX_LENGTH));
        }
    }

    /**
     * Valida la fecha de evaluación
     */
    private void validarFechaEvaluacion(LocalDate fechaEvaluacion) {
        if (fechaEvaluacion == null) {
            throw new ResolucionBOException("La fecha de evaluación no puede ser nula");
        }

        if (fechaEvaluacion.isAfter(LocalDate.now())) {
            throw new ResolucionBOException("La fecha de evaluación no puede ser futura");
        }

        // Validar que no sea demasiado antigua
        if (fechaEvaluacion.isBefore(LocalDate.now().minusYears(1))) {
            throw new ResolucionBOException("La fecha de evaluación no puede ser mayor a 1 año atrás");
        }
    }

    /**
     * Valida una resolución completa
     */
    private void validarResolucionCompleta(Resolucion resolucion) {
        if (resolucion == null) {
            throw new ResolucionBOException("La resolución no puede ser nula");
        }

        validarSolicitudParaResolucion(resolucion.getSolicitud());
        validarDecision(resolucion.getDecision());
        validarMotivo(resolucion.getMotivo());
        validarFechaEvaluacion(resolucion.getFechaEvaluacion());
    }

    /**
     * Valida que una resolución sea modificable según reglas de negocio
     */
    private void validarResolucionModificable(Resolucion resolucion) {
        // Validar periodo de modificación
        LocalDate fechaLimite = resolucion.getFechaEvaluacion().plusDays(DIAS_MODIFICACION_LIMITE);

        if (LocalDate.now().isAfter(fechaLimite)) {
            throw new ResolucionBOException(String.format("El periodo de modificación ha expirado (%d días desde evaluación)", DIAS_MODIFICACION_LIMITE));
        }

        // Validar periodo posresultados
        if (resolucion.getSolicitud().getBeca().getFechaResultados() != null) {
            LocalDate fechaLimiteResultados = resolucion.getSolicitud().getBeca().getFechaResultados().plusDays(DIAS_POST_RESULTADOS_LIMITE);

            if (LocalDate.now().isAfter(fechaLimiteResultados)) {
                throw new ResolucionBOException(String.format("No se pueden modificar resoluciones después de %d días " +
                                "de publicados los resultados", DIAS_POST_RESULTADOS_LIMITE));
            }
        }
    }

    /**
     * Valida los datos necesarios para evaluación por ML
     */
    private void validarDatosParaML(SolicitudDTOGobierno solicitud) {
        if (solicitud.getHistorialAcademico() == null) {
            throw new ResolucionBOException("La solicitud debe tener historial académico para evaluación automática");
        }

        if (solicitud.getInformacionSocioeconomica() == null) {
            throw new ResolucionBOException("La solicitud debe tener información socioeconómica para evaluación automática");
        }

        // Validar rangos de valores
        double promedio = solicitud.getHistorialAcademico().getPromedio();
        if (promedio < 0 || promedio > 100) {
            throw new ResolucionBOException("El promedio debe estar entre 0 y 100");
        }

        if (solicitud.getHistorialAcademico().getSemestre() < 1 ||
                solicitud.getHistorialAcademico().getSemestre() > 12) {
            throw new ResolucionBOException("El semestre debe estar entre 1 y 12");
        }
    }

    /**
     * Valida la resolución generada por ML
     */
    private void validarResolucionML(ResolucionDTOGobierno resolucion) {
        if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
            throw new ResolucionBOException("La resolución de ML no contiene una decisión válida");
        }

        if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
            throw new ResolucionBOException("La resolución de ML no contiene un motivo válido");
        }

        List<String> decisionesValidas = Arrays.asList("ACEPTADA", "RECHAZADA", "DEVUELTA");

        if (!decisionesValidas.contains(resolucion.getDecision().toUpperCase())) {
            throw new ResolucionBOException("La decisión de ML es inválida: " + resolucion.getDecision());
        }
    }

    /**
     * Valida los parámetros de búsqueda
     */
    private void validarParametrosBusqueda(String tipoFiltro, String filtro) {
        if (tipoFiltro == null || tipoFiltro.trim().isEmpty()) {
            throw new ResolucionBOException("El tipo de filtro no puede estar vacío");
        }

        if (filtro == null || filtro.trim().isEmpty()) {
            throw new ResolucionBOException("El filtro no puede estar vacío");
        }

        List<String> tiposFiltroValidos = Arrays.asList("MATRICULA", "NOMBRE", "ID_SOLICITUD");

        if (!tiposFiltroValidos.contains(tipoFiltro.toUpperCase())) {
            throw new ResolucionBOException("Tipo de filtro inválido: " + tipoFiltro);
        }
    }

    /**
     * Verifica que no exista una resolución para esta solicitud
     */
    private void verificarResolucionNoExistente(long idSolicitud) {
        try {
            Resolucion existente = resolucionDAO.obtenerPorId((int) idSolicitud);
            if (existente != null) {
                throw new ResolucionBOException("Ya existe una resolución para esta solicitud");
            }
        } catch (Exception ex) {
            if (!ex.getMessage().contains("No se encontró")) {
                throw new ResolucionBOException("Error al verificar resolución existente: " + ex.getMessage());
            }
        }
    }

    /**
     * Actualiza el estado de la solicitud según la decisión
     */
    private void actualizarEstadoSolicitud(Resolucion resolucion) {
        EstadoSolicitud nuevoEstado;

        switch (resolucion.getDecision()) {
            case ACEPTADA:
                nuevoEstado = EstadoSolicitud.ACEPTADA;
                break;
            case RECHAZADA:
                nuevoEstado = EstadoSolicitud.RECHAZADA;
                break;
            case DEVUELTA:
                nuevoEstado = EstadoSolicitud.DEVUELTA;
                break;
            default:
                throw new ResolucionBOException("Decisión no reconocida: " + resolucion.getDecision());
        }
        resolucion.getSolicitud().setEstado(nuevoEstado);
    }
}
