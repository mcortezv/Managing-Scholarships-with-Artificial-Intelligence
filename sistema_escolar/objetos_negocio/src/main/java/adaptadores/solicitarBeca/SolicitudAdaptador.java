/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.solicitarBeca;
import adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import adaptadores.solicitarBeca.excepciones.SolicitudAdaptadorException;
import solicitarBeca.DocumentoDTO;
import solicitarBeca.SolicitudDTO;
import gobierno.DocumentoDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import org.bson.types.ObjectId;
import solicitarBeca.dominio.Documento;
import solicitarBeca.dominio.Solicitud;
import solicitarBeca.dominio.enums.EstadoSolicitud;
import solicitarBeca.repository.documents.SolicitudDocument;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudAdaptador {

    public static SolicitudDTO toDTO(Solicitud solicitud) {
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setId(solicitud.getId());
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toDTO(solicitud.getEstudiante()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            ArrayList<DocumentoDTO> documentosDTO = new ArrayList<>();
            for (Documento documento : solicitud.getDocumentos()) {
                documentosDTO.add(DocumentoAdaptador.toDTO(documento));
            }
            dto.setDocumentos(documentosDTO);
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir entidad Solicitud a DTO");
        }
    }

    public static SolicitudDTOGobierno toDTOGobierno(Solicitud solicitud) {
        try {
            SolicitudDTOGobierno dto = new SolicitudDTOGobierno();
            dto.setId(solicitud.getId());
            dto.setBeca(BecaAdaptador.toDTOGobierno(solicitud.getBeca()));
            dto.setEstudiante(EstudianteAdaptador.toDTOGobierno(solicitud.getEstudiante()));
            dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTOGobierno(solicitud.getInformacionSocioeconomica()));
            dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTOGobierno(solicitud.getHistorialAcademico()));
            ArrayList<DocumentoDTOGobierno> documentosDTO = new ArrayList<>();
            for (Documento documento : solicitud.getDocumentos()) {
                documentosDTO.add(DocumentoAdaptador.toDTOGobierno(documento));
            }
            dto.setDocumentos(documentosDTO);
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir entidad Solicitud a DTO Gobierno");
        }
    }

    public static Solicitud toEntity(SolicitudDTO dto) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setId(dto.getId());
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(dto.getEstudiante()));
            solicitud.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toEntity(dto.getInformacionSocioeconomica()));
            solicitud.setHistorialAcademico(HistorialAcademicoAdaptador.toEntity(dto.getHistorialAcademico()));
            ArrayList<Documento> documentos = new ArrayList<>();
            for (DocumentoDTO documento : dto.getDocumentos()) {
                documentos.add(DocumentoAdaptador.toEntity(documento));
            }
            solicitud.setDocumentos(documentos);
            solicitud.setFecha(dto.getFecha());
            solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            return solicitud;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir DTO Solicitud a Solicitud");
        }
    }

    public static SolicitudDocument toDocument(Solicitud solicitud, ObjectId estudianteId, List<ObjectId> documentos) {
        try {
            SolicitudDocument document = new SolicitudDocument();
            document.set_id(new ObjectId());
            document.setId(solicitud.getId());
            document.setBeca(solicitud.getBeca());
            document.setEstudiante(estudianteId);
            document.setInformacionSocioeconomica(solicitud.getInformacionSocioeconomica());
            document.setHistorialAcademico(solicitud.getHistorialAcademico());
            document.setDocumentos(documentos);
            document.setFecha(solicitud.getFecha());
            document.setEstado(solicitud.getEstado());
            return document;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Solicitud a Document");
        }
    }
    public static List<SolicitudDTO> toDTO(List<Solicitud> solicitudes) {
        List<SolicitudDTO> listaDTOs = new ArrayList<>();
        if (solicitudes != null) {
            for (Solicitud solicitud : solicitudes) {
                listaDTOs.add(toDTO(solicitud));
            }
        }

        return listaDTOs;
    }
}
