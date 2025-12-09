package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.HistorialAcademicoAdaptadorException;
import datosGobierno.dominioGobierno.HistorialAcademico;
import datosGobierno.dominioGobierno.enums.Carrera;
import dtoGobierno.HistorialAcademicoDTO;

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
}
