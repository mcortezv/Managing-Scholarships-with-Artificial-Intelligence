package excepciones;

public class NegociosSolicitarPagoException extends RuntimeException {
    public NegociosSolicitarPagoException(String msg) { super(msg); }
}