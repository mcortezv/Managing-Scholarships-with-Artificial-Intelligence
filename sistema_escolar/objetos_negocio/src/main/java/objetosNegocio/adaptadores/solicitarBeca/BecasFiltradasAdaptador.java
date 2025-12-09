package objetosNegocio.adaptadores.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import solicitarBeca.BecaDTO;
import gobierno.BecaDTOGobierno;
import solicitarBeca.dominio.Beca;
import solicitarBeca.dominio.BecasFiltradas;
import gobierno.BecasDisponiblesDTOGobierno;
import solicitarBeca.BecasFiltradasDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Becas filtradas adaptador.
 *
 * @author Cortez, Manuel;
 */
public class BecasFiltradasAdaptador {

    /**
     * To entity becas filtradas.
     *
     * @param dto the dto
     * @return the becas filtradas
     */
    public static BecasFiltradas toEntity(BecasDisponiblesDTOGobierno dto) {
        try {
            BecasFiltradas becasFiltradas = new BecasFiltradas();
            List<Beca> becas = new ArrayList<>();
            for (BecaDTOGobierno becaResponseDTO : dto.getBecas()) {
                becas.add(BecaAdaptador.toEntity(becaResponseDTO));
            }
            becasFiltradas.setBecas(becas);
            return becasFiltradas;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir BecasDisponiblesDTOGobierno a entidad BecasFiltradas");
        }
    }

    /**
     * To entity becas filtradas.
     *
     * @param dto the dto
     * @return the becas filtradas
     */
    public static BecasFiltradas toEntity(BecasFiltradasDTO dto) {
        try {
            BecasFiltradas becasFiltradas = new BecasFiltradas();
            List<Beca> becas = new ArrayList<>();
            for (BecaDTO becaResponseDTO : dto.getBecas()) {
                becas.add(BecaAdaptador.toEntity(becaResponseDTO));
            }
            becasFiltradas.setBecas(becas);
            return becasFiltradas;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir BecasFiltradasDTO a entidad BecasFiltradas");
        }
    }

    /**
     * To dto becas filtradas dto.
     *
     * @param becasFiltradas the becas filtradas
     * @return the becas filtradas dto
     */
    public static BecasFiltradasDTO toDTO(BecasFiltradas becasFiltradas) {
        try {
            BecasFiltradasDTO becasFiltradasDTO = new BecasFiltradasDTO();
            List<BecaDTO> becas = new ArrayList<>();
            for (Beca beca : becasFiltradas.getBecas()) {
                becasFiltradasDTO.getBecas().add(BecaAdaptador.toDTO(beca));
            }
            becasFiltradasDTO.setBecas(becas);
            return becasFiltradasDTO;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad BecasFiltradas a BecasFiltradasDTO");
        }
    }

    /**
     * To dto gobierno becas disponibles dto gobierno.
     *
     * @param becasFiltradas the becas filtradas
     * @return the becas disponibles dto gobierno
     */
    public static BecasDisponiblesDTOGobierno toDTOGobierno(BecasFiltradas becasFiltradas) {
        try {
            BecasDisponiblesDTOGobierno becasFiltradasDTO = new BecasDisponiblesDTOGobierno();
            List<BecaDTOGobierno> becas = new ArrayList<>();
            for (Beca beca : becasFiltradas.getBecas()) {
                becasFiltradasDTO.getBecas().add(BecaAdaptador.toDTOGobierno(beca));
            }
            becasFiltradasDTO.setBecas(becas);
            return becasFiltradasDTO;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad BecasFiltradas a BecasDisponiblesDTOGobierno");
        }
    }

    /**
     * To dto becas filtradas dto.
     *
     * @param becasFiltradas the becas filtradas
     * @return the becas filtradas dto
     */
    public static BecasFiltradasDTO toDTO(BecasDisponiblesDTOGobierno becasFiltradas) {
        try {
            BecasFiltradasDTO becasFiltradasDTO = new BecasFiltradasDTO();
            List<BecaDTO> becas = new ArrayList<>();
            for (BecaDTOGobierno beca : becasFiltradas.getBecas()) {
                becas.add(BecaAdaptador.toDTO(beca));
            }
            becasFiltradasDTO.setBecas(becas);
            return becasFiltradasDTO;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir BecasDisponiblesDTOGobierno a BecasFiltradasDTO");
        }
    }
}
