package views.validadoresPaypal;

public class Validador {

    public static void validarCorreoPaypal(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new CredencialInvalida("Favor de introducir un correo o teléfono");
        }
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new CredencialInvalida("Favor de introducir un correo o teléfono válido");
        }
    }

    public static void validarContraseniaPaypal(String contra) {
        if (contra == null || contra.trim().isEmpty()) {
            throw new CredencialInvalida("Ingrese una contraseña");
        }
        if (!contra.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            throw new CredencialInvalida("Favor de introducir una contraseña válida");
        }
    }
}