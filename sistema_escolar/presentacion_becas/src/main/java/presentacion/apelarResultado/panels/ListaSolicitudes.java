package presentacion.apelarResultado.panels;

import presentacion.apelarResultado.ApelarResultado;
import presentacion.apelarResultado.PanelApelarResultado;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.styles.Button;
import presentacion.styles.CustomTable;
import presentacion.styles.enums.PanelCategory;
import solicitarBeca.SolicitudDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaSolicitudes extends PanelApelarResultado {

    private JLabel lblTotalSolicitudes;
    private DefaultTableModel tableModel;
    private CustomTable table;
    private List<SolicitudDTO> solicitudesCache;

    public ListaSolicitudes(ApelarResultado mainFrame, CoordinadorAplicacionApelarResultado coordinadorAplicacion) {
        super(mainFrame, coordinadorAplicacion);
    }

    @Override
    public void startComponents() {
        centralPanel.removeAll();
        centralPanel.setLayout(new GridBagLayout());
        centralPanel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JPanel headerApelacion = new RoundedPanel(20, new Color(235, 235, 235));
        headerApelacion.setPreferredSize(new Dimension(250, 50));
        headerApelacion.setMaximumSize(new Dimension(250, 50));
        headerApelacion.setLayout(new GridBagLayout());
        JLabel lblTituloIzq = new JLabel("Apelación");
        lblTituloIzq.setFont(new Font("SansSerif", Font.PLAIN, 20));
        headerApelacion.add(lblTituloIzq);

        JPanel cardInfo = new RoundedPanel(20, new Color(235, 235, 235));
        cardInfo.setPreferredSize(new Dimension(250, 200));
        cardInfo.setMaximumSize(new Dimension(250, 200));
        cardInfo.setLayout(new BoxLayout(cardInfo, BoxLayout.Y_AXIS));
        cardInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblInfoTitulo = new JLabel("Solicitudes:");
        lblInfoTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblInfoTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTotalSolicitudes = new JLabel("0");
        lblTotalSolicitudes.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTotalSolicitudes.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel infoBox = new RoundedPanel(10, Color.WHITE);
        infoBox.setMaximumSize(new Dimension(200, 40));
        infoBox.add(lblTotalSolicitudes);

        leftPanel.add(headerApelacion);
        leftPanel.add(Box.createVerticalStrut(30));

        cardInfo.add(Box.createVerticalStrut(10));
        cardInfo.add(lblInfoTitulo);
        cardInfo.add(Box.createVerticalStrut(10));
        cardInfo.add(infoBox);
        cardInfo.add(Box.createVerticalStrut(20));

        leftPanel.add(cardInfo);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);

        JPanel headerSolicitudes = new RoundedPanel(20, new Color(235, 235, 235));
        headerSolicitudes.setPreferredSize(new Dimension(250, 50));
        headerSolicitudes.setMaximumSize(new Dimension(250, 50));
        headerSolicitudes.setLayout(new GridBagLayout());
        JLabel lblTituloDer = new JLabel("Historial de Solicitudes");
        lblTituloDer.setFont(new Font("SansSerif", Font.PLAIN, 20));
        headerSolicitudes.add(lblTituloDer);

        JPanel tableContainer = new RoundedPanel(20, Color.WHITE);
        tableContainer.setLayout(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tableContainer.setPreferredSize(new Dimension(700, 400));

        String[] columnNames = {"Beca", "Fecha", "Estado", "Ver"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        table = new CustomTable(tableModel, mainFrame, PanelCategory.LISTA_SOLICITUDES, this, coordinadorAplicacionApelarResultado);
        table.setRowHeight(30);

        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private final Button button = new Button("...");
            private int currentRow;

            {
                button.setOpaque(true);
                button.setFont(new Font("SansSerif", Font.BOLD, 14));
                button.setBackground(Color.WHITE);
                button.addActionListener(e -> {
                    fireEditingStopped();
                    if (solicitudesCache != null && currentRow >= 0 && currentRow < solicitudesCache.size()) {
                        SolicitudDTO seleccionado = solicitudesCache.get(currentRow);
                        coordinadorAplicacionApelarResultado.irADetalleSolicitud(seleccionado);
                    }
                });
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                this.currentRow = row;
                return button;
            }
        });

        table.getColumnModel().getColumn(3).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
            Button btn = new Button("...");
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            btn.setBackground(Color.WHITE);
            return btn;
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableContainer.add(scrollPane, BorderLayout.CENTER);

        rightPanel.add(headerSolicitudes);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(tableContainer);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(20, 20, 20, 20);
        centralPanel.add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        centralPanel.add(Box.createHorizontalStrut(20), gbc);

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centralPanel.add(rightPanel, gbc);

        btnBack.addActionListener(e -> coordinadorAplicacionApelarResultado.regresarAlMenuPrincipal());

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void setSolicitudesCache(List<SolicitudDTO> solicitudes) {
        this.solicitudesCache = solicitudes;

        if (lblTotalSolicitudes != null) {
            lblTotalSolicitudes.setText(String.valueOf(solicitudes.size()));
        }

        if (tableModel == null) return;
        tableModel.setRowCount(0);

        for (SolicitudDTO s : solicitudes) {
            Object[] row = {
                    s.getBeca().getNombre(),
                    s.getFecha(),
                    s.getEstado(),
                    "..."
            };
            tableModel.addRow(row);
        }
    }

    public static class RoundedPanel extends JPanel {
        private int cornerRadius = 15;
        private final Color backgroundColor;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            this.cornerRadius = radius;
            this.backgroundColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.setColor(new Color(0,0,0,30));
            graphics.fillRoundRect(3, 3, width-3, height-3, arcs.width, arcs.height);

            graphics.setColor(backgroundColor);
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        }
    }
}