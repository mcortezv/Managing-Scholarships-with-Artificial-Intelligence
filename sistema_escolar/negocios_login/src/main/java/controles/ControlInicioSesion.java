package controles;
import objetosNegocio.bo.sesion.SesionUsuario;
import itson.LoginDTOItson;
import objetosNegocio.bo.solicitarBeca.intefaces.IEstudianteBO;
import solicitarBeca.EstudianteDTO;

/**
 * The type Control inicio sesion.
 *
 * @author Cortez, Manuel;
 */
public class ControlInicioSesion {
    private final IEstudianteBO estudianteBO;
    private final SesionUsuario sesionUsuario = SesionUsuario.getInstance();

    /**
     * Instantiates a new Control inicio sesion.
     *
     * @param estudianteBO the estudiante bo
     */
    public ControlInicioSesion(IEstudianteBO estudianteBO) {
        this.estudianteBO = estudianteBO;
    }

    /**
     * Solicitar login boolean.
     *
     * @param solicitudLoginDTO the solicitud login dto
     * @return the boolean
     */
    public boolean solicitarLogin(LoginDTOItson solicitudLoginDTO){
        return estudianteBO.iniciarSesion(solicitudLoginDTO);
    }

    /**
     * Cerrar sesion.
     */
    public void cerrarSesion(){
        estudianteBO.cerrarSesion();
    }

    /**
     * Gets estudiante logueado.
     *
     * @return the estudiante logueado
     */
    public EstudianteDTO getEstudianteLogueado() {
        return sesionUsuario.getEstudianteLogeado();
    }
}
