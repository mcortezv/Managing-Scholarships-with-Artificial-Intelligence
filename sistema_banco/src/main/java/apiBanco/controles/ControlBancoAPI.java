package apiBanco.controles;

import datos.dominioBanco.Cuenta;
import datos.dtosBanco.DatosTarjetaDTO;
import datos.excepcionesBanco.BancoException;
import datos.serviceBanco.CuentaService;
import datos.serviceBanco.TransaccionService;
import views.panels.MainFrameBanco;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ControlBancoAPI {

    private final CuentaService cuentaService;
    private final TransaccionService transaccionService;
    private MainFrameBanco ventanaBancaria;

    public ControlBancoAPI(){
        this.cuentaService = new CuentaService();
        this.transaccionService = new TransaccionService();
    }

    public void abrirVentanaPago(ActionListener listenerBotonPagar) {
        this.ventanaBancaria = new MainFrameBanco();
        this.ventanaBancaria.setAccionPagar(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estás seguro de que deseas realizar el pago?",
                    "Confirmar Pago",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (respuesta == JOptionPane.YES_OPTION) {
                listenerBotonPagar.actionPerformed(e);
            }
        });
        this.ventanaBancaria.setAccionCancelar(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Estás seguro de que deseas cancelar?\nSe perderán los datos de la transacción.",
                    "Cancelar Transacción",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (respuesta == JOptionPane.YES_OPTION) {
                cerrarVentana();
            }
        });

        this.ventanaBancaria.mostrar();
    }

    public void cerrarVentana() {
        if (this.ventanaBancaria != null) {
            this.ventanaBancaria.cerrar();
            this.ventanaBancaria = null;
        }
    }

    public boolean confirmarPago(double monto, String concepto) {
        if (this.ventanaBancaria == null) {
            return false;
        }
        DatosTarjetaDTO datosDTO = ventanaBancaria.obtenerDatos();
        if(datosDTO == null){
            return false;
        }
        return procesarSolicitudPago(datosDTO, monto, concepto);
    }

    public boolean procesarSolicitudPago(DatosTarjetaDTO datosTarjeta, double monto, String concepto) {
        String numeroLimpio = "";
        try {
            if(datosTarjeta == null){
                return false;
            }
            numeroLimpio = datosTarjeta.getNumeroTarjeta().replaceAll("\\D", "");

            Cuenta cuenta = cuentaService.validarYObtenerCuenta(
                    numeroLimpio,
                    datosTarjeta.getCv(),
                    datosTarjeta.getFechaVencimiento()
            );

            if (!cuentaService.verificarFondos(cuenta, monto)) {
                transaccionService.registrarFallo(numeroLimpio, monto, concepto, "FONDOS INSUFICIENTES");
                mostrarMensaje("La cuenta no tiene fondos suficientes para realizar esta operación.", "Fondos Insuficientes", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            boolean descuentoExitoso = cuentaService.descontarSaldo(cuenta, monto);

            if (descuentoExitoso) {
                transaccionService.registrarExito(cuenta, monto, concepto);
                mostrarMensaje("Pago realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cerrarVentana();
                return true;
            } else {
                transaccionService.registrarFallo(numeroLimpio, monto, concepto, "ERROR SISTEMA");
                mostrarMensaje("Ocurrió un error interno al procesar el saldo.", "Error del Sistema", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (BancoException e) {
            mostrarMensaje(e.getMessage(), "Error en la Cuenta", JOptionPane.ERROR_MESSAGE);
            String numLog = numeroLimpio.isEmpty() ? datosTarjeta.getNumeroTarjeta() : numeroLimpio;
            transaccionService.registrarFallo(numLog, monto, concepto, e.getMessage());
            return false;
        } catch (Exception e) {
            mostrarMensaje("Ocurrió un error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private void mostrarMensaje(String mensaje, String titulo, int tipoIcono) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoIcono);
    }

}
