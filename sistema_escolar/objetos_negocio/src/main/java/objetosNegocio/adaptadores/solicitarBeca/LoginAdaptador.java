package objetosNegocio.adaptadores.solicitarBeca;

import itson.LoginDTOItson;
import solicitarBeca.LoginDTO;

/**
 * Clase adaptadora para convertir entre LoginDTOItson y LoginDTO.
 */
public class LoginAdaptador {

    /**
     * Convierte de la entidad de "itson" a la de "solicitarBeca".
     * * @param itson Entidad origen
     * @return Entidad destino
     */
    public static LoginDTO aLoginDTO(LoginDTOItson itson) {
        if (itson == null) {
            return null;
        }
        LoginDTO dto = new LoginDTO();
        dto.setUsuario(itson.getUsuario());
        dto.setContrasenia(itson.getContrasenia());
        return dto;
    }

    /**
     * Convierte de la entidad de "solicitarBeca" a la de "itson".
     * * @param dto Entidad origen
     * @return Entidad destino
     */
    public static LoginDTOItson aLoginDTOItson(LoginDTO dto) {
        if (dto == null) {
            return null;
        }
        LoginDTOItson itson = new LoginDTOItson();
        itson.setUsuario(dto.getUsuario());
        itson.setContrasenia(dto.getContrasenia());
        return itson;
    }
}