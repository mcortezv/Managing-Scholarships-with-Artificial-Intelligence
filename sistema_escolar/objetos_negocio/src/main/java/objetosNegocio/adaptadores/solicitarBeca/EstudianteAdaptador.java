package objetosNegocio.adaptadores.solicitarBeca;
import gobierno.TutorDTOGobierno;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.EstudianteAdaptadorException;
import solicitarBeca.EstudianteDTO;
import gobierno.EstudianteDTOGobierno;
import itson.EstudianteDTOItson;
import solicitarBeca.dominio.Estudiante;
import solicitarBeca.dominio.Tutor;
import solicitarBeca.dominio.enums.Carrera;
import solicitarBeca.dominio.enums.Parentesco;

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
            if (dto.getTutor() != null) {
                Tutor tutor = new Tutor();
                tutor.setNombre(dto.getTutor().getNombre());
                tutor.setParentesco(Parentesco.valueOf(dto.getTutor().getParentesco()));
                tutor.setCorreo(dto.getTutor().getCorreo());
                tutor.setTelefono(dto.getTutor().getTelefono());
                tutor.setDireccion(dto.getTutor().getDireccion());
                estudiante.setTutor(tutor);
            }
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
            if (estudiante.getTutor() != null) {
                TutorDTOGobierno tutor = new TutorDTOGobierno();
                tutor.setId(estudiante.getTutor().getId());
                tutor.setNombre(estudiante.getTutor().getNombre());
                tutor.setDireccion(estudiante.getTutor().getDireccion());
                tutor.setCorreo(estudiante.getTutor().getCorreo());
                tutor.setTelefono(estudiante.getTutor().getTelefono());
                tutor.setParentesco(estudiante.getTutor().getParentesco().toString());
                dto.setTutor(tutor);
            }
            dto.setCarrera(estudiante.getCarrera().toString());
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setCorreo(estudiante.getCorreo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Estudiante a EstudianteDTOGobierno");
        }
    }

    /**
     * Convierte un DTO de Gobierno a una Entidad de Negocio
     */
    public static Estudiante toEntity(EstudianteDTOGobierno dto) {
        try {
            if (dto == null) return null;

            Estudiante estudiante = new Estudiante();
            estudiante.setMatricula(dto.getMatricula());
            estudiante.setNombre(dto.getNombre());
            if (dto.getCarrera() != null) {
                estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            }

            estudiante.setTelefono(dto.getTelefono());
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setCorreo(dto.getCorreo());

            if (dto.getTutor() != null) {
                Tutor tutor = new Tutor();
                tutor.setNombre(dto.getTutor().getNombre());
                tutor.setDireccion(dto.getTutor().getDireccion());
                tutor.setCorreo(dto.getTutor().getCorreo());
                tutor.setTelefono(dto.getTutor().getTelefono());
                if (dto.getTutor().getParentesco() != null) {
                    tutor.setParentesco(Parentesco.valueOf(dto.getTutor().getParentesco()));
                }

                estudiante.setTutor(tutor);
            }

            return estudiante;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir EstudianteDTOGobierno a entidad Estudiante: " + ex.getMessage());
        }
    }
}
