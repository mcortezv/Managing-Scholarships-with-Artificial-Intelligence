/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.validadores;

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
        if (tema.length() > 100) {
            throw new IllegalArgumentException("El tema no puede exceder 200 caracteres");
        }
    }
    
    public static void validarSeleccion(Object seleccion, String nombreCampo) {
        if (seleccion == null) {
            throw new IllegalArgumentException("Debe seleccionar " + nombreCampo);
        }
    }
}
