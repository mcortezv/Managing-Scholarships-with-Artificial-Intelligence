package presentacion.coordinacion;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.evaluarSolicitudes.EvaluacionCompletadaPanel;
import presentacion.evaluarSolicitudes.EvaluacionPanel;
import presentacion.evaluarSolicitudes.EvaluarConvocatoriaPanel;
import presentacion.login.HubPanel;
import presentacion.login.IniciarSesionPanel;
import javax.swing.*;
import presentacion.modificarResolucion.BuscarResolucionPanel;
import presentacion.modificarResolucion.ModficacionCompletadaPanel;
import presentacion.modificarResolucion.ModificarConvocatoriaPanel;
import presentacion.modificarResolucion.ModificarResolucionPanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cortez, Manuel;
 */
public final class MainFrame extends JFrame {
    private final JPanel centralPanel;
    private final Map<String, JPanel> panels;
    private ICoordinadorAplicacion coordinadorAplicacion;


    public MainFrame(ICoordinadorAplicacion coordinadorAplicacion) {
        setTitle("Sistema de Evaluación de Solicitudes");
        setResizable(false);
        setSize(1500,900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        centralPanel = new JPanel(new BorderLayout());
        this.coordinadorAplicacion = coordinadorAplicacion;
        panels = new HashMap<String, JPanel>();

        // Login
        panels.put("iniciarSesion", new IniciarSesionPanel(this, coordinadorAplicacion));
        panels.put("hub", new HubPanel(this, coordinadorAplicacion));


        // Evaluar Solicitudes
        panels.put("evaluarConvocatoria", new EvaluarConvocatoriaPanel(this, coordinadorAplicacion));
        panels.put("evaluacion", new EvaluacionPanel(this, coordinadorAplicacion));
        panels.put("evaluacionCompletada", new EvaluacionCompletadaPanel(this, coordinadorAplicacion));


        // Modificar Resolucion
        panels.put("modificarConvocatoria", new ModificarConvocatoriaPanel(this, coordinadorAplicacion));
        panels.put("buscarResolucion", new BuscarResolucionPanel(this, coordinadorAplicacion));
        panels.put("modificarResolucion", new ModificarResolucionPanel(this, coordinadorAplicacion));
        panels.put("modificacionCompletada", new ModficacionCompletadaPanel(this, coordinadorAplicacion));

        add(centralPanel, BorderLayout.CENTER);
        centralPanel.add(panels.get("iniciarSesion"));
    }

    public void showPanel(String nuevoPanel) {
        centralPanel.removeAll();
        centralPanel.add(panels.get(nuevoPanel), BorderLayout.CENTER);
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public JPanel getPanel(String panel) {
        return panels.get(panel);
    }
}
