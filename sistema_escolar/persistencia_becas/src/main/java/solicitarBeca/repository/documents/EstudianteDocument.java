package solicitarBeca.repository.documents;
import org.bson.types.ObjectId;
import solicitarBeca.dominio.Tutor;
import solicitarBeca.dominio.enums.Carrera;
import java.time.Instant;

/**
 * The type Estudiante document.
 *
 * @author Cortez, Manuel;
 */
public class EstudianteDocument {
    private ObjectId _id;
    private Long matricula;
    private String nombre;
    private Carrera carrera;
    private Tutor tutor;
    private String contrasenia;
    private String telefono;
    private String direccion;
    private String correo;
    private Instant creadoEn;

    /**
     * Instantiates a new Estudiante document.
     */
    public EstudianteDocument() {}

    /**
     * Instantiates a new Estudiante document.
     *
     * @param _id         the id
     * @param carrera     the carrera
     * @param contrasenia the contrasenia
     * @param correo      the correo
     * @param direccion   the direccion
     * @param matricula   the matricula
     * @param nombre      the nombre
     * @param telefono    the telefono
     * @param tutor       the tutor
     * @param creadoEn    the creado en
     */
    public EstudianteDocument(ObjectId _id,Carrera carrera, String contrasenia, String correo, String direccion, Long matricula, String nombre, String telefono, Tutor tutor, Instant  creadoEn) {
        this._id = _id;
        this.carrera = carrera;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.direccion = direccion;
        this.matricula = matricula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tutor = tutor;
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
     * Gets carrera.
     *
     * @return the carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * Sets carrera.
     *
     * @param carrera the carrera
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Gets contrasenia.
     *
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Sets contrasenia.
     *
     * @param contrasenia the contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Gets correo.
     *
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Sets correo.
     *
     * @param correo the correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Gets direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets direccion.
     *
     * @param direccion the direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets matricula.
     *
     * @return the matricula
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * Sets matricula.
     *
     * @param matricula the matricula
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets telefono.
     *
     * @param telefono the telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets tutor.
     *
     * @return the tutor
     */
    public Tutor getTutor() {
        return tutor;
    }

    /**
     * Sets tutor.
     *
     * @param tutor the tutor
     */
    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
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
