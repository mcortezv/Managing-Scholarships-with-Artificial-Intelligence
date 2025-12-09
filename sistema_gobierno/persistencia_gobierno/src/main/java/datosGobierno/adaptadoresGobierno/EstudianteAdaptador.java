package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.EstudianteAdaptadorException;
import datosGobierno.documents.EstudianteDocument;
import datosGobierno.dominioGobierno.Estudiante;
import datosGobierno.dominioGobierno.enums.Carrera;
import dtoGobierno.EstudianteDTO;
import gobierno.EstudianteDTOGobierno;

/**
 * The type Estudiante adaptador.
 */
public class EstudianteAdaptador {

    /**
     * To entity estudiante.
     *
     * @param dto the dto
     * @return the estudiante
     */
    public static Estudiante toEntity(EstudianteDTO dto) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            estudiante.setNombre(dto.getNombre());
            estudiante.setCorreo(dto.getCorreo());
            estudiante.setTelefono(dto.getTelefono());
            if (estudiante.getContrasenia() != null) {
                estudiante.setContrasenia(dto.getContrasenia());
            }
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setMatricula(dto.getMatricula());
            return estudiante;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir EstudianteDTO a entidad Estudiante");
        }
    }

    /**
     * To entity estudiante.
     *
     * @param dto the dto
     * @return the estudiante
     */
    public static Estudiante toEntity(EstudianteDTOGobierno dto) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            estudiante.setNombre(dto.getNombre());
            estudiante.setCorreo(dto.getCorreo());
            estudiante.setTelefono(dto.getTelefono());
            if (estudiante.getContrasenia() != null) {
                estudiante.setContrasenia(dto.getContrasenia());
            }
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setMatricula(dto.getMatricula());
            return estudiante;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir EstudianteDTOGobierno a entidad Estudiante");
        }
    }

    /**
     * To document estudiante document.
     *
     * @param dto the dto
     * @return the estudiante document
     */
    public static EstudianteDocument toDocument(EstudianteDTOGobierno dto) {
        try {
            EstudianteDocument estudiante = new EstudianteDocument();
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            estudiante.setNombre(dto.getNombre());
            estudiante.setCorreo(dto.getCorreo());
            estudiante.setTelefono(dto.getTelefono());
            if (estudiante.getContrasenia() != null) {
                estudiante.setContrasenia(dto.getContrasenia());
            }
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setMatricula(dto.getMatricula());
            return estudiante;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir EstudianteDTOGobierno a entidad EstudianteDocument");
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
            dto.setCarrera(estudiante.getCarrera().toString());
            dto.setNombre(estudiante.getNombre());
            dto.setCorreo(estudiante.getCorreo());
            if (estudiante.getContrasenia() != null) {
                dto.setContrasenia(estudiante.getContrasenia().toString());
            }
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setMatricula(estudiante.getMatricula());
            return dto;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir entidad Estudiante a EstudianteDTO");
        }
    }
}
