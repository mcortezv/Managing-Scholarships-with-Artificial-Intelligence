package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.InformacionSocioeconomica;
import datosGobierno.dominioGobierno.enums.TipoVivienda;
import dtoGobierno.InformacionSocioeconomicaDTO;
import gobierno.InformacionSocioeconomicaDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.InformacionSocioeconomicaAdaptadorException;

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
    public static InformacionSocioeconomica toEntity(InformacionSocioeconomicaDTO dto){
        try {
            InformacionSocioeconomica informacionSocioeconomica = new InformacionSocioeconomica();
            informacionSocioeconomica.setIngresoTotalFamilarMensual(dto.getIngresoTotalFamilarMensual());
            informacionSocioeconomica.setTipoVivienda(TipoVivienda.valueOf(dto.getTipoVivienda()));
            informacionSocioeconomica.setTrabajo(dto.getTrabajo());
            informacionSocioeconomica.setDeudas(dto.getDeudas());
            return informacionSocioeconomica;
        } catch (Exception sinUso){
            throw new InformacionSocioeconomicaAdaptadorException("No se pudo mappear la InformacionSocioeconomicaDTO a entidad InformacionSocioeconomica");
        }
    }

    /**
     * To entity informacion socioeconomica.
     *
     * @param dto the dto
     * @return the informacion socioeconomica
     */
    public static InformacionSocioeconomica toEntity(InformacionSocioeconomicaDTOGobierno dto){
        try {
            InformacionSocioeconomica informacionSocioeconomica = new InformacionSocioeconomica();
            informacionSocioeconomica.setIngresoTotalFamilarMensual(dto.getIngresoTotalFamilarMensual());
            informacionSocioeconomica.setTipoVivienda(TipoVivienda.valueOf(dto.getTipoVivienda()));
            informacionSocioeconomica.setTrabajo(dto.getTrabajo());
            informacionSocioeconomica.setDeudas(dto.getDeudas());
            return informacionSocioeconomica;
        } catch (Exception sinUso){
            throw new InformacionSocioeconomicaAdaptadorException("No se pudo mappear la InformacionSocioeconomicaDTOGobierno a entidad InformacionSocioeconomica");
        }
    }

    /**
     * To dto informacion socioeconomica dto.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     * @return the informacion socioeconomica dto
     */
    public static InformacionSocioeconomicaDTO toDTO(InformacionSocioeconomica informacionSocioeconomica){
        try {
            InformacionSocioeconomicaDTO dto = new InformacionSocioeconomicaDTO();
            dto.setIngresoTotalFamilarMensual(informacionSocioeconomica.getIngresoTotalFamilarMensual());
            dto.setTipoVivienda(informacionSocioeconomica.getTipoVivienda().toString());
            dto.setTrabajo(informacionSocioeconomica.getTrabajo());
            dto.setDeudas(informacionSocioeconomica.getDeudas());
            return dto;
        } catch (Exception sinUso){
            throw new InformacionSocioeconomicaAdaptadorException("No se pudo mappear la entidad InformacionSocioeconomica a InformacionSocioeconomicaDTO");
        }
    }

    /**
     * To infraestructura dto informacion socioeconomica dto gobierno.
     *
     * @param informacionSocioeconomica the informacion socioeconomica
     * @return the informacion socioeconomica dto gobierno
     */
    public static InformacionSocioeconomicaDTOGobierno toInfraestructuraDTO(InformacionSocioeconomica informacionSocioeconomica){
        try {
            InformacionSocioeconomicaDTOGobierno dto = new InformacionSocioeconomicaDTOGobierno();
            dto.setIngresoTotalFamilarMensual(informacionSocioeconomica.getIngresoTotalFamilarMensual());
            dto.setTipoVivienda(informacionSocioeconomica.getTipoVivienda().toString());
            dto.setTrabajo(informacionSocioeconomica.getTrabajo());
            dto.setDeudas(informacionSocioeconomica.getDeudas());
            return dto;
        } catch (Exception sinUso){
            throw new InformacionSocioeconomicaAdaptadorException("No se pudo mappear la entidad InformacionSocioeconomica a InformacionSocioeconomicaDTOGobierno");
        }
    }
}
