package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.Beca;
import datosGobierno.dominioGobierno.enums.TipoBeca;
import dtoGobierno.BecaDTO;
import gobierno.BecaDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.BecaAdaptadorException;

/**
 *
 * @author Cortez, Manuel;
 */
public class BecaAdaptador {

    public static Beca toEntity(BecaDTO dto){
        try {
            Beca beca = new Beca();
            beca.setCodigo(dto.getCodigo());
            beca.setTipo(TipoBeca.valueOf(dto.getTipo()));
            beca.setNombre(dto.getNombre());
            beca.setDescripcion(dto.getDescripcion());
            beca.setFechaInicio(dto.getFechaInicio());
            beca.setFechaFin(dto.getFechaFin());
            beca.setBecasDisponibles(dto.getBecasDisponibles());
            beca.setRequisitos(RequisitosAdaptador.toEntity(dto.getRequisitos()));
            beca.setFechaResultados(dto.getFechaResultados());
            return beca;
        } catch (Exception sinUso){
            throw new BecaAdaptadorException("No se pudo mappear la DTO a Entidad");
        }
    }

    public static Beca toEntity(BecaDTOGobierno dto){
        try {
            Beca beca = new Beca();
            beca.setCodigo(dto.getCodigo());
            beca.setTipo(TipoBeca.valueOf(dto.getTipo()));
            beca.setNombre(dto.getNombre());
            beca.setDescripcion(dto.getDescripcion());
            beca.setFechaInicio(dto.getFechaInicio());
            beca.setFechaFin(dto.getFechaFin());
            beca.setBecasDisponibles(dto.getBecasDisponibles());
            beca.setRequisitos(RequisitosAdaptador.toEntity(dto.getRequisitos()));
            beca.setFechaResultados(dto.getFechaResultados());
            return beca;
        } catch (Exception sinUso){
            throw new BecaAdaptadorException("No se pudo mappear la DTO de Infraestructura a Entidad");
        }
    }

    public static BecaDTO toDTO(Beca beca){
        try {
            BecaDTO dto = new BecaDTO();
            dto.setCodigo(beca.getCodigo());
            dto.setTipo(beca.getTipo().toString());
            dto.setNombre(beca.getNombre());
            dto.setDescripcion(beca.getDescripcion());
            dto.setFechaInicio(beca.getFechaInicio());
            dto.setFechaFin(beca.getFechaFin());
            dto.setBecasDisponibles(beca.getBecasDisponibles());
            dto.setRequisitos(RequisitosAdaptador.toDTO(beca.getRequisitos()));
            dto.setFechaResultados(beca.getFechaResultados());
            return dto;
        } catch (Exception sinUso){
            throw new BecaAdaptadorException("No se pudo mappear la Entidad a DTO");
        }
    }


    public static BecaDTOGobierno toInfraestructuraDTO(Beca  beca){
        try {
            BecaDTOGobierno dto = new BecaDTOGobierno();
            dto.setCodigo(beca.getCodigo());
            dto.setTipo(beca.getTipo().toString());
            dto.setNombre(beca.getNombre());
            dto.setDescripcion(beca.getDescripcion());
            dto.setFechaInicio(beca.getFechaInicio());
            dto.setFechaFin(beca.getFechaFin());
            dto.setBecasDisponibles(beca.getBecasDisponibles());
            dto.setRequisitos(RequisitosAdaptador.toInfraestructuraDTO(beca.getRequisitos()));
            dto.setFechaResultados(beca.getFechaResultados());
            return dto;
        } catch (Exception sinUso){
            throw new BecaAdaptadorException("No se pudo mappear la Entidad a DTO Infraestructura");
        }
    }
}
