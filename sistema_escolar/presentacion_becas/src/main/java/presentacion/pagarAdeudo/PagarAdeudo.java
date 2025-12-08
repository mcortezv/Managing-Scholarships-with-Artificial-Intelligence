package presentacion.pagarAdeudo;

import presentacion.CoordinadorAplicacion;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.pagarAdeudo.panels.*;
import presentacion.login.panels.NorthPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public final class PagarAdeudo extends JFrame {

    private NorthPanel northPanel;
    private final JPanel centralPanel;
    private final Map<String, JPanel> panels;
    private final CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo;

    public PagarAdeudo(CoordinadorAplicacion coordinadorAplicacion, CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo) {
        setTitle("Pagar Adeudo");
        setResizable(false);
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        northPanel = new NorthPanel();
        centralPanel = new JPanel(new BorderLayout());

        this.coordinadorAplicacionPagarAdeudo = coordinadorAplicacionPagarAdeudo;

        panels = new HashMap<>();

        initializePanels();

        add(northPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        northPanel.setVisible(false);

        coordinadorAplicacionPagarAdeudo.setPagarAdeudo(this);

        showPanel("consultaAdeudoMenu");
    }

    public void initializePanels() {

        PanelPagarAdeudo panel;

        panel = new ConsultaAdeudoMenu(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("consultaAdeudoMenu", panel);

        panel = new ListaPrestamosBiblioteca(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("listaPrestamosBiblioteca", panel);

        panel = new ListaClasesColegiatura(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("listaClasesColegiatura", panel);

        panel = new DetallePrestamo(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("detallePrestamo", panel);

        panel = new DetalleClase(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("detalleClase", panel);

        panel = new MetodosDePago(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("metodosDePago", panel);

        panel = new ConfirmacionPago(this, coordinadorAplicacionPagarAdeudo);
        panel.startComponents();
        panels.put("confirmacionPago", panel);


    }

    public void showPanel(String nuevoPanel) {
        centralPanel.removeAll();
        JPanel p =  panels.get(nuevoPanel);
        if (p != null) {
            centralPanel.add(p, BorderLayout.CENTER);
        }
        centralPanel.revalidate();
        centralPanel.repaint();
    }


    public JPanel getPanel(String key) {
        return panels.get(key);
    }
}
