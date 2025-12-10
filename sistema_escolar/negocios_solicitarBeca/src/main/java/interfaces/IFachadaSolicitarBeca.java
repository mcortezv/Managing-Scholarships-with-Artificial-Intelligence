/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import objetosNegocio.bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import solicitarBeca.*;
import java.util.List;

/**
 * The interface Fachada solicitar beca.
 *
 * @author janethcristinagalvanquinonez
 */
public interface IFachadaSolicitarBeca {

    /**
     * Obtener becas filtradas becas filtradas dto.
     *
     * @param requisitos the requisitos
     * @return the becas filtradas dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    BecasFiltradasDTO obtenerBecasFiltradas(RequisitosDTO requisitos) throws SolicitudInvalidaException;

    /**
     * Sets beca.
     *
     * @param becaActual the beca actual
     */
    void setBeca(BecaDTO becaActual);

    /**
     * Obtener beca por id beca dto.
     *
     * @param id the id
     * @return the beca dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    BecaDTO obtenerBecaPorId(Long id) throws SolicitudInvalidaException;

    /**
     * Iniciar nueva solicitud.
     *
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void iniciarNuevaSolicitud() throws SolicitudInvalidaException;

    /**
     * Obtener estudiante estudiante dto.
     *
     * @param estudianteDTO the estudiante dto
     * @return the estudiante dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    EstudianteDTO obtenerEstudiante(EstudianteDTO estudianteDTO) throws SolicitudInvalidaException;

    /**
     * Sets historial academico.
     *
     * @param historialAcademicoDTO the historial academico dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void setHistorialAcademico(HistorialAcademicoDTO historialAcademicoDTO) throws SolicitudInvalidaException;

    /**
     * Sets datos tutor.
     *
     * @param tutor the tutor
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void setDatosTutor(TutorDTO tutor) throws SolicitudInvalidaException;

    /**
     * Sets informacion socioeconomica.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void setInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomica) throws SolicitudInvalidaException;

    /**
     * Sets documentos.
     *
     * @param documentos the documentos
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void setDocumentos(List<DocumentoDTO> documentos) throws SolicitudInvalidaException;

    /**
     * Obtener solicitud actual solicitud dto.
     *
     * @return the solicitud dto
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    SolicitudDTO obtenerSolicitudActual() throws SolicitudInvalidaException;

    /**
     * Guardar solicitud boolean.
     *
     * @return the boolean
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    boolean guardarSolicitud() throws SolicitudInvalidaException;

    /**
     * Cancelar solicitud.
     *
     * @throws SolicitudInvalidaException the solicitud invalida exception
     */
    void cancelarSolicitud() throws SolicitudInvalidaException;
}
