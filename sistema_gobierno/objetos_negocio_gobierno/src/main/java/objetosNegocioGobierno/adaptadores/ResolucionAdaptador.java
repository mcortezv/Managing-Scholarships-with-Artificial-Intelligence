package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.enums.Decision;
import gobierno.ResolucionDTOGobierno;
import dtoGobierno.ResolucionDTO;
import objetosNegocioGobierno.adaptadores.excepciones.ResolucionAdaptadorException;

/**
 * The type Resolucion adaptador.
 *
 * @author Cortez, Manuel;
 */
public class ResolucionAdaptador {

    /**
     * To entity resolucion.
     *
     * @param dto the dto
     * @return the resolucion
     */
    public static Resolucion toEntity(ResolucionDTO dto){
        try {
            Resolucion resolucion = new Resolucion();
            resolucion.setSolicitud(SolicitudAdaptador.toEntity(dto.getSolicitud()));
            resolucion.setDecision(Decision.valueOf(dto.getDecision()));
            resolucion.setMotivo(dto.getMotivo());
            if (dto.getPrecision() != null){
                resolucion.setPrecision(dto.getPrecision());
            }
            resolucion.setFechaEvaluacion(dto.getFechaEvaluacion());
            return resolucion;
        } catch (Exception sinUso){
            throw new ResolucionAdaptadorException("No se pudo mappear la ResolucionDTO a entidad Resolucion");
        }
    }

    /**
     * To entity resolucion.
     *
     * @param dto the dto
     * @return the resolucion
     */
    public static Resolucion toEntity(ResolucionDTOGobierno dto){
        try {
            Resolucion resolucion = new Resolucion();
            resolucion.setSolicitud(SolicitudAdaptador.toEntity(dto.getSolicitud()));
            resolucion.setDecision(Decision.valueOf(dto.getDecision()));
            resolucion.setMotivo(dto.getMotivo());
            if (dto.getPrecision() != null){
                resolucion.setPrecision(dto.getPrecision());
            }
            resolucion.setFechaEvaluacion(dto.getFechaEvaluacion());
            return resolucion;
        } catch (Exception sinUso){
            throw new ResolucionAdaptadorException("No se pudo mappear la ResolucionDTOGobierno a entidad Resolucion");
        }
    }

    /**
     * To dto resolucion dto.
     *
     * @param resolucion the resolucion
     * @return the resolucion dto
     */
    public static ResolucionDTO toDTO(Resolucion resolucion){
        try {
            ResolucionDTO dto = new ResolucionDTO();
            dto.setSolicitud(SolicitudAdaptador.toDTO(resolucion.getSolicitud()));
            dto.setDecision(resolucion.getDecision().toString());
            dto.setMotivo(resolucion.getMotivo());
            if (resolucion.getPrecision() != null){
                dto.setPrecision(resolucion.getPrecision());
            }
            dto.setFechaEvaluacion(resolucion.getFechaEvaluacion());
            return dto;
        } catch (Exception sinUso){
            throw new ResolucionAdaptadorException("No se pudo mappear la entidad Resolucion a ResolucionDTO");
        }
    }

    /**
     * To infraestructura dto resolucion dto gobierno.
     *
     * @param resolucion the resolucion
     * @return the resolucion dto gobierno
     */
    public static ResolucionDTOGobierno toInfraestructuraDTO(Resolucion  resolucion){
        try {
            ResolucionDTOGobierno dto = new ResolucionDTOGobierno();
            dto.setSolicitud(SolicitudAdaptador.toInfraestructuraDTO(resolucion.getSolicitud()));
            dto.setDecision(resolucion.getDecision().toString());
            dto.setMotivo(resolucion.getMotivo());
            if (resolucion.getPrecision() != null){
                dto.setPrecision(resolucion.getPrecision());
            }
            dto.setFechaEvaluacion(resolucion.getFechaEvaluacion());
            return dto;
        } catch (Exception sinUso){
            throw new ResolucionAdaptadorException("No se pudo mappear la entidad Resolucion a ResolucionDTOGobierno");
        }
    }
}
