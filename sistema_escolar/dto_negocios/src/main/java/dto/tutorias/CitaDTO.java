package dto.tutorias;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author katia
 */
public class CitaDTO {
    private Long id;
    private Long matriculaAlumno;
    private Long idTutor;
    private Long idHorario;
    private Long idMateria;
    
    //para historial, pantallas
    private String nombreTutor;
    private String nombreMateria;

    private String tema;
    private String modalidad;
    private LocalDate fecha;
    private LocalTime hora;
    private String ubicacion;  
    private String estado;

    public CitaDTO() {
    }

    public CitaDTO(Long id, Long matriculaAlumno, Long idTutor, Long idHorario, Long idMateria, String nombreTutor, String nombreMateria, String tema, String modalidad, LocalDate fecha, LocalTime hora, String ubicacion, String estado) {
        this.id = id;
        this.matriculaAlumno = matriculaAlumno;
        this.idTutor = idTutor;
        this.idHorario = idHorario;
        this.idMateria = idMateria;
        this.nombreTutor = nombreTutor;
        this.nombreMateria = nombreMateria;
        this.tema = tema;
        this.modalidad = modalidad;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(Long matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CitaDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", tutor='" + nombreTutor + '\'' +
                ", materia='" + nombreMateria + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }



}
