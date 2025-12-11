package fachadas;
import controles.ControlBanco;
import excepciones.InfraestructuraBancoException;
import interfaces.IFachadaBanco;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.awt.event.ActionListener;


/**
 *  CASO DE USO PAGAR ADEUDO
 * @author Escalante, Sebastian
 */
public class FachadaBanco implements IFachadaBanco {

    private final ControlBanco controlBanco;

    public FachadaBanco(ControlBanco controlBanco) {
        this.controlBanco = controlBanco;
    }

    @Override
    public void mostrarPantallaPago(ActionListener listenerBotonPagar){
        controlBanco.mostrarVentanaPago(listenerBotonPagar);
    }

    @Override
    public SolicitudPagoDTOI ejecutarPago(SolicitudPagoDTOI solicitud){
        return controlBanco.ejecutarPago(solicitud);
    }

    @Override
    public void cerrarVentana() {
        controlBanco.cerrarVentana();
    }
}
