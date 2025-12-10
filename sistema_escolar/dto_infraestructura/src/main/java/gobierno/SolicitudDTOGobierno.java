/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gobierno;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Solicitud dto gobierno.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDTOGobierno {
    private Long id;
    private BecaDTOGobierno beca;
    private EstudianteDTOGobierno estudiante;
    private InformacionSocioeconomicaDTOGobierno informacionSocioeconomica;
    private HistorialAcademicoDTOGobierno historialAcademico;
    private List<DocumentoDTOGobierno> documentos;
    private LocalDate fecha;
    private String estado;

    /**
     * Instantiates a new Solicitud dto gobierno.
     */
    public SolicitudDTOGobierno() {}

    /**
     * Instantiates a new Solicitud dto gobierno.
     *
     * @param beca                      the beca
     * @param documentos                the documentos
     * @param estado                    the estado
     * @param estudiante                the estudiante
     * @param fecha                     the fecha
     * @param historialAcademico        the historial academico
     * @param id                        the id
     * @param informacionSocioeconomica the informacion socioeconomica
     */
    public SolicitudDTOGobierno(BecaDTOGobierno beca, List<DocumentoDTOGobierno> documentos, String estado, EstudianteDTOGobierno estudiante, LocalDate fecha, HistorialAcademicoDTOGobierno historialAcademico, Long id, InformacionSocioeconomicaDTOGobierno informacionSocioeconomica) {
        this.beca = beca;
        this.documentos = documentos;
        this.estado = estado;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.historialAcademico = historialAcademico;
        this.id = id;
        this.informacionSocioeconomica = informacionSocioeconomica;
    }

    /**
     * Gets beca.
     *
     * @return the beca
     */
    public BecaDTOGobierno getBeca() {
        return beca;
    }

    /**
     * Sets beca.
     *
     * @param beca the beca
     */
    public void setBeca(BecaDTOGobierno beca) {
        this.beca = beca;
    }

    /**
     * Gets documentos.
     *
     * @return the documentos
     */
    public List<DocumentoDTOGobierno> getDocumentos() {
        return documentos;
    }

    /**
     * Sets documentos.
     *
     * @param documentos the documentos
     */
    public void setDocumentos(List<DocumentoDTOGobierno> documentos) {
        this.documentos = documentos;
    }

    /**
     * Gets estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
    public HistorialAcademicoDTOGobierno getHistorialAcademico() {
        return historialAcademico;
    }

    /**
     * Sets historial academico.
     *
     * @param historialAcademico the historial academico
     */
    public void setHistorialAcademico(HistorialAcademicoDTOGobierno historialAcademico) {
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
    public InformacionSocioeconomicaDTOGobierno getInformacionSocioeconomica() {
        return informacionSocioeconomica;
    }

    /**
     * Sets informacion socioeconomica.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     */
    public void setInformacionSocioeconomica(InformacionSocioeconomicaDTOGobierno informacionSocioeconomica) {
        this.informacionSocioeconomica = informacionSocioeconomica;
    }
}
