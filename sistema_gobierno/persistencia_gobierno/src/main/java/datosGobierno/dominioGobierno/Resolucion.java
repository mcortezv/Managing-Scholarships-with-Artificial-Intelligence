package datosGobierno.dominioGobierno;
import datosGobierno.dominioGobierno.enums.Decision;
import java.time.LocalDate;

/**
 * The type Resolucion.
 */
public class Resolucion {
    private Solicitud solicitud;
    private Decision decision;
    private String motivo;
    private LocalDate fechaEvaluacion;
    private Double precision;

    /**
     * Instantiates a new Resolucion.
     */
    public Resolucion(){}

    /**
     * Instantiates a new Resolucion.
     *
     * @param decision        the decision
     * @param fechaEvaluacion the fecha evaluacion
     * @param motivo          the motivo
     * @param precison        the precison
     * @param solicitud       the solicitud
     */
    public Resolucion(Decision decision, LocalDate fechaEvaluacion, String motivo, Double precison, Solicitud solicitud) {
        this.decision = decision;
        this.fechaEvaluacion = fechaEvaluacion;
        this.motivo = motivo;
        this.precision = precison;
        this.solicitud = solicitud;
    }

    /**
     * Gets decision.
     *
     * @return the decision
     */
    public Decision getDecision() {
        return decision;
    }

    /**
     * Sets decision.
     *
     * @param decision the decision
     */
    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    /**
     * Gets fecha evaluacion.
     *
     * @return the fecha evaluacion
     */
    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    /**
     * Sets fecha evaluacion.
     *
     * @param fechaEvaluacion the fecha evaluacion
     */
    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    /**
     * Gets motivo.
     *
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets motivo.
     *
     * @param motivo the motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Gets solicitud.
     *
     * @return the solicitud
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     * Sets solicitud.
     *
     * @param solicitud the solicitud
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Gets precision.
     *
     * @return the precision
     */
    public Double getPrecision() {
        return precision;
    }

    /**
     * Sets precision.
     *
     * @param precision the precision
     */
    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}
