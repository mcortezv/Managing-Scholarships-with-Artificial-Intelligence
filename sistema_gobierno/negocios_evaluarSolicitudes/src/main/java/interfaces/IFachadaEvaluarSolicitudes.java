package interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaEvaluarSolicitudes {

    List<BecaDTO> obtenerListadoBecas();

    ResolucionDTO evaluacionAutomatica(SolicitudDTO solicitud);

    boolean resolverSolicitudManual(ResolucionDTO resolucionDTO);

    boolean resolver(ResolucionDTO resolucionDTO);

    List<SolicitudDTO> obtenerSolicitudes(String tipoBeca);
}
