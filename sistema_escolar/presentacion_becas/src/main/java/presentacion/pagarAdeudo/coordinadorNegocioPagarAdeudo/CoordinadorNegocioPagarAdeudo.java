package presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo;


import interfaces.IFachadaPago;
import java.awt.event.ActionListener;
import java.util.List;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import solicitarBeca.EstudianteDTO;

public class CoordinadorNegocioPagarAdeudo implements ICoordinadorNegocioPagarAdeudo {
    private final IFachadaPago fachadaPago;

    public CoordinadorNegocioPagarAdeudo(IFachadaPago fachadaPago) {
        this.fachadaPago = fachadaPago;
    }

    @Override
    public double calcularTotalPrestamos(List<PrestamoDTO> prestamos) {
        double total = 0.0;
        if (prestamos != null) {
            for (PrestamoDTO p : prestamos) {
                total += p.getCosto();
            }
        }
        return total;
    }

    @Override
    public double calcularTotalClases(List<ClaseDTO> clases) {
        double total = 0.0;
        if (clases != null) {
            for (ClaseDTO c : clases) {
                total += c.getCosto();
            }
        }
        return total;
    }

    @Override
    public List<PrestamoDTO> obtenerListaPrestamos(EstudianteDTO estudianteDTO) {
        return fachadaPago.solicitarListaPrestamos(estudianteDTO);
    }

    @Override
    public List<ClaseDTO> obtenerListaClases(EstudianteDTO estudianteDTO) {
        return fachadaPago.solicitarListaClases(estudianteDTO);
    }


    @Override
    public void mostrarVentanaPago(ActionListener listenerBotonPagarDelBanco) {
        fachadaPago.solicitarVistaDePago(listenerBotonPagarDelBanco);
    }

    @Override
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) {
        return fachadaPago.realizarPago(solicitudPagoDTO);
    }

    @Override
    public void cerrarVentanaBanco() {
        fachadaPago.cerrarVentana();
    }

    @Override
    public void mostrarVentanaPaypal(double monto, String concepto, ActionListener listener) {
        fachadaPago.solicitarVistaPaypal(monto, concepto, listener);
    }

    @Override
    public void cerrarVentanaPaypal() {
        fachadaPago.cerrarVentanaPaypal();
    }

    @Override
    public SolicitudPagoDTO realizarPagoPaypal(SolicitudPagoDTO solicitudPagoDTO){
        return fachadaPago.realizarPagoPaypal(solicitudPagoDTO);
    }

    @Override
    public void notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO) {
        fachadaPago.notificarLiquidacion(solicitudPagoDTO);
    }
}