package datos.repositoryItson.documents.pagarAdeudo;

import java.util.Date;

public class PrestamoDocument {
    private Long idEstudiante;
    private double costo;
    private Date fechaPrestamo;
    private Date fechaDevolucionProgramada;
    private String isbn;
    private String titulo;
    private String campus;
    private String detalles;
    private String estatus;


    public PrestamoDocument(){}

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Long getIdEstudiante(){
        return this.idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante){
        this.idEstudiante = idEstudiante;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucionProgramada() {
        return fechaDevolucionProgramada;
    }

    public void setFechaDevolucionProgramada(Date fechaDevolucionProgramada) {
        this.fechaDevolucionProgramada = fechaDevolucionProgramada;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
