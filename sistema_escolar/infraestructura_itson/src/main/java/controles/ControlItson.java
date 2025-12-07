package controles;

import apiItson.ItsonAPI;
import apiItson.interfaces.IItsonAPI;
import itson.pagarAdeudo.ClaseDTOI;
import itson.pagarAdeudo.PrestamoDTOI;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.adaptadoresItson.EstudianteAdaptador;
import datos.adaptadoresItson.HistorialAcademicoAdaptador;
import datos.adaptadoresItson.pagarAdeudo.ClaseAdaptador;
import datos.adaptadoresItson.pagarAdeudo.PrestamoAdaptador;
import datos.dominioItson.Estudiante;
import itson.ActividadDTOItson;
import itson.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.HistorialAcademicoDTOItson;
import itson.LoginDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;

import java.util.List;

public class ControlItson {

    IItsonAPI api;

    public ControlItson() {
        this.api = new ItsonAPI();
    }

    public boolean verificarLogin(LoginDTOItson loginDTO) {
        LoginDTOItson dtoInfra = new LoginDTOItson();
        dtoInfra.setUsuario(loginDTO.getUsuario());
        dtoInfra.setContrasenia(loginDTO.getContrasenia());
        return api.verificarLogin(dtoInfra);
    }

    public EstudianteDTOItson verificarEstudiante(Long matricula) {
        Estudiante estudiante = api.obtenerDatosEstudiante(matricula);
        return EstudianteAdaptador.toResponseDTO(estudiante);
    }

    public HistorialAcademicoDTOItson verificarHistorialAcademico(Long matricula) {
        return HistorialAcademicoAdaptador.toDTOItson(api.obtenerHistorialAcademico(matricula));
    }

    //Caso de uso Pagar adeudo
    public List<PrestamoDTOI> obtenerListaPrestamos(Long matricula) {
        return PrestamoAdaptador.toDtoItson(api.obtenerListaPrestamosBiblioteca(matricula));
    }

    public List<ClaseDTOI> obtenerListaClases(Long matricula) {
        return ClaseAdaptador.toDtoItson(api.obtenerListaClaseColegiatura(matricula));
    }

    public boolean solicitarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI) {
        return api.notificarLiquidacion(solicitudPagoDTOI);
    }

    // caso de uso actividades
    public ActividadesDTOItson obtenerActividades() {
        return api.soloicitarActividades();
    }

    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividadDTOItson) {
        return api.solicitarGrupos(actividadDTOItson);
    }

    public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson) {
        return api.inscribirActividad(inscripcionDTOItson);
    }
    
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        return api.obtenerInscripciones(estudianteDTO);
    }

}
