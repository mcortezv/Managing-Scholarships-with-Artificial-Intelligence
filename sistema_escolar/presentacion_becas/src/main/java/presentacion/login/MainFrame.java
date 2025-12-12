package presentacion.login;

import presentacion.CoordinadorAplicacion;
import presentacion.login.panels.HubPanel;
import presentacion.login.panels.IniciarSesionPanel;
import presentacion.login.panels.NorthPanel;
import presentacion.styles.Button;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Cortez, Manuel;
 */
public final class MainFrame extends JFrame {
    private final NorthPanel northPanel;
    private final JPanel centralPanel;
    private Button btnSolicitarBeca;
    private Button btnApelacion;
    private Button btnPagoAdeudo;
    private Button btnActividades;
    private Button btnTutorias;
    private Button btnCarteras;
    private final Map<String, JPanel> panels;
    private CoordinadorAplicacion coordinadorAplicacion;


    public MainFrame(CoordinadorAplicacion coordinadorAplicacion) {
        setTitle("Sistema de Aplicaciones Escolares");
        setResizable(false);
        setSize(1500,900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        northPanel = new NorthPanel();
        centralPanel = new JPanel(new BorderLayout());
        this.coordinadorAplicacion = coordinadorAplicacion;

        btnSolicitarBeca = new Button("Solicitar Beca");
        Button btnEvaluarSolicitudes = new Button("Evaluar Solicitudes");
        btnApelacion = new Button("Apelacion");
        btnPagoAdeudo = new Button("Pago Adeudo");
        btnActividades = new Button("Actividades");
        btnTutorias = new Button("Tutorías");
        btnCarteras = new Button("Carteras");


        northPanel.add(btnSolicitarBeca);
        northPanel.add(btnEvaluarSolicitudes);
        northPanel.add(btnApelacion);
        northPanel.add(btnPagoAdeudo);
        northPanel.add(btnActividades);
        northPanel.add(btnTutorias);
        northPanel.add(btnCarteras);

        panels = new HashMap<String, JPanel>();

        panels.put("iniciarSesionPanel", new IniciarSesionPanel(this, coordinadorAplicacion));
        panels.put("hubPanel", new HubPanel(this, coordinadorAplicacion));

        add(northPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        northPanel.setVisible(false);
        showPanel("confirmacionPanel");

        btnSolicitarBeca.addActionListener(e -> {
            coordinadorAplicacion.solicitarBeca();
        });

        btnPagoAdeudo.addActionListener(e ->{
            coordinadorAplicacion.pagarAdeudo();
        });
        
        btnActividades.addActionListener( e->{
            coordinadorAplicacion.actividades();
        });
        btnTutorias.addActionListener(e -> {
            coordinadorAplicacion.tutorias();
        });

        btnApelacion.addActionListener(e ->{
            coordinadorAplicacion.apelacion();
        });

    }

    public void showPanel(String nuevoPanel) {
        centralPanel.removeAll();
        centralPanel.add(panels.get(nuevoPanel), BorderLayout.CENTER);
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public NorthPanel getNorthPanel() {
        return northPanel;
    }
}