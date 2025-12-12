package presentacion.interfaces;

import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.login.exceptions.ContraseniaInvalidaException;
import presentacion.login.exceptions.IDInvalidoException;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.solicitarBeca.exceptions.*;
import solicitarBeca.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;


/**
 * The interface Coordinador aplicacion.
 *
 * @autor Cortez, Manuel;
 */
public interface ICoordinadorAplicacion {
    /**
     * Iniciar gui.
     */
    void iniciarGUI();
    boolean intentarIniciarSesion(LoginDTO loginDTO) throws IDInvalidoException, ContraseniaInvalidaException;
    void cerrarSesion();
    void guardarInfoEstudiante(LoginDTO loginDTO);
    EstudianteDTO getEstudianteLogueado();

    /**
     * Main.
     */
    void main();

    /**
     * Mostrar main frame.
     */
    void mostrarMainFrame();

    /**
     * Solicitar beca.
     */
    void solicitarBeca();

    /**
     * Pagar adeudo.
     */
    void pagarAdeudo();

    /**
     * Actividades.
     */
    void actividades();

    /**
     * Sets coordinador aplicacion pagar adeudo.
     *
     * @param c the c
     */
    void setCoordinadorAplicacionPagarAdeudo(CoordinadorAplicacionPagarAdeudo c);

    /**
     * Sets coordinador aplicacion actividades.
     *
     * @param c the c
     */
    void setCoordinadorAplicacionActividades(CoordinadorAplicacionActividades c);

    /**
     * Procesar informacion general.
     *
     * @param requisitosDTO the requisitos dto
     * @throws PromedioInvalidoException the promedio invalido exception
     * @throws IngresoInvalidoException  the ingreso invalido exception
     */
    void procesarInformacionGeneral(RequisitosDTO requisitosDTO) throws PromedioInvalidoException, IngresoInvalidoException;

    /**
     * Mostrar becas disponibles.
     *
     * @param becasFiltradasDTO the becas filtradas dto
     */
    void mostrarBecasDisponibles(BecasFiltradasDTO becasFiltradasDTO);

    /**
     * Sets beca seleccionada dto.
     *
     * @param becaDTO the beca dto
     */
    void setBecaSeleccionadaDTO(BecaDTO becaDTO);

    /**
     * Mostrar beca seleccionada.
     */
    void mostrarBecaSeleccionada();

    /**
     * Iniciar solicitud.
     */
    void iniciarSolicitud();

    /**
     * Procesar datos solicitante.
     *
     * @throws NombresInvalidosException  the nombres invalidos exception
     * @throws DireccionInvalidaException the direccion invalida exception
     * @throws TelefonoInvalidoException  the telefono invalido exception
     * @throws IDInvalidoException        the id invalido exception
     */
    void procesarDatosSolicitante() throws NombresInvalidosException, DireccionInvalidaException, TelefonoInvalidoException, IDInvalidoException;

    /**
     * Mostrar panel hitorial academico.
     */
    void mostrarPanelHitorialAcademico();

    /**
     * Procesar historial academico.
     *
     * @param historialAcademicoDTO the historial academico dto
     */
    void procesarHistorialAcademico(HistorialAcademicoDTO historialAcademicoDTO);

    /**
     * Procesar datos tutor.
     *
     * @param tutorDTO the tutor dto
     * @throws NombresInvalidosException the nombres invalidos exception
     * @throws ApellidoInvalidoException the apellido invalido exception
     * @throws TelefonoInvalidoException the telefono invalido exception
     * @throws IDInvalidoException       the id invalido exception
     */
    void procesarDatosTutor(TutorDTO tutorDTO) throws NombresInvalidosException, ApellidoInvalidoException, TelefonoInvalidoException, IDInvalidoException;

    /**
     * Procesar informacion socioeconomica.
     *
     * @param infoSocioeconomica the info socioeconomica
     */
    void procesarInformacionSocioeconomica(InformacionSocioeconomicaDTO infoSocioeconomica);

    /**
     * Procesar documentos.
     *
     * @param documentosCargados the documentos cargados
     * @throws IOException the io exception
     */
    void procesarDocumentos(Map<String, File> documentosCargados) throws IOException;

    /**
     * Mostrar resumen.
     */
    void mostrarResumen();

    /**
     * Enviar solicitud a gobierno.
     */
    void enviarSolicitudAGobierno();
}
