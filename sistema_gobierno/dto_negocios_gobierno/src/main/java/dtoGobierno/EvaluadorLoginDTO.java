package dtoGobierno;

public class EvaluadorLoginDTO {
    private Long matricula;
    private String contrasenia;

    public EvaluadorLoginDTO(String contrasenia, Long matricula) {
        this.contrasenia = contrasenia;
        this.matricula = matricula;
    }

    public EvaluadorLoginDTO() {}

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
