package adaptadores.solicitarBeca;
import adaptadores.solicitarBeca.excepciones.RequisitosAdaptadorException;
//import datosGobierno.repositoryGobierno.documents.RequisitosDocument;
import solicitarBeca.RequisitosDTO;
import gobierno.RequisitosDTOGobierno;
import solicitarBeca.dominio.Requisitos;

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

//    public static Requisitos toEntity(RequisitosDocument document){
//        try {
//            Requisitos requisitos = new Requisitos();
//            requisitos.setPromedioMinimo(document.getPromedioMinimo());
//            requisitos.setIngresoFamiliarMaximo(document.getIngresoFamiliarMaximo());
//            requisitos.setPorcentajeBajas(document.getPorcentajeBajas());
//            requisitos.setCargaAcademica(document.getCargaAcademica());
//            requisitos.setIndiceReprobacion(document.getIndiceReprobacion());
//            requisitos.setTrabajo(document.isTrabajo());
//            requisitos.setDeudas(document.isDeudas());
//            return requisitos;
//        } catch (Exception ex) {
//            throw new datosGobierno.adaptadoresGobierno.excepciones.RequisitosAdaptadorException("Error al convertir Documento Requisitos a Entidad");
//        }
//    }
}