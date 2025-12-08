package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.BecaAdaptadorException;
import datosGobierno.dominioGobierno.Beca;
import datosGobierno.dominioGobierno.enums.TipoBeca;
import dtoGobierno.BecaDTO;
import gobierno.BecaDTOGobierno;

/**
 *
 * @author Cortez, Manuel;
 */
public class BecaAdaptador {

    public static BecaDTO toDTO(Beca beca){
        try {
            BecaDTO becaDTO = new BecaDTO();
            becaDTO.setCodigo(beca.getCodigo());
            becaDTO.setNombre(beca.getNombre());
            becaDTO.setBecasDisponibles(beca.getBecasDisponibles());
            becaDTO.setDescripcion(beca.getDescripcion());
            becaDTO.setFechaInicio(beca.getFechaInicio());
            becaDTO.setFechaFin(beca.getFechaFin());
            becaDTO.setFechaResultados(beca.getFechaResultados());
            becaDTO.setTipo(beca.getTipo().toString());
            becaDTO.setRequisitos(RequisitosAdaptador.toDTO(beca.getRequisitos()));
            return becaDTO;
        } catch (Exception ex) {
            throw new BecaAdaptadorException("Error al convertir entidad Beca a DTO");
        }
    }

    public static BecaDTO toDTO(BecaDTOGobierno beca){
        try {
            BecaDTO becaDTO = new BecaDTO();
            becaDTO.setCodigo(beca.getCodigo());
            becaDTO.setNombre(beca.getNombre());
            becaDTO.setBecasDisponibles(beca.getBecasDisponibles());
            becaDTO.setDescripcion(beca.getDescripcion());
            becaDTO.setFechaInicio(beca.getFechaInicio());
            becaDTO.setFechaFin(beca.getFechaFin());
            becaDTO.setFechaResultados(beca.getFechaResultados());
            becaDTO.setTipo(beca.getTipo().toString());
            becaDTO.setRequisitos(RequisitosAdaptador.toDTO(beca.getRequisitos()));
            return becaDTO;
        } catch (Exception ex) {
            throw new BecaAdaptadorException("Error al convertir entidad Beca a DTO");
        }
    }

    public static BecaDTOGobierno toDTOGobierno(Beca beca){
        try {
            BecaDTOGobierno becaDTO = new BecaDTOGobierno();
            becaDTO.setCodigo(beca.getCodigo());
            becaDTO.setNombre(beca.getNombre());
            becaDTO.setBecasDisponibles(beca.getBecasDisponibles());
            becaDTO.setDescripcion(beca.getDescripcion());
            becaDTO.setFechaInicio(beca.getFechaInicio());
            becaDTO.setFechaFin(beca.getFechaFin());
            becaDTO.setFechaResultados(beca.getFechaResultados());
            becaDTO.setTipo(beca.getTipo().toString());
            becaDTO.setRequisitos(RequisitosAdaptador.toDTOGobierno(beca.getRequisitos()));
            return becaDTO;
        } catch (Exception ex) {
            throw new BecaAdaptadorException("Error al convertir entidad Beca a DTO");
        }
    }

    public static Beca toEntity(BecaDTO becaDTO){
        try {
            Beca beca = new Beca();
            beca.setCodigo((long) becaDTO.getCodigo());
            beca.setNombre(becaDTO.getNombre());
            beca.setBecasDisponibles(becaDTO.getBecasDisponibles());
            beca.setDescripcion(becaDTO.getDescripcion());
            beca.setFechaInicio(becaDTO.getFechaInicio());
            beca.setFechaFin(becaDTO.getFechaFin());
            beca.setFechaResultados(becaDTO.getFechaResultados());
            beca.setTipo(TipoBeca.valueOf(becaDTO.getTipo()));
            beca.setRequisitos(RequisitosAdaptador.toEntity(becaDTO.getRequisitos()));
            return beca;
        } catch (Exception ex) {
            throw new BecaAdaptadorException("Error al convertir DTO Beca a entidad");
        }
    }

    public static Beca toEntity(BecaDTOGobierno becaResponseDTO){
        try {
            Beca beca = new Beca();
            beca.setCodigo(becaResponseDTO.getCodigo());
            beca.setNombre(becaResponseDTO.getNombre());
            beca.setBecasDisponibles(becaResponseDTO.getBecasDisponibles());
            beca.setDescripcion(becaResponseDTO.getDescripcion());
            beca.setFechaInicio(becaResponseDTO.getFechaInicio());
            beca.setFechaFin(becaResponseDTO.getFechaFin());
            beca.setFechaResultados(becaResponseDTO.getFechaResultados());
            beca.setTipo(TipoBeca.valueOf(becaResponseDTO.getTipo().toString()));
            beca.setRequisitos(RequisitosAdaptador.toEntity(becaResponseDTO.getRequisitos()));
            return beca;
        } catch (Exception ex) {
            throw new BecaAdaptadorException("Error al convertir ResponseDTO Beca a entidad");
        }
    }
}
