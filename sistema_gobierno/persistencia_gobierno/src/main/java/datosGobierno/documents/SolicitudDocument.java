package datosGobierno.documents;
import datosGobierno.dominioGobierno.Beca;
import datosGobierno.dominioGobierno.HistorialAcademico;
import datosGobierno.dominioGobierno.InformacionSocioeconomica;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import org.bson.types.ObjectId;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Solicitud document.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDocument {
    private ObjectId _id;
    private Long id;
    private Beca beca;
    private ObjectId estudiante;
    private InformacionSocioeconomica informacionSocioeconomica;
    private HistorialAcademico historialAcademico;
    private List<ObjectId> documentos;
    private LocalDate fecha;
    private EstadoSolicitud estado;
    private Instant creadoEn;

    /**
     * Instantiates a new Solicitud document.
     */
    public SolicitudDocument() {}

    /**
     * Instantiates a new Solicitud document.
     *
     * @param _id                       the id
     * @param beca                      the beca
     * @param documentos                the documentos
     * @param estado                    the estado
     * @param estudiante                the estudiante
     * @param fecha                     the fecha
     * @param historialAcademico        the historial academico
     * @param id                        the id
     * @param informacionSocioeconomica the informacion socioeconomica
     * @param creadoEn                  the creado en
     */
    public SolicitudDocument(ObjectId _id, Beca beca, List<ObjectId> documentos, EstadoSolicitud estado, ObjectId estudiante, LocalDate fecha, HistorialAcademico historialAcademico, Long id, InformacionSocioeconomica informacionSocioeconomica, Instant  creadoEn) {
        this._id = _id;
        this.beca = beca;
        this.documentos = documentos;
        this.estado = estado;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.historialAcademico = historialAcademico;
        this.id = id;
        this.informacionSocioeconomica = informacionSocioeconomica;
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
     * Gets beca.
     *
     * @return the beca
     */
    public Beca getBeca() {
        return beca;
    }

    /**
     * Sets beca.
     *
     * @param beca the beca
     */
    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    /**
     * Gets documentos.
     *
     * @return the documentos
     */
    public List<ObjectId> getDocumentos() {
        return documentos;
    }

    /**
     * Sets documentos.
     *
     * @param documentos the documentos
     */
    public void setDocumentos(List<ObjectId> documentos) {
        this.documentos = documentos;
    }

    /**
     * Gets estado.
     *
     * @return the estado
     */
    public EstadoSolicitud getEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
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
     * Gets fecha.
     *
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Sets fecha.
     *
     * @param fecha the fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets historial academico.
     *
     * @return the historial academico
     */
    public HistorialAcademico getHistorialAcademico() {
        return historialAcademico;
    }

    /**
     * Sets historial academico.
     *
     * @param historialAcademico the historial academico
     */
    public void setHistorialAcademico(HistorialAcademico historialAcademico) {
        this.historialAcademico = historialAcademico;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets informacion socioeconomica.
     *
     * @return the informacion socioeconomica
     */
    public InformacionSocioeconomica getInformacionSocioeconomica() {
        return informacionSocioeconomica;
    }

    /**
     * Sets informacion socioeconomica.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     */
    public void setInformacionSocioeconomica(InformacionSocioeconomica informacionSocioeconomica) {
        this.informacionSocioeconomica = informacionSocioeconomica;
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
