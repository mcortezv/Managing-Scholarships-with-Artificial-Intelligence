package objetosNegocio.adaptadores.solicitarBeca;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.InformacionSocioeconomicaAdaptadorException;
import solicitarBeca.InformacionSocioeconomicaDTO;
import gobierno.InformacionSocioeconomicaDTOGobierno;
import solicitarBeca.dominio.InformacionSocioeconomica;
import solicitarBeca.dominio.enums.TipoVivienda;

/**
 * The type Informacion socioeconomica adaptador.
 *
 * @author Cortez, Manuel;
 */
public class InformacionSocioeconomicaAdaptador {

    /**
     * To entity informacion socioeconomica.
     *
     * @param dto the dto
     * @return the informacion socioeconomica
     */
    public static InformacionSocioeconomica toEntity(InformacionSocioeconomicaDTO dto) {
        try {
            InformacionSocioeconomica informacionSocioeconomica = new InformacionSocioeconomica();
            informacionSocioeconomica.setIngresoTotalFamilarMensual(dto.getIngresoTotalFamilarMensual());
            informacionSocioeconomica.setTipoVivienda(TipoVivienda.valueOf(dto.getTipoVivienda()));
            informacionSocioeconomica.setDeudas(dto.isDeudas());
            informacionSocioeconomica.setTrabajo(dto.isTrabajo());
            return informacionSocioeconomica;
        } catch (Exception ex) {
            throw new InformacionSocioeconomicaAdaptadorException("Error al convertir InformacionSocioeconomicaDTO a entidad InformacionSocioeconomica");
        }
    }

    /**
     * To dto informacion socioeconomica dto.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     * @return the informacion socioeconomica dto
     */
    public static InformacionSocioeconomicaDTO toDTO(InformacionSocioeconomica informacionSocioeconomica) {
        try {
            InformacionSocioeconomicaDTO dto = new InformacionSocioeconomicaDTO();
            dto.setIngresoTotalFamilarMensual(informacionSocioeconomica.getIngresoTotalFamilarMensual());
            dto.setTipoVivienda(informacionSocioeconomica.getTipoVivienda().toString());
            dto.setDeudas(informacionSocioeconomica.getDeudas());
            dto.setTrabajo(informacionSocioeconomica.getTrabajo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad InformacionSocioeconomica a InformacionSocioeconomicaDTO");
        }
    }

    /**
     * To dto gobierno informacion socioeconomica dto gobierno.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     * @return the informacion socioeconomica dto gobierno
     */
    public static InformacionSocioeconomicaDTOGobierno toDTOGobierno(InformacionSocioeconomica informacionSocioeconomica) {
        try {
            InformacionSocioeconomicaDTOGobierno dto = new InformacionSocioeconomicaDTOGobierno();
            dto.setIngresoTotalFamilarMensual(informacionSocioeconomica.getIngresoTotalFamilarMensual());
            dto.setTipoVivienda(informacionSocioeconomica.getTipoVivienda().toString());
            dto.setDeudas(informacionSocioeconomica.getDeudas());
            dto.setTrabajo(informacionSocioeconomica.getTrabajo());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad InformacionSocioeconomica a InformacionSocioeconomicaDTOGobierno");
        }
    }
}
