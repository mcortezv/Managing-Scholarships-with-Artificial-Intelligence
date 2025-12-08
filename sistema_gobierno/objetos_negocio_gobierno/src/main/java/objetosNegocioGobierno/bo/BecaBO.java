package objetosNegocioGobierno.bo;
import datosGobierno.adaptadoresGobierno.BecaAdaptador;
import datosGobierno.daoGobierno.interfacesGobierno.IBecaDAO;
import datosGobierno.dominioGobierno.Beca;
import dtoGobierno.BecaDTO;
import objetosNegocioGobierno.bo.interfaces.IBecaBO;
import java.util.ArrayList;
import java.util.List;


public class BecaBO implements IBecaBO {
    private IBecaDAO becaDAO;

    public BecaBO(IBecaDAO becaDAO) {
        this.becaDAO = becaDAO;
    }

    @Override
    public List<BecaDTO> obtenerListadoBecas(){
        List<Beca> becas = becaDAO.obtenerBecasConSolicitudes();
        List<BecaDTO> resultado = new ArrayList<>();
        for (Beca beca : becas) {
            resultado.add(BecaAdaptador.toDTO(beca));
        }
        return resultado;
    }
}
