package objetosNegocio.bo.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.BecasFiltradasAdaptador;
import objetosNegocio.adaptadores.solicitarBeca.RequisitosAdaptador;
import solicitarBeca.BecaDTO;
import solicitarBeca.BecasFiltradasDTO;
import solicitarBeca.RequisitosDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import objetosNegocio.bo.solicitarBeca.excepciones.BecaInvalidaException;
import objetosNegocio.bo.solicitarBeca.intefaces.IBecasFiltradasBO;
import interfaces.IFachadaGobierno;
import java.util.Objects;

/**
 * The type Becas filtradas bo.
 *
 * @author Cortez, Manuel;
 */
public class BecasFiltradasBO implements IBecasFiltradasBO {
    private final IFachadaGobierno fachadaGobierno;

    /**
     * Instantiates a new Becas filtradas bo.
     *
     * @param fachadaGobierno the fachada gobierno
     */
    public BecasFiltradasBO(IFachadaGobierno fachadaGobierno) {
        this.fachadaGobierno = fachadaGobierno;
    }

    @Override
    public BecasFiltradasDTO obtenerBecasFiltradas(RequisitosDTO requisitos) throws BecaInvalidaException {
        BecasDisponiblesDTOGobierno becasDisponiblesResponseDTO = fachadaGobierno.obtenerBecas(RequisitosAdaptador.toDTOGobierno(requisitos));
        if (becasDisponiblesResponseDTO == null || becasDisponiblesResponseDTO.getBecas() == null
                || becasDisponiblesResponseDTO.getBecas().isEmpty()) {
            throw new BecaInvalidaException("No existe ninguna beca para estos requisitos");
        }
        return BecasFiltradasAdaptador.toDTO(becasDisponiblesResponseDTO);
    }

    @Override
    public BecaDTO obtenerBecaPorCodigo(Long codigo, BecasFiltradasDTO becasFiltradas) throws BecaInvalidaException {
        for (BecaDTO beca : becasFiltradas.getBecas()) {
            if (Objects.equals(beca.getCodigo(), codigo)) {
                return beca;
            }
        }
        throw new BecaInvalidaException("No existe ninguna beca con ese codigo");
    }
}
