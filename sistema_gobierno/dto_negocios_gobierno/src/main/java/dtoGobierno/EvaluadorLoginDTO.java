package dtoGobierno;

/**
 * The type Evaluador login dto.
 *
 * @author Cortez, Manuel;
 */
public class EvaluadorLoginDTO {
    private Long matricula;
    private String contrasenia;

    /**
     * Instantiates a new Evaluador login dto.
     *
     * @param contrasenia the contrasenia
     * @param matricula   the matricula
     */
    public EvaluadorLoginDTO(String contrasenia, Long matricula) {
        this.contrasenia = contrasenia;
        this.matricula = matricula;
    }

    /**
     * Instantiates a new Evaluador login dto.
     */
    public EvaluadorLoginDTO() {}

    /**
     * Gets contrasenia.
     *
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Sets contrasenia.
     *
     * @param contrasenia the contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Gets matricula.
     *
     * @return the matricula
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * Sets matricula.
     *
     * @param matricula the matricula
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
