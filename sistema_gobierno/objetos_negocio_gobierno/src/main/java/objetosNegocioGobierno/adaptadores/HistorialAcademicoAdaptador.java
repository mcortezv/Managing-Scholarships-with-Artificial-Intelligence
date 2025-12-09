package objetosNegocioGobierno.adaptadores;
import datosGobierno.dominioGobierno.HistorialAcademico;
import datosGobierno.dominioGobierno.enums.Carrera;
import dtoGobierno.HistorialAcademicoDTO;
import gobierno.HistorialAcademicoDTOGobierno;
import objetosNegocioGobierno.adaptadores.excepciones.HistorialAcademicoAdaptadorException;

/**
 * The type Historial academico adaptador.
 *
 * @author Cortez, Manuel;
 */
public class HistorialAcademicoAdaptador {

    /**
     * To entity historial academico.
     *
     * @param dto the dto
     * @return the historial academico
     */
    public static HistorialAcademico toEntity(HistorialAcademicoDTO dto){
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCarrera(Carrera.valueOf(dto.getCarrera()));
            historialAcademico.setPromedio(dto.getPromedio());
            historialAcademico.setPorcentajeBajas(dto.getPorcentajeBajas());
            historialAcademico.setCargaAcademica(dto.getCargaAcademica());
            historialAcademico.setSemestre(dto.getSemestre());
            historialAcademico.setIndiceReprobacion(dto.getIndiceReprobacion());
            return historialAcademico;
        } catch (Exception sinUso){
            throw new HistorialAcademicoAdaptadorException("No se pudo mappear el HistorialAcademicoDTO a entidad HistorialAcademico");
        }
    }

    /**
     * To entity historial academico.
     *
     * @param dto the dto
     * @return the historial academico
     */
    public static HistorialAcademico toEntity(HistorialAcademicoDTOGobierno dto){
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCarrera(Carrera.valueOf(dto.getCarrera()));
            historialAcademico.setPromedio(dto.getPromedio());
            historialAcademico.setPorcentajeBajas(dto.getPorcentajeBajas());
            historialAcademico.setCargaAcademica(dto.getCargaAcademica());
            historialAcademico.setSemestre(dto.getSemestre());
            historialAcademico.setIndiceReprobacion(dto.getIndiceReprobacion());
            return historialAcademico;
        } catch (Exception sinUso){
            throw new HistorialAcademicoAdaptadorException("No se pudo mappear el HistorialAcademicoDTOGobierno a entidad HistorialAcademico");
        }
    }

    /**
     * To dto historial academico dto.
     *
     * @param historialAcademico the historial academico
     * @return the historial academico dto
     */
    public static HistorialAcademicoDTO toDTO(HistorialAcademico historialAcademico){
        try {
            HistorialAcademicoDTO dto = new HistorialAcademicoDTO();
            dto.setCarrera(historialAcademico.getCarrera().toString());
            dto.setPromedio(historialAcademico.getPromedio());
            dto.setPorcentajeBajas(historialAcademico.getPorcentajeBajas());
            dto.setCargaAcademica(historialAcademico.getCargaAcademica());
            dto.setSemestre(historialAcademico.getSemestre());
            dto.setIndiceReprobacion(historialAcademico.getIndiceReprobacion());
            return dto;
        } catch (Exception sinUso){
            throw new HistorialAcademicoAdaptadorException("No se pudo mappear la entidad HistorialAcademico a HistorialAcademicoDTO");
        }
    }

    /**
     * To infraestructura dto historial academico dto gobierno.
     *
     * @param historialAcademico the historial academico
     * @return the historial academico dto gobierno
     */
    public static HistorialAcademicoDTOGobierno toInfraestructuraDTO(HistorialAcademico historialAcademico){
        try {
            HistorialAcademicoDTOGobierno dto = new HistorialAcademicoDTOGobierno();
            dto.setCarrera(historialAcademico.getCarrera().toString());
            dto.setPromedio(historialAcademico.getPromedio());
            dto.setPorcentajeBajas(historialAcademico.getPorcentajeBajas());
            dto.setCargaAcademica(historialAcademico.getCargaAcademica());
            dto.setSemestre(historialAcademico.getSemestre());
            dto.setIndiceReprobacion(historialAcademico.getIndiceReprobacion());
            return dto;
        } catch (Exception sinUso){
            throw new HistorialAcademicoAdaptadorException("No se pudo mappear la entidad HistorialAcademico a HistorialAcademicoDTOGobierno");
        }
    }
}
