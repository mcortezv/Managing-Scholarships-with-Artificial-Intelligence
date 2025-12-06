package datos.dominioItson.pagarAdeudo;

import datos.dominioItson.pagarAdeudo.enums.Campus;

public class Clase {
    private Long idEstudiante;
    private double costo;
    private String nombre;
    private String horario;
    private String profesor;
    private String aula;
    private Campus campus;
    private String detalles;
    private String estatus;


    public Clase(){}

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

    public Campus getCampus() {
        return campus;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante){
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Campus getCampus(String s) {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
