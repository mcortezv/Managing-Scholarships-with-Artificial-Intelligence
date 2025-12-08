package presentacion.coordinacion;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.SolicitudDTO;
import interfaces.IFachadaEvaluarSolicitudes;
import interfaces.IFachadaModificarResolucion;
import presentacion.coordinacion.interfaces.ICoordinadorNegocio;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class CoordinadorNegocio implements ICoordinadorNegocio {
    private IFachadaEvaluarSolicitudes fachadaEvaluarSolicitudes;
    private IFachadaModificarResolucion fachadaModificarResolucion;

    public CoordinadorNegocio(IFachadaEvaluarSolicitudes fachadaEvaluarSolicitudes,IFachadaModificarResolucion fachadaModificarResolucion) {
        this.fachadaEvaluarSolicitudes = fachadaEvaluarSolicitudes;
        this.fachadaModificarResolucion = fachadaModificarResolucion;
    }

    @Override
    public boolean iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO) {
        return true;
    }

    @Override
    public List<BecaDTO> obtenerBecasConSolicitudes(){
        return fachadaEvaluarSolicitudes.obtenerListadoBecas();
    }

    @Override
    public List<SolicitudDTO> obtenerSolicitudes(String tipoBeca){
        return fachadaEvaluarSolicitudes.obtenerSolicitudes(tipoBeca);
    }
}
