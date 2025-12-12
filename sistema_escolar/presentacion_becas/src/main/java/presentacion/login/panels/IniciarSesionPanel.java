package presentacion.login.panels;
import itson.LoginDTOItson;
import presentacion.CoordinadorAplicacion;
import presentacion.login.MainFrame;
import presentacion.login.exceptions.ContraseniaInvalidaException;
import presentacion.login.exceptions.IDInvalidoException;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.TextField;
import javax.swing.*;
import java.awt.*;

/**
 * The type Iniciar sesion panel.
 *
 * @author Cortez, Manuel;
 */
public class IniciarSesionPanel extends Panel {
    private Label titulo;
    private ImgPanel img;
    private Label lblUsuario;
    private TextField txtUsuario;
    private Label lblPassword;
    private PasswordField txtPassword;
    private Button btnIniciarSesion;
    private final CoordinadorAplicacion coordinadorAplicacion;

    /**
     * Instantiates a new Iniciar sesion panel.
     *
     * @param frame                 the frame
     * @param coordinadorAplicacion the coordinador aplicacion
     */
    public IniciarSesionPanel(MainFrame frame, CoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));
        btnBack.setVisible(false);

        titulo = new Label("Iniciar Sesión");
        titulo.setFont(Style.TITLE_FONT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        centralPanel.add(titulo);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        img = new ImgPanel("/assets/usuario.png");
        img.setMaximumSize(new Dimension(300, 300));
        img.setAlignmentY(CENTER_ALIGNMENT);
        centralPanel.add(img);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        lblUsuario = new Label("Usuario");
        txtUsuario = new TextField(1);
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblUsuario);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        txtUsuario.setMaximumSize(new Dimension(400, 60));
        centralPanel.add(txtUsuario);
        centralPanel.add(Box.createVerticalStrut(Style.BLOQUE_ESPACIO));

        lblPassword = new Label("Contraseña");
        txtPassword = new PasswordField(1);
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(lblPassword);
        centralPanel.add(Box.createVerticalStrut(Style.LBL_ESPACIO));
        txtPassword.setMaximumSize(new Dimension(400, 60));
        centralPanel.add(txtPassword);
        centralPanel.add(Box.createVerticalStrut(Style.TITULO_ESPACIO));

        btnIniciarSesion = new Button("Iniciar Sesión");
        btnIniciarSesion.setAlignmentX(CENTER_ALIGNMENT);
        btnIniciarSesion.setEnabled(false);
        centralPanel.add(btnIniciarSesion);
        
        Runnable toggle = () -> btnIniciarSesion.setEnabled(
            !txtUsuario.getText().trim().isEmpty() &&
            txtPassword.getPassword().length > 0
        );
        
        javax.swing.event.DocumentListener dl = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { toggle.run(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { toggle.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { toggle.run(); }
        };
        txtUsuario.getDocument().addDocumentListener(dl);
        txtPassword.getDocument().addDocumentListener(dl);
        txtPassword.addActionListener(e -> btnIniciarSesion.doClick());
        toggle.run();

        btnIniciarSesion.addActionListener(e -> {
            try {
                String usuario = txtUsuario.getText().trim();
                Long usuarioLong = Long.parseLong(usuario);
                String contrasenia = new String(txtPassword.getPassword());
                LoginDTOItson loginDTO = new LoginDTOItson(usuarioLong,contrasenia);
                boolean verificarLogin = coordinadorAplicacion.intentarIniciarSesion(loginDTO);
                if(verificarLogin) {
                    txtUsuario.setText("");
                    txtPassword.setText("");
                    Style.DARK_MODE = false;
                    coordinadorAplicacion.guardarInfoEstudiante(loginDTO);
                    mainFrame.showPanel("hubPanel");
                    mainFrame.getNorthPanel().setVisible(true);
                    SwingUtilities.invokeLater(() -> {
                        SwingUtilities.updateComponentTreeUI(mainFrame);
                        mainFrame.repaint();
                    });
                }else{
                    JOptionPane.showMessageDialog(this, "La contraseña no coincide con el usuario");
                }
            } catch (IDInvalidoException | ContraseniaInvalidaException ex) {
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainFrame, "Error intentando iniciar sesión.", "Inicio de sesión", JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
