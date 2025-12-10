// ICoordinadorNegocio.java
package presentacion.coordinacion.interfaces;

import dtoGobierno.BecaDTO;
import dtoGobierno.EvaluadorLoginDTO;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;

import java.util.List;

/**
 * Interface para el coordinador de negocio
 * @author Cortez, Manuel
 */
public interface ICoordinadorNegocio {

    // Gestión de sesión
    boolean iniciarSesion(EvaluadorLoginDTO evaluadorLoginDTO);
    void cerrarSesion();
    boolean haySesionIniciada();

    // Evaluar Solicitudes
    List<BecaDTO> obtenerBecasConSolicitudes();
    List<SolicitudDTO> obtenerSolicitudes(String tipoBeca);
    ResolucionDTO evaluarSolicitudAutomatica(SolicitudDTO solicitud);
    boolean evaluarSolicitudManual(ResolucionDTO resolucion);
    boolean guardarResolucion(ResolucionDTO resolucion);

    // Modificar Resolución
    ResolucionDTO buscarResolucion(String tipoFiltro, String filtro);
    ResolucionDTO reevaluarAutomatica(SolicitudDTO solicitud);
    boolean modificarResolucionManual(ResolucionDTO resolucion);
    boolean modificarResolucion(ResolucionDTO resolucion);
}