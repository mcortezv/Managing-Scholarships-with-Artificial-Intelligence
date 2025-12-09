package datosGobierno.dominioGobierno;
import datosGobierno.dominioGobierno.enums.Decision;
import java.time.LocalDate;
import java.util.Optional;

/**
 *
 * @author Cortez, Manuel;
 */
public class Resolucion {
    private Solicitud solicitud;
    private Decision decision;
    private String motivo;
    private LocalDate fechaEvaluacion;
    private Double precison;

    public Resolucion(){}

    public Resolucion(Decision decision, LocalDate fechaEvaluacion, String motivo, Double precison, Solicitud solicitud) {
        this.decision = decision;
        this.fechaEvaluacion = fechaEvaluacion;
        this.motivo = motivo;
        this.precison = precison;
        this.solicitud = solicitud;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
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

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Double getPrecison() {
        return precison;
    }

    public void setPrecison(Double precison) {
        this.precison = precison;
    }
}
