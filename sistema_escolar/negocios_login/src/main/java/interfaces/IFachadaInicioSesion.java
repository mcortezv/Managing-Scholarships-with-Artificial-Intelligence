package interfaces;
import itson.LoginDTOItson;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.LoginDTO;

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
    boolean solicitarLogin(LoginDTO solicitudLoginDTO);

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
