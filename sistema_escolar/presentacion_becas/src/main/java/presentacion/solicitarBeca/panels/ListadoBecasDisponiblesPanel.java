package presentacion.solicitarBeca.panels;
import presentacion.solicitarBeca.PanelSolicitarBeca;
import solicitarBeca.BecaDTO;
import solicitarBeca.RequisitosDTO;
import presentacion.CoordinadorAplicacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import presentacion.solicitarBeca.SolicitarBeca;
import presentacion.styles.Button;
import presentacion.styles.ComboBox;
import presentacion.styles.Label;
import presentacion.styles.Style;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * The type Listado becas disponibles panel.
 */
public class ListadoBecasDisponiblesPanel extends PanelSolicitarBeca {
    private Label titulo;
    private Label subtitulo;
    private JScrollPane scroll;
    private JPanel lista;
    private ComboBox<BecaDTO> ddlBecas;
    private Button btnSeleccionar;
    private JPanel selectorPanel;

    /**
     * Instantiates a new Listado becas disponibles panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public ListadoBecasDisponiblesPanel(SolicitarBeca frame, CoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
    }

    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(40));

        titulo = new Label("Becas Disponibles");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(10));

        subtitulo = new Label("Selecciona la beca que mejor se adapte a tu perfil");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitulo.setForeground(new Color(100, 100, 100));
        subtitulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(subtitulo);
        centralPanel.add(Box.createVerticalStrut(35));

        lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        scroll.setAlignmentX(CENTER_ALIGNMENT);

        centralPanel.add(scroll);
        centralPanel.add(Box.createVerticalStrut(30));

        selectorPanel = new JPanel();
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setOpaque(false);
        selectorPanel.setAlignmentX(CENTER_ALIGNMENT);

        Label lblSelector = new Label("Beca Seleccionada:");
        lblSelector.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblSelector.setAlignmentX(CENTER_ALIGNMENT);
        selectorPanel.add(lblSelector);
        selectorPanel.add(Box.createVerticalStrut(12));

        ddlBecas = new ComboBox<>(new BecaDTO[]{});
        ddlBecas.setAlignmentX(CENTER_ALIGNMENT);
        ddlBecas.setMaximumSize(new Dimension(550, 55));
        ddlBecas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        selectorPanel.add(ddlBecas);

        centralPanel.add(selectorPanel);
        centralPanel.add(Box.createVerticalStrut(25));

        btnSeleccionar = new Button("Continuar con Solicitud");
        btnSeleccionar.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(btnSeleccionar);
        centralPanel.add(Box.createVerticalStrut(30));

        btnBack.addActionListener(e -> mainFrame.showPanel("informacionGeneralPanel"));

        btnSeleccionar.addActionListener(e -> {
            BecaDTO seleccionada = (BecaDTO) ddlBecas.getSelectedItem();
            coordinadorAplicacion.setBecaSeleccionadaDTO(seleccionada);
            coordinadorAplicacion.mostrarBecaSeleccionada();
        });
    }

    /**
     * Sets becas.
     *
     * @param becas the becas
     */
    public void setBecas(List<BecaDTO> becas) {
        for (BecaDTO beca : becas) {
            ddlBecas.add(beca);
        }
        lista.removeAll();
        if (becas != null && !becas.isEmpty()) {
            for (BecaDTO b : becas) {
                lista.add(crearItem(b));
                lista.add(Box.createVerticalStrut(18));
            }
        } else {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setOpaque(false);
            emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));

            Label lbl = new Label("Lo sentimos, no hay becas disponibles para tu perfil");
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            lbl.setForeground(new Color(150, 150, 150));
            lbl.setAlignmentX(CENTER_ALIGNMENT);
            emptyPanel.add(Box.createVerticalStrut(60));
            emptyPanel.add(lbl);
            lista.add(emptyPanel);
        }
        lista.revalidate();
        lista.repaint();

        ddlBecas.setModel(new DefaultComboBoxModel<>(
                becas == null ? new BecaDTO[]{} : becas.toArray(new BecaDTO[0])
        ));
    }

    private JComponent crearItem(BecaDTO b) {
        JPanel card = new JPanel();
        card.setOpaque(true);
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout(20, 0));
        card.setMaximumSize(new Dimension(1100, 140));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        // Icono lateral
        JPanel iconPanel = new JPanel();
        iconPanel.setOpaque(false);
        iconPanel.setPreferredSize(new Dimension(70, 70));
        iconPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel icon = new JLabel("🎓");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 38));
        icon.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        iconPanel.add(icon);

        JLabel titul = new JLabel("<html><b>" + safe(b.getNombre()) + "</b></html>");
        titul.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titul.setForeground(new Color(40, 40, 40));

        JLabel desc = new JLabel("<html>" + descripcionBeca(b) + "</html>");
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        desc.setForeground(new Color(90, 90, 90));

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(titul);
        text.add(Box.createVerticalStrut(10));
        text.add(desc);

        card.add(iconPanel, BorderLayout.WEST);
        card.add(text, BorderLayout.CENTER);
        return card;
    }

    private String safe(String s) { return s == null ? "" : s; }

    private String descripcionBeca(BecaDTO b) {
        RequisitosDTO r = b.getRequisitos();
        if (r == null) return "Dirigida a estudiantes con buen desempeño académico";
        StringBuilder sb = new StringBuilder("Requisitos: ");
        boolean pusoAlgo = false;

        if (r.getPromedioMinimo() > 0) {
            sb.append("Promedio ≥ ").append(r.getPromedioMinimo());
            pusoAlgo = true;
        }
        if (r.getIngresoFamiliarMaximo() > 0) {
            if (pusoAlgo) sb.append(" • ");
            sb.append("Ingreso ≤ $").append((int) r.getIngresoFamiliarMaximo());
            pusoAlgo = true;
        }
        if (!pusoAlgo) sb.append("Según perfil académico y socioeconómico");

        return sb.toString();
    }
}