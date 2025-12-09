/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import adaptadores.solicitarBeca.EstudianteAdaptador;
import controles.ControlSolicitarBeca;
import bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import interfaces.IFachadaSolicitarBeca;
import solicitarBeca.*;
import solicitarBeca.dominio.*;
import java.util.List;

/**
 * FACHADA
 * 
 * @author janethcristinagalvanquinonez
 */
public class FachadaSolicitarBeca implements IFachadaSolicitarBeca {
    private final ControlSolicitarBeca controlSolicitud;
    private Solicitud solicitudActual;
    private Estudiante estudianteActual;

    public FachadaSolicitarBeca(ControlSolicitarBeca gestor) {
        this.controlSolicitud = gestor;
    }

    public BecasFiltradasDTO obtenerBecasFiltradas(RequisitosDTO requisitos) throws SolicitudInvalidaException {
        return controlSolicitud.obtenerBecasFiltradas(requisitos);
    }

    @Override
    public BecaDTO obtenerBecaPorId(Long id) throws SolicitudInvalidaException {
        return controlSolicitud.obtenerBecaPorId(id);
    }

    @Override
    public void iniciarNuevaSolicitud() throws SolicitudInvalidaException {
        controlSolicitud.iniciarSolicitud();
    }

    @Override
    public void setBeca(BecaDTO becaActual) {
        controlSolicitud.setBecaActual(becaActual);
    }

    @Override
    public EstudianteDTO obtenerEstudiante(EstudianteDTO estudianteDTO) throws SolicitudInvalidaException {
        EstudianteDTO estudiantDTO = controlSolicitud.obtenerEstudiante(estudianteDTO.getMatricula());
        estudianteActual = EstudianteAdaptador.toEntity(estudiantDTO);
        return estudiantDTO;
    }

    @Override
    public void setHistorialAcademico(HistorialAcademicoDTO historialAcademicoDTO) throws SolicitudInvalidaException {
        controlSolicitud.asignarHistorial(historialAcademicoDTO);
    }

    @Override
    public void setDatosTutor(TutorDTO tutor) throws SolicitudInvalidaException {
        controlSolicitud.asignarTutor(tutor);
    }

    @Override
    public void setInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomica)
            throws SolicitudInvalidaException {
        controlSolicitud.setInformacionSocioeconomica(informacionSocioeconomica);
    }

    @Override
    public void setDocumentos(List<DocumentoDTO> documentos) throws SolicitudInvalidaException {
        controlSolicitud.asignarDocumentos(documentos);
    }

    @Override
    public SolicitudDTO obtenerSolicitudActual() throws SolicitudInvalidaException {
        return controlSolicitud.obtenerSolicitudActual();
    }

    @Override
    public boolean guardarSolicitud() throws SolicitudInvalidaException {
        return controlSolicitud.guardarSolicitud();
    }

    @Override
    public void cancelarSolicitud() throws SolicitudInvalidaException {
        controlSolicitud.cancelarSolicitud();
    }
}
