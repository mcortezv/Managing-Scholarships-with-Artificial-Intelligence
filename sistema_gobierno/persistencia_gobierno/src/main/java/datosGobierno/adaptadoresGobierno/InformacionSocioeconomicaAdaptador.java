package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.InformacionSocioeconomicaAdaptadorException;
import datosGobierno.dominioGobierno.InformacionSocioeconomica;
import datosGobierno.dominioGobierno.enums.TipoVivienda;
import dtoGobierno.InformacionSocioeconomicaDTO;


public class InformacionSocioeconomicaAdaptador {

    public static InformacionSocioeconomica toEntity(InformacionSocioeconomicaDTO dto) {
        try {
            InformacionSocioeconomica informacionSocioeconomica = new InformacionSocioeconomica();
            informacionSocioeconomica.setDeudas(dto.getDeudas());
            informacionSocioeconomica.setTrabajo(dto.getTrabajo());
            informacionSocioeconomica.setTipoVivienda(TipoVivienda.valueOf(dto.getTipoVivienda()));
            informacionSocioeconomica.setIngresoTotalFamilarMensual(dto.getIngresoTotalFamilarMensual());
            return informacionSocioeconomica;
        } catch (Exception ex) {
            throw new InformacionSocioeconomicaAdaptadorException("Error al convertir InformacionSocioeconomicaDTO a InformacionSocioeconomica");
        }
    }

    public static InformacionSocioeconomicaDTO toDTO(InformacionSocioeconomica informacionSocioeconomica) {
        try {
            InformacionSocioeconomicaDTO dto = new InformacionSocioeconomicaDTO();
            dto.setDeudas(informacionSocioeconomica.getDeudas());
            dto.setTrabajo(informacionSocioeconomica.getTrabajo());
            dto.setTipoVivienda(informacionSocioeconomica.getTipoVivienda().toString());
            dto.setIngresoTotalFamilarMensual(informacionSocioeconomica.getIngresoTotalFamilarMensual());
            return dto;
        } catch (Exception ex) {
            throw new InformacionSocioeconomicaAdaptadorException("Error al convertir InformacionSocioeconomica a InformacionSocioeconomicaDTO");
        }
    }
}
