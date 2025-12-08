package presentacion.coordinacion.interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.SolicitudDTO;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface ICoordinadorNegocio {

    boolean iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO);

    List<BecaDTO> obtenerBecasConSolicitudes();

    List<SolicitudDTO> obtenerSolicitudes(String tipoBeca);
}
