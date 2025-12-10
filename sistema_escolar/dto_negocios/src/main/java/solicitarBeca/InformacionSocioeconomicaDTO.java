package solicitarBeca;
import java.math.BigDecimal;

/**
 * The type Informacion socioeconomica dto.
 *
 * @author Escalante, Sebastian.
 */
public class InformacionSocioeconomicaDTO {
    private BigDecimal ingresoTotalFamilarMensual;
    private boolean dependenciaEconomica;
    private String tipoVivienda;
    private boolean trabajo;
    private boolean deudas;

    /**
     * Instantiates a new Informacion socioeconomica dto.
     */
    public InformacionSocioeconomicaDTO() {}

    /**
     * Instantiates a new Informacion socioeconomica dto.
     *
     * @param trabajo                    the trabajo
     * @param tipoVivienda               the tipo vivienda
     * @param ingresoTotalFamilarMensual the ingreso total familar mensual
     * @param deudas                     the deudas
     */
    public InformacionSocioeconomicaDTO(boolean trabajo, String tipoVivienda, BigDecimal ingresoTotalFamilarMensual, boolean deudas) {
        this.trabajo = trabajo;
        this.tipoVivienda = tipoVivienda;
        this.ingresoTotalFamilarMensual = ingresoTotalFamilarMensual;
        this.deudas = deudas;
    }

    /**
     * Instantiates a new Informacion socioeconomica dto.
     *
     * @param ingresoBig           the ingreso big
     * @param dependenciaEconomica the dependencia economica
     * @param generaIngreso        the genera ingreso
     */
    public InformacionSocioeconomicaDTO(BigDecimal ingresoBig, boolean dependenciaEconomica, boolean generaIngreso) {
        this.ingresoTotalFamilarMensual = ingresoBig;
        this.trabajo = generaIngreso;
        this.dependenciaEconomica = dependenciaEconomica;
    }


    /**
     * Is deudas boolean.
     *
     * @return the boolean
     */
    public boolean isDeudas() {
        return deudas;
    }

    /**
     * Sets deudas.
     *
     * @param deudas the deudas
     */
    public void setDeudas(boolean deudas) {
        this.deudas = deudas;
    }

    /**
     * Gets ingreso total familar mensual.
     *
     * @return the ingreso total familar mensual
     */
    public BigDecimal getIngresoTotalFamilarMensual() {
        return ingresoTotalFamilarMensual;
    }

    /**
     * Sets ingreso total familar mensual.
     *
     * @param ingresoTotalFamilarMensual the ingreso total familar mensual
     */
    public void setIngresoTotalFamilarMensual(BigDecimal ingresoTotalFamilarMensual) {
        this.ingresoTotalFamilarMensual = ingresoTotalFamilarMensual;
    }

    /**
     * Gets tipo vivienda.
     *
     * @return the tipo vivienda
     */
    public String getTipoVivienda() {
        return tipoVivienda;
    }

    /**
     * Sets tipo vivienda.
     *
     * @param tipoVivienda the tipo vivienda
     */
    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    /**
     * Is trabajo boolean.
     *
     * @return the boolean
     */
    public boolean isTrabajo() {
        return trabajo;
    }

    /**
     * Sets trabajo.
     *
     * @param trabajo the trabajo
     */
    public void setTrabajo(boolean trabajo) {
        this.trabajo = trabajo;
    }

    /**
     * Gets dependencia economica.
     *
     * @return the dependencia economica
     */
    public String getDependenciaEconomica() {
        return null;
    }

    /**
     * Gets ingreso familiar st.
     *
     * @return the ingreso familiar st
     */
    public String getIngresoFamiliarSt() {
        return null;
    }

    /**
     * Gets trabajo st.
     *
     * @return the trabajo st
     */
    public String getTrabajoSt() {
        return null;
    }
}