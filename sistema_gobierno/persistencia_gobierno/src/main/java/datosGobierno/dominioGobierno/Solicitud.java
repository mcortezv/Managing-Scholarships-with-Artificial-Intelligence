/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Solicitud.
 *
 * @author Cortez, Manuel;
 */
public class Solicitud {
    private Long id;
    private Beca beca;
    private Estudiante estudiante;
    private InformacionSocioeconomica informacionSocioeconomica;
    private HistorialAcademico historialAcademico;
    private List<Documento> documentos;
    private LocalDate fecha;
    private EstadoSolicitud estado;

    /**
     * Instantiates a new Solicitud.
     */
    public Solicitud() {}

    /**
     * Instantiates a new Solicitud.
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
    public Solicitud(Beca beca, List<Documento> documentos, EstadoSolicitud estado, Estudiante estudiante, LocalDate fecha, HistorialAcademico historialAcademico, long id, InformacionSocioeconomica informacionSocioeconomica) {
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
    public List<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * Sets documentos.
     *
     * @param documentos the documentos
     */
    public void setDocumentos(List<Documento> documentos) {
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
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Sets estudiante.
     *
     * @param estudiante the estudiante
     */
    public void setEstudiante(Estudiante estudiante) {
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
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
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
}
