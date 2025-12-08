package datosGobierno.daoGobierno;
import datosGobierno.daoGobierno.excepcionesGobierno.ResolucionDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.IResolucionDAO;
import datosGobierno.dominioGobierno.Resolucion;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class ResolucionDAO implements IResolucionDAO {

    @Override
    public boolean guardar(Resolucion resolucion){
        try {
            return true;
        } catch (Exception sinUso){
            throw new ResolucionDAOException("Error al guardar la resolucion");
        }
    }

    @Override
    public boolean actualizar(Resolucion resolucion){
        try {
            return true;
        } catch (Exception sinUso){
            throw new ResolucionDAOException("Error al actualizar la resolucion");
        }
    }

    @Override
    public Resolucion obtenerPorId(int id){
        try {
            return null;
        } catch (Exception sinUso){
            throw new ResolucionDAOException("Error al obtener la resolucion por id");
        }
    }

    @Override
    public Resolucion obtenerPorFiltro(String tipoFiltro, String filtro){
        try {
            return null;
        } catch (Exception sinUso){
            throw new ResolucionDAOException("Error al obtener la resolucion por filtro");
        }
    }

    @Override
    public List<Resolucion> obtenerTodas(){
        try {
            return null;
        } catch (Exception sinUso){
            throw new ResolucionDAOException("Error al obtener todas las resoluciones");
        }
    }
}
