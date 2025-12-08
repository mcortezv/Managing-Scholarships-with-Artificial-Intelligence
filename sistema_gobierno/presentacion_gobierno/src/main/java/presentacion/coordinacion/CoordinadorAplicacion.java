package presentacion.coordinacion;

import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.coordinacion.interfaces.ICoordinadorNegocio;
import presentacion.evaluarSolicitudes.EvaluacionCompletadaPanel;
import presentacion.evaluarSolicitudes.EvaluacionPanel;
import presentacion.evaluarSolicitudes.EvaluarConvocatoriaPanel;
import presentacion.modificarResolucion.BuscarResolucionPanel;
import presentacion.modificarResolucion.ModficacionCompletadaPanel;
import presentacion.modificarResolucion.ModificarConvocatoriaPanel;
import presentacion.modificarResolucion.ModificarResolucionPanel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Coordinador de aplicación corregido
 * @author Cortez, Manuel
 */
public class CoordinadorAplicacion implements ICoordinadorAplicacion {
    private final ICoordinadorNegocio coordinadorNegocio;
    private final MainFrame frame;

    // Estado de navegación
    private BecaDTO becaSeleccionada;
    private SolicitudDTO solicitudSeleccionada;
    private ResolucionDTO resolucionActual;

    public CoordinadorAplicacion(ICoordinadorNegocio coordinadorNegocio) {
        this.coordinadorNegocio = coordinadorNegocio;
        this.frame = new MainFrame(this);
        this.frame.setVisible(true);
    }

    // ============= GESTIÓN DE SESIÓN =============

