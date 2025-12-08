package presentacion.login;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.ImgPanel;
import presentacion.styles.Panel;
import javax.swing.*;
import presentacion.styles.Button;
import presentacion.styles.Style;
import presentacion.styles.Label;
import java.awt.*;

public class HubPanel extends Panel {
    private JPanel navbar;
    private ImgPanel imgPanel;
    private Button btnCerrarSesion;
    private Label titulo;
    private Button btnEvaluar;
    private Button btnModificar;
    private ImgPanel hero;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public HubPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        southPanel.setVisible(false);
        navbar = new JPanel();
        navbar.setSize(1500, 80);
        navbar.setMaximumSize(new Dimension(1500, 80));
        navbar.setPreferredSize(new Dimension(1500, 80));
        navbar.setBackground(Color.gray);
        navbar.setLayout(new BorderLayout());

        imgPanel = new ImgPanel("/assets/logo.png");
        imgPanel.setPreferredSize(new Dimension(135, 50));
        imgPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setButtonSize(160, 50);
        btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 14));
        leftPanel.setOpaque(false);
        leftPanel.add(imgPanel);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 14));
        rightPanel.setOpaque(false);
        rightPanel.add(btnCerrarSesion);

        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        titulo = new Label("Evaluación de Solicitudes");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        JPanel main = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnEvaluar = new Button("Evaluar");
        btnModificar = new Button("Modificar");
        main.setMaximumSize(new Dimension(400, 90));
        main.setOpaque(false);
        main.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(btnEvaluar);
        main.add(btnModificar);
        centralPanel.add(main);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));

        imgPanel = new ImgPanel("/assets/hero.png");
        imgPanel.setMaximumSize(new Dimension(1500, 520));
        centralPanel.add(imgPanel);


        navbar.add(leftPanel, BorderLayout.WEST);
        navbar.add(rightPanel, BorderLayout.EAST);
        add(navbar, BorderLayout.NORTH);

        btnCerrarSesion.addActionListener(e -> {
            coordinadorAplicacion.cerrarSesion();
        });

        btnModificar.addActionListener(e -> {
            coordinadorAplicacion.modificar();
        });

        btnEvaluar.addActionListener(e -> {
            coordinadorAplicacion.evaluar();
        });
    }
}
