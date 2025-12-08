package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.RequisitosAdaptadorException;
import datosGobierno.dominioGobierno.Requisitos;
import dtoGobierno.RequisitosDTO;
import gobierno.RequisitosDTOGobierno;

/**
 *
 * @author Cortez, Manuel;
 */
public class RequisitosAdaptador {

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
        } catch (Exception ex) {
            throw new RequisitosAdaptadorException("Error al convertir entidad Requisitos a DTO");
        }
    }

    public static RequisitosDTO toDTO(RequisitosDTOGobierno requisitos){
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
        } catch (Exception ex) {
            throw new RequisitosAdaptadorException("Error al convertir entidad Requisitos a DTO");
        }
    }

    public static RequisitosDTOGobierno toDTOGobierno(Requisitos requisitos){
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
        } catch (Exception ex) {
            throw new RequisitosAdaptadorException("Error al convertir entidad Requisitos a DTO");
        }
    }

    public static RequisitosDTOGobierno toDTOGobierno(RequisitosDTO requisitos){
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
        } catch (Exception ex) {
            throw new RequisitosAdaptadorException("Error al convertir entidad Requisitos a DTO");
        }
    }

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
        } catch (Exception ex) {
            throw new RequisitosAdaptadorException("Error al convertir ResponseDTO Requisistos a Entidad");
        }
    }

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
        } catch (Exception ex) {
            throw new RequisitosAdaptadorException("Error al convertir DTO Requisitos a Entidad");
        }
    }
}