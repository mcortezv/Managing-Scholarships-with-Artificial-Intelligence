package objetosNegocio.bo.solicitarBeca.intefaces;
import solicitarBeca.BecaDTO;
import solicitarBeca.BecasFiltradasDTO;
import solicitarBeca.RequisitosDTO;
import objetosNegocio.bo.solicitarBeca.excepciones.BecaInvalidaException;

/**
 * The interface Becas filtradas bo.
 *
 * @author Cortez, Manuel;
 */
public interface IBecasFiltradasBO {

    /**
     * Obtener becas filtradas becas disponibles dto gobierno.
     *
     * @param requisitos the requisitos
     * @return the becas disponibles dto gobierno
     * @throws BecaInvalidaException the beca invalida exception
     */
    BecasFiltradasDTO obtenerBecasFiltradas(RequisitosDTO requisitos) throws BecaInvalidaException;

    /**
     * Obtener beca por codigo beca.
     *
     * @param codigo         the codigo
     * @param becasFiltradas the becas filtradas
     * @return the beca
     * @throws BecaInvalidaException the beca invalida exception
     */
    BecaDTO obtenerBecaPorCodigo(Long codigo, BecasFiltradasDTO becasFiltradas) throws BecaInvalidaException;
}
