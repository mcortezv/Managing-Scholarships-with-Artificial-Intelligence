/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoGobierno;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Solicitud dto.
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDTO {
    private long id;
    private BecaDTO beca;
    private EstudianteDTO estudiante;
    private InformacionSocioeconomicaDTO informacionSocioeconomica;
    private HistorialAcademicoDTO historialAcademico;
    private List<DocumentoDTO> documentos;
    private LocalDate fecha;
    private String estado;

    /**
     * Instantiates a new Solicitud dto.
     */
    public SolicitudDTO() {}

    /**
     * Instantiates a new Solicitud dto.
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
    public SolicitudDTO(BecaDTO beca, List<DocumentoDTO> documentos, String estado, EstudianteDTO estudiante, LocalDate fecha, HistorialAcademicoDTO historialAcademico, long id, InformacionSocioeconomicaDTO informacionSocioeconomica) {
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
    public BecaDTO getBeca() {
        return beca;
    }

    /**
     * Sets beca.
     *
     * @param beca the beca
     */
    public void setBeca(BecaDTO beca) {
        this.beca = beca;
    }

    /**
     * Gets documentos.
     *
     * @return the documentos
     */
    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    /**
     * Sets documentos.
     *
     * @param documentos the documentos
     */
    public void setDocumentos(List<DocumentoDTO> documentos) {
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
    public HistorialAcademicoDTO getHistorialAcademico() {
        return historialAcademico;
    }

    /**
     * Sets historial academico.
     *
     * @param historialAcademico the historial academico
     */
    public void setHistorialAcademico(HistorialAcademicoDTO historialAcademico) {
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
    public InformacionSocioeconomicaDTO getInformacionSocioeconomica() {
        return informacionSocioeconomica;
    }

    /**
     * Sets informacion socioeconomica.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     */
    public void setInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomica) {
        this.informacionSocioeconomica = informacionSocioeconomica;
    }

    @Override
    public String toString() {
        return estudiante.getNombre() + "\n" +
                beca.getTipo() + "\n\n" +
                beca.getDescripcion() +
                "\n\nMatricula: " + estudiante.getMatricula() +
                "\nCarrera: " + estudiante.getCarrera() +
                "\nPromedio General: " + historialAcademico.getPromedio()  +
                "\nIngreso Mensual: " + informacionSocioeconomica.getIngresoTotalFamilarMensual()  +
                "\nTipo Vivienda: " + informacionSocioeconomica.getTipoVivienda()  +
                "\nCarga Académica: " + historialAcademico.getCargaAcademica()  +
                "\nProcentaje Bajas: " + historialAcademico.getPorcentajeBajas()  +
                "\nÍndice Reprobación: " + historialAcademico.getIndiceReprobacion()  +
                "\nSemestre: " + historialAcademico.getSemestre()  +
                "\n\nEstado de la Solicitud: " + estado;
    }
}
