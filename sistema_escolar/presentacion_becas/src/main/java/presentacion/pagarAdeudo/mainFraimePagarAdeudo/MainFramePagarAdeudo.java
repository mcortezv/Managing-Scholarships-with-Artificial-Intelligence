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

/**
 * Ventana Principal (Frame) del módulo de Pagar Adeudo.
 * <p>
 * Esta clase hereda de JFrame y actúa como el contenedor base de la interfaz gráfica.
 * Implementa un mecanismo de navegación manual (similar a un CardLayout) utilizando un Map
 * para gestionar y alternar entre los diferentes paneles (vistas) de la aplicación.
 */
public class MainFramePagarAdeudo extends JFrame {

    // Panel central donde se renderizan las vistas dinámicas
    private final JPanel centralPanel;

    // Componentes de la interfaz inicial
    private final Button btnBiblioteca;
    private Button btnColegiatura;

    // Referencias a los coordinadores para la comunicación con la lógica
    private CoordinadorAplicacion coordinadorAplicacion;
    private CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo;

    // Mapa que funciona como registro de vistas: "NombreClave" -> Panel
    private final Map<String, JPanel> panels;

    // Referencia a la clase gestora de la ventana (si aplica)
    private PagarAdeudo pagarAdeudo;

    /**
     * Constructor de la ventana principal.
     * Configura las propiedades básicas de la ventana (tamaño, posición) e inicializa
     * los componentes gráficos y el gestor de navegación.
     *
     * @param coordinadorAplicacion Coordinador global de la aplicación.
     * @param coordinadorAplicacionPagarAdeudo Coordinador específico de este módulo.
     */
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

        pagarAdeudo = new PagarAdeudo(coordinadorAplicacion, coordinadorAplicacionPagarAdeudo);
        pagarAdeudo.setVisible(true);

        panels.put("consultaAdeudoMenu", new ConsultaAdeudoMenu(pagarAdeudo, coordinadorAplicacionPagarAdeudo));
    }

    /**
     * Cambia el contenido visible del panel central.
     * <p>
     * Este método elimina el contenido actual, busca el nuevo panel en el mapa
     * usando la clave proporcionada y lo dibuja en pantalla.
     *
     * @param nuevoPanel Clave (String) que identifica al panel que se desea mostrar.
     */
    public void showPanel(String nuevoPanel) {
        centralPanel.removeAll();

        centralPanel.add(panels.get(nuevoPanel), BorderLayout.CENTER);

        centralPanel.revalidate();
        centralPanel.repaint();
    }
}