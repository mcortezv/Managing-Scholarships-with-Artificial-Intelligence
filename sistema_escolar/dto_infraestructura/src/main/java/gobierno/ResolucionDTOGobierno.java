package gobierno;
import java.time.LocalDate;

/**
 *
 * @author Cortez, Manuel;
 */
public class ResolucionDTOGobierno {
    private SolicitudDTOGobierno solicitud;
    private String decision;
    private String motivo;
    private LocalDate fechaEvaluacion;
    private Double precision;

    public ResolucionDTOGobierno(){}

    public ResolucionDTOGobierno(String decision, LocalDate fechaEvaluacion, String motivo, Double precision, SolicitudDTOGobierno solicitud) {
        this.decision = decision;
        this.fechaEvaluacion = fechaEvaluacion;
        this.motivo = motivo;
        this.precision = precision;
        this.solicitud = solicitud;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public SolicitudDTOGobierno getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudDTOGobierno solicitud) {
        this.solicitud = solicitud;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}
