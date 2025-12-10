package presentacion;

import itson.LoginDTOItson;
import interfaces.*;
import objetosNegocio.bo.solicitarBeca.excepciones.SolicitudInvalidaException;
import presentacion.interfaces.ICoordinadorNegocio;
import solicitarBeca.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Cortez, Manuel;
 */
public class CoordinadorNegocio implements ICoordinadorNegocio {

    private final IFachadaInicioSesion fachadaInicioSesion;
    private final IFachadaSolicitarBeca fachadaSolicitarBeca;

    public CoordinadorNegocio(IFachadaInicioSesion fachadaInicioSesion, IFachadaSolicitarBeca fachadaSolicitarBeca) {
        this.fachadaInicioSesion = fachadaInicioSesion;
        this.fachadaSolicitarBeca = fachadaSolicitarBeca;
    }

    @Override
    public boolean solicitarInicioSesion(LoginDTOItson solicitudLoginDTO) {
        return fachadaInicioSesion.solicitarLogin(solicitudLoginDTO);
    }

    @Override
    public void solicitarCerrarSesion() {
        fachadaInicioSesion.solicitarLogOut();
    }

    @Override
    public EstudianteDTO getEstudianteLogueado() {
        return fachadaInicioSesion.getEstudianteLogueado();
    }

    @Override
    public BecasFiltradasDTO obtenerBecasDisponibles(RequisitosDTO requisitosDTO) {
        return fachadaSolicitarBeca.obtenerBecasFiltradas(requisitosDTO);
    }

    @Override
    public void iniciarSolicitud(BecaDTO becaDTO) throws SolicitudInvalidaException {
        fachadaSolicitarBeca.setBeca(becaDTO);
        fachadaSolicitarBeca.iniciarNuevaSolicitud();
    }

    @Override
    public EstudianteDTO procesarEstudiante(EstudianteDTO estudianteDTO) {
        return fachadaSolicitarBeca.obtenerEstudiante(estudianteDTO);
    }

    @Override
    public void procesarHistorialAcademico(HistorialAcademicoDTO historialAcademicDTO) {
        fachadaSolicitarBeca.setHistorialAcademico(historialAcademicDTO);
    }

    @Override
    public void procesarTutor(TutorDTO tutorDTO) {
        fachadaSolicitarBeca.setDatosTutor(tutorDTO);
    }

    @Override
    public void procesarInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomicaDTO) {
        fachadaSolicitarBeca.setInformacionSocioeconomica(informacionSocioeconomicaDTO);
    }

    @Override
    public void procesarDocumentos(Map<String, File> documentosCargados) throws IOException {
        try {
            List<DocumentoDTO> documentoDTOList = new ArrayList<>();
            for (Map.Entry<String, File> entry : documentosCargados.entrySet()) {
                String tipo = entry.getKey();
                File documento = entry.getValue();
                DocumentoDTO documentoDTO = new DocumentoDTO();
                documentoDTO.setIdentificador(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
                documentoDTO.setTipo(tipo);
                documentoDTO.setContenido(Files.readAllBytes(documento.toPath()));
                documentoDTO.setEstudiante(fachadaInicioSesion.getEstudianteLogueado().getMatricula());

                documentoDTOList.add(documentoDTO);
            }
            fachadaSolicitarBeca.setDocumentos(documentoDTOList);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar documentos: " + e.getMessage(), e);
        }
    }

    @Override
    public SolicitudDTO getSolicitudActual() {
        return fachadaSolicitarBeca.obtenerSolicitudActual();
    }

    @Override
    public boolean enviarSolicitudAGobierno(){
        return fachadaSolicitarBeca.guardarSolicitud();
    }
}