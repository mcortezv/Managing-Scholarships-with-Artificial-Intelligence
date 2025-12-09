package objetosNegocioGobierno.bo;
import datosGobierno.adaptadoresGobierno.BecaAdaptador;
import datosGobierno.daoGobierno.interfacesGobierno.IBecaDAO;
import datosGobierno.dominioGobierno.Beca;
import dtoGobierno.BecaDTO;
import objetosNegocioGobierno.bo.excepciones.BecaBOException;
import objetosNegocioGobierno.bo.interfaces.IBecaBO;
import java.util.ArrayList;
import java.util.List;

/**
 * Business Object corregido para gestión de becas
 *
 * @author Cortez, Manuel
 */
public class BecaBO implements IBecaBO {
    private final IBecaDAO becaDAO;

    /**
     * Instantiates a new Beca bo.
     *
     * @param becaDAO the beca dao
     */
    public BecaBO(IBecaDAO becaDAO) {
        if (becaDAO == null) {
            throw new IllegalArgumentException("BecaDAO no puede ser nulo");
        }
        this.becaDAO = becaDAO;
    }

    @Override
    public List<BecaDTO> obtenerListadoBecas() {
        try {
            List<Beca> becas = becaDAO.obtenerBecasConSolicitudes();

            if (becas == null || becas.isEmpty()) {
                throw new BecaBOException("No hay becas con solicitudes disponibles para evaluación");
            }

            List<BecaDTO> resultado = new ArrayList<>();
            for (Beca beca : becas) {
                BecaDTO dto = BecaAdaptador.toDTO(beca);
                resultado.add(dto);
            }

            if (resultado.isEmpty()) {
                throw new BecaBOException("No hay becas con solicitudes disponibles");
            }

            return resultado;

        } catch (BecaBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BecaBOException("Error al obtener listado de becas: " + ex.getMessage());
        }
    }
}