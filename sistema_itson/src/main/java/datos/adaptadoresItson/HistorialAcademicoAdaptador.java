package datos.adaptadoresItson;
import datos.dominioItson.HistorialAcademico;
import itson.HistorialAcademicoDTOItson;


public class HistorialAcademicoAdaptador {

    public static HistorialAcademicoDTOItson toDTOItson(HistorialAcademico historialAcademico){
        HistorialAcademicoDTOItson itson = new HistorialAcademicoDTOItson();
        itson.setEstudiante(historialAcademico.getMatriculaEstudiante());
        itson.setCarrera(historialAcademico.getCarrera());
        itson.setPromedio(historialAcademico.getPromedio());
        itson.setPorcentajeBajas(historialAcademico.getPorcentajeBajas());
        itson.setCargaAcademica(historialAcademico.getCargaAcademica());
        itson.setSemestre(historialAcademico.getSemestre());
        itson.setIndiceReprobacion(historialAcademico.getIndiceReprobacion());
        return itson;
    }
}
