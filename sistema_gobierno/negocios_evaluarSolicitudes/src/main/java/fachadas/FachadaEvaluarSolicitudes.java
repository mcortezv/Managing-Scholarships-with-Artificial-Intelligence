package fachadas;
import controles.ControlEvaluarSolicitudes;
import dtoGobierno.BecaDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import interfaces.IFachadaEvaluarSolicitudes;
import java.util.List;

/**
 * The type Fachada evaluar solicitudes.
 *
 * @author Cortez, Manuel;
 */
public class FachadaEvaluarSolicitudes implements IFachadaEvaluarSolicitudes {
    private final ControlEvaluarSolicitudes controlEvaluarSolicitudes;

    public List<BecaDTO> obtenerListadoBecas(){
        return this.controlEvaluarSolicitudes.obtenerListadoBecas();
    }

    /**
     * Instantiates a new Fachada evaluar solicitudes.
     *
     * @param controlEvaluarSolicitudes the control evaluar solicitudes
     */
    public FachadaEvaluarSolicitudes(ControlEvaluarSolicitudes controlEvaluarSolicitudes) {
        this.controlEvaluarSolicitudes = controlEvaluarSolicitudes;
    }

    @Override
    public ResolucionDTO evaluacionAutomatica(SolicitudDTO solicitud){
        return controlEvaluarSolicitudes.evaluacionAutomatica(solicitud);
    }

    @Override
    public boolean resolverSolicitudManual(ResolucionDTO resolucionDTO) {
        return controlEvaluarSolicitudes.resolverSolicitudManual(resolucionDTO);
    }

    @Override
    public boolean resolver(ResolucionDTO resolucionDTO){
        return controlEvaluarSolicitudes.resolver(resolucionDTO);
    }

    @Override
    public List<SolicitudDTO> obtenerSolicitudes(String tipoBeca){
        return controlEvaluarSolicitudes.obtenerSolicitudes(tipoBeca);
    }
}
