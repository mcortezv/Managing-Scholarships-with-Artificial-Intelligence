package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.DocumentoAdaptadorException;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.enums.TipoDocumento;
import dtoGobierno.DocumentoDTO;
import gobierno.DocumentoDTOGobierno;

/**
 * The type Documento adaptador.
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
     * To entity documento.
     *
     * @param dto the dto
     * @return the documento
     */
    public static Documento toEntity(DocumentoDTOGobierno dto) {
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            return documento;
        } catch (Exception ex) {
            throw new DocumentoAdaptadorException("Error al convertir DocumentoDTOGobierno a entidad Documento");
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
            throw new DocumentoAdaptadorException("Error al converir entidada Documento a DocumentoDTO");
        }
    }
}
