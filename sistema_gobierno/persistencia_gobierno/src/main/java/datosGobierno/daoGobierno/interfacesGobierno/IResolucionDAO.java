package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.dominioGobierno.Resolucion;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public interface IResolucionDAO {

    boolean guardar(Resolucion resolucion);

    boolean actualizar(Resolucion resolucion);

    Resolucion obtenerPorId(int id);

    Resolucion obtenerPorFiltro(String tipoFiltro, String filtro);

    List<Resolucion> obtenerTodas();
}