    @Override
    public void iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO) {
        try {
            System.out.println("DEBUG: Intentando iniciar sesión...");

            if (coordinadorNegocio.iniciarSesion(evaluadorLoginDTO)) {
                System.out.println("DEBUG: Sesión iniciada correctamente");
                mostrarMensaje("Bienvenido al sistema", "Inicio de Sesión",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.showPanel("hub");
            } else {
                System.out.println("DEBUG: Credenciales inválidas");
                mostrarMensaje("Credenciales inválidas. Por favor, verifique su matrícula y contraseña.",
                        "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.err.println("ERROR en iniciarSesion: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al iniciar sesión: " + ex.getMessage());
        }
    }

    @Override
    public void cerrarSesion() {
        try {
            int confirmacion = JOptionPane.showConfirmDialog(
                    frame,
                    "¿Está seguro que desea cerrar sesión?",
                    "Confirmar Cierre de Sesión",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                limpiarEstadoAplicacion();
                coordinadorNegocio.cerrarSesion();
                frame.showPanel("iniciarSesion");
                mostrarMensaje("Sesión cerrada correctamente", "Cierre de Sesión",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            System.err.println("ERROR en cerrarSesion: " + ex.getMessage());
            mostrarError("Error al cerrar sesión: " + ex.getMessage());
        }
    }

    @Override
    public void volverHub() {
        try {
            System.out.println("DEBUG: Volviendo al Hub");
            limpiarEstadoNavegacion();
            frame.showPanel("hub");
        } catch (Exception ex) {
            System.err.println("ERROR en volverHub: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Métodos para navegación del Hub
    public void evaluar() {
        iniciarEvaluarConvocatoria();
    }

    public void modificar() {
        iniciarModificarConvocatoria();
    }

    public void hub() {
        volverHub();
    }

    // ============= EVALUAR SOLICITUDES =============

    @Override
    public void iniciarEvaluarConvocatoria() {
        try {
            System.out.println("DEBUG: Iniciando evaluar convocatoria");
            validarSesionActiva();

            List<BecaDTO> becas = coordinadorNegocio.obtenerBecasConSolicitudes();
            System.out.println("DEBUG: Becas obtenidas del negocio: " +
                    (becas != null ? becas.size() : "null"));

            EvaluarConvocatoriaPanel panel =
                    (EvaluarConvocatoriaPanel) frame.getPanel("evaluarConvocatoria");

            panel.setBecas(becas);
            frame.showPanel("evaluarConvocatoria");

            System.out.println("DEBUG: Panel mostrado correctamente");

        } catch (Exception ex) {
            System.err.println("ERROR en iniciarEvaluarConvocatoria: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al cargar convocatorias: " + ex.getMessage());
        }
    }

    @Override
    public void seleccionarConvocatoriaEvaluar(BecaDTO becaDTO) {
        try {
            System.out.println("DEBUG: Seleccionando convocatoria: " +
                    (becaDTO != null ? becaDTO.getNombre() : "null"));

            validarSesionActiva();

            if (becaDTO == null) {
                mostrarError("Debe seleccionar una beca");
                return;
            }

            this.becaSeleccionada = becaDTO;
            List<SolicitudDTO> solicitudes =
                    coordinadorNegocio.obtenerSolicitudes(becaDTO.getTipo());

            System.out.println("DEBUG: Solicitudes obtenidas: " +
                    (solicitudes != null ? solicitudes.size() : "null"));

            EvaluacionPanel panel = (EvaluacionPanel) frame.getPanel("evaluacion");
            panel.setBecaActual(becaDTO);
            panel.setSolicitudes(solicitudes);
            frame.showPanel("evaluacion");

        } catch (Exception ex) {
            System.err.println("ERROR en seleccionarConvocatoriaEvaluar: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al cargar solicitudes: " + ex.getMessage());
        }
    }

    public void evaluarConvocatoria(BecaDTO becaDTO) {
        seleccionarConvocatoriaEvaluar(becaDTO);
    }

    @Override
    public void seleccionarSolicitudEvaluar(SolicitudDTO solicitud) {
        try {
            validarSesionActiva();

            if (solicitud == null) {
                mostrarError("Debe seleccionar una solicitud");
                return;
            }

            if (!"ACTIVA".equals(solicitud.getEstado())) {
                mostrarError("Solo se pueden evaluar solicitudes en estado ACTIVA");
                return;
            }

            this.solicitudSeleccionada = solicitud;
            mostrarOpcionesEvaluacion();

        } catch (Exception ex) {
            System.err.println("ERROR en seleccionarSolicitudEvaluar: " + ex.getMessage());
            mostrarError("Error al seleccionar solicitud: " + ex.getMessage());
        }
    }

    @Override
    public void evaluarAutomatica(SolicitudDTO solicitud) {
        try {
            System.out.println("DEBUG: Evaluando automáticamente");
            validarSesionActiva();

            if (solicitud == null) {
                mostrarError("No hay solicitud seleccionada");
                return;
            }

            mostrarMensaje("Procesando evaluación automática...\nEsto puede tomar unos segundos.",
                    "Evaluando", JOptionPane.INFORMATION_MESSAGE);

            ResolucionDTO resolucion =
                    coordinadorNegocio.evaluarSolicitudAutomatica(solicitud);

            this.resolucionActual = resolucion;

            int confirmacion = JOptionPane.showConfirmDialog(
                    frame,
                    String.format("Evaluación Automática:\n\nDecisión: %s\nMotivo: %s\n\n¿Desea guardar esta evaluación?",
                            resolucion.getDecision(), resolucion.getMotivo()),
                    "Confirmar Evaluación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                guardarEvaluacion(resolucion);
            }

        } catch (Exception ex) {
            System.err.println("ERROR en evaluarAutomatica: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error en evaluación automática: " + ex.getMessage());
        }
    }

    @Override
    public void evaluarManual(ResolucionDTO resolucion) {
        try {
            System.out.println("DEBUG: Evaluando manualmente");
            validarSesionActiva();

            if (resolucion == null) {
                mostrarError("Debe proporcionar una resolución");
                return;
            }

            if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
                mostrarError("Debe seleccionar una decisión");
                return;
            }

            if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
                mostrarError("Debe proporcionar un motivo");
                return;
            }

            if (resolucion.getMotivo().length() < 10) {
                mostrarError("El motivo debe tener al menos 10 caracteres");
                return;
            }

            if (resolucion.getFechaEvaluacion() == null) {
                resolucion.setFechaEvaluacion(LocalDate.now());
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    frame,
                    String.format("¿Confirma la evaluación manual?\n\nDecisión: %s\nMotivo: %s",
                            resolucion.getDecision(), resolucion.getMotivo()),
                    "Confirmar Evaluación Manual",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                guardarEvaluacion(resolucion);
            }

        } catch (Exception ex) {
            System.err.println("ERROR en evaluarManual: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error en evaluación manual: " + ex.getMessage());
        }
    }

    private void guardarEvaluacion(ResolucionDTO resolucion) {
        try {
            System.out.println("DEBUG: Guardando evaluación");
            boolean exitoso = coordinadorNegocio.guardarResolucion(resolucion);

            if (exitoso) {
                EvaluacionCompletadaPanel panel =
                        (EvaluacionCompletadaPanel) frame.getPanel("evaluacionCompletada");
                panel.setResolucion(resolucion);
                frame.showPanel("evaluacionCompletada");

                mostrarMensaje("Evaluación guardada exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarError("No se pudo guardar la evaluación");
            }

        } catch (Exception ex) {
            System.err.println("ERROR en guardarEvaluacion: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al guardar evaluación: " + ex.getMessage());
        }
    }

    @Override
    public void evaluarOtraSolicitud() {
        try {
            System.out.println("DEBUG: Evaluando otra solicitud");
            if (becaSeleccionada != null) {
                seleccionarConvocatoriaEvaluar(becaSeleccionada);
            } else {
                iniciarEvaluarConvocatoria();
            }
        } catch (Exception ex) {
            System.err.println("ERROR en evaluarOtraSolicitud: " + ex.getMessage());
            mostrarError("Error al volver a evaluación: " + ex.getMessage());
        }
    }

    // ============= MODIFICAR RESOLUCIÓN =============

    @Override
    public void iniciarModificarConvocatoria() {
        try {
            System.out.println("DEBUG: Iniciando modificar convocatoria");
            validarSesionActiva();
            limpiarEstadoModificacion();
            frame.showPanel("modificarConvocatoria");

        } catch (Exception ex) {
            System.err.println("ERROR en iniciarModificarConvocatoria: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al iniciar modificación: " + ex.getMessage());
        }
    }

    @Override
    public void iniciarBusquedaResolucion() {
        try {
            System.out.println("DEBUG: Iniciando búsqueda de resolución");
            validarSesionActiva();

            BuscarResolucionPanel panel =
                    (BuscarResolucionPanel) frame.getPanel("buscarResolucion");
            panel.limpiarBusqueda();
            frame.showPanel("buscarResolucion");

        } catch (Exception ex) {
            System.err.println("ERROR en iniciarBusquedaResolucion: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al iniciar búsqueda: " + ex.getMessage());
        }
    }

    @Override
    public void buscarResolucion(String tipoFiltro, String filtro) {
        try {
            System.out.println("DEBUG: Buscando resolución - Tipo: " + tipoFiltro + ", Filtro: " + filtro);
            validarSesionActiva();

            if (tipoFiltro == null || tipoFiltro.trim().isEmpty()) {
                mostrarError("Debe seleccionar un tipo de filtro");
                return;
            }

            if (filtro == null || filtro.trim().isEmpty()) {
                mostrarError("Debe ingresar un valor de búsqueda");
                return;
            }

            ResolucionDTO resolucion =
                    coordinadorNegocio.buscarResolucion(tipoFiltro, filtro);

            this.resolucionActual = resolucion;
            mostrarResolucionEncontrada(resolucion);

        } catch (Exception ex) {
            System.err.println("ERROR en buscarResolucion: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al buscar resolución: " + ex.getMessage());
        }
    }

    private void mostrarResolucionEncontrada(ResolucionDTO resolucion) {
        try {
            System.out.println("DEBUG: Mostrando resolución encontrada");
            ModificarResolucionPanel panel =
                    (ModificarResolucionPanel) frame.getPanel("modificarResolucion");
            panel.setResolucion(resolucion);
            frame.showPanel("modificarResolucion");

        } catch (Exception ex) {
            System.err.println("ERROR en mostrarResolucionEncontrada: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al mostrar resolución: " + ex.getMessage());
        }
    }

    @Override
    public void reevaluarAutomatica(SolicitudDTO solicitud) {
        try {
            System.out.println("DEBUG: Re-evaluando automáticamente");
            validarSesionActiva();

            if (solicitud == null) {
                mostrarError("No hay solicitud para re-evaluar");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    frame,
                    "¿Desea re-evaluar esta solicitud automáticamente?\n" +
                            "Esto generará una nueva resolución.",
                    "Confirmar Re-evaluación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            mostrarMensaje("Re-evaluando solicitud...\nEsto puede tomar unos segundos.",
                    "Procesando", JOptionPane.INFORMATION_MESSAGE);

            ResolucionDTO nuevaResolucion =
                    coordinadorNegocio.reevaluarAutomatica(solicitud);

            this.resolucionActual = nuevaResolucion;
            mostrarResolucionEncontrada(nuevaResolucion);

            mostrarMensaje("Re-evaluación completada exitosamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            System.err.println("ERROR en reevaluarAutomatica: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error en re-evaluación automática: " + ex.getMessage());
        }
    }

    @Override
    public void modificarResolucion(ResolucionDTO resolucion) {
        try {
            System.out.println("DEBUG: Modificando resolución");
            validarSesionActiva();

            if (resolucion == null) {
                mostrarError("No hay resolución para modificar");
                return;
            }

            if (resolucion.getDecision() == null || resolucion.getDecision().trim().isEmpty()) {
                mostrarError("Debe seleccionar una decisión");
                return;
            }

            if (resolucion.getMotivo() == null || resolucion.getMotivo().trim().isEmpty()) {
                mostrarError("Debe proporcionar un motivo");
                return;
            }

            if (resolucion.getMotivo().length() < 10) {
                mostrarError("El motivo debe tener al menos 10 caracteres");
                return;
            }

            String motivoLower = resolucion.getMotivo().toLowerCase();
            if (!motivoLower.contains("modificación") &&
                    !motivoLower.contains("corrección") &&
                    !motivoLower.contains("revisión") &&
                    !motivoLower.contains("modificacion") &&
                    !motivoLower.contains("correccion") &&
                    !motivoLower.contains("revision")) {
                mostrarError("El motivo debe explicar claramente la razón de la modificación");
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    frame,
                    String.format("¿Confirma la modificación?\n\nNueva Decisión: %s\nMotivo: %s",
                            resolucion.getDecision(), resolucion.getMotivo()),
                    "Confirmar Modificación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                guardarModificacion(resolucion);
            }

        } catch (Exception ex) {
            System.err.println("ERROR en modificarResolucion: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al modificar resolución: " + ex.getMessage());
        }
    }

    private void guardarModificacion(ResolucionDTO resolucion) {
        try {
            System.out.println("DEBUG: Guardando modificación");
            boolean exitoso = coordinadorNegocio.modificarResolucion(resolucion);

            if (exitoso) {
                ModficacionCompletadaPanel panel =
                        (ModficacionCompletadaPanel) frame.getPanel("modificacionCompletada");
                panel.setResolucion(resolucion);
                frame.showPanel("modificacionCompletada");

                mostrarMensaje("Modificación guardada exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarError("No se pudo guardar la modificación");
            }

        } catch (Exception ex) {
            System.err.println("ERROR en guardarModificacion: " + ex.getMessage());
            ex.printStackTrace();
            mostrarError("Error al guardar modificación: " + ex.getMessage());
        }
    }

    @Override
    public void modificarOtraResolucion() {
        iniciarBusquedaResolucion();
    }

    // ============= UTILIDADES =============

    private void validarSesionActiva() {
        if (!coordinadorNegocio.haySesionIniciada()) {
            throw new RuntimeException("No hay sesión activa");
        }
    }

    private void limpiarEstadoNavegacion() {
        this.becaSeleccionada = null;
        this.solicitudSeleccionada = null;
        this.resolucionActual = null;
    }

    private void limpiarEstadoModificacion() {
        this.resolucionActual = null;
    }

    private void limpiarEstadoAplicacion() {
        limpiarEstadoNavegacion();
        limpiarEstadoModificacion();
    }

    private void mostrarOpcionesEvaluacion() {
        String[] opciones = {"Evaluar Automático", "Evaluar Manual", "Cancelar"};

        int seleccion = JOptionPane.showOptionDialog(
                frame,
                "Seleccione el tipo de evaluación para la solicitud seleccionada",
                "Opciones de Evaluación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == 0) {
            evaluarAutomatica(solicitudSeleccionada);
        } else if (seleccion == 1) {
            EvaluacionPanel panel = (EvaluacionPanel) frame.getPanel("evaluacion");
            panel.mostrarFormularioEvaluacionManual(solicitudSeleccionada);
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                frame,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(frame, mensaje, titulo, tipo);
    }
}