
package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Estudiante;
import datosGobierno.dominioGobierno.enums.Carrera;
import dtoGobierno.EstudianteDTO;
import gobierno.EstudianteDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.EstudianteAdaptadorException;

/**
 *
 * @author Cortez, Manuel;
 */
public class EstudianteAdaptador {

    public static Estudiante toEntity(EstudianteDTO dto){
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setMatricula(dto.getMatricula());
            estudiante.setNombre(dto.getNombre());
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            if (dto.getTutor() != null) {
                estudiante.setTutor(TutorAdaptador.toEntity(dto.getTutor()));
            }
            estudiante.setTelefono(dto.getTelefono());
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setCorreo(dto.getCorreo());
            return estudiante;
        } catch (Exception sinUso){
            throw new EstudianteAdaptadorException("No se pudo mappear la DTO a Entidad");
        }
    }

    public static Estudiante toEntity(EstudianteDTOGobierno dto){
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setMatricula(dto.getMatricula());
            estudiante.setNombre(dto.getNombre());
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            if (estudiante.getTutor() != null) {
                estudiante.setTutor(TutorAdaptador.toEntity(dto.getTutor()));
            }
            estudiante.setTelefono(dto.getTelefono());
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setCorreo(dto.getCorreo());
            return estudiante;
        } catch (Exception sinUso){
            throw new EstudianteAdaptadorException("No se pudo mappear la DTO de Infraestructura a Entidad");
        }
    }

    public static EstudianteDTO toDTO(Estudiante estudiante){
        try {
            EstudianteDTO dto = new EstudianteDTO();
            dto.setMatricula(estudiante.getMatricula());
            dto.setNombre(estudiante.getNombre());
            dto.setCarrera(estudiante.getCarrera().toString());
            if (estudiante.getTutor() != null) {
                dto.setTutor(TutorAdaptador.toDTO(estudiante.getTutor()));
            }
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setCorreo(estudiante.getCorreo());
            return dto;
        } catch (Exception sinUso){
            throw new EstudianteAdaptadorException("No se pudo mappear la Entidad a DTO");
        }
    }

    public static EstudianteDTOGobierno toInfraestructuraDTO(Estudiante  estudiante){
        try {
            EstudianteDTOGobierno dto = new EstudianteDTOGobierno();
            dto.setMatricula(estudiante.getMatricula());
            dto.setNombre(estudiante.getNombre());
            dto.setCarrera(estudiante.getCarrera().toString());
            if (estudiante.getTutor() != null){
                dto.setTutor(TutorAdaptador.toInfraestructuraDTO(estudiante.getTutor()));
            }
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setCorreo(estudiante.getCorreo());
            return dto;
        } catch (Exception sinUso){
            throw new EstudianteAdaptadorException("No se pudo mappear la Entidad a DTO Infraestructura");
        }
    }
}
