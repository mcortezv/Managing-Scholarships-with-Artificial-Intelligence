package apiItson.interfaces;
import itson.pagarAdeudo.SolicitudPagoDTOI;
import datos.dominioItson.HistorialAcademico;
import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import itson.LoginDTOItson;
import datos.dominioItson.Estudiante;
import itson.ActividadDTOItson;
import itson.ActividadesDTOItson;
import itson.EstudianteDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.util.List;

public interface IItsonAPI {

    boolean verificarLogin(LoginDTOItson dto);

    Estudiante obtenerDatosEstudiante(Long matricula);

    HistorialAcademico obtenerHistorialAcademico(Long matricula);




    //pagar adeudo
    List<Prestamo> obtenerListaPrestamosBiblioteca(Long matricula);
    List<Clase> obtenerListaClaseColegiatura(Long matricula);
    boolean notificarLiquidacion(SolicitudPagoDTOI solicitudPagoDTOI);
    //actividades
     ActividadesDTOItson soloicitarActividades();
     GruposResponseDTOItson solicitarGrupos(ActividadDTOItson actividad);
     InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTOItson);
     InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO);
     GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcion);


}
