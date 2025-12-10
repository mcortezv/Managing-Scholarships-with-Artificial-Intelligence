package objetosNegocio.adaptadores.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.TutorAdaptadorException;
import solicitarBeca.TutorDTO;
import solicitarBeca.dominio.Tutor;
import solicitarBeca.dominio.enums.Parentesco;

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
    public static Tutor toEntity(TutorDTO dto) {
        try {
            Tutor tutor = new Tutor();
            tutor.setNombre(dto.getNombre());
            tutor.setParentesco(Parentesco.valueOf(dto.getParentesco()));
            tutor.setTelefono(dto.getTelefono());
            tutor.setDireccion(dto.getDireccion());
            tutor.setCorreo(dto.getCorreo());
            return tutor;
        } catch (Exception ex) {
            throw new TutorAdaptadorException("Error al convertir TutorDTO a entidad Tutor");
        }
    }

    /**
     * To dto tutor dto.
     *
     * @param tutor the tutor
     * @return the tutor dto
     */
    public static TutorDTO toDTO(Tutor tutor) {
        try {
            TutorDTO dto = new TutorDTO();
            dto.setNombre(tutor.getNombre());
            dto.setParentesco(tutor.getParentesco().toString());
            dto.setTelefono(tutor.getTelefono());
            dto.setDireccion(tutor.getDireccion());
            dto.setCorreo(tutor.getCorreo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad Tutor a TutorDTO");
        }
    }
}
