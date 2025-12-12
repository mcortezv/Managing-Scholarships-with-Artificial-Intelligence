package solicitarBeca;

public class LoginDTO {
    private Long usuario;
    private String contrasenia;

    /**
     * Instantiates a new Login dto itson.
     */
    public LoginDTO() {
    }

    /**
     * Instantiates a new Login dto itson.
     *
     * @param usuario     the usuario
     * @param contrasenia the contrasenia
     */
    public LoginDTO(Long usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Gets usuario.
     *
     * @return the usuario
     */
    public Long getUsuario() {
        return usuario;
    }

    /**
     * Sets usuario.
     *
     * @param usuario the usuario
     */
    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

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
}
