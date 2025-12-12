package views.validadoresPaypal;

public class CredencialInvalida extends RuntimeException {
    public CredencialInvalida(String message) {
        super(message);
    }
}
