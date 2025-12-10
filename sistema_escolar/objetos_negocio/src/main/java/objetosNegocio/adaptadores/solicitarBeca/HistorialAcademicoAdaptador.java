package objetosNegocio.adaptadores.solicitarBeca;
import gobierno.HistorialAcademicoDTOGobierno;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.BecasFiltradasAdaptadorException;
import objetosNegocio.adaptadores.solicitarBeca.excepciones.HistorialAcademicoAdaptadorException;
import solicitarBeca.HistorialAcademicoDTO;
import solicitarBeca.dominio.HistorialAcademico;
import solicitarBeca.dominio.enums.Carrera;
import itson.HistorialAcademicoDTOItson;

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
    public static HistorialAcademico toEntity(HistorialAcademicoDTOItson dto) {
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCarrera( Carrera.valueOf(dto.getCarrera()));
            historialAcademico.setPromedio( dto.getPromedio());
            historialAcademico.setPorcentajeBajas( dto.getPorcentajeBajas());
            historialAcademico.setCargaAcademica( dto.getCargaAcademica());
            historialAcademico.setSemestre( dto.getSemestre());
            historialAcademico.setIndiceReprobacion( dto.getIndiceReprobacion());
            return historialAcademico;
        } catch (Exception ex) {
            throw new HistorialAcademicoAdaptadorException("Error al convertir HistorialAcademicoDTOItson a entidad HistorialAcademico");
        }
    }

    /**
     * To entity historial academico.
     *
     * @param dto the dto
     * @return the historial academico
     */
    public static HistorialAcademico toEntity(HistorialAcademicoDTO dto) {
        try {
            HistorialAcademico historialAcademico = new HistorialAcademico();
            historialAcademico.setCarrera(Carrera.valueOf(dto.getCarrera()));
            historialAcademico.setPromedio( dto.getPromedio());
            historialAcademico.setPorcentajeBajas( dto.getPorcentajeBajas());
            historialAcademico.setCargaAcademica( dto.getCargaAcademica());
            historialAcademico.setSemestre( dto.getSemestre());
            historialAcademico.setIndiceReprobacion( dto.getIndiceReprobacion());
            return historialAcademico;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir HistorialAcademicoDTO a entidad HistorialAcademico");
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
            dto.setCarrera( historialAcademico.getCarrera().toString());
            dto.setPromedio( historialAcademico.getPromedio());
            dto.setPorcentajeBajas( historialAcademico.getPorcentajeBajas());
            dto.setCargaAcademica( historialAcademico.getCargaAcademica());
            dto.setSemestre( historialAcademico.getSemestre());
            dto.setIndiceReprobacion( historialAcademico.getIndiceReprobacion());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad HistorialAcademico a HistorialAcademicoDTO");
        }
    }

    /**
     * To dto historial academico dto.
     *
     * @param historialAcademicoDTOItson the historial academico
     * @return the historial academico dto
     */
    public static HistorialAcademicoDTO toDTO(HistorialAcademicoDTOItson historialAcademicoDTOItson) {
        try {
            HistorialAcademicoDTO dto = new HistorialAcademicoDTO();
            dto.setCarrera( historialAcademicoDTOItson.getCarrera().toString());
            dto.setPromedio( historialAcademicoDTOItson.getPromedio());
            dto.setPorcentajeBajas( historialAcademicoDTOItson.getPorcentajeBajas());
            dto.setCargaAcademica( historialAcademicoDTOItson.getCargaAcademica());
            dto.setSemestre( historialAcademicoDTOItson.getSemestre());
            dto.setIndiceReprobacion( historialAcademicoDTOItson.getIndiceReprobacion());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad HistorialAcademicoDTOGobierno a HistorialAcademicoDTOItson");
        }
    }

    /**
     * To dto gobierno historial academico dto gobierno.
     *
     * @param historialAcademico the historial academico
     * @return the historial academico dto gobierno
     */
    public static HistorialAcademicoDTOGobierno toDTOGobierno(HistorialAcademico historialAcademico) {
        try {
            HistorialAcademicoDTOGobierno dto = new HistorialAcademicoDTOGobierno();
            dto.setCarrera( historialAcademico.getCarrera().toString());
            dto.setPromedio( historialAcademico.getPromedio());
            dto.setPorcentajeBajas( historialAcademico.getPorcentajeBajas());
            dto.setCargaAcademica( historialAcademico.getCargaAcademica());
            dto.setSemestre( historialAcademico.getSemestre());
            dto.setIndiceReprobacion( historialAcademico.getIndiceReprobacion());
            return dto;
        } catch (Exception ex) {
            throw new BecasFiltradasAdaptadorException("Error al convertir entidad HistorialAcademico a HistorialAcademicoDTOGobierno");
        }
    }
}
