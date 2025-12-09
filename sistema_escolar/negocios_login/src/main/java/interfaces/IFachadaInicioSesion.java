package interfaces;
import itson.LoginDTOItson;
import solicitarBeca.EstudianteDTO;

/**
 * The interface Fachada inicio sesion.
 *
 * @author Cortez, Manuel;
 */
public interface IFachadaInicioSesion {

    /**
     * Solicitar login boolean.
     *
     * @param solicitudLoginDTO the solicitud login dto
     * @return the boolean
     */
    boolean solicitarLogin(LoginDTOItson solicitudLoginDTO);

    /**
     * Solicitar log out.
     */
    void solicitarLogOut();

    /**
     * Gets estudiante logueado.
     *
     * @return the estudiante logueado
     */
    EstudianteDTO getEstudianteLogueado();
}
