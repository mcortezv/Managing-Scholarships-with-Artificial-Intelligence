package presentacion.coordinacion;

import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import interfaces.IFachadaEvaluarSolicitudes;
import interfaces.IFachadaModificarResolucion;
import presentacion.coordinacion.interfaces.ICoordinadorNegocio;

import java.time.LocalDate;
import java.util.List;

/**
 * Coordinador de negocio que gestiona las operaciones entre la presentación y los subsistemas
 * @author Cortez, Manuel
 */
public class CoordinadorNegocio implements ICoordinadorNegocio {
    private final IFachadaEvaluarSolicitudes fachadaEvaluarSolicitudes;
    private final IFachadaModificarResolucion fachadaModificarResolucion;

    // Estado de sesión
    private EvaluadorLoginDTO evaluadorActual;
    private boolean sesionIniciada;

    public CoordinadorNegocio(IFachadaEvaluarSolicitudes fachadaEvaluarSolicitudes,
                              IFachadaModificarResolucion fachadaModificarResolucion) {
        this.fachadaEvaluarSolicitudes = fachadaEvaluarSolicitudes;
        this.fachadaModificarResolucion = fachadaModificarResolucion;
        this.sesionIniciada = false;
    }

    // ============= GESTIÓN DE SESIÓN =============

