package datos.dtosBanco;

public class DatosTarjetaDTO {

    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaVencimiento;
    private String cvv;

    private String telefono;
    private String email;

    public DatosTarjetaDTO() {
    }

    public DatosTarjetaDTO(String numeroTarjeta, String nombreTitular, String fechaVencimiento, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
    }


    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCv() {
        return cvv;
    }

    public void setCv(String cvv) {
        this.cvv = cvv;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}