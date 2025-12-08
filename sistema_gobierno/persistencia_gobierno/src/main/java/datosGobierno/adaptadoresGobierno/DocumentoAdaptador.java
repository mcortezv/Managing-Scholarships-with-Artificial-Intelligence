package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.DocumentoAdaptadorException;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.enums.TipoDocumento;
import dtoGobierno.DocumentoDTO;


public class DocumentoAdaptador {

    public static Documento toEntity(DocumentoDTO dto) {
        try {
            Documento documento = new Documento();
            documento.setIdentificador(dto.getIdentificador());
            documento.setTipo(TipoDocumento.valueOf(dto.getTipo()));
            documento.setContenido(dto.getContenido());
            documento.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            return documento;
        } catch (Exception ex) {
            throw new DocumentoAdaptadorException("Error al convertir Documento DTO a Documento");
        }
    }

    public static DocumentoDTO toDTO(Documento documento) {
        try {
            DocumentoDTO dto = new DocumentoDTO();
            dto.setIdentificador(documento.getIdentificador());
            dto.setTipo(documento.getTipo().toString());
            dto.setContenido(documento.getContenido());
            dto.setEstudiante(EstudianteAdaptador.toDTO(documento.getEstudiante()));
            return dto;
        } catch (Exception ex) {
            throw new DocumentoAdaptadorException("Error al converit Documento a DocumentoDTO");
        }
    }

}
