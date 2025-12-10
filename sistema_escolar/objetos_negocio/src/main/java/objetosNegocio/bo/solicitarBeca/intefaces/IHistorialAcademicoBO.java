package objetosNegocio.bo.solicitarBeca.intefaces;
import objetosNegocio.bo.solicitarBeca.excepciones.HistorialInvalidoException;
import solicitarBeca.HistorialAcademicoDTO;

/**
 * The interface Historial academico bo.
 *
 * @author Cortez, Manuel;
 */
public interface IHistorialAcademicoBO {

    /**
     * Crear historial historial academico dto.
     *
     * @param matricula the matricula
     * @return the historial academico dto
     * @throws HistorialInvalidoException the historial invalido exception
     */
    HistorialAcademicoDTO crearHistorial(Long matricula) throws HistorialInvalidoException;
}
