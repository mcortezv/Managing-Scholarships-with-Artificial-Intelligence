/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoGobierno;
import java.time.LocalDate;
import java.util.List;

/**
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

    public SolicitudDTO() {}

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

    public BecaDTO getBeca() {
        return beca;
    }

    public void setBeca(BecaDTO beca) {
        this.beca = beca;
    }

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public HistorialAcademicoDTO getHistorialAcademico() {
        return historialAcademico;
    }

    public void setHistorialAcademico(HistorialAcademicoDTO historialAcademico) {
        this.historialAcademico = historialAcademico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InformacionSocioeconomicaDTO getInformacionSocioeconomica() {
        return informacionSocioeconomica;
    }

    public void setInformacionSocioeconomica(InformacionSocioeconomicaDTO informacionSocioeconomica) {
        this.informacionSocioeconomica = informacionSocioeconomica;
    }

    @Override
    public String toString() {
        return estudiante.getNombre() + "\n" + beca.getTipo() + "\n\n" + beca.getDescripcion() + "\n\nMatricula: " +
                estudiante.getMatricula() + "\n\nCarrera: " + estudiante.getCarrera() + "\n\nPromedio General: " + historialAcademico.getPromedio()  +
                "\n\nEstado de la Solicitud: " + estado;
    }
}
