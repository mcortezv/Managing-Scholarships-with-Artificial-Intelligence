package fachadas;
import controles.ControlModificarResolucion;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import interfaces.IFachadaModificarResolucion;

/**
 * Fachada corregida con todos los métodos
 *
 * @author Cortez, Manuel;
 */
public class FachadaModificarResolucion implements IFachadaModificarResolucion {
    private final ControlModificarResolucion controlModificarResolucion;

    /**
     * Instantiates a new Fachada modificar resolucion.
     *
     * @param controlModificarResolucion the control modificar resolucion
     */
    public FachadaModificarResolucion(ControlModificarResolucion controlModificarResolucion) {
        this.controlModificarResolucion = controlModificarResolucion;
    }

    @Override
    public ResolucionDTO buscarResolucion(String nombre, String filtro){
        return controlModificarResolucion.buscarResolucion(nombre, filtro);
    }

    @Override
    public ResolucionDTO resolverAutomatico(SolicitudDTO solicitud){
        return controlModificarResolucion.resolverAutomatico(solicitud);
    }

    @Override
    public boolean resolverManual(ResolucionDTO resolucionDTO){
        return controlModificarResolucion.resolverManual(resolucionDTO);
    }

    @Override
    public boolean modificarResolucion(ResolucionDTO resolucionDTO){
        return controlModificarResolucion.modificarResolucion(resolucionDTO);
    }
}