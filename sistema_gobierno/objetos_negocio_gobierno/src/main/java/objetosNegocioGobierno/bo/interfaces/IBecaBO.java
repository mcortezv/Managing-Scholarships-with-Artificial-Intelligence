package objetosNegocioGobierno.bo.interfaces;
import dtoGobierno.BecaDTO;
import java.util.List;

/**
 * The interface Beca bo.
 *
 * @author Cortez, Manuel;
 */
public interface IBecaBO {

    /**
     * Obtener listado becas list.
     *
     * @return the list
     */
    List<BecaDTO> obtenerListadoBecas();
}
