package apiPaypal.controles;

import datos.dominioPaypal.Usuario;
import datos.servicePaypal.TransaccionService;
import datos.servicePaypal.UsuarioService;
import views.panels.MainFramePaypal;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ControlPaypalAPI {

    private final UsuarioService usuarioService;
    private final TransaccionService transaccionService;
    private MainFramePaypal ventanaPaypal;

    private Usuario usuarioAutenticado;
    private double montoActual;
    private String conceptoActual;

    public ControlPaypalAPI(){
        this.usuarioService = new UsuarioService();
        this.transaccionService = new TransaccionService();
    }

    public void abrirVentanaPago(double monto, String concepto, ActionListener listenerBotonPagarExitoso) {
        this.montoActual = monto;
        this.conceptoActual = concepto;
        this.ventanaPaypal = new MainFramePaypal();

        this.ventanaPaypal.setAccionIngresar(e -> {
            autenticarUsuario();
        });
        this.ventanaPaypal.setAccionPagarFinal(e -> {
            if (procesarPagoFinal()) {
                listenerBotonPagarExitoso.actionPerformed(e);
            }
        });

        this.ventanaPaypal.setAccionCancelar(e -> {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Cancelar?", "Salir", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) cerrarVentana();
        });
        this.ventanaPaypal.setAccionVolver(e -> {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Cancelar?", "Salir", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) cerrarVentana();
        });

        this.ventanaPaypal.mostrarLogin();
        this.ventanaPaypal.mostrar();
    }

    private void autenticarUsuario() {
        String[] credenciales = ventanaPaypal.obtenerDatosLogin();
        if (credenciales == null) return;

        try {
            Usuario usuario = usuarioService.validarCredenciales(credenciales[0], credenciales[1]);
            if (usuario != null) {
                this.usuarioAutenticado = usuario;
                this.ventanaPaypal.mostrarConfirmacion(
                        usuario.getNombre(),
                        usuario.getSaldo(),
                        this.montoActual
                );
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean procesarPagoFinal() {
        if (usuarioAutenticado == null) return false;

        try {
            if (!usuarioService.verificarFondos(usuarioAutenticado, montoActual)) {
                transaccionService.registrarFallo(usuarioAutenticado.getEmail(), montoActual, conceptoActual, "SALDO INSUFICIENTE");
                JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            boolean exito = usuarioService.descontarSaldo(usuarioAutenticado, montoActual);
            if (exito) {
                transaccionService.registrarExito(usuarioAutenticado, montoActual, conceptoActual);
                JOptionPane.showMessageDialog(null, "¡Pago Exitoso!", "PayPal", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cerrarVentana() {
        if (this.ventanaPaypal != null) {
            this.ventanaPaypal.cerrar();
            this.ventanaPaypal = null;
            this.usuarioAutenticado = null;
        }
    }
}