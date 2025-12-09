/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;

/**
 * The type Requisitos.
 *
 * @author Cortez, Manuel;
 */
public class Requisitos {
    private double promedioMinimo;
    private double ingresoFamiliarMaximo;
    private double porcentajeBajas;
    private double cargaAcademica;
    private double indiceReprobacion;
    private boolean trabajo;
    private boolean deudas;

    /**
     * Instantiates a new Requisitos.
     */
    public Requisitos() {
    }

    /**
     * Instantiates a new Requisitos.
     *
     * @param promedioMinimo        the promedio minimo
     * @param ingresoFamiliarMaximo the ingreso familiar maximo
     * @param porcentajeBajas       the porcentaje bajas
     * @param cargaAcademica        the carga academica
     * @param indiceReprobacion     the indice reprobacion
     * @param trabajo               the trabajo
     * @param deudas                the deudas
     */
    public Requisitos(double promedioMinimo, double ingresoFamiliarMaximo, double porcentajeBajas, double cargaAcademica, double indiceReprobacion, boolean trabajo, boolean deudas) {
        this.promedioMinimo = promedioMinimo;
        this.ingresoFamiliarMaximo = ingresoFamiliarMaximo;
        this.porcentajeBajas = porcentajeBajas;
        this.cargaAcademica = cargaAcademica;
        this.indiceReprobacion = indiceReprobacion;
        this.trabajo = trabajo;
        this.deudas = deudas;
    }

    /**
     * Gets promedio minimo.
     *
     * @return the promedio minimo
     */
    public double getPromedioMinimo() {
        return promedioMinimo;
    }

    /**
     * Sets promedio minimo.
     *
     * @param promedioMinimo the promedio minimo
     */
    public void setPromedioMinimo(double promedioMinimo) {
        this.promedioMinimo = promedioMinimo;
    }

    /**
     * Gets ingreso familiar maximo.
     *
     * @return the ingreso familiar maximo
     */
    public double getIngresoFamiliarMaximo() {
        return ingresoFamiliarMaximo;
    }

    /**
     * Sets ingreso familiar maximo.
     *
     * @param ingresoFamiliarMaximo the ingreso familiar maximo
     */
    public void setIngresoFamiliarMaximo(double ingresoFamiliarMaximo) {
        this.ingresoFamiliarMaximo = ingresoFamiliarMaximo;
    }

    /**
     * Gets porcentaje bajas.
     *
     * @return the porcentaje bajas
     */
    public double getPorcentajeBajas() {
        return porcentajeBajas;
    }

    /**
     * Sets porcentaje bajas.
     *
     * @param porcentajeBajas the porcentaje bajas
     */
    public void setPorcentajeBajas(double porcentajeBajas) {
        this.porcentajeBajas = porcentajeBajas;
    }

    /**
     * Gets carga academica.
     *
     * @return the carga academica
     */
    public double getCargaAcademica() {
        return cargaAcademica;
    }

    /**
     * Sets carga academica.
     *
     * @param cargaAcademica the carga academica
     */
    public void setCargaAcademica(double cargaAcademica) {
        this.cargaAcademica = cargaAcademica;
    }

    /**
     * Gets indice reprobacion.
     *
     * @return the indice reprobacion
     */
    public double getIndiceReprobacion() {
        return indiceReprobacion;
    }

    /**
     * Sets indice reprobacion.
     *
     * @param indiceReprobacion the indice reprobacion
     */
    public void setIndiceReprobacion(double indiceReprobacion) {
        this.indiceReprobacion = indiceReprobacion;
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
}
