package objetosNegocio.bo.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.EstudianteAdaptador;
import itson.LoginDTOItson;
import objetosNegocio.adaptadores.solicitarBeca.LoginAdaptador;
import objetosNegocio.bo.sesion.SesionUsuario;
import objetosNegocio.bo.solicitarBeca.excepciones.EstudianteInvalidoException;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.LoginDTO;
import solicitarBeca.repository.dao.interfaces.IEstudianteDAO;
import itson.EstudianteDTOItson;
import objetosNegocio.bo.solicitarBeca.intefaces.IEstudianteBO;
import interfaces.IFachadaITSON;

/**
 * The type Estudiante bo.
 *
 * @author Cortez, Manuel;
 */
public class EstudianteBO implements IEstudianteBO {
    private final IFachadaITSON fachadaITSON;
    private final IEstudianteDAO estudianteDAO;

    /**
     * Instantiates a new Estudiante bo.
     *
     * @param fachadaITSON  the fachada itson
     * @param estudianteDAO the estudiante dao
     */
    public EstudianteBO(IFachadaITSON fachadaITSON, IEstudianteDAO estudianteDAO){
        this.fachadaITSON = fachadaITSON;
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public boolean iniciarSesion(LoginDTO solicitudLoginDTO) {
        try {
            boolean credencialesCorrectas = fachadaITSON.verificarLogin(LoginAdaptador.aLoginDTOItson(solicitudLoginDTO));
            if (credencialesCorrectas) {
                Long matricula = solicitudLoginDTO.getUsuario();
                EstudianteDTOItson estudianteCompleto = fachadaITSON.verificarEstudiante(matricula);
                if (estudianteCompleto == null) {
                    return false;
                }
                SesionUsuario.getInstance().setEstudianteLogueado(EstudianteAdaptador.toDTO(estudianteCompleto));
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new EstudianteInvalidoException(ex.getMessage());
        }
    }

    @Override
    public void cerrarSesion() {
        SesionUsuario.getInstance().limpiarSesion();
    }

    @Override
    public EstudianteDTO crearEstudiante(Long matricula){
        return EstudianteAdaptador.toDTO(fachadaITSON.verificarEstudiante(matricula));
    }
}
