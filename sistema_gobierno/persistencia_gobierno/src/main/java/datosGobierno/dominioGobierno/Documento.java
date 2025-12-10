/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;
import datosGobierno.dominioGobierno.enums.TipoDocumento;

/**
 * The type Documento.
 *
 * @author Cortez, Manuel;
 */
public class Documento {
    private Long identificador;
    private TipoDocumento tipo;
    private byte[] contenido;
    private Long estudiante;

    /**
     * Instantiates a new Documento.
     */
    public Documento() {}

    /**
     * Instantiates a new Documento.
     *
     * @param contenido     the contenido
     * @param estudiante    the estudiante
     * @param identificador the identificador
     * @param tipo          the tipo
     */
    public Documento(byte[] contenido, Long estudiante, Long identificador, TipoDocumento tipo) {
        this.contenido = contenido;
        this.estudiante = estudiante;
        this.identificador = identificador;
        this.tipo = tipo;
    }

    /**
     * Get contenido byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getContenido() {
        return contenido;
    }

    /**
     * Sets contenido.
     *
     * @param contenido the contenido
     */
    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    /**
     * Gets estudiante.
     *
     * @return the estudiante
     */
    public Long getEstudiante() {
        return estudiante;
    }

    /**
     * Sets estudiante.
     *
     * @param estudiante the estudiante
     */
    public void setEstudiante(Long estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * Gets identificador.
     *
     * @return the identificador
     */
    public Long getIdentificador() {
        return identificador;
    }

    /**
     * Sets identificador.
     *
     * @param identificador the identificador
     */
    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    /**
     * Gets tipo.
     *
     * @return the tipo
     */
    public TipoDocumento getTipo() {
        return tipo;
    }

    /**
     * Sets tipo.
     *
     * @param tipo the tipo
     */
    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }
}
