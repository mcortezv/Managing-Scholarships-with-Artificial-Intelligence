/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.validadores;

import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class Validadores {
    public static void validarTextoNoVacio(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío");
        }
    }
    
    public static void validarTema(String tema) {
        validarTextoNoVacio(tema, "tema");
        if (tema.length() < 5) {
            throw new IllegalArgumentException("El tema debe tener al menos 5 caracteres");
        }
        if (tema.length() > 50) {
            throw new IllegalArgumentException("El tema no puede exceder 50 caracteres");
        }
        if (!tema.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El tema debe contener solo letras.");
        }
    }
    
    public static void validarSeleccion(Object seleccion, String nombreCampo) {
        if (seleccion == null) {
            throw new IllegalArgumentException("Debe seleccionar " + nombreCampo);
        }
    }
    
    public static void validarFecha(LocalDate fecha, String nombreCampo) {
        if (fecha == null) {
            throw new IllegalArgumentException("Debe seleccionar " + nombreCampo);
        }
        LocalDate hoy = LocalDate.now();
        if (fecha.isBefore(hoy)) {
            throw new IllegalArgumentException("No se puede agendar una tutoría en una fecha pasada");
        }
    }
    
    
}
