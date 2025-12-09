package solicitarBeca;

/**
 * The type Documento dto.
 *
 * @author Escalante, Sebastian.
 */
public class DocumentoDTO {
    private Long identificador;
    private String tipo;
    private byte[] contenido;
    private EstudianteDTO estudiante;

    /**
     * Instantiates a new Documento dto.
     */
    public DocumentoDTO() {
    }

    /**
     * Instantiates a new Documento dto.
     *
     * @param contenido     the contenido
     * @param estudiante    the estudiante
     * @param identificador the identificador
     * @param tipo          the tipo
     */
    public DocumentoDTO(byte[] contenido, EstudianteDTO estudiante, Long identificador, String tipo) {
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
    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    /**
     * Sets estudiante.
     *
     * @param estudiante the estudiante
     */
    public void setEstudiante(EstudianteDTO estudiante) {
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
