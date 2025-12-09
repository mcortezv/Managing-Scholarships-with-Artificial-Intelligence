package presentacion.apelarResultado.coordinadorAplicacionApelarResultado;

import dto.apelacionResultado.ApelacionDTO;
import presentacion.apelarResultado.ApelarResultado;
import solicitarBeca.SolicitudDTO;

public interface ICoordinadorAplicacionApelarResultado {
    void iniciarApelacion();
    void irADetalleSolicitud(SolicitudDTO solicitudSeleccionada);
    void regresarAlMenuPrincipal();
    void procesarApelacion(ApelacionDTO apelacionDTO);
    void setApelarResultado(ApelarResultado apelarResultado);
}