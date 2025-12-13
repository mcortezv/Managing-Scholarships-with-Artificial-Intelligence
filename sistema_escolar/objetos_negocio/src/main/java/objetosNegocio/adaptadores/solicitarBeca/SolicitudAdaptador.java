/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.adaptadores.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.SolicitudAdaptadorException;
import solicitarBeca.DocumentoDTO;
import solicitarBeca.SolicitudDTO;
import gobierno.DocumentoDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import solicitarBeca.dominio.Documento;
import solicitarBeca.dominio.Estudiante;
import solicitarBeca.dominio.Solicitud;
import solicitarBeca.dominio.enums.EstadoSolicitud;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Solicitud adaptador.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudAdaptador {

    /**
     * To dto solicitud dto.
     *
     * @param solicitud the solicitud
     * @return the solicitud dto
     */
    public static SolicitudDTO toDTO(Solicitud solicitud) {
        try {
            SolicitudDTO dto = new SolicitudDTO();
            dto.setId(solicitud.getId());
            dto.setBeca(BecaAdaptador.toDTO(solicitud.getBeca()));
            if (solicitud.getEstudiante() != null) {
                dto.setEstudiante(EstudianteAdaptador.toDTO(solicitud.getEstudiante()));
            }
            if (solicitud.getInformacionSocioeconomica() != null) {
                dto.setInformacionSocioeconomica(InformacionSocioeconomicaAdaptador.toDTO(solicitud.getInformacionSocioeconomica()));
            }
            if (solicitud.getHistorialAcademico() != null) {
                dto.setHistorialAcademico(HistorialAcademicoAdaptador.toDTO(solicitud.getHistorialAcademico()));
            }
            if (solicitud.getDocumentos() != null) {
                ArrayList<DocumentoDTO> documentosDTO = new ArrayList<>();
                for (Documento documento : solicitud.getDocumentos()) {
                    documentosDTO.add(DocumentoAdaptador.toDTO(documento));
                }
                dto.setDocumentos(documentosDTO);
            }
            dto.setFecha(solicitud.getFecha());
            dto.setEstado(solicitud.getEstado().toString());
            return dto;
        } catch (Exception ex) {
            throw new SolicitudAdaptadorException("Error al convertir entidad Solicitud a SolicitudDTO");
        }
    }

    /**
     * To dto gobierno solicitud dto gobierno.
     *
     * @param solicitud the solicitud
     * @return the solicitud dto gobierno
     */
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
            throw new SolicitudAdaptadorException("Error al convertir entidad Solicitud a SolicitudDTOGobierno");
        }
    }

    /**
     * To entity solicitud.
     *
     * @param dto the dto
     * @return the solicitud
     */
    public static Solicitud toEntity(SolicitudDTO dto) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setId(dto.getId());
            solicitud.setBeca(BecaAdaptador.toEntity(dto.getBeca()));
            Estudiante estudiante = EstudianteAdaptador.toEntity(dto.getEstudiante());
            solicitud.setEstudiante(estudiante);
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
            throw new BecasFiltradasAdaptadorException("Error al convertir SolicitudDTO a entidad Solicitud");
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
