package objetosNegocio.adaptadores.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.DocumentoAdaptadorException;
import solicitarBeca.DocumentoDTO;
import gobierno.DocumentoDTOGobierno;
import solicitarBeca.dominio.Documento;
import solicitarBeca.dominio.enums.TipoDocumento;

/**
 * The type Documento adaptador.
 *
 * @author Cortez, Manuel;
 */
public class DocumentoAdaptador {

    /**
     * To entity documento.
     *
     * @param dto the dto
     * @return the documento
     */
    public static Documento toEntity(DocumentoDTO dto) {
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            return documento;
        } catch (Exception ex) {
            throw new DocumentoAdaptadorException("Error al convertir DocumentoDTO a entidad Documento");
        }
    }

    /**
     * To dto documento dto.
     *
     * @param documento the documento
     * @return the documento dto
     */
    public static DocumentoDTO toDTO(Documento documento) {
        try {
            DocumentoDTO dto = new DocumentoDTO();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(EstudianteAdaptador.toDTO(documento.getEstudiante()));
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Documento a DocumentoDTO");
        }
    }

    /**
     * To dto gobierno documento dto gobierno.
     *
     * @param documento the documento
     * @return the documento dto gobierno
     */
    public static DocumentoDTOGobierno toDTOGobierno(Documento documento) {
        try {
            DocumentoDTOGobierno dto = new DocumentoDTOGobierno();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(EstudianteAdaptador.toDTOGobierno(documento.getEstudiante()));
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Documento a DocumentoDTOGobierno");
        }
    }
}
