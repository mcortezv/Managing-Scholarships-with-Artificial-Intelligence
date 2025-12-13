package fachadas;
import controles.ControlInicioSesion;
import interfaces.IFachadaInicioSesion;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.LoginDTO;


/**
 * The type Fachada inicio sesion.
 */
public class FachadaInicioSesion implements IFachadaInicioSesion {
    /**
     * The Control inicio sesion.
     */
    public ControlInicioSesion controlInicioSesion;

    /**
     * Instantiates a new Fachada inicio sesion.
     *
     * @param controlInicioSesion the control inicio sesion
     */
    public FachadaInicioSesion(ControlInicioSesion controlInicioSesion) {
        this.controlInicioSesion = controlInicioSesion;
    }

    @Override
    public boolean solicitarLogin(LoginDTO solicitudLoginDTO) {
        return controlInicioSesion.solicitarLogin(solicitudLoginDTO);
    }

    @Override
    public void solicitarLogOut() {
        controlInicioSesion.cerrarSesion();
    }

    public EstudianteDTO getEstudianteLogueado() {
        return controlInicioSesion.getEstudianteLogueado();
    }
}
