/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.daoGobierno.documents;
import datosGobierno.dominioGobierno.*;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import org.bson.types.ObjectId;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDocument {
    private Long id;
    private Beca beca;
    private ObjectId estudiante;
    private InformacionSocioeconomica informacionSocioeconomica;
    private HistorialAcademico historialAcademico;
    private List<ObjectId> documentos;
    private LocalDate fecha;
    private EstadoSolicitud estado;

    public SolicitudDocument() {}

    public SolicitudDocument(Beca beca, List<ObjectId> documentos, EstadoSolicitud estado, ObjectId estudiante, LocalDate fecha, HistorialAcademico historialAcademico, Long id, InformacionSocioeconomica informacionSocioeconomica) {
        this.beca = beca;
        this.documentos = documentos;
        this.estado = estado;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.historialAcademico = historialAcademico;
        this.id = id;
        this.informacionSocioeconomica = informacionSocioeconomica;
    }

    public Beca getBeca() {
        return beca;
    }

    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    public List<ObjectId> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<ObjectId> documentos) {
        this.documentos = documentos;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public ObjectId getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(ObjectId estudiante) {
        this.estudiante = estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public HistorialAcademico getHistorialAcademico() {
        return historialAcademico;
    }

    public void setHistorialAcademico(HistorialAcademico historialAcademico) {
        this.historialAcademico = historialAcademico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InformacionSocioeconomica getInformacionSocioeconomica() {
        return informacionSocioeconomica;
    }

    public void setInformacionSocioeconomica(InformacionSocioeconomica informacionSocioeconomica) {
        this.informacionSocioeconomica = informacionSocioeconomica;
    }
}
