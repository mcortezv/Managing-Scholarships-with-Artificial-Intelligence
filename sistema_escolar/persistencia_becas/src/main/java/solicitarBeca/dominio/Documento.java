/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solicitarBeca.dominio;
import solicitarBeca.dominio.enums.TipoDocumento;

import java.util.Arrays;

/**
 *
 * @author Cortez, Manuel;
 */
public class Documento {
    private Long identificador;
    private TipoDocumento tipo;
    private byte[] contenido;
    private Estudiante estudiante;

    public Documento() {}

    public Documento(byte[] contenido, Estudiante estudiante, Long identificador, TipoDocumento tipo) {
        this.contenido = contenido;
        this.estudiante = estudiante;
        this.identificador = identificador;
        this.tipo = tipo;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Documento{" +
                ", identificador=" + identificador +
                ", tipo=" + tipo +
                ", estudiante=" + estudiante +
                '}';
    }
}
