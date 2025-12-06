package datos.dominioItson.pagarAdeudo;

import java.util.Date;

public class Prestamo {
    private Long idEstudiante;
    private double costo;
    private String fechaPrestamo;
    private String fechaDevolucionProgramada;
    private String isbn;
    private String titulo;
    private String campus;
    private String detalles;
    private String estatus;


    public Prestamo(){}

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Long getIdEstudiante(){
        return this.idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante){
        this.idEstudiante = idEstudiante;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucionProgramada() {
        return fechaDevolucionProgramada;
    }

    public void setFechaDevolucionProgramada(String fechaDevolucionProgramada) {
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
