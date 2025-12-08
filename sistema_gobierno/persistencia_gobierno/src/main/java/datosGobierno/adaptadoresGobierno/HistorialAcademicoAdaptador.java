package datosGobierno.adaptadoresGobierno;
import datosGobierno.adaptadoresGobierno.excepciones.HistorialAcademicoAdaptadorException;
import datosGobierno.dominioGobierno.HistorialAcademico;
import dtoGobierno.HistorialAcademicoDTO;


public class HistorialAcademicoAdaptador {

    public static HistorialAcademico toEntity(HistorialAcademicoDTO dto) {
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCargaAcademica(dto.getCargaAcademica());
            historialAcademico.setIndiceReprobacion(dto.getIndiceReprobacion());
            historialAcademico.setPorcentajeBajas(dto.getPorcentajeBajas());
            historialAcademico.setCarrera(historialAcademico.getCarrera());
            historialAcademico.setPromedio(historialAcademico.getPromedio());
            historialAcademico.setSemestre(historialAcademico.getSemestre());
            return historialAcademico;
        } catch (Exception ex) {
            throw new HistorialAcademicoAdaptadorException("Error al convertir HistorialAcademicoDTO a HistorialAcademico");
        }
    }

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
            throw new HistorialAcademicoAdaptadorException("Error al convertir HistorialAcademico a HistorialAcademicoDTO");
        }
    }
}
