package gobierno.apelacion;

public class ApelacionDTOGobierno {
    private Long idSolicitudApelacion;
    private String redaccion;
    private Long idEstudiante;
    private String fechaApelacion;

    public ApelacionDTOGobierno() {
    }

    public Long getIdSolicitudApelacion() {
        return idSolicitudApelacion;
    }

    public void setIdSolicitudApelacion(Long idSolicitudApelacion) {
        this.idSolicitudApelacion = idSolicitudApelacion;
    }

    public String getRedaccion() {
        return redaccion;
    }

    public void setRedaccion(String redaccion) {
        this.redaccion = redaccion;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getFechaApelacion() {
        return fechaApelacion;
    }

    public void setFechaApelacion(String fechaApelacion) {
        this.fechaApelacion = fechaApelacion;
    }
}