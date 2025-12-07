package presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo;

import dtoGobierno.EstudianteDTO;
import pagarAdeudo.ClaseDTO;
import pagarAdeudo.PrestamoDTO;
import pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.dominio.enums.pagarAdeudo.MetodoPago;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public interface ICoordinadorNegocioPagarAdeudo {
    //itson
    double calcularTotalPrestamos(List<PrestamoDTO> prestamos);
    double calcularTotalClases(List<ClaseDTO> clases);
    List<PrestamoDTO> obtenerListaPrestamos(EstudianteDTO estudianteDTO);
    List<ClaseDTO> obtenerListaClases(EstudianteDTO estudianteDTO);

    // Banco
    void mostrarVentanaPago(ActionListener listener);
    SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) throws Exception;
    void cerrarVentanaBanco();

    // PayPal
    void mostrarVentanaPaypal(double monto, String concepto, ActionListener listener);
    void cerrarVentanaPaypal();
    SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO solicitudPagoDTO);
    boolean notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO);
}
