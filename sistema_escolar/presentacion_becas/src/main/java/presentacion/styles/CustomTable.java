package presentacion.styles;

import presentacion.apelarResultado.ApelarResultado;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.styles.enums.PanelCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

public class CustomTable extends JTable {

    private PagarAdeudo owner;
    private ApelarResultado apelarResultado;
    private final PanelCategory category;
    private JPanel previous;
    private CoordinadorAplicacionPagarAdeudo coordinador;
    private CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultado;

    public CustomTable(
            TableModel model,
            PagarAdeudo owner,
            PanelCategory category,
            JPanel previous,
            CoordinadorAplicacionPagarAdeudo coordinador
    ) {
        super(model);
        this.owner = owner;
        this.category = category;
        this.previous = previous;
        this.coordinador = coordinador;

        configStyle();
    }
    public CustomTable(
            TableModel model,
            ApelarResultado owner,
            PanelCategory category,
            JPanel previous,
            CoordinadorAplicacionApelarResultado coordinador
    ){
        super(model);
        this.apelarResultado = owner;
        this.category = category;
        this.previous = previous;
        this.coordinadorAplicacionApelarResultado = coordinador;
    }

    @Override
    public void setModel(TableModel model) {
        super.setModel(model);
        alingColumn();
    }

    private void configStyle() {

        setOpaque(true);
        this.setForeground(Color.BLACK);
        this.setRowHeight(60);

        this.setShowHorizontalLines(false);
        this.setShowVerticalLines(false);
        this.setGridColor(new Color(255, 255, 255));
        setIntercellSpacing(new Dimension(10, 10));

        this.setSelectionBackground(Color.white);
        this.setFillsViewportHeight(true);

        JTableHeader header = this.getTableHeader();
        header.setOpaque(false);
        alingColumn();

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
            ) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setBackground(Color.white);
                label.setForeground(Color.black);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                return label;
            }
        });

        header.setReorderingAllowed(false);
    }

    public void alingColumn() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return getColumnName(column).equals("Ver");
    }

    public void addColumnButton() {
        try {
            getColumn("Ver").setCellRenderer(new ButtonRenderer());

            getColumn("Ver").setCellEditor(
                    new ButtonEditor(
                            new JCheckBox(),
                            owner,
                            category,
                            coordinador
                    )
            );

        } catch (IllegalArgumentException e) {
            System.out.println("La tabla no tiene columna 'Ver'.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
