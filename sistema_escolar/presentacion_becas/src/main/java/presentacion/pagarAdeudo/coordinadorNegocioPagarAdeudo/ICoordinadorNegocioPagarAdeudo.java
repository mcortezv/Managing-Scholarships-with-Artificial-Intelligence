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
    List<PrestamoDTO> obtenerListaPrestamos(EstudianteDTO estudianteDTO);
    List<ClaseDTO> obtenerListaClases(EstudianteDTO estudianteDTO);
    double calcularTotalPrestamos(List<PrestamoDTO> prestamos);
    double calcularTotalClases(List<ClaseDTO> clases);
    void mostrarVentanaPago(ActionListener listener);
    SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitud) throws Exception;
    void cerrarVentanaBanco();

    void mostrarVentanaPaypal(ActionListener listenerBotonPagarPaypal);
    void cerrarVentanaPaypal();
    SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO solicitudPagoDTO);
    boolean notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO);
}
