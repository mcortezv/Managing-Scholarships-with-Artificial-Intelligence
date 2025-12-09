package presentacion.pagarAdeudo.panels;

import objetosNegocio.bo.sesion.SesionUsuario;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.PanelPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.styles.Button;
import presentacion.styles.Style;
import solicitarBeca.EstudianteDTO;
import javax.swing.*;
import java.awt.*;

public class ConsultaAdeudoMenu extends PanelPagarAdeudo {

    public ConsultaAdeudoMenu(PagarAdeudo frame, CoordinadorAplicacionPagarAdeudo coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        startComponents();
    }

    @Override
    public void startComponents() {
        centralPanel.removeAll();

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Style.PANEL_COLOR);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel title = new JLabel("Consulta de adeudo");
        title.setFont(new Font("SansSerif", Font.PLAIN, 28));
        title.setOpaque(true);
        title.setBackground(new Color(235, 235, 235));
        title.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(title);
        centralPanel.add(titlePanel);

        JPanel contentBox = new JPanel();
        contentBox.setPreferredSize(new Dimension(450, 500));
        contentBox.setMaximumSize(new Dimension(450, 500));
        contentBox.setBackground(new Color(235, 235, 235));
        contentBox.setLayout(new BoxLayout(contentBox, BoxLayout.Y_AXIS));
        contentBox.setBorder(BorderFactory.createEmptyBorder(80, 30, 80, 30));

        Button btnBiblioteca = new Button("Biblioteca");
        btnBiblioteca.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBiblioteca.setMaximumSize(new Dimension(200, 50));

        Button btnColegiatura = new Button("Colegiatura");
        btnColegiatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnColegiatura.setMaximumSize(new Dimension(200, 50));

        contentBox.add(btnBiblioteca);
        contentBox.add(Box.createVerticalStrut(30));
        contentBox.add(btnColegiatura);

        centralPanel.add(contentBox);
        btnBack.addActionListener(e -> {
            coordinadorAplicacion.regresarAlMenuPrincipal();
        });

        btnBiblioteca.addActionListener(e -> {
            EstudianteDTO estudianteDTO = new EstudianteDTO();
            estudianteDTO.setMatricula(SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
            coordinadorAplicacion.seleccionarAdeudoBiblioteca(estudianteDTO);
        });

        btnColegiatura.addActionListener(e -> {
            EstudianteDTO estudianteDTO = new EstudianteDTO();
            estudianteDTO.setMatricula(SesionUsuario.getInstance().getEstudianteLogeado().getMatricula());
            coordinadorAplicacion.seleccionarAdeudoColegiatura(estudianteDTO);
        });

        revalidate();
        repaint();
    }
}