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

public class EvaluarConvocatoriaPanel extends Panel {
    private Label titulo;
    private JPanel lista;
    private JScrollPane scroll;
    private ComboBox<BecaDTO> comboConvocatoria;
    private Button btnEvaluar;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public EvaluarConvocatoriaPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        titulo = new Label("Evaluar Convocatoria");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));

        scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
        scroll.setAlignmentX(CENTER_ALIGNMENT);

        centralPanel.add(scroll);

        comboConvocatoria = new ComboBox<>(new BecaDTO[]{});
        comboConvocatoria.setAlignmentX(CENTER_ALIGNMENT);
        comboConvocatoria.setMaximumSize(new Dimension(400, 55));
        centralPanel.add(comboConvocatoria);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        btnEvaluar = new Button("Evaluar");
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

    public void setBecas(List<BecaDTO> becas) {
        lista.removeAll();

        if (becas != null && !becas.isEmpty()) {
            for (BecaDTO b : becas) {
                lista.add(crearItem(b));
                lista.add(Box.createVerticalStrut(16));
            }
        } else {
            Label lbl = new Label("Lo sentimos. No hay Convocatorias Disponibles para Evaluar.");
            lbl.setAlignmentX(CENTER_ALIGNMENT);
            lbl.setForeground(Style.TEXT_COLOR);
            lista.add(lbl);
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
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BorderLayout());
        card.setMaximumSize(new Dimension(980, 100));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1,1,1,1,new java.awt.Color(220,220,220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JLabel titul = new JLabel("<html><b>" + safe(b.getNombre()) + "</b>:</html>");
        titul.setFont(Style.LABEL_FONT);
        titul.setForeground(Style.TEXT_COLOR);

        JLabel desc = new JLabel("<html>" + descripcionBeca(b) + "</html>");
        desc.setFont(Style.LABEL_FONT.deriveFont(Font.PLAIN, 20f));
        desc.setForeground(Style.TEXT_COLOR);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(titul);
        text.add(Box.createVerticalStrut(6));
        text.add(desc);

        card.add(Box.createRigidArea(new Dimension(56, 56)), BorderLayout.WEST);
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
            sb.append("ingreso familiar ≤ ").append((int) r.getIngresoFamiliarMaximo());
            pusoAlgo = true;
        }
        if (!pusoAlgo) sb.append("cuyo perfil cumple la convocatoria");

        sb.append(".");
        return sb.toString();
    }
}