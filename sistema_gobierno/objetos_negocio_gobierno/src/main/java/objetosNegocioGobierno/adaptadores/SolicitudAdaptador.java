package objetosNegocioGobierno.adaptadores;
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
 * The type Solicitud adaptador.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudAdaptador {

    /**
     * To entity solicitud.
     *
     * @param dto the dto
     * @return the solicitud
     */
    public static Solicitud toEntity(SolicitudDTO dto){
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setIdSolicitud(dto.getIdSolicitud());
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            if (dto.getDocumentos() != null) {
                List<Documento> documentos = new ArrayList<>();
                for (DocumentoDTO documentoDTO : dto.getDocumentos()){
                    documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
                }
                solicitud.setDocumentos(documentos);
            }
            solicitud.setFecha(dto.getFecha());
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            return solicitud;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la SolicitudDTO a entidad Solicitud");
        }
    }

    /**
     * To entity solicitud.
     *
     * @param dto the dto
     * @return the solicitud
     */
    public static Solicitud toEntity(SolicitudDTOGobierno dto){
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setIdSolicitud(dto.getId());
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            if (solicitud.getDocumentos() != null) {
                List<Documento> documentos = new ArrayList<>();
                for (DocumentoDTOGobierno documentoDTO : dto.getDocumentos()){
                    documentos.add(DocumentoAdaptador.toEntity(documentoDTO));
                }
                solicitud.setDocumentos(documentos);
            }
            solicitud.setFecha(dto.getFecha());
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            return solicitud;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la SolicitudDTOGobierno a entidad Solicitud");
        }
    }

    /**
     * To dto solicitud dto.
     *
     * @param solicitud the solicitud
     * @return the solicitud dto
     */
    public static SolicitudDTO toDTO(Solicitud solicitud){
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setIdSolicitud(solicitud.getIdSolicitud());
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toDTO(solicitud.getEstudiante()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            if (solicitud.getDocumentos() != null) {
                List<DocumentoDTO> documentoDTO = new ArrayList<>();
                for (Documento documento : solicitud.getDocumentos()){
                    documentoDTO.add(DocumentoAdaptador.toDTO(documento));
                }
                dto.setDocumentos(documentoDTO);
            }
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la entidad Solicitud a SolicitudDTO");
        }
    }

    /**
     * To infraestructura dto solicitud dto gobierno.
     *
     * @param solicitud the solicitud
     * @return the solicitud dto gobierno
     */
    public static SolicitudDTOGobierno toInfraestructuraDTO(Solicitud  solicitud){
        try {
            SolicitudDTOGobierno dto = new SolicitudDTOGobierno();
            dto.setId(solicitud.getIdSolicitud());
            dto.setBeca(BecaAdaptador.toInfraestructuraDTO(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toInfraestructuraDTO(solicitud.getEstudiante()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toInfraestructuraDTO(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toInfraestructuraDTO(solicitud.getHistorialAcademico()));
            if (solicitud.getDocumentos() != null){
                List<DocumentoDTOGobierno> documentoInfraestructuraDTO = new ArrayList<>();
                for (Documento documento : solicitud.getDocumentos()){
                    documentoInfraestructuraDTO.add(DocumentoAdaptador.toInfraestructuraDTO(documento));
                }
                dto.setDocumentos(documentoInfraestructuraDTO);
            }
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception sinUso){
            throw new SolicitudAdaptadorException("No se pudo mappear la entidad Solicitud a SolicitudDTOGobierno");
        }
    }
}
