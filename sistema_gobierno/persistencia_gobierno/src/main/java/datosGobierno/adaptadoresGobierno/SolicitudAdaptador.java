package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.SolicitudAdaptadorException;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.DocumentoDTO;
import dtoGobierno.SolicitudDTO;
import java.util.ArrayList;
import java.util.List;


public class SolicitudAdaptador {

    public static Solicitud toEntity(SolicitudDTO dto) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            solicitud.setFecha(dto.getFecha());
            solicitud.setId(dto.getId());
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            List<Documento> documentos = new ArrayList<>();
            for (DocumentoDTO documentoDTO : dto.getDocumentos()) {
                documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
            }
            solicitud.setDocumentos(documentos);
            return solicitud;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir SolicitudDTO a Solicitud");
        }
    }

    public static SolicitudDTO toDTO(Solicitud solicitud) {
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            dto.setEstado(solicitud.getEstado().toString());
            dto.setEstudiante(EstudianteAdaptador.toDTO(solicitud.getEstudiante()));
            dto.setFecha(solicitud.getFecha());
            dto.setId(solicitud.getId());
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            List<DocumentoDTO> documentos = new ArrayList<>();
            for (Documento documento: solicitud.getDocumentos()) {
                documentos.add(DocumentoAdaptador.toDTO(documento));
            }
            dto.setDocumentos(documentos);
            return dto;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir Solicitud a SolicitudDTO");
        }
    }
}
