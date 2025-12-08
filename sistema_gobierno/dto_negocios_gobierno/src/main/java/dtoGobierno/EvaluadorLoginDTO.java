package dtoGobierno;

public class EvaluadorLoginDTO {
    private String matricula;
    private String contrasenia;

    public EvaluadorLoginDTO(String contrasenia, String matricula) {
        this.contrasenia = contrasenia;
        this.matricula = matricula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
