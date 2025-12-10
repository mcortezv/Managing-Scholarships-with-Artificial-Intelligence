package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.enums.TipoDocumento;
import gobierno.DocumentoDTOGobierno;
import dtoGobierno.DocumentoDTO;
import objetosNegocioGobierno.adaptadores.excepciones.DocumentoAdaptadorException;

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
    public static Documento toEntity(DocumentoDTO dto){
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(dto.getEstudiante());
            return documento;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear el DocumentoDTO a entidad Documento");
        }
    }

    /**
     * To entity documento.
     *
     * @param dto the dto
     * @return the documento
     */
    public static Documento toEntity(DocumentoDTOGobierno dto){
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(dto.getEstudiante());
            return documento;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear el DocumentoDTOGobierno a entidad Documento");
        }
    }

    /**
     * To dto documento dto.
     *
     * @param documento the documento
     * @return the documento dto
     */
    public static DocumentoDTO toDTO(Documento documento){
        try {
            DocumentoDTO dto = new DocumentoDTO();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(documento.getEstudiante());
            return dto;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear el DocumentoDTO a entidad Documento");
        }
    }

    /**
     * To infraestructura dto documento dto gobierno.
     *
     * @param documento the documento
     * @return the documento dto gobierno
     */
    public static DocumentoDTOGobierno toInfraestructuraDTO(Documento  documento){
        try {
            DocumentoDTOGobierno dto = new DocumentoDTOGobierno();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(documento.getEstudiante());
            return dto;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear la entidad Documento a DocumentoDTOGobierno");
        }
    }
}
