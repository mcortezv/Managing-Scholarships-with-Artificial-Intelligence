/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;
import datosGobierno.dominioGobierno.enums.TipoVivienda;
import java.math.BigDecimal;

/**
 * The type Informacion socioeconomica.
 *
 * @author Cortez, Manuel;
 */
public class InformacionSocioeconomica {
    private  BigDecimal ingresoTotalFamilarMensual;
    private TipoVivienda tipoVivienda;
    private boolean trabajo;
    private boolean deudas;

    /**
     * Instantiates a new Informacion socioeconomica.
     */
    public InformacionSocioeconomica() {}

    /**
     * Instantiates a new Informacion socioeconomica.
     *
     * @param ingresoTotalFamilarMensual the ingreso total familar mensual
     * @param tipoVivienda               the tipo vivienda
     * @param trabajo                    the trabajo
     * @param deudas                     the deudas
     */
    public InformacionSocioeconomica(BigDecimal ingresoTotalFamilarMensual, TipoVivienda tipoVivienda, boolean trabajo, boolean deudas) {
        this.trabajo = trabajo;
        this.tipoVivienda = tipoVivienda;
        this.ingresoTotalFamilarMensual = ingresoTotalFamilarMensual;
        this.deudas = deudas;
    }

    /**
     * Gets trabajo.
     *
     * @return the trabajo
     */
    public boolean getTrabajo() {
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
     * Gets tipo vivienda.
     *
     * @return the tipo vivienda
     */
    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    /**
     * Sets tipo vivienda.
     *
     * @param tipoVivienda the tipo vivienda
     */
    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
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
     * Gets deudas.
     *
     * @return the deudas
     */
    public boolean getDeudas() {
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
}
