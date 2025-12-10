package objetosNegocio.bo.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.HistorialAcademicoAdaptador;
import objetosNegocio.bo.solicitarBeca.excepciones.HistorialInvalidoException;
import interfaces.IFachadaITSON;
import objetosNegocio.bo.solicitarBeca.intefaces.IHistorialAcademicoBO;
import solicitarBeca.HistorialAcademicoDTO;

/**
 * The type Historial academico bo.
 *
 * @author Cortez, Manuel;
 */
public class HistorialAcademicoBO implements IHistorialAcademicoBO {
    private IFachadaITSON fachadaITSON;

    /**
     * Instantiates a new Historial academico bo.
     *
     * @param fachadaITSON the fachada itson
     */
    public HistorialAcademicoBO(IFachadaITSON fachadaITSON) {
        this.fachadaITSON = fachadaITSON;
    }

    @Override
    public HistorialAcademicoDTO crearHistorial(Long matricula) throws HistorialInvalidoException {
        try {
            return HistorialAcademicoAdaptador.toDTO(fachadaITSON.verificarHistorialAcademcio(matricula));
        } catch (Exception e) {
            throw new HistorialInvalidoException("Ningun historial academico asociado la matricula");
        }
    }
}
