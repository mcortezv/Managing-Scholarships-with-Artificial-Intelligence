package dto.pagarAdeudo;

public class SolicitudPagoDTO {
    private Long idEstudiante;
    private String referenciaPago;
    private String fechaPago;
    private double montoPagado;
    private String metodoPago;
    private String estatusPago;
    private String tipoAdeudo;

    public SolicitudPagoDTO() {
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }


    public String getTipoAdeudo() {
        return tipoAdeudo;
    }

    public void setTipoAdeudo(String tipoAdeudo) {
        this.tipoAdeudo = tipoAdeudo;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstatusPago() {
        return estatusPago;
    }

    public void setEstatusPago(String estatusPago) {
        this.estatusPago = estatusPago;
    }
}
