package views.validadoresBanco;

import views.exceptions.BancoViewsException;

import java.time.YearMonth;
import java.util.regex.Pattern;

public class Validador {

    private static final Pattern PATRON_NOMBRE = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    private static final Pattern PATRON_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PATRON_TELEFONO = Pattern.compile("^\\d{10}$");
    private static final Pattern PATRON_CVV = Pattern.compile("^\\d{3,4}$");

    public static void validarNumeroTarjeta(String numeroTarjeta) {
        if (esVacio(numeroTarjeta)) {
            throw new BancoViewsException("El número de tarjeta es requerido.");
        }
        String limpio = numeroTarjeta.replace("-", "").replace(" ", "").trim();

        if (!limpio.matches("\\d{16}")) {
            throw new BancoViewsException("El número de tarjeta debe contener 16 dígitos.");
        }

    }

    public static void validarFechaCaducidad(String fecha) {
        if (esVacio(fecha)) {
            throw new BancoViewsException("La fecha de vencimiento es requerida.");
        }
        if (!fecha.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
            throw new BancoViewsException("La fecha debe tener el formato MM/YY (Ej. 12/26).");
        }
        int mesInput;
        int anioInput;
        try {
            String[] partes = fecha.split("/");
            mesInput = Integer.parseInt(partes[0]);
            anioInput = Integer.parseInt("20" + partes[1]);
        } catch (NumberFormatException e) {
            throw new BancoViewsException("Fecha inválida: caracteres no numéricos.");
        }
        try {
            YearMonth fechaTarjeta = YearMonth.of(anioInput, mesInput);
            YearMonth fechaActual = YearMonth.now();

            if (fechaTarjeta.isBefore(fechaActual)) {
                throw new BancoViewsException("La tarjeta está vencida.");
            }
        } catch (java.time.DateTimeException e) {
            throw new BancoViewsException("Fecha inválida (Mes o año incorrecto).");
        }
    }


    public static void validarCVV(String cvv) {
        if (esVacio(cvv)) {
            throw new BancoViewsException("El CVV es requerido.");
        }
        if (!PATRON_CVV.matcher(cvv).matches()) {
            throw new BancoViewsException("El CVV debe ser de 3 o 4 dígitos.");
        }
    }


    public static void validarNombre(String nombre) {
        if (esVacio(nombre)) {
            throw new BancoViewsException("El nombre del titular es requerido.");
        }
        if (!PATRON_NOMBRE.matcher(nombre).matches()) {
            throw new BancoViewsException("El nombre solo puede contener letras.");
        }
    }


    public static void validarTelefono(String telefono) {
        if (esVacio(telefono)) {
            throw new BancoViewsException("El teléfono es requerido.");
        }
        if (!PATRON_TELEFONO.matcher(telefono).matches()) {
            throw new BancoViewsException("El teléfono debe ser de 10 dígitos numéricos.");
        }
    }


    public static void validarEmail(String email) {
        if (esVacio(email)) {
            throw new BancoViewsException("El email es requerido.");
        }
        if (!PATRON_EMAIL.matcher(email).matches()) {
            throw new BancoViewsException("El formato del email es inválido.");
        }
    }

    private static boolean esVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}