package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Requisitos;
import dtoGobierno.RequisitosDTO;
import gobierno.RequisitosDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.RequisitosAdaptadorException;

/**
 * The type Requisitos adaptador.
 *
 * @author Cortez, Manuel;
 */
public class RequisitosAdaptador {

    /**
     * To entity requisitos.
     *
     * @param dto the dto
     * @return the requisitos
     */
    public static Requisitos toEntity(RequisitosDTO dto){
        try {
            Requisitos requisitos = new Requisitos();
            requisitos.setPromedioMinimo(dto.getPromedioMinimo());
            requisitos.setIngresoFamiliarMaximo(dto.getIngresoFamiliarMaximo());
            requisitos.setPorcentajeBajas(dto.getPorcentajeBajas());
            requisitos.setCargaAcademica(dto.getCargaAcademica());
            requisitos.setIndiceReprobacion(dto.getIndiceReprobacion());
            requisitos.setTrabajo(dto.isTrabajo());
            requisitos.setDeudas(dto.isDeudas());
            return requisitos;
        } catch (Exception sinUso){
            throw new RequisitosAdaptadorException("No se pudo mappear el RequisitosDTO a entidad Requisitos");
        }
    }

    /**
     * To entity requisitos.
     *
     * @param dto the dto
     * @return the requisitos
     */
    public static Requisitos toEntity(RequisitosDTOGobierno dto){
        try {
            Requisitos requisitos = new Requisitos();
            requisitos.setPromedioMinimo(dto.getPromedioMinimo());
            requisitos.setIngresoFamiliarMaximo(dto.getIngresoFamiliarMaximo());
            requisitos.setPorcentajeBajas(dto.getPorcentajeBajas());
            requisitos.setCargaAcademica(dto.getCargaAcademica());
            requisitos.setIndiceReprobacion(dto.getIndiceReprobacion());
            requisitos.setTrabajo(dto.isTrabajo());
            requisitos.setDeudas(dto.isDeudas());
            return requisitos;
        } catch (Exception sinUso){
            throw new RequisitosAdaptadorException("No se pudo mappear el RequisitosDTOGobierno a entidad Requisitos");
        }
    }

    /**
     * To dto requisitos dto.
     *
     * @param requisitos the requisitos
     * @return the requisitos dto
     */
    public static RequisitosDTO toDTO(Requisitos requisitos){
        try {
            RequisitosDTO dto = new RequisitosDTO();
            dto.setPromedioMinimo(requisitos.getPromedioMinimo());
            dto.setIngresoFamiliarMaximo(requisitos.getIngresoFamiliarMaximo());
            dto.setPorcentajeBajas(requisitos.getPorcentajeBajas());
            dto.setCargaAcademica(requisitos.getCargaAcademica());
            dto.setIndiceReprobacion(requisitos.getIndiceReprobacion());
            dto.setTrabajo(requisitos.isTrabajo());
            dto.setDeudas(requisitos.isDeudas());
            return dto;
        } catch (Exception sinUso){
            throw new RequisitosAdaptadorException("No se pudo mappear la entidad Requisitos a RequisitosDTO");
        }
    }

    /**
     * To infraestructura dto requisitos dto gobierno.
     *
     * @param requisitos the requisitos
     * @return the requisitos dto gobierno
     */
    public static RequisitosDTOGobierno toInfraestructuraDTO(Requisitos  requisitos){
        try {
            RequisitosDTOGobierno dto = new RequisitosDTOGobierno();
            dto.setPromedioMinimo(requisitos.getPromedioMinimo());
            dto.setIngresoFamiliarMaximo(requisitos.getIngresoFamiliarMaximo());
            dto.setPorcentajeBajas(requisitos.getPorcentajeBajas());
            dto.setCargaAcademica(requisitos.getCargaAcademica());
            dto.setIndiceReprobacion(requisitos.getIndiceReprobacion());
            dto.setTrabajo(requisitos.isTrabajo());
            dto.setDeudas(requisitos.isDeudas());
            return dto;
        } catch (Exception sinUso){
            throw new RequisitosAdaptadorException("No se pudo mappear la entidad Requisitos a RequisitosDTOGobierno");
        }
    }
}
