package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.enums.TipoDocumento;
import gobierno.DocumentoDTOGobierno;
import dtoGobierno.DocumentoDTO;
import objetosNegocioGobierno.adaptadores.excepciones.DocumentoAdaptadorException;

/**
 *
 * @author Cortez, Manuel;
 */
public class DocumentoAdaptador {

    public static Documento toEntity(DocumentoDTO dto){
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            return documento;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear la DTO a Entidad");
        }
    }

    public static Documento toEntity(DocumentoDTOGobierno dto){
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            return documento;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear la DTO de Infraestructura a Entidad");
        }
    }

    public static DocumentoDTO toDTO(Documento documento){
        try {
            DocumentoDTO dto = new DocumentoDTO();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(EstudianteAdaptador.toDTO(documento.getEstudiante()));
            return dto;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear la Entidad a DTO");
        }
    }

    public static DocumentoDTOGobierno toInfraestructuraDTO(Documento  documento){
        try {
            DocumentoDTOGobierno dto = new DocumentoDTOGobierno();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(EstudianteAdaptador.toInfraestructuraDTO(documento.getEstudiante()));
            return dto;
        } catch (Exception sinUso){
            throw new DocumentoAdaptadorException("No se pudo mappear la Entidad a DTO Infraestructura");
        }
    }
}