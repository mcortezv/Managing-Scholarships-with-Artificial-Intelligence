package objetosNegocio.bo.solicitarBeca.intefaces;
import objetosNegocio.bo.solicitarBeca.excepciones.DocumentoInvalidoException;
import solicitarBeca.DocumentoDTO;

/**
 * The interface Documento bo.
 *
 * @author Cortez, Manuel;
 */
public interface IDocumentoBO {

    /**
     * Guardar documento.
     *
     * @param documento the documento
     * @throws DocumentoInvalidoException the documento invalido exception
     */
    void guardarDocumento(DocumentoDTO documento) throws DocumentoInvalidoException;
}
