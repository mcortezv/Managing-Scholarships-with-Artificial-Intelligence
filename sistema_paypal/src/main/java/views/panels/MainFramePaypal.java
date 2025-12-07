package views.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFramePaypal extends JDialog {

    private final PanelInicioSesionPaypal panelLogin;
    private final PanelRealizarPago panelRealizarPago;
    private final JPanel mainContainer;
    private final CardLayout cardLayout;

    public MainFramePaypal(){
        super((Frame) null, "Pasarela de Pago - Paypal", true);
        setUndecorated(true);
        setSize(600, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        panelLogin = new PanelInicioSesionPaypal();
        panelRealizarPago = new PanelRealizarPago();

        mainContainer.add(panelLogin, "LOGIN");
        mainContainer.add(panelRealizarPago, "CONFIRMACION");

        this.add(mainContainer);
    }

    public void mostrar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    public void mostrarLogin() {
        cardLayout.show(mainContainer, "LOGIN");
    }

    public void mostrarConfirmacion(String nombreUsuario, double saldo, double montoPagar) {
        panelRealizarPago.setDatos(nombreUsuario, saldo, montoPagar);
        cardLayout.show(mainContainer, "CONFIRMACION");
    }

    public void setAccionIngresar(ActionListener listener) {
        panelLogin.addIngresarListener(listener);
    }

    public void setAccionVolver(ActionListener listener) {
        panelLogin.addVolverListener(listener);
    }

    public String[] obtenerDatosLogin() {
        return panelLogin.getDatosLogin();
    }

    public void setAccionPagarFinal(ActionListener listener) {
        panelRealizarPago.setAccionPagarFinal(listener);
    }
}