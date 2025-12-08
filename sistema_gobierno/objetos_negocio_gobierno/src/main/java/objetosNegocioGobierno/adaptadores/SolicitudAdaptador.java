package objetosNegocioGobierno.adaptadores;
import datosGobierno.daoGobierno.documents.SolicitudDocument;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.DocumentoDTO;
import gobierno.DocumentoDTOGobierno;
import dtoGobierno.SolicitudDTO;
import gobierno.SolicitudDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.SolicitudAdaptadorException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudAdaptador {

    public static Solicitud toEntity(SolicitudDTO dto){
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setId(dto.getId());
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            List<Documento> documentos = new ArrayList<>();
            for (DocumentoDTO documentoDTO : dto.getDocumentos()){
                documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
            }
            solicitud.setDocumentos(documentos);
            solicitud.setFecha(dto.getFecha());
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            return solicitud;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la DTO a Entidad");
        }
    }

    public static Solicitud toEntity(SolicitudDTOGobierno dto){
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setId(dto.getId());
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            List<Documento> documentos = new ArrayList<>();
            for (DocumentoDTOGobierno documentoDTO : dto.getDocumentos()){
                documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
            }
            solicitud.setDocumentos(documentos);
            solicitud.setFecha(dto.getFecha());
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            return solicitud;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la DTO de Infraestructura a Entidad");
        }
    }

    public static SolicitudDTO toDTO(Solicitud solicitud){
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setId(solicitud.getId());
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toDTO(solicitud.getEstudiante()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            List<DocumentoDTO> documentoDTO = new ArrayList<>();
            for (Documento documento : solicitud.getDocumentos()){
                documentoDTO.add(DocumentoAdaptador.toDTO(documento));
            }
            dto.setDocumentos(documentoDTO);
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la Entidad a DTO");
        }
    }

    public static SolicitudDTOGobierno toInfraestructuraDTO(Solicitud  solicitud){
        try {
            SolicitudDTOGobierno dto = new SolicitudDTOGobierno();
            dto.setId(solicitud.getId());
            dto.setBeca(BecaAdaptador.toInfraestructuraDTO(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toInfraestructuraDTO(solicitud.getEstudiante()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toInfraestructuraDTO(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toInfraestructuraDTO(solicitud.getHistorialAcademico()));
            List<DocumentoDTOGobierno> documentoInfraestructuraDTO = new ArrayList<>();
            for (Documento documento : solicitud.getDocumentos()){
                documentoInfraestructuraDTO.add(DocumentoAdaptador.toInfraestructuraDTO(documento));
            }
            dto.setDocumentos(documentoInfraestructuraDTO);
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la Entidad a DTO Infraestructura");
        }
    }


    public static SolicitudDTO toDTO(SolicitudDocument solicitud){
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setId(solicitud.getId());
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la Entidad a DTO");
        }
    }
}
