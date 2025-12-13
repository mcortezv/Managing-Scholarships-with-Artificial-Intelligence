package excepciones;

/**
 * Excepción de Negocio para el Caso de Uso "Solicitar Pago".
 * <p>
 * Esta excepción se utiliza para encapsular errores que ocurren durante la orquestación
 * del proceso de pago en la capa de controladores de negocio.
 * <p>
 * Al extender de {@link RuntimeException}, es una excepción "no verificada" (unchecked),
 * lo que significa que indica condiciones de error que el sistema no necesariamente
 * espera recuperar en el flujo normal, o que se propagarán hasta una capa global de manejo de errores.
 */
public class NegociosSolicitarPagoException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param msg Mensaje descriptivo del error, útil para mostrar feedback al usuario
     * o para registrar en los logs del sistema.
     */
    public NegociosSolicitarPagoException(String msg) {
        super(msg);
    }
}