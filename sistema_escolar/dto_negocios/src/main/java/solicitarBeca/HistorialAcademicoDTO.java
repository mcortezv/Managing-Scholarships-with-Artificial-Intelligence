/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solicitarBeca;

/**
 * The type Historial academico dto.
 *
 * @author Cortez, Manuel;
 */
public class HistorialAcademicoDTO {
    private Long matriculaEstudiante;
    private String carrera;
    private double promedio;
    private double porcentajeBajas;
    private double cargaAcademica;
    private int semestre;
    private double indiceReprobacion;

    /**
     * Instantiates a new Historial academico dto.
     */
    public HistorialAcademicoDTO() {}

    /**
     * Instantiates a new Historial academico dto.
     *
     * @param cargaAcademica      the carga academica
     * @param carrera             the carrera
     * @param indiceReprobacion   the indice reprobacion
     * @param matriculaEstudiante the matricula estudiante
     * @param porcentajeBajas     the porcentaje bajas
     * @param promedio            the promedio
     * @param semestre            the semestre
     */
    public HistorialAcademicoDTO(double cargaAcademica, String carrera, double indiceReprobacion, Long matriculaEstudiante, double porcentajeBajas, double promedio, int semestre) {
        this.cargaAcademica = cargaAcademica;
        this.carrera = carrera;
        this.indiceReprobacion = indiceReprobacion;
        this.matriculaEstudiante = matriculaEstudiante;
        this.porcentajeBajas = porcentajeBajas;
        this.promedio = promedio;
        this.semestre = semestre;
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
     * Gets carrera.
     *
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * Sets carrera.
     *
     * @param carrera the carrera
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
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
     * Gets matricula estudiante.
     *
     * @return the matricula estudiante
     */
    public Long getMatriculaEstudiante() {
        return matriculaEstudiante;
    }

    /**
     * Sets matricula estudiante.
     *
     * @param matriculaEstudiante the matricula estudiante
     */
    public void setMatriculaEstudiante(Long matriculaEstudiante) {
        this.matriculaEstudiante = matriculaEstudiante;
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
     * Gets promedio.
     *
     * @return the promedio
     */
    public double getPromedio() {
        return promedio;
    }

    /**
     * Sets promedio.
     *
     * @param promedio the promedio
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    /**
     * Gets semestre.
     *
     * @return the semestre
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Sets semestre.
     *
     * @param semestre the semestre
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
}
