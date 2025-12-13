package presentacion.pagarAdeudo;

import presentacion.CoordinadorAplicacion;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.pagarAdeudo.panels.*;
import presentacion.login.panels.NorthPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Ventana Principal (Frame) del módulo de Pagos.
 * <p>
 * Esta clase gestiona la ventana de la aplicación para el módulo de adeudos.
 * Su responsabilidad principal es inicializar y almacenar todas las vistas (paneles)
 * disponibles y permitir la navegación entre ellas mediante un sistema de claves.
 */
public final class PagarAdeudo extends JFrame {

    private NorthPanel northPanel;
    private final JPanel centralPanel;

    /**
     * Mapa que funciona como registro de vistas.
     * Almacena las instancias de los paneles asociadas a una clave de texto (String).
     */
    private final Map<String, JPanel> panels;
    private final CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo;

    /**
     * Constructor de la ventana principal.
     * Configura las propiedades del Frame, inicializa el gestor de navegación
     * y carga la pantalla inicial.
     *
     * @param coordinadorAplicacion Coordinador global de la aplicación.
     * @param coordinadorAplicacionPagarAdeudo Coordinador específico del módulo de pagos.
     */
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

    /**
     * Inicializa y registra todos los paneles del módulo en el mapa de navegación.
     * Instancia cada clase de vista (UI) y ejecuta su configuración inicial.
     */
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
    }

    /**
     * Cambia el panel visible en el centro de la ventana.
     * Busca el panel en el registro mediante su clave y actualiza la interfaz.
     *
     * @param nuevoPanel La clave (String) del panel que se desea mostrar.
     */
    public void showPanel(String nuevoPanel) {
        centralPanel.removeAll();
        JPanel p =  panels.get(nuevoPanel);
        if (p != null) {
            centralPanel.add(p, BorderLayout.CENTER);
        }
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    /**
     * Recupera una instancia de panel específica.
     * Utilizado por el coordinador para inyectar datos en una vista antes de mostrarla.
     *
     * @param key La clave del panel a recuperar.
     * @return El objeto JPanel correspondiente.
     */
    public JPanel getPanel(String key) {
        return panels.get(key);
    }
}