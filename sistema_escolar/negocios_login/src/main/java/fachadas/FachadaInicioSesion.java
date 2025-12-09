package fachadas;
import controles.ControlInicioSesion;
import itson.LoginDTOItson;
import interfaces.IFachadaInicioSesion;
import solicitarBeca.EstudianteDTO;


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
    public boolean solicitarLogin(LoginDTOItson solicitudLoginDTO) {
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
