package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Tutor;
import datosGobierno.dominioGobierno.enums.Parentesco;
import dtoGobierno.TutorDTO;
import gobierno.TutorDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.TutorAdaptadorException;

/**
 * The type Tutor adaptador.
 *
 * @author Cortez, Manuel;
 */
public class TutorAdaptador {

    /**
     * To entity tutor.
     *
     * @param dto the dto
     * @return the tutor
     */
    public static Tutor toEntity(TutorDTO dto){
        try {
            Tutor tutor = new Tutor();
            tutor.setId(dto.getId());
            tutor.setNombre(dto.getNombre());
            tutor.setParentesco(Parentesco.valueOf(dto.getParentesco()));
            tutor.setTelefono(dto.getTelefono());
            tutor.setDireccion(dto.getDireccion());
            tutor.setCorreo(dto.getCorreo());
            return tutor;
        } catch (Exception sinUso){
            throw new TutorAdaptadorException("No se pudo mappear el TutorDTO a entidad Tutor");
        }
    }

    /**
     * To entity tutor.
     *
     * @param dto the dto
     * @return the tutor
     */
    public static Tutor toEntity(TutorDTOGobierno dto){
        try {
            Tutor tutor = new Tutor();
            tutor.setId(dto.getId());
            tutor.setNombre(dto.getNombre());
            tutor.setParentesco(Parentesco.valueOf(dto.getParentesco()));
            tutor.setTelefono(dto.getTelefono());
            tutor.setDireccion(dto.getDireccion());
            tutor.setCorreo(dto.getCorreo());
            return tutor;
        } catch (Exception sinUso){
            throw new TutorAdaptadorException("No se pudo mappear la TutorDTOGobierno a entidad Tutor");
        }
    }

    /**
     * To dto tutor dto.
     *
     * @param tutor the tutor
     * @return the tutor dto
     */
    public static TutorDTO toDTO(Tutor tutor){
        try {
            TutorDTO dto = new TutorDTO();
            dto.setId(tutor.getId());
            dto.setNombre(tutor.getNombre());
            dto.setParentesco(tutor.getParentesco().toString());
            dto.setTelefono(tutor.getTelefono());
            dto.setDireccion(tutor.getDireccion());
            dto.setCorreo(tutor.getCorreo());
            return dto;
        } catch (Exception sinUso){
            throw new TutorAdaptadorException("No se pudo mappear la entidad Tutor a TutorDTO");
        }
    }

    /**
     * To infraestructura dto tutor dto gobierno.
     *
     * @param tutor the tutor
     * @return the tutor dto gobierno
     */
    public static TutorDTOGobierno toInfraestructuraDTO(Tutor tutor){
        try {
            TutorDTOGobierno dto = new TutorDTOGobierno();
            dto.setId(tutor.getId());
            dto.setNombre(tutor.getNombre());
            dto.setParentesco(tutor.getParentesco().toString());
            dto.setTelefono(tutor.getTelefono());
            dto.setDireccion(tutor.getDireccion());
            dto.setCorreo(tutor.getCorreo());
            return dto;
        } catch (Exception sinUso){
            throw new TutorAdaptadorException("No se pudo mappear la entidad Tutor a TutorDTOGobierno");
        }
    }
}
