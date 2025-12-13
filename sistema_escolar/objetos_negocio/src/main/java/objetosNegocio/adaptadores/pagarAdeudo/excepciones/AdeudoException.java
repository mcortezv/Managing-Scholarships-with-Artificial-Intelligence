package objetosNegocio.adaptadores.pagarAdeudo.excepciones;

/**
 * Excepción de Dominio para la Gestión de Adeudos.
 * <p>
 * Esta excepción personalizada se utiliza en la capa de Objetos de Negocio (Business Objects)
 * y Adaptadores para encapsular errores lógicos o de validación relacionados con el proceso de adeudos.
 * <p>
 * Al heredar de {@link RuntimeException}, permite interrumpir el flujo de ejecución cuando
 * no se cumplen las precondiciones del negocio (ej. "No hay deudas pendientes") sin obligar
 * a ensuciar el código con bloques try-catch excesivos en cada nivel.
 */
public class AdeudoException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param message Mensaje descriptivo que explica la razón del error de negocio.
     * Este mensaje suele mostrarse directamente al usuario final en la interfaz.
     */
    public AdeudoException(String message) {
        super(message);
    }
}