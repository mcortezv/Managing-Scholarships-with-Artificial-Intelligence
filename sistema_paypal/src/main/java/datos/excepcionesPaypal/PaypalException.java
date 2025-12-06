package datos.excepcionesPaypal;

public class PaypalException extends RuntimeException {
    public PaypalException(String message) {
        super(message);
    }
}
