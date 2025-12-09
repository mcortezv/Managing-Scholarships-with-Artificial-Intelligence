/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gobierno;

/**
 * The type Documento dto gobierno.
 *
 * @author Cortez, Manuel;
 */
public class DocumentoDTOGobierno {
    private Long identificador;
    private String tipo;
    private byte[] contenido;
    private EstudianteDTOGobierno estudiante;

    /**
     * Instantiates a new Documento dto gobierno.
     */
    public DocumentoDTOGobierno() {}

    /**
     * Instantiates a new Documento dto gobierno.
     *
     * @param contenido     the contenido
     * @param estudiante    the estudiante
     * @param identificador the identificador
     * @param tipo          the tipo
     */
    public DocumentoDTOGobierno(byte[] contenido, EstudianteDTOGobierno estudiante, Long identificador, String tipo) {
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
    public EstudianteDTOGobierno getEstudiante() {
        return estudiante;
    }

    /**
     * Sets estudiante.
     *
     * @param estudiante the estudiante
     */
    public void setEstudiante(EstudianteDTOGobierno estudiante) {
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
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets tipo.
     *
     * @param tipo the tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
