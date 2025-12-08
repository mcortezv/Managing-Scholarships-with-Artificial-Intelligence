package presentacion.login;
import dtoGobierno.EvaluadorLoginDTO;
import presentacion.coordinacion.MainFrame;
import presentacion.coordinacion.interfaces.ICoordinadorAplicacion;
import presentacion.styles.*;
import presentacion.styles.Button;
import presentacion.styles.Label;
import presentacion.styles.Panel;
import presentacion.styles.TextField;
import javax.swing.*;
import java.awt.*;


public class IniciarSesionPanel extends Panel {
    private Label titulo;
    private ImgPanel img;
    private Label lblUsuario;
    private TextField txtUsuario;
    private Label lblPassword;
    private PasswordField txtPassword;
    private Button btnIniciarSesion;
    private ICoordinadorAplicacion coordinadorAplicacion;

    public IniciarSesionPanel(MainFrame frame, ICoordinadorAplicacion coordinadorAplicacion) {
        super(frame, coordinadorAplicacion);
        this.coordinadorAplicacion = coordinadorAplicacion;
    }

    @Override
    public void startComponents() {
       southPanel.setVisible(false);
        centralPanel.add(Box.createVerticalStrut(Style.TOP_ESPACIO));

        titulo = new Label("Iniciar Sesión Evaluador");
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
        txtUsuario = new presentacion.styles.TextField(1);
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
                Long matricula = Long.parseLong(txtUsuario.getText());
                String contrasenia = txtPassword.getText();
                EvaluadorLoginDTO dto = new EvaluadorLoginDTO();
                dto.setMatricula(matricula);
                dto.setContrasenia(contrasenia);
                coordinadorAplicacion.iniciarSesion(dto);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(mainFrame, "La matricula debe ser un numero entero", "Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