    @Override
    public boolean iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO) {
        try {
            // Validar credenciales
            if (evaluadorLoginDTO == null) {
                return false;
            }

            if (evaluadorLoginDTO.getMatricula() == null ||
                    evaluadorLoginDTO.getContrasenia() == null ||
                    evaluadorLoginDTO.getContrasenia().trim().isEmpty()) {
                return false;
            }

            // Aquí iría la validación real con la base de datos
            // Por ahora, validación simple
            if (evaluadorLoginDTO.getMatricula() > 0 &&
                    evaluadorLoginDTO.getContrasenia().length() >= 6) {

                this.evaluadorActual = evaluadorLoginDTO;
                this.sesionIniciada = true;
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.err.println("Error al iniciar sesión: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public void cerrarSesion() {
        this.evaluadorActual = null;
        this.sesionIniciada = false;
    }

    @Override
    public boolean haySesionIniciada() {
        return sesionIniciada;
    }

    @Override
    public EvaluadorLoginDTO getEvaluadorActual() {
        return evaluadorActual;
    }

    // ============= EVALUAR SOLICITUDES =============

    @Override
    public List<BecaDTO> obtenerBecasConSolicitudes() {
        try {
            validarSesionActiva();
            List<BecaDTO> becas = fachadaEvaluarSolicitudes.obtenerListadoBecas();

            if (becas == null || becas.isEmpty()) {
                throw new RuntimeException("No hay becas con solicitudes disponibles");
            }

            return becas;

        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener becas: " + ex.getMessage());
        }
    }

    @Override
    public List<SolicitudDTO> obtenerSolicitudes(String tipoBeca) {
        try {
            validarSesionActiva();

            if (tipoBeca == null || tipoBeca.trim().isEmpty()) {
                throw new RuntimeException("Debe seleccionar un tipo de beca");
            }

            List<SolicitudDTO> solicitudes =
                    fachadaEvaluarSolicitudes.obtenerSolicitudes(tipoBeca);

            if (solicitudes == null || solicitudes.isEmpty()) {
                throw new RuntimeException(
                        "No hay solicitudes para el tipo de beca: " + tipoBeca);
            }

            return solicitudes;

        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener solicitudes: " + ex.getMessage());
        }
    }

    @Override
    public ResolucionDTO evaluarSolicitudAutomatica(SolicitudDTO solicitud) {
        try {
            validarSesionActiva();
            validarSolicitudParaEvaluacion(solicitud);

            ResolucionDTO resolucion = fachadaEvaluarSolicitudes.evaluacionAutomatica(solicitud);

            if (resolucion == null) {
                throw new RuntimeException("Error al generar evaluación automática");
            }

            return resolucion;

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Error en evaluación automática: " + ex.getMessage());
        }
    }

    @Override
    public boolean evaluarSolicitudManual(ResolucionDTO resolucion) {
        try {
            validarSesionActiva();
            validarResolucionManual(resolucion);

            return fachadaEvaluarSolicitudes.resolverSolicitudManual(resolucion);

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Error al evaluar solicitud manual: " + ex.getMessage());
        }
    }

    @Override
    public boolean guardarResolucion(ResolucionDTO resolucion) {
        try {
            validarSesionActiva();
            validarResolucionParaGuardar(resolucion);

            return fachadaEvaluarSolicitudes.resolver(resolucion);

        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar resolución: " + ex.getMessage());
        }
    }

    // ============= MODIFICAR RESOLUCIÓN =============

    @Override
    public ResolucionDTO buscarResolucion(String tipoFiltro, String filtro) {
        try {
            validarSesionActiva();
            validarParametrosBusqueda(tipoFiltro, filtro);

            ResolucionDTO resolucion =
                    fachadaModificarResolucion.buscarResolucion(tipoFiltro, filtro);

            if (resolucion == null) {
                throw new RuntimeException(
                        "No se encontró resolución con los criterios especificados");
            }

            return resolucion;

        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar resolución: " + ex.getMessage());
        }
    }

    @Override
    public ResolucionDTO reevaluarAutomatica(SolicitudDTO solicitud) {
        try {
            validarSesionActiva();
            validarSolicitudParaReevaluacion(solicitud);

            ResolucionDTO resolucion =
                    fachadaModificarResolucion.resolverAutomatico(solicitud);

            if (resolucion == null) {
                throw new RuntimeException("Error al re-evaluar automáticamente");
            }

            return resolucion;

        } catch (Exception ex) {
            throw new RuntimeException("Error en re-evaluación: " + ex.getMessage());
        }
    }

    @Override
    public boolean modificarResolucionManual(ResolucionDTO resolucion) {
        try {
            validarSesionActiva();
            validarResolucionParaModificacion(resolucion);

            return fachadaModificarResolucion.resolverManual(resolucion);

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Error al modificar resolución manual: " + ex.getMessage());
        }
    }

    @Override
    public boolean modificarResolucion(ResolucionDTO resolucion) {
        try {
            validarSesionActiva();
            validarResolucionParaModificacion(resolucion);

            return fachadaModificarResolucion.modificarResolucion(resolucion);

        } catch (Exception ex) {
            throw new RuntimeException(
                    "Error al modificar resolución: " + ex.getMessage());
        }
    }

    // ============= VALIDACIONES DE COORDINADOR =============

    /**
     * Valida que haya una sesión activa
     */
    private void validarSesionActiva() {
        if (!sesionIniciada || evaluadorActual == null) {
            throw new RuntimeException("No hay sesión iniciada");
        }
    }

    /**
     * Valida una solicitud antes de evaluarla
     */
    private void validarSolicitudParaEvaluacion(SolicitudDTO solicitud) {
        if (solicitud == null) {
            throw new RuntimeException("La solicitud no puede ser nula");
        }

        if (!"ACTIVA".equals(solicitud.getEstado())) {
            throw new RuntimeException(
                    "Solo se pueden evaluar solicitudes en estado ACTIVA");
        }

        if (solicitud.getEstudiante() == null) {
            throw new RuntimeException("La solicitud debe tener un estudiante asociado");
        }

        if (solicitud.getBeca() == null) {
            throw new RuntimeException("La solicitud debe tener una beca asociada");
        }
    }

    /**
     * Valida una solicitud antes de re-evaluarla
     */
    private void validarSolicitudParaReevaluacion(SolicitudDTO solicitud) {
        if (solicitud == null) {
            throw new RuntimeException("La solicitud no puede ser nula");
        }

        if ("ACTIVA".equals(solicitud.getEstado())) {
            throw new RuntimeException(
                    "No se puede re-evaluar una solicitud en estado ACTIVA");
        }
    }

    /**
     * Valida una resolución manual
     */
    private void validarResolucionManual(ResolucionDTO resolucion) {
        if (resolucion == null) {
            throw new RuntimeException("La resolución no puede ser nula");
        }

        if (resolucion.getSolicitud() == null) {
            throw new RuntimeException(
                    "La resolución debe estar asociada a una solicitud");
        }

        if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
            throw new RuntimeException("Debe especificar una decisión");
        }

        if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
            throw new RuntimeException("Debe especificar un motivo");
        }

        if (resolucion.getMotivo().length() < 10) {
            throw new RuntimeException("El motivo debe tener al menos 10 caracteres");
        }

        if (resolucion.getFechaEvaluacion() == null) {
            throw new RuntimeException("Debe especificar una fecha de evaluación");
        }
    }

    /**
     * Valida una resolución antes de guardarla
     */
    private void validarResolucionParaGuardar(ResolucionDTO resolucion) {
        validarResolucionManual(resolucion);

        if (resolucion.getFechaEvaluacion().isAfter(LocalDate.now())) {
            throw new RuntimeException("La fecha de evaluación no puede ser futura");
        }
    }

    /**
     * Valida una resolución antes de modificarla
     */
    private void validarResolucionParaModificacion(ResolucionDTO resolucion) {
        validarResolucionManual(resolucion);

        // Validar que el motivo indique modificación
        String motivoLower = resolucion.getMotivo().toLowerCase();
        if (!motivoLower.contains("modificación") &&
                !motivoLower.contains("corrección") &&
                !motivoLower.contains("revisión")) {
            throw new RuntimeException(
                    "El motivo debe explicar claramente la razón de la modificación");
        }
    }

    /**
     * Valida los parámetros de búsqueda
     */
    private void validarParametrosBusqueda(String tipoFiltro, String filtro) {
        if (tipoFiltro == null || tipoFiltro.trim().isEmpty()) {
            throw new RuntimeException("Debe especificar un tipo de filtro");
        }

        if (filtro == null || filtro.trim().isEmpty()) {
            throw new RuntimeException("Debe especificar un valor de filtro");
        }

        // Validar tipos de filtro válidos
        if (!"MATRICULA".equals(tipoFiltro.toUpperCase()) &&
                !"NOMBRE".equals(tipoFiltro.toUpperCase()) &&
                !"ID_SOLICITUD".equals(tipoFiltro.toUpperCase())) {
            throw new RuntimeException("Tipo de filtro inválido: " + tipoFiltro);
        }

        // Validaciones específicas por tipo
        if ("MATRICULA".equals(tipoFiltro.toUpperCase()) ||
                "ID_SOLICITUD".equals(tipoFiltro.toUpperCase())) {
            try {
                Long.parseLong(filtro);
            } catch (NumberFormatException ex) {
                throw new RuntimeException("El valor debe ser numérico para " + tipoFiltro);
            }
        }

        if ("NOMBRE".equals(tipoFiltro.toUpperCase()) && filtro.length() < 3) {
            throw new RuntimeException("El nombre debe tener al menos 3 caracteres");
        }
    }
}