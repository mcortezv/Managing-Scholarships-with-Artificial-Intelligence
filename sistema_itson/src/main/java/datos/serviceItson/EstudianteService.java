package datos.serviceItson;
import datos.excepciones.DaoException;
import itson.LoginDTOItson;
import datos.dominioItson.Estudiante;
import datos.repositoryItson.daoItson.impl.EstudianteDAO;

public class EstudianteService {

    private final EstudianteDAO estudianteDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
    }

    public boolean verificarLogin(LoginDTOItson dto){
        try{
            Estudiante estudiante = estudianteDAO.findByMatricula(dto.getUsuario()).orElse(null);
            if (estudiante == null || estudiante.getContrasenia() == null) {
                return false;
            }
            return estudiante.getContrasenia().equals(dto.getContrasenia());
        }catch (Exception ex){
            throw new DaoException("contraseña no coincide con usuario");
        }
    }
    
    public Estudiante solicitarDatosEstudiante(Long matricula){
        return estudianteDAO.findByMatricula(matricula).orElse(null);
    }
    
    
    
}
