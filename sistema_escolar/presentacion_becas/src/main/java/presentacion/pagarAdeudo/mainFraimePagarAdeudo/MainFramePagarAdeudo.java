package presentacion.pagarAdeudo.mainFraimePagarAdeudo;

import presentacion.CoordinadorAplicacion;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.ICoordinadorAplicacionPagarAdeudo;
import presentacion.pagarAdeudo.panels.ConsultaAdeudoMenu;
import presentacion.styles.Button;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFramePagarAdeudo extends JFrame {
    private final JPanel centralPanel;
    private final Button btnBiblioteca;
    private Button btnColegiatura;
    private CoordinadorAplicacion coordinadorAplicacion;
    private CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo;
    private final Map<String, JPanel> panels;
    private PagarAdeudo pagarAdeudo;

    public MainFramePagarAdeudo(CoordinadorAplicacion coordinadorAplicacion, CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo){
        setResizable(false);
        setSize(1500,900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        centralPanel = new JPanel();
        this.coordinadorAplicacionPagarAdeudo = coordinadorAplicacionPagarAdeudo;
        btnBiblioteca = new Button("Bibloteca");
        btnColegiatura = new Button("Colegiatura");
        centralPanel.add(btnBiblioteca);
        centralPanel.add(btnColegiatura);
        add(centralPanel, BorderLayout.CENTER);

        panels = new HashMap<String, JPanel>();
        pagarAdeudo = new PagarAdeudo(coordinadorAplicacion,coordinadorAplicacionPagarAdeudo);
        pagarAdeudo.setVisible(true);

        panels.put("consultaAdeudoMenu", new ConsultaAdeudoMenu(pagarAdeudo, coordinadorAplicacionPagarAdeudo));
    }

}
