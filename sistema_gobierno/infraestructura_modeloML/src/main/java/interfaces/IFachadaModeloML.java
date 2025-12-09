package interfaces;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;

/**
 * The interface Fachada modelo ml.
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaModeloML {

    /**
     * Generar prediccion resolucion dto gobierno.
     *
     * @param solicitud the solicitud
     * @return the resolucion dto gobierno
     */
    ResolucionDTOGobierno generarPrediccion(SolicitudDTOGobierno solicitud);
}
