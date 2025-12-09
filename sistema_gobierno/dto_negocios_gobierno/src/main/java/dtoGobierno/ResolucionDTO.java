package dtoGobierno;
import java.time.LocalDate;

/**
 * The type Resolucion dto.
 *
 * @author Cortez, Manuel;
 */
public class ResolucionDTO {
    private SolicitudDTO solicitud;
    private String decision;
    private String motivo;
    private LocalDate fechaEvaluacion;
    private Double precision;

    /**
     * Instantiates a new Resolucion dto.
     */
    public ResolucionDTO(){}

    /**
     * Instantiates a new Resolucion dto.
     *
     * @param decision        the decision
     * @param fechaEvaluacion the fecha evaluacion
     * @param motivo          the motivo
     * @param precision       the precision
     * @param solicitud       the solicitud
     */
    public ResolucionDTO(String decision, LocalDate fechaEvaluacion, String motivo, Double precision, SolicitudDTO solicitud) {
        this.decision = decision;
        this.fechaEvaluacion = fechaEvaluacion;
        this.motivo = motivo;
        this.precision = precision;
        this.solicitud = solicitud;
    }

    /**
     * Gets decision.
     *
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * Sets decision.
     *
     * @param decision the decision
     */
    public void setDecision(String decision) {
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
    public SolicitudDTO getSolicitud() {
        return solicitud;
    }

    /**
     * Sets solicitud.
     *
     * @param solicitud the solicitud
     */
    public void setSolicitud(SolicitudDTO solicitud) {
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
