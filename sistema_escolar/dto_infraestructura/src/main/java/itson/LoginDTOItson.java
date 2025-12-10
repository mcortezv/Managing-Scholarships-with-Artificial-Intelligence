package itson;

/**
 * The type Login dto itson.
 *
 * @author Escalante, Sebastian.
 */
public class LoginDTOItson {
    private Long usuario;
    private String contrasenia;

    /**
     * Instantiates a new Login dto itson.
     */
    public LoginDTOItson() {
    }

    /**
     * Instantiates a new Login dto itson.
     *
     * @param usuario     the usuario
     * @param contrasenia the contrasenia
     */
    public LoginDTOItson(Long usuario, String contrasenia) {
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
