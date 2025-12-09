package objetosNegocio.bo.sesion;
import solicitarBeca.EstudianteDTO;


/**
 * The type Sesion usuario.
 */
public class SesionUsuario {
    private static SesionUsuario instancia;
    private EstudianteDTO estudianteLogeado;

    private SesionUsuario(){}

    /**
     * Get instance sesion usuario.
     *
     * @return the sesion usuario
     */
    public static SesionUsuario getInstance(){
        if(instancia == null){
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    /**
     * Get estudiante logeado estudiante dto.
     *
     * @return the estudiante dto
     */
    public EstudianteDTO getEstudianteLogeado(){
        return estudianteLogeado;
    }

    /**
     * Sets estudiante logueado.
     *
     * @param estudianteLogueado the estudiante logueado
     */
    public void setEstudianteLogueado(EstudianteDTO estudianteLogueado) {
        this.estudianteLogeado = estudianteLogueado;
    }

    /**
     * Limpiar sesion.
     */
    public void limpiarSesion(){
        this.estudianteLogeado = null;
    }

    /**
     * Hay sesion activa boolean.
     *
     * @return the boolean
     */
    public boolean haySesionActiva(){
        return this.estudianteLogeado != null;
    }
}
