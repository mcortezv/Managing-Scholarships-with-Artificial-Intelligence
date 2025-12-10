package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.SolicitudAdaptadorException;
import datosGobierno.documents.SolicitudDocument;
import datosGobierno.dominioGobierno.Documento;
import datosGobierno.dominioGobierno.Estudiante;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.DocumentoDTO;
import dtoGobierno.SolicitudDTO;
import gobierno.DocumentoDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import gobierno.DocumentoDTOGobierno;
import gobierno.SolicitudDTOGobierno;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Solicitud adaptador.
 */
public class SolicitudAdaptador {

    public static Solicitud toEntity(SolicitudDTO dto) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            if (dto.getEstudiante() != null) {
                solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            }
            solicitud.setFecha(dto.getFecha());
            solicitud.setIdSolicitud(dto.getIdSolicitud());
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
            solicitud.setIdSolicitud(dto.getId());
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

    public static SolicitudDocument toDocument(SolicitudDTOGobierno dto) {
        try {
            SolicitudDocument solicitud = new SolicitudDocument();
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            solicitud.setFecha(dto.getFecha());
            solicitud.setIdSolicitud(dto.getId());
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            return solicitud;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir SolicitudDTOGobierno a entidad SolicitudDocument");
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
            dto.setIdSolicitud(solicitud.getIdSolicitud());
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

    public static SolicitudDTOGobierno toDTOGobierno(Solicitud solicitud) {
        try {
            SolicitudDTOGobierno dto = new SolicitudDTOGobierno();
            dto.setId(solicitud.getIdSolicitud());
            dto.setFecha(solicitud.getFecha());
            if (solicitud.getEstado() != null) {
                dto.setEstado(solicitud.getEstado().toString());
            }
            dto.setBeca(BecaAdaptador.toDTOGobierno(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toDTOGobierno(solicitud.getEstudiante()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTOGobierno(solicitud.getHistorialAcademico()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTOGobierno(solicitud.getInformacionSocioeconomica()));
            List<DocumentoDTOGobierno> documentosDTO = new ArrayList<>();
            if (solicitud.getDocumentos() != null) {
                for (Documento documento : solicitud.getDocumentos()) {
                    documentosDTO.add(DocumentoAdaptador.toDTOGobierno(documento));
                }
            }
            dto.setDocumentos(documentosDTO);
            return dto;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir Solicitud a SolicitudDTOGobierno: " + ex.getMessage());
        }
    }

    public static Solicitud toEntity(SolicitudDocument doc, Estudiante estudianteEntity) {
        try {
            if (doc == null) {
                return null;
            }
            Solicitud solicitud = new Solicitud();
            solicitud.setIdSolicitud(doc.getIdSolicitud());
            solicitud.setFecha(doc.getFecha());
            solicitud.setEstado(doc.getEstado());
            solicitud.setEstudiante(estudianteEntity);
//            solicitud.setBeca(BecaAdaptador.toEntity(doc.getBeca()));
//            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(doc.getHistorialAcademico()));
//            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(doc.getInformacionSocioeconomica()));
            solicitud.setDocumentos(new ArrayList<>());

            return solicitud;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir SolicitudDocument a Entidad: " + ex.getMessage());
        }
    }

    public static List<SolicitudDTOGobierno> toDTOGobierno(List<Solicitud> solicitudes) {
        if (solicitudes == null) {
            return new ArrayList<>();
        }
        try {
            List<SolicitudDTOGobierno> listaDTO = new ArrayList<>();
            for (Solicitud solicitud : solicitudes) {
                listaDTO.add(toDTOGobierno(solicitud));
            }
            return listaDTO;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir lista de Solicitudes: " + ex.getMessage());
        }
    }



}
