package presentacion.apelarResultado.coordinadorAplicacionApelarResultado;

import objetosNegocio.bo.sesion.SesionUsuario;
import dto.apelacionResultado.ApelacionDTO;
import presentacion.CoordinadorAplicacion;
import presentacion.apelarResultado.ApelarResultado;
import presentacion.apelarResultado.coordinadorNegocioApelarResultado.ICoordinadorNegocioApelarResultado;
import presentacion.apelarResultado.panels.DetalleSolicitud;
import presentacion.apelarResultado.panels.ListaSolicitudes;
import solicitarBeca.EstudianteDTO;
import solicitarBeca.SolicitudDTO;

import javax.swing.*;
import java.util.List;

public class CoordinadorAplicacionApelarResultado implements ICoordinadorAplicacionApelarResultado {

    private final CoordinadorAplicacion coordinadorPadre;
    private final ICoordinadorNegocioApelarResultado coordinadorNegocio;
    private ApelarResultado apelarResultadoFrame;
    private List<SolicitudDTO> solicitudesCache;

    public CoordinadorAplicacionApelarResultado(ICoordinadorNegocioApelarResultado cn, CoordinadorAplicacion cp) {
        this.coordinadorNegocio = cn;
        this.coordinadorPadre = cp;
    }

    @Override
    public void iniciarApelacion() {
        if (apelarResultadoFrame != null) {
            apelarResultadoFrame.dispose();
        }
        apelarResultadoFrame = new ApelarResultado(coordinadorPadre, this);
        EstudianteDTO estudianteLogueado = SesionUsuario.getInstance().getEstudianteLogeado();

        if (this.solicitudesCache == null) {
            this.solicitudesCache = coordinadorNegocio.obtenerSolicitudesPorEstudiante(estudianteLogueado);
        }

        ListaSolicitudes panelLista = (ListaSolicitudes) apelarResultadoFrame.getPanel("listaSolicitudes");
        panelLista.setSolicitudesCache(this.solicitudesCache);

        apelarResultadoFrame.setVisible(true);
        apelarResultadoFrame.showPanel("listaSolicitudes");
    }

    @Override
    public void irADetalleSolicitud(SolicitudDTO solicitudSeleccionada) {
        if (apelarResultadoFrame != null) {
            DetalleSolicitud panelDetalle = (DetalleSolicitud) apelarResultadoFrame.getPanel("detalleSolicitud");
            panelDetalle.setSolicitud(solicitudSeleccionada);
            apelarResultadoFrame.showPanel("detalleSolicitud");
        }
    }

    @Override
    public void regresarAlMenuPrincipal() {
        if (apelarResultadoFrame != null) {
            apelarResultadoFrame.dispose();
        }
        limpiarCache();
        coordinadorPadre.mostrarMainFrame();
    }

    @Override
    public void procesarApelacion(ApelacionDTO apelacionDTO) {
        try {
            boolean exito = coordinadorNegocio.registrarApelacion(apelacionDTO);

            if(exito) {
                JOptionPane.showMessageDialog(apelarResultadoFrame,
                        "Apelación enviada correctamente.\nSe le notificará el resultado.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCache();
                iniciarApelacion();
            } else {
                JOptionPane.showMessageDialog(apelarResultadoFrame,
                        "Error al enviar la apelación.\nIntente más tarde.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(apelarResultadoFrame,
                    "Error de conexión: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void setApelarResultado(ApelarResultado apelarResultado) {
        this.apelarResultadoFrame = apelarResultado;
    }

    private void limpiarCache() {
        this.solicitudesCache = null;
    }
}