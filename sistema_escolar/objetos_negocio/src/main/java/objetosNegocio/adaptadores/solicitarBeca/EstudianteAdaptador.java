package objetosNegocio.adaptadores.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.EstudianteAdaptadorException;
import solicitarBeca.EstudianteDTO;
import gobierno.EstudianteDTOGobierno;
import itson.EstudianteDTOItson;
import solicitarBeca.dominio.Estudiante;
import solicitarBeca.dominio.enums.Carrera;

/**
 * The type Estudiante adaptador.
 *
 * @author Cortez, Manuel;
 */
public class EstudianteAdaptador {

    /**
     * To entity estudiante.
     *
     * @param dto the dto
     * @return the estudiante
     */
    public static Estudiante toEntity(EstudianteDTOItson dto) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setMatricula(dto.getMatricula());
            estudiante.setNombre(dto.getNombre());
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            estudiante.setTelefono(dto.getTelefono());
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setCorreo(dto.getCorreo());
            return estudiante;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir EstudianteDTOItson a entidad Estudiante");
        }
    }

    /**
     * To entity estudiante.
     *
     * @param dto the dto
     * @return the estudiante
     */
    public static Estudiante toEntity(EstudianteDTO dto) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setMatricula(dto.getMatricula());
            estudiante.setNombre(dto.getNombre());
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            estudiante.setTelefono(dto.getTelefono());
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setCorreo(dto.getCorreo());
            return estudiante;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir EstudianteDTO a entidad Estudiante");
        }
    }

    /**
     * To dto estudiante dto.
     *
     * @param estudiante the estudiante
     * @return the estudiante dto
     */
    public static EstudianteDTO toDTO(Estudiante estudiante) {
        try {
            EstudianteDTO dto = new EstudianteDTO();
            dto.setMatricula(estudiante.getMatricula());
            dto.setNombre(estudiante.getNombre());
            dto.setTutor(TutorAdaptador.toDTO(estudiante.getTutor()));
            dto.setCarrera(estudiante.getCarrera().toString());
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setCorreo(estudiante.getCorreo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Estudiante a EstudianteDTO");
        }
    }

    /**
     * To dto estudiante dto.
     *
     * @param estudiante the estudiante
     * @return the estudiante dto
     */
    public static EstudianteDTO toDTO(EstudianteDTOItson estudiante) {
        try {
            EstudianteDTO dto = new EstudianteDTO();
            dto.setMatricula(estudiante.getMatricula());
            dto.setNombre(estudiante.getNombre());
            dto.setCarrera(estudiante.getCarrera().toString());
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setCorreo(estudiante.getCorreo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir EstudianteDTOItson a EstudianteDTO");
        }
    }

    /**
     * To dto gobierno estudiante dto gobierno.
     *
     * @param estudiante the estudiante
     * @return the estudiante dto gobierno
     */
    public static EstudianteDTOGobierno toDTOGobierno(Estudiante estudiante) {
        try {
            EstudianteDTOGobierno dto = new EstudianteDTOGobierno();
            dto.setMatricula(estudiante.getMatricula());
            dto.setNombre(estudiante.getNombre());
            dto.setCarrera(estudiante.getCarrera().toString());
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setCorreo(estudiante.getCorreo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Estudiante a EstudianteDTOGobierno");
        }
    }
}
