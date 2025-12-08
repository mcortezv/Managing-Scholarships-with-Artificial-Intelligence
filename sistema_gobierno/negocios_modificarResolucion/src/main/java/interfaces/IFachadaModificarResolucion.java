package interfaces;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;

/**
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaModificarResolucion {

    ResolucionDTO buscarResolucion(String nombre, String filtro);

    ResolucionDTO resolverAutomatico(SolicitudDTO solicitud);

    boolean resolverManual(ResolucionDTO resolucionDTO);

    boolean modificarResolucion(ResolucionDTO resolucionDTO);
}
