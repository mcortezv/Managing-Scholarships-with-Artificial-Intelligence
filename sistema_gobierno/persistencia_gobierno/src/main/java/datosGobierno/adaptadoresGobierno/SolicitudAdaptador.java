package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.SolicitudAdaptadorException;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.DocumentoDTO;
import dtoGobierno.SolicitudDTO;
import gobierno.DocumentoDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Solicitud adaptador.
 */
public class SolicitudAdaptador {

    /**
     * To entity solicitud.
     *
     * @param dto the dto
     * @return the solicitud
     */
    public static Solicitud toEntity(SolicitudDTO dto) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            if (dto.getEstudiante() != null) {
                solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            }
            solicitud.setFecha(dto.getFecha());
            solicitud.setId(dto.getId());
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            if (dto.getDocumentos() != null) {
                List<Documento> documentos = new ArrayList<>();
                for (DocumentoDTO documentoDTO : dto.getDocumentos()) {
                    documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
                }
                solicitud.setDocumentos(documentos);
            }
            return solicitud;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir SolicitudDTO a entidad Solicitud");
        }
    }

    /**
     * To entity solicitud.
     *
     * @param dto the dto
     * @return the solicitud
     */
    public static Solicitud toEntity(SolicitudDTOGobierno dto) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            if (dto.getEstudiante() != null) {
                solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            }
            solicitud.setFecha(dto.getFecha());
            solicitud.setId(dto.getId());
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            if (dto.getDocumentos() != null) {
                List<Documento> documentos = new ArrayList<>();
                for (DocumentoDTOGobierno documentoDTO : dto.getDocumentos()) {
                    documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
                }
                solicitud.setDocumentos(documentos);
            }
            return solicitud;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir SolicitudDTOGobierno a entidad Solicitud");
        }
    }


    /**
     * To dto solicitud dto.
     *
     * @param solicitud the solicitud
     * @return the solicitud dto
     */
    public static SolicitudDTO toDTO(Solicitud solicitud) {
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            dto.setEstado(solicitud.getEstado().toString());
            if (solicitud.getEstudiante() != null) {
                dto.setEstudiante(EstudianteAdaptador.toDTO(solicitud.getEstudiante()));
            }
            dto.setFecha(solicitud.getFecha());
            dto.setId(solicitud.getId());
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            if (solicitud.getDocumentos() != null) {
                List<DocumentoDTO> documentos = new ArrayList<>();
                for (Documento documento: solicitud.getDocumentos()) {
                    documentos.add(DocumentoAdaptador.toDTO(documento));
                }
                dto.setDocumentos(documentos);
            }
            return dto;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir entidad Solicitud a SolicitudDTO");
        }
    }
}
