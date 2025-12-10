package presentacion.coordinacion.interfaces;
import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;

/**
 * Interface para el coordinador de aplicación
 * @author Cortez, Manuel
 */
public interface ICoordinadorAplicacion {

    // Gestión de sesión
    void iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO);
    void cerrarSesion();
    void volverHub();

    // Evaluar Solicitudes - Navegación
    void iniciarEvaluarConvocatoria();
    void seleccionarConvocatoriaEvaluar(BecaDTO becaDTO);
    void evaluarAutomatica(SolicitudDTO solicitud);
    void evaluarManual(ResolucionDTO resolucion);
    void evaluarOtraSolicitud();
    void finalizarEvaluacion();

    // Modificar Resolución - Navegación
    void iniciarModificarConvocatoria();
    void iniciarBusquedaResolucion();
    void buscarResolucion(String tipoFiltro, String filtro);

    void reevaluarAutomatica(SolicitudDTO solicitud);
    void modificarResolucion(ResolucionDTO resolucion);
    void modificarOtraResolucion();
}