package presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo;


import dtoGobierno.EstudianteDTO;
import interfaces.IFachadaPago;
import java.awt.event.ActionListener;
import java.util.List;
import pagarAdeudo.ClaseDTO;

import pagarAdeudo.PrestamoDTO;
import pagarAdeudo.SolicitudPagoDTO;


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
    public SolicitudPagoDTO realizarPago(SolicitudPagoDTO solicitudPagoDTO) throws Exception {
        return fachadaPago.realizarPago(solicitudPagoDTO);
    }

    @Override
    public void cerrarVentanaBanco() {
        fachadaPago.cerrarVentana();
    }

    @Override
    public boolean notificarLiquidacion(SolicitudPagoDTO solicitudPagoDTO) {
        return fachadaPago.notificarLiquidacion(solicitudPagoDTO);
    }
}