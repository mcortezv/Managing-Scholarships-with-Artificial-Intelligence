package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.HistorialAcademicoAdaptadorException;
import datosGobierno.dominioGobierno.HistorialAcademico;
import datosGobierno.dominioGobierno.enums.Carrera;
import dtoGobierno.HistorialAcademicoDTO;
import gobierno.HistorialAcademicoDTOGobierno;

/**
 * The type Historial academico adaptador.
 */
public class HistorialAcademicoAdaptador {

    /**
     * To entity historial academico.
     *
     * @param dto the dto
     * @return the historial academico
     */
    public static HistorialAcademico toEntity(HistorialAcademicoDTO dto) {
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCargaAcademica(dto.getCargaAcademica());
            historialAcademico.setIndiceReprobacion(dto.getIndiceReprobacion());
            historialAcademico.setPorcentajeBajas(dto.getPorcentajeBajas());
            historialAcademico.setCarrera(Carrera.valueOf(dto.getCarrera()));
            historialAcademico.setPromedio(dto.getPromedio());
            historialAcademico.setSemestre(dto.getSemestre());
            return historialAcademico;
        } catch (Exception ex) {
            throw new HistorialAcademicoAdaptadorException("Error al convertir HistorialAcademicoDTO a entidad HistorialAcademico");
        }
    }

    /**
     * To entity historial academico.
     *
     * @param dto the dto
     * @return the historial academico
     */
    public static HistorialAcademico toEntity(HistorialAcademicoDTOGobierno dto) {
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCargaAcademica(dto.getCargaAcademica());
            historialAcademico.setIndiceReprobacion(dto.getIndiceReprobacion());
            historialAcademico.setPorcentajeBajas(dto.getPorcentajeBajas());
            historialAcademico.setCarrera(Carrera.valueOf(dto.getCarrera()));
            historialAcademico.setPromedio(dto.getPromedio());
            historialAcademico.setSemestre(dto.getSemestre());
            return historialAcademico;
        } catch (Exception ex) {
            throw new HistorialAcademicoAdaptadorException("Error al convertir HistorialAcademicoDTOGobierno a entidad HistorialAcademico");
        }
    }

    /**
     * To dto historial academico dto.
     *
     * @param historialAcademico the historial academico
     * @return the historial academico dto
     */
    public static HistorialAcademicoDTO toDTO(HistorialAcademico historialAcademico) {
        try {
            HistorialAcademicoDTO dto = new HistorialAcademicoDTO();
            dto.setCargaAcademica(historialAcademico.getCargaAcademica());
            dto.setIndiceReprobacion(historialAcademico.getIndiceReprobacion());
            dto.setPorcentajeBajas(historialAcademico.getPorcentajeBajas());
            dto.setCarrera(historialAcademico.getCarrera().toString());
            dto.setPromedio(historialAcademico.getPromedio());
            dto.setSemestre(historialAcademico.getSemestre());
            return dto;
        } catch (Exception ex) {
            throw new HistorialAcademicoAdaptadorException("Error al convertir entidad HistorialAcademico a HistorialAcademicoDTO");
        }
    }

    public static HistorialAcademicoDTOGobierno toDTOGobierno(HistorialAcademico entity) {
        try {
            if (entity == null) {
                return null;
            }
            HistorialAcademicoDTOGobierno dto = new HistorialAcademicoDTOGobierno();
            dto.setCargaAcademica(entity.getCargaAcademica());
            dto.setIndiceReprobacion(entity.getIndiceReprobacion());
            dto.setPorcentajeBajas(entity.getPorcentajeBajas());
            dto.setPromedio(entity.getPromedio());
            dto.setSemestre(entity.getSemestre());
            if (entity.getCarrera() != null) {
                dto.setCarrera(entity.getCarrera().toString());
            }
            return dto;
        } catch (Exception ex) {
            throw new HistorialAcademicoAdaptadorException("Error al convertir HistorialAcademico a HistorialAcademicoDTOGobierno");
        }
    }
}
