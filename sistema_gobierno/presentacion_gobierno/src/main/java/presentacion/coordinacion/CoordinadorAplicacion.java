package presentacion.coordinacion;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.coordinacion.interfaces.ICoordinadorNegocio;
import presentacion.evaluarSolicitudes.EvaluacionPanel;
import presentacion.evaluarSolicitudes.EvaluarConvocatoriaPanel;


/**
 *
 * @author Cortez, Manuel;
 */
public class CoordinadorAplicacion implements ICoordinadorAplicacion {
    private ICoordinadorNegocio coordinadorNegocio;
    private MainFrame frame;

    public CoordinadorAplicacion(ICoordinadorNegocio coordinadorNegocio) {
        this.coordinadorNegocio = coordinadorNegocio;
        frame = new MainFrame(this);
        frame.setVisible(true);
    }

    @Override
    public void iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO) {
        if (coordinadorNegocio.iniciarSesion(evaluadorLoginDTO)){
            frame.showPanel("hub");
        }
    }

    @Override
    public void cerrarSesion() {
        frame.showPanel("iniciarSesion");
    }

    public void modificar(){
        frame.getPanel("modificarConvocatoria");
    }

    public void evaluar(){
        EvaluarConvocatoriaPanel panel = (EvaluarConvocatoriaPanel) frame.getPanel("evaluarConvocatoria");
        panel.setBecas(coordinadorNegocio.obtenerBecasConSolicitudes());
        frame.showPanel("evaluarConvocatoria");
    }

    public void hub(){
        frame.showPanel("hub");
    }

    public void evaluarConvocatoria(BecaDTO becaDTO){
        EvaluacionPanel panel = (EvaluacionPanel) frame.getPanel("evaluacion");
        panel.setSolicitudes(coordinadorNegocio.obtenerSolicitudes(becaDTO.getTipo()));
        frame.showPanel("evaluacion");
    }
}
