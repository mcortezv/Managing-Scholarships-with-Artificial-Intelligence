/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import javax.swing.*;
import java.awt.*;

/**
 * The type Panel.
 *
 * @author Cortez, Manuel;
 */
public abstract class Panel extends JPanel {
    /**
     * The Main frame.
     */
    protected MainFrame mainFrame;
    /**
     * The Central panel.
     */
    protected JPanel centralPanel;
    /**
     * The South panel.
     */
    protected JPanel southPanel;
    /**
     * The Btn back.
     */
    protected Button btnBack;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public Panel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        mainFrame = frame;
        this.coordinadorAplicacion = coordinadorAplicacion;
        btnBack = new Button("Volver");
        setLayout(new BorderLayout());
        centralPanel = new JPanel();
        southPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(1500, 700));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        southPanel.setPreferredSize(new Dimension(1500, 100));
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        southPanel.setBackground(Style.PANEL_COLOR);
        centralPanel.setBackground(Style.PANEL_COLOR);
        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        startComponents();
        add(centralPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(btnBack);
    }

    /**
     * Start components.
     */
    public abstract void startComponents();
}