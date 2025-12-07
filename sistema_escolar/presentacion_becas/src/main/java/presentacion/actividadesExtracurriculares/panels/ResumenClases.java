/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.panels;

import dto.actividades.ActividadDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ResumenClases extends PanelActividades {

    private JPanel panelContenido;
    private JPanel panelGrupo;

    public ResumenClases(ActividadesExtracurriculares actividadesExtracurriculares, CoordinadorAplicacionActividades coordinadorAplicacionActividades) {
        super(actividadesExtracurriculares, coordinadorAplicacionActividades);
    }

    @Override
    public void startComponents() {
        centralPanel.removeAll();
        centralPanel.setBackground(new Color(240, 240, 240));
        centralPanel.add(Box.createVerticalStrut(20));
        JLabel titulo = new JLabel("LISTA DE GRUPOS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(20));

        panelContenido = new JPanel();
        panelContenido.setBackground(Color.white);
        panelContenido.setMaximumSize(new Dimension(600, 500));
        panelContenido.setPreferredSize(new Dimension(700, 500));
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenido.setBorder(new EmptyBorder(20, 20, 20, 20));
        centralPanel.add(panelContenido);
    }

    public void obtenerGrupos(GruposResponseDTO gruposResponseDTO) {
        panelContenido.removeAll();
        //  GruposResponseDTO gruposResponseDTO= coordinadorAplicacionActividades.getGruposResponseDTO();
        for (GrupoDTO grupo : gruposResponseDTO.getGrupos()) {
            panelGrupo = new JPanel();
            panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.Y_AXIS));
            panelGrupo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            panelGrupo.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
            panelGrupo.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(30, 60, 160), 2),
                    new EmptyBorder(10, 15, 10, 15)
            ));
            JLabel labelNombre = new JLabel(grupo.getNombreActividad());
            labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelNombre.setFont(new Font("SansSerif", Font.BOLD, 16));
            JLabel labelPrecio = new JLabel("Precio " + (String.valueOf(grupo.getCosto())));
            labelPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelPrecio.setFont(new Font("SansSerif", Font.PLAIN, 16));
            JLabel labelUnidad = new JLabel("Unidad: " + grupo.getUnidad());
            labelUnidad.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelUnidad.setFont(new Font("SansSerif", Font.PLAIN, 16));
            JLabel labelHora = new JLabel(grupo.getHoraInicio() + " - " + grupo.getHoraFin());
            labelHora.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelHora.setFont(new Font("SansSerif", Font.PLAIN, 16));
            panelGrupo.add(labelNombre);
            panelGrupo.add(labelPrecio);
            panelGrupo.add(labelUnidad);
            panelGrupo.add(labelHora);
            panelGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            panelContenido.add(panelGrupo);

            panelGrupo.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    coordinadorAplicacionActividades.mostrarGrupo(grupo);
                }

            });

            botonVolver.addActionListener(e -> {
                coordinadorAplicacionActividades.inscribirActividad();
            });
        }
        panelContenido.revalidate();
        panelContenido.repaint();
    }

}
