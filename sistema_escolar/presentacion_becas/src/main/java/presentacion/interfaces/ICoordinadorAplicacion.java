package presentacion.interfaces;
import itson.LoginDTOItson;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.login.exceptions.ContraseniaInvalidaException;
import presentacion.login.exceptions.IDInvalidoException;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.solicitarBeca.exceptions.*;
import solicitarBeca.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public interface ICoordinadorAplicacion {
    void iniciarGUI();
    boolean intentarIniciarSesion(LoginDTOItson loginDTO) throws IDInvalidoException, ContraseniaInvalidaException;
    void cerrarSesion();
    void guardarInfoEstudiante(LoginDTOItson loginDTOItson);
    EstudianteDTO getEstudianteLogueado();
    void main();
    void mostrarMainFrame();
    void solicitarBeca();
    void pagarAdeudo();
    void actividades();
    void setCoordinadorAplicacionPagarAdeudo(CoordinadorAplicacionPagarAdeudo c);
    void setCoordinadorAplicacionActividades(CoordinadorAplicacionActividades c);
    void procesarInformacionGeneral(RequisitosDTO requisitosDTO) throws PromedioInvalidoException, IngresoInvalidoException;
    void mostrarBecasDisponibles(BecasFiltradasDTO becasFiltradasDTO);
    void setBecaSeleccionadaDTO(BecaDTO becaDTO);
    void mostrarBecaSeleccionada();
    void iniciarSolicitud();
    void procesarDatosSolicitante() throws NombresInvalidosException, DireccionInvalidaException, TelefonoInvalidoException, IDInvalidoException;
    void mostrarPanelHitorialAcademico();
    void procesarHistorialAcademico(HistorialAcademicoDTO historialAcademicoDTO);
    void procesarDatosTutor(TutorDTO tutorDTO) throws NombresInvalidosException, ApellidoInvalidoException, TelefonoInvalidoException, IDInvalidoException;
    void procesarInformacionSocioeconomica(InformacionSocioeconomicaDTO infoSocioeconomica);
    void procesarDocumentos(Map<String, File> documentosCargados) throws IOException;
    void mostrarResumen();
    void enviarSolicitudAGobierno();
}