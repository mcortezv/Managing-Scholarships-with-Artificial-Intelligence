package fachadas;
import controles.ControlModeloML;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import interfaces.IFachadaModeloML;

/**
 * The type Fachada modelo ml.
 *
 * @author Cortez, Manuel;
 */
public class FachadaModeloML implements IFachadaModeloML {
    private final ControlModeloML controlModeloML;

    /**
     * Instantiates a new Fachada modelo ml.
     *
     * @param controlModeloML the control modelo ml
     */
    public FachadaModeloML(ControlModeloML controlModeloML) {
        this.controlModeloML = controlModeloML;
    }

    @Override
    public ResolucionDTOGobierno generarPrediccion(SolicitudDTOGobierno solicitud) {
        return controlModeloML.predecir(solicitud);
    }
}
