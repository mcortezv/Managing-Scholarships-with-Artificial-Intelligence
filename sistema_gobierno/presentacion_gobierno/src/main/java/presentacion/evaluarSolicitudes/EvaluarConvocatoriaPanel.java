package presentacion.evaluarSolicitudes;
import dtoGobierno.BecaDTO;
import dtoGobierno.RequisitosDTO;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * The type Evaluar convocatoria panel.
 *
 * @autor Cortez, Manuel;
 */
public class EvaluarConvocatoriaPanel extends Panel {
    private Label titulo;
    private JPanel lista;
    private JScrollPane scroll;
    private ComboBox<BecaDTO> comboConvocatoria;
    private Button btnEvaluar;
    private ICoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Evaluar convocatoria panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public EvaluarConvocatoriaPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.X_AXIS));
        tituloPanel.setOpaque(false);
        tituloPanel.setMaximumSize(new Dimension(900, 60));

        titulo = new Label("Evaluar Convocatoria");
        titulo.setFont(Style.TITLE_FONT);

        tituloPanel.add(Box.createHorizontalGlue());
        tituloPanel.add(titulo);
        tituloPanel.add(Box.createHorizontalGlue());
        centralPanel.add(tituloPanel);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        JPanel listaContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(3, 3, getWidth()-3, getHeight()-3, 20, 20);
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(45, 52, 54),
                        0, getHeight(), new Color(55, 62, 64)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth()-3, getHeight()-3, 20, 20);
                g2.setColor(new Color(70, 70, 70));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth()-4, getHeight()-4, 20, 20);
            }
        };
        listaContainer.setLayout(new BorderLayout());
        listaContainer.setOpaque(false);
        listaContainer.setMaximumSize(new Dimension(1000, 500));
        listaContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        listaContainer.add(scroll, BorderLayout.CENTER);
        centralPanel.add(listaContainer);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        JPanel selectorPanel = new JPanel();
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setOpaque(false);
        selectorPanel.setMaximumSize(new Dimension(500, 100));

        Label lblSelector = new Label("Seleccione una Convocatoria:");
        lblSelector.setFont(Style.LABEL_FONT);
        lblSelector.setForeground(Color.WHITE);
        lblSelector.setAlignmentX(CENTER_ALIGNMENT);
        selectorPanel.add(lblSelector);
        selectorPanel.add(Box.createVerticalStrut(10));

        comboConvocatoria = new ComboBox<>(new BecaDTO[]{});
        comboConvocatoria.setAlignmentX(CENTER_ALIGNMENT);
        comboConvocatoria.setMaximumSize(new Dimension(450, 55));
        selectorPanel.add(comboConvocatoria);

        centralPanel.add(selectorPanel);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        btnEvaluar = new Button("Comenzar Evaluación");
        btnEvaluar.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(btnEvaluar);

        btnEvaluar.addActionListener(e -> {
            BecaDTO seleccionada = (BecaDTO) comboConvocatoria.getSelectedItem();
            if (seleccionada == null) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Debe seleccionar una beca",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            coordinadorAplicacion.seleccionarConvocatoriaEvaluar(seleccionada);
        });

        btnBack.addActionListener(e -> {
            coordinadorAplicacion.volverHub();
        });
    }

    /**
     * Sets becas.
     *
     * @param becas the becas
     */
    public void setBecas(List<BecaDTO> becas) {
        lista.removeAll();

        if (becas != null && !becas.isEmpty()) {
            for (BecaDTO b : becas) {
                lista.add(crearItem(b));
                lista.add(Box.createVerticalStrut(12));
            }
        } else {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setOpaque(false);
            emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
            emptyPanel.setMaximumSize(new Dimension(800, 200));
            emptyPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
            emptyPanel.add(Box.createVerticalStrut(20));

            Label lbl = new Label("No hay Convocatorias Disponibles para Evaluar");
            lbl.setAlignmentX(CENTER_ALIGNMENT);
            lbl.setFont(Style.LABEL_FONT.deriveFont(20f));
            lbl.setForeground(new Color(180, 180, 180));
            emptyPanel.add(lbl);
            lista.add(emptyPanel);
        }

        lista.revalidate();
        lista.repaint();

        comboConvocatoria.removeAllItems();
        if (becas != null) {
            for (BecaDTO beca : becas) {
                comboConvocatoria.addItem(beca);
            }
        }
    }

    private JComponent crearItem(BecaDTO b) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(2, 2, getWidth()-2, getHeight()-2, 15, 15);
                g2.setColor(new Color(248, 249, 250));
                g2.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 15, 15);
                g2.setColor(new Color(220, 220, 220));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth()-3, getHeight()-3, 15, 15);
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(100, 149, 237),
                        0, getHeight(), new Color(72, 119, 207)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, 8, getHeight()-2, 15, 15);
            }
        };
        card.setOpaque(false);
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(950, 110));
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titul = new JLabel("<html><b style='font-size:18px; color:#2c3e50;'>" +
                safe(b.getNombre()) + "</b></html>");
        titul.setFont(Style.LABEL_FONT);

        JLabel desc = new JLabel("<html><span style='font-size:16px; color:#555;'>" +
                descripcionBeca(b) + "</span></html>");
        desc.setFont(Style.LABEL_FONT.deriveFont(Font.PLAIN, 18f));

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(titul);
        text.add(Box.createVerticalStrut(8));
        text.add(desc);

        card.add(text, BorderLayout.CENTER);

        return card;
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }

    private String descripcionBeca(BecaDTO b) {
        RequisitosDTO r = b.getRequisitos();
        if (r == null) return "Está dirigida a estudiantes con buen desempeño académico.";

        StringBuilder sb = new StringBuilder("Está dirigida a estudiantes ");
        boolean pusoAlgo = false;

        if (r.getPromedioMinimo() > 0) {
            sb.append("con promedio ≥ ").append(r.getPromedioMinimo());
            pusoAlgo = true;
        }
        if (r.getIngresoFamiliarMaximo() > 0) {
            if (pusoAlgo) sb.append(", ");
            sb.append("ingreso familiar ≤ $").append((int) r.getIngresoFamiliarMaximo());
            pusoAlgo = true;
        }
        if (!pusoAlgo) sb.append("cuyo perfil cumple la convocatoria");

        sb.append(".");
        return sb.toString();
    }
}