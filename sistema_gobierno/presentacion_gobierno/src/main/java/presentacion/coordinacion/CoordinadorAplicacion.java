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
import java.util.List;

/**
 * Coordinador de aplicación que gestiona la navegación y el estado de la UI
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
            if (coordinadorNegocio.iniciarSesion(evaluadorLoginDTO)) {
                mostrarMensaje("Bienvenido al sistema", "Inicio de Sesión",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.showPanel("hub");
            } else {
                mostrarMensaje("Credenciales inválidas. Por favor, verifique su matrícula y contraseña.",
                        "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
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
                // Limpiar estado
                limpiarEstadoAplicacion();

                // Cerrar sesión en el negocio
                coordinadorNegocio.cerrarSesion();

                // Volver a login
                frame.showPanel("iniciarSesion");

                mostrarMensaje("Sesión cerrada correctamente", "Cierre de Sesión",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            mostrarError("Error al cerrar sesión: " + ex.getMessage());
        }
    }

    @Override
    public void volverHub() {
        limpiarEstadoNavegacion();
        frame.showPanel("hub");
    }

    // ============= EVALUAR SOLICITUDES - NAVEGACIÓN =============

    @Override
    public void iniciarEvaluarConvocatoria() {
        try {
            validarSesionActiva();

            // Cargar becas con solicitudes
            List<BecaDTO> becas = coordinadorNegocio.obtenerBecasConSolicitudes();

            // Preparar panel
            EvaluarConvocatoriaPanel panel =
                    (EvaluarConvocatoriaPanel) frame.getPanel("evaluarConvocatoria");
            panel.setBecas(becas);

            // Mostrar panel
            frame.showPanel("evaluarConvocatoria");

        } catch (Exception ex) {
            mostrarError("Error al cargar convocatorias: " + ex.getMessage());
        }
    }

    @Override
    public void seleccionarConvocatoriaEvaluar(BecaDTO becaDTO) {
        try {
            validarSesionActiva();

            if (becaDTO == null) {
                mostrarError("Debe seleccionar una beca");
                return;
            }

            // Guardar beca seleccionada
            this.becaSeleccionada = becaDTO;

            // Cargar solicitudes de la beca
            List<SolicitudDTO> solicitudes =
                    coordinadorNegocio.obtenerSolicitudes(becaDTO.getTipo());

            // Preparar panel de evaluación
            EvaluacionPanel panel = (EvaluacionPanel) frame.getPanel("evaluacion");
            panel.setBecaActual(becaDTO);
            panel.setSolicitudes(solicitudes);

            // Mostrar panel
            frame.showPanel("evaluacion");

        } catch (Exception ex) {
            mostrarError("Error al cargar solicitudes: " + ex.getMessage());
        }
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

            // Guardar solicitud seleccionada
            this.solicitudSeleccionada = solicitud;

            // Mostrar opciones de evaluación
            mostrarOpcionesEvaluacion();

        } catch (Exception ex) {
            mostrarError("Error al seleccionar solicitud: " + ex.getMessage());
        }
    }

    @Override
    public void evaluarAutomatica(SolicitudDTO solicitud) {
        try {
            validarSesionActiva();

            if (solicitud == null) {
                mostrarError("No hay solicitud seleccionada");
                return;
            }

            // Mostrar mensaje de procesamiento
            mostrarMensaje("Procesando evaluación automática...\nEsto puede tomar unos segundos.",
                    "Evaluando", JOptionPane.INFORMATION_MESSAGE);

            // Realizar evaluación automática
            ResolucionDTO resolucion =
                    coordinadorNegocio.evaluarSolicitudAutomatica(solicitud);

            // Guardar resolución
            this.resolucionActual = resolucion;

            // Preguntar si desea guardar
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
            mostrarError("Error en evaluación automática: " + ex.getMessage());
        }
    }

    @Override
    public void evaluarManual(ResolucionDTO resolucion) {
        try {
            validarSesionActiva();

            if (resolucion == null) {
                mostrarError("Debe proporcionar una resolución");
                return;
            }

            // Validar campos
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

            // Confirmar evaluación manual
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
            mostrarError("Error en evaluación manual: " + ex.getMessage());
        }
    }

    /**
     * Guarda una evaluación (automática o manual)
     */
    private void guardarEvaluacion(ResolucionDTO resolucion) {
        try {
            boolean exitoso = coordinadorNegocio.guardarResolucion(resolucion);

            if (exitoso) {
                // Preparar panel de completado
                EvaluacionCompletadaPanel panel =
                        (EvaluacionCompletadaPanel) frame.getPanel("evaluacionCompletada");
                panel.setResolucion(resolucion);

                // Mostrar panel de completado
                frame.showPanel("evaluacionCompletada");

                mostrarMensaje("Evaluación guardada exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarError("No se pudo guardar la evaluación");
            }

        } catch (Exception ex) {
            mostrarError("Error al guardar evaluación: " + ex.getMessage());
        }
    }

    /**
     * Vuelve a la lista de solicitudes para evaluar otra
     */
    @Override
    public void evaluarOtraSolicitud() {
        try {
            if (becaSeleccionada != null) {
                seleccionarConvocatoriaEvaluar(becaSeleccionada);
            } else {
                iniciarEvaluarConvocatoria();
            }
        } catch (Exception ex) {
            mostrarError("Error al volver a evaluación: " + ex.getMessage());
        }
    }

    // ============= MODIFICAR RESOLUCIÓN - NAVEGACIÓN =============

    @Override
    public void iniciarModificarConvocatoria() {
        try {
            validarSesionActiva();

            ModificarConvocatoriaPanel panel =
                    (ModificarConvocatoriaPanel) frame.getPanel("modificarConvocatoria");

            // Limpiar estado previo
            limpiarEstadoModificacion();

            frame.showPanel("modificarConvocatoria");

        } catch (Exception ex) {
            mostrarError("Error al iniciar modificación: " + ex.getMessage());
        }
    }

    @Override
    public void iniciarBusquedaResolucion() {
        try {
            validarSesionActiva();

            BuscarResolucionPanel panel =
                    (BuscarResolucionPanel) frame.getPanel("buscarResolucion");

            // Limpiar búsqueda anterior
            panel.limpiarBusqueda();

            frame.showPanel("buscarResolucion");

        } catch (Exception ex) {
            mostrarError("Error al iniciar búsqueda: " + ex.getMessage());
        }
    }

    @Override
    public void buscarResolucion(String tipoFiltro, String filtro) {
        try {
            validarSesionActiva();

            if (tipoFiltro == null || tipoFiltro.trim().isEmpty()) {
                mostrarError("Debe seleccionar un tipo de filtro");
                return;
            }

            if (filtro == null || filtro.trim().isEmpty()) {
                mostrarError("Debe ingresar un valor de búsqueda");
                return;
            }

            // Buscar resolución
            ResolucionDTO resolucion =
                    coordinadorNegocio.buscarResolucion(tipoFiltro, filtro);

            // Guardar resolución encontrada
            this.resolucionActual = resolucion;

            // Mostrar en panel de modificación
            mostrarResolucionEncontrada(resolucion);

        } catch (Exception ex) {
            mostrarError("Error al buscar resolución: " + ex.getMessage());
        }
    }

    /**
     * Muestra la resolución encontrada en el panel de modificación
     */
    private void mostrarResolucionEncontrada(ResolucionDTO resolucion) {
        try {
            ModificarResolucionPanel panel =
                    (ModificarResolucionPanel) frame.getPanel("modificarResolucion");

            panel.setResolucion(resolucion);

            frame.showPanel("modificarResolucion");

        } catch (Exception ex) {
            mostrarError("Error al mostrar resolución: " + ex.getMessage());
        }
    }

    @Override
    public void reevaluarAutomatica(SolicitudDTO solicitud) {
        try {
            validarSesionActiva();

            if (solicitud == null) {
                mostrarError("No hay solicitud para re-evaluar");
                return;
            }

            // Confirmar re-evaluación
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

            // Mostrar mensaje de procesamiento
            mostrarMensaje("Re-evaluando solicitud...\nEsto puede tomar unos segundos.",
                    "Procesando", JOptionPane.INFORMATION_MESSAGE);

            // Re-evaluar
            ResolucionDTO nuevaResolucion =
                    coordinadorNegocio.reevaluarAutomatica(solicitud);

            // Actualizar resolución actual
            this.resolucionActual = nuevaResolucion;

            // Mostrar en panel
            mostrarResolucionEncontrada(nuevaResolucion);

            mostrarMensaje("Re-evaluación completada exitosamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            mostrarError("Error en re-evaluación automática: " + ex.getMessage());
        }
    }

    @Override
    public void modificarResolucion(ResolucionDTO resolucion) {
        try {
            validarSesionActiva();

            if (resolucion == null) {
                mostrarError("No hay resolución para modificar");
                return;
            }

            // Validar campos
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

            // Validar que explique la modificación
            String motivoLower = resolucion.getMotivo().toLowerCase();
            if (!motivoLower.contains("modificación") &&
                    !motivoLower.contains("corrección") &&
                    !motivoLower.contains("revisión")) {
                mostrarError("El motivo debe explicar claramente la razón de la modificación");
                return;
            }

            // Confirmar modificación
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
            mostrarError("Error al modificar resolución: " + ex.getMessage());
        }
    }

    /**
     * Guarda una modificación de resolución
     */
    private void guardarModificacion(ResolucionDTO resolucion) {
        try {
            boolean exitoso = coordinadorNegocio.modificarResolucion(resolucion);

            if (exitoso) {
                // Preparar panel de completado
                ModficacionCompletadaPanel panel =
                        (ModficacionCompletadaPanel) frame.getPanel("modificacionCompletada");
                panel.setResolucion(resolucion);

                // Mostrar panel
                frame.showPanel("modificacionCompletada");

                mostrarMensaje("Modificación guardada exitosamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarError("No se pudo guardar la modificación");
            }

        } catch (Exception ex) {
            mostrarError("Error al guardar modificación: " + ex.getMessage());
        }
    }

    @Override
    public void modificarOtraResolucion() {
        iniciarBusquedaResolucion();
    }

    // ============= UTILIDADES =============

    /**
     * Valida que haya una sesión activa
     */
    private void validarSesionActiva() {
        if (!coordinadorNegocio.haySesionIniciada()) {
            throw new RuntimeException("No hay sesión activa");
        }
    }

    /**
     * Limpia el estado de navegación
     */
    private void limpiarEstadoNavegacion() {
        this.becaSeleccionada = null;
        this.solicitudSeleccionada = null;
        this.resolucionActual = null;
    }

    /**
     * Limpia el estado de modificación
     */
    private void limpiarEstadoModificacion() {
        this.resolucionActual = null;
    }

    /**
     * Limpia todo el estado de la aplicación
     */
    private void limpiarEstadoAplicacion() {
        limpiarEstadoNavegacion();
        limpiarEstadoModificacion();
    }

    /**
     * Muestra opciones de evaluación
     */
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
            // El panel de evaluación manejará la entrada manual
            EvaluacionPanel panel = (EvaluacionPanel) frame.getPanel("evaluacion");
            panel.mostrarFormularioEvaluacionManual(solicitudSeleccionada);
        }
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                frame,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra un mensaje genérico
     */
    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(frame, mensaje, titulo, tipo);
    }
}