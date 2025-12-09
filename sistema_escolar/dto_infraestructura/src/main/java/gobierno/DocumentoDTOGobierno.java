/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gobierno;

/**
 *
 * @author Cortez, Manuel;
 */
public class DocumentoDTOGobierno {
    private Long identificador;
    private String tipo;
    private byte[] contenido;
    private EstudianteDTOGobierno estudiante;

    public DocumentoDTOGobierno() {}

    public DocumentoDTOGobierno(byte[] contenido, EstudianteDTOGobierno estudiante, Long identificador, String tipo) {
        this.contenido = contenido;
        this.estudiante = estudiante;
        this.identificador = identificador;
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public EstudianteDTOGobierno getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTOGobierno estudiante) {
        this.estudiante = estudiante;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
