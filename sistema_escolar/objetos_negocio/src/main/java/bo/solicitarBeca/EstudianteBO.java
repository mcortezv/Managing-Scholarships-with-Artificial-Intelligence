package bo.solicitarBeca;
import adaptadores.solicitarBeca.EstudianteAdaptador;
import itson.LoginDTOItson;
import bo.sesion.SesionUsuario;
import bo.solicitarBeca.excepciones.EstudianteInvalidoException;
import dto.actividades.EstudianteDTO;
import solicitarBeca.repository.dao.interfaces.IEstudianteDAO;
import itson.EstudianteDTOItson;
import interfaces.solicitarBeca.IEstudianteBO;
import interfaces.IFachadaITSON;
import solicitarBeca.repository.documents.EstudianteDocument;

/**
 *
 * @author Cortez, Manuel;
 */
public class EstudianteBO implements IEstudianteBO {
    private final IFachadaITSON fachadaITSON;
    private final IEstudianteDAO estudianteDAO;

    public EstudianteBO(IFachadaITSON fachadaITSON, IEstudianteDAO estudianteDAO){
        this.fachadaITSON = fachadaITSON;
        this.estudianteDAO = estudianteDAO;
    }


    public boolean iniciarSesion(LoginDTOItson solicitudLoginDTO) {
        try {
            boolean credencialesCorrectas = fachadaITSON.verificarLogin(solicitudLoginDTO);
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
            ex.printStackTrace();
            throw new EstudianteInvalidoException(ex.getMessage());
        }
    }



    public void cerrarSesion() {
        System.out.println("matricula del estudiante logeado: " + SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
        System.out.println("Llamando a cerrar sesión en EstudianteBO");
        SesionUsuario.getInstance().limpiarSesion();
        System.out.println("Sesión cerrada correctamente");
    }

   
    public EstudianteDTOItson crearEstudiante(Long matricula){
        return fachadaITSON.verificarEstudiante(matricula);
    }


    public void guardarEstudiante(EstudianteDocument estudiante) throws EstudianteInvalidoException {
        try {
            estudianteDAO.guardar(estudiante);
        } catch (Exception ex) {
            throw new EstudianteInvalidoException(ex.getMessage());
        }
    }

    @Override
    public EstudianteDTO obtenerEstudiante(EstudianteDTO estudianteDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
