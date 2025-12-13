package objetosNegocio.adaptadores.apelacionResultado;

import dto.apelacionResultado.ApelacionDTO;
import gobierno.apelacion.ApelacionDTOGobierno;

public class ApelacionAdaptador {

    /**
     * Convierte de DTO Local -> DTO Gobierno
     * (Para enviar la apelación al sistema externo)
     */
    public static ApelacionDTOGobierno toDTOGobierno(ApelacionDTO apelacionDTO) {
        try {
            if (apelacionDTO == null) return null;

            ApelacionDTOGobierno dtoGobierno = new ApelacionDTOGobierno();

            dtoGobierno.setIdSolicitudApelacion(apelacionDTO.getIdSolicitudApelacion());
            dtoGobierno.setRedaccion(apelacionDTO.getRedaccion());
            dtoGobierno.setIdEstudiante(apelacionDTO.getIdEstudiante());
            dtoGobierno.setFechaApelacion(apelacionDTO.getFechaApelacion());

            return dtoGobierno;
        } catch (Exception ex) {
            throw new RuntimeException("Error al convertir ApelacionDTO a ApelacionDTOGobierno: " + ex.getMessage());
        }
    }

    /**
     * Convierte de DTO Gobierno -> DTO Local
     * (Para recibir la respuesta del sistema externo)
     */
    public static ApelacionDTO toDTO(ApelacionDTOGobierno apelacionDTOGobierno) {
        try {
            if (apelacionDTOGobierno == null) return null;
            ApelacionDTO dto = new ApelacionDTO();
            dto.setIdSolicitudApelacion(apelacionDTOGobierno.getIdSolicitudApelacion());
            dto.setRedaccion(apelacionDTOGobierno.getRedaccion());
            dto.setIdEstudiante(apelacionDTOGobierno.getIdEstudiante());
            dto.setFechaApelacion(apelacionDTOGobierno.getFechaApelacion());
            return dto;
        } catch (Exception ex) {
            throw new RuntimeException("Error al convertir ApelacionDTOGobierno a ApelacionDTO: " + ex.getMessage());
        }
    }
}