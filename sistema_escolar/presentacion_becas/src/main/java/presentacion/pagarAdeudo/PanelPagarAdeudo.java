package presentacion.pagarAdeudo;

import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.styles.Button;
import presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;

/**
 * Clase base abstracta para todos los paneles del módulo de Pagar Adeudo.
 * <p>
 * Define la estructura visual común (Layout) y los componentes compartidos
 * por todas las vistas, como el panel central de contenido y el panel inferior
 * de navegación. Obliga a las subclases a implementar la inicialización de sus componentes.
 */
public abstract class PanelPagarAdeudo extends JPanel {

    protected PagarAdeudo mainFrame; // Referencia a la ventana principal contenedora
    protected JPanel centralPanel; // Contenedor para el contenido dinámico específico de cada vista
    protected JPanel southPanel; // Contenedor inferior para controles de navegación
    protected Button btnBack; // Botón de "Volver" compartido por todas las pantallas
    protected CoordinadorAplicacionPagarAdeudo coordinadorAplicacion; // Referencia al coordinador para la lógica de negocio

    /**
     * Constructor de la clase base.
     * Configura el diseño general (BorderLayout), inicializa los paneles estructurales
     * y establece los estilos visuales básicos.
     *
     * @param frame Ventana principal de la aplicación.
     * @param coordinadorAplicacion Coordinador del módulo de pagos.
     */
    public PanelPagarAdeudo(PagarAdeudo frame, CoordinadorAplicacionPagarAdeudo coordinadorAplicacion){
        mainFrame = frame;
        btnBack = new Button("Volver");
        this.coordinadorAplicacion = coordinadorAplicacion;
        setLayout(new BorderLayout());
        centralPanel = new JPanel();
        southPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(1500, 750));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        southPanel.setPreferredSize(new Dimension(1500, 100));
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        centralPanel.setBackground(Style.PANEL_COLOR);
        southPanel.setBackground(Style.PANEL_COLOR);
        add(centralPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(btnBack);
    }

    /**
     * Método abstracto que define el contrato para las subclases.
     * Cada panel hijo debe implementar este método para cargar sus etiquetas,
     * tablas y lógica visual específica.
     */
    public abstract void startComponents();
}