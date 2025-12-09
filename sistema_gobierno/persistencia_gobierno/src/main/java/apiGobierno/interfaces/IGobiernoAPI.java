package apiGobierno.interfaces;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;

/**
 * The interface Gobierno api.
 *
 * @author Cortez, Manuel;
 */
public interface IGobiernoAPI {

    /**
     * Guardar solicitud boolean.
     *
     * @param solicitudDTO the solicitud dto
     * @return the boolean
     */
    boolean guardarSolicitud(SolicitudDTOGobierno solicitudDTO);

    /**
     * Obtener becas becas disponibles dto gobierno.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas disponibles dto gobierno
     */
    BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO);
}
