package objetosNegocio.bo.solicitarBeca.intefaces;
import itson.LoginDTOItson;
import objetosNegocio.bo.solicitarBeca.excepciones.EstudianteInvalidoException;
import solicitarBeca.EstudianteDTO;

/**
 * The interface Estudiante bo.
 *
 * @author Cortez, Manuel;
 */
public interface IEstudianteBO {

    /**
     * Iniciar sesion boolean.
     *
     * @param solicitudLoginDTO the solicitud login dto
     * @return the boolean
     */
    boolean iniciarSesion(LoginDTOItson solicitudLoginDTO);

    /**
     * Crear estudiante estudiante dto.
     *
     * @param matricula the matricula
     * @return the estudiante dto
     * @throws EstudianteInvalidoException the estudiante invalido exception
     */
    EstudianteDTO crearEstudiante(Long matricula) throws EstudianteInvalidoException;

    /**
     * Guardar estudiante.
     *
     * @param estudiante the estudiante
     * @throws EstudianteInvalidoException the estudiante invalido exception
     */
    void guardarEstudiante(EstudianteDTO estudiante) throws EstudianteInvalidoException;

    /**
     * Cerrar sesion.
     */
    void cerrarSesion();
}
