package datosGobierno.documents;
import org.bson.types.ObjectId;
import solicitarBeca.dominio.enums.TipoDocumento;
import java.time.Instant;

/**
 * The type Documento document.
 *
 * @author Cortez, Manuel;
 */
public class DocumentoDocument {
    private ObjectId _id;
    private Long identificador;
    private TipoDocumento tipo;
    private byte[] contenido;
    private ObjectId estudiante;
    private Instant creadoEn;

    /**
     * Instantiates a new Documento document.
     */
    public DocumentoDocument() {}

    /**
     * Instantiates a new Documento document.
     *
     * @param contenido     the contenido
     * @param estudiante    the estudiante
     * @param identificador the identificador
     * @param tipo          the tipo
     * @param creadoEn      the creado en
     */
    public DocumentoDocument(byte[] contenido, ObjectId estudiante, Long identificador, TipoDocumento tipo, Instant  creadoEn) {
        this.contenido = contenido;
        this.estudiante = estudiante;
        this.identificador = identificador;
        this.tipo = tipo;
        this.creadoEn = creadoEn;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public ObjectId get_id() {
        return _id;
    }

    /**
     * Sets id.
     *
     * @param _id the id
     */
    public void set_id(ObjectId _id) {
        this._id = _id;
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
    public ObjectId getEstudiante() {
        return estudiante;
    }

    /**
     * Sets estudiante.
     *
     * @param estudiante the estudiante
     */
    public void setEstudiante(ObjectId estudiante) {
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

    /**
     * Gets creado en.
     *
     * @return the creado en
     */
    public Instant getCreadoEn() {
        return creadoEn;
    }

    /**
     * Sets creado en.
     *
     * @param creadoEn the creado en
     */
    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}
