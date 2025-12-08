package presentacion.coordinacion.interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;

/**
 *
 * @author Cortez, Manuel;
 */
public interface ICoordinadorAplicacion {

    void iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO);

    void cerrarSesion();

    void modificar();

    void evaluar();

    void hub();

    void evaluarConvocatoria(BecaDTO becaDTO);
}
