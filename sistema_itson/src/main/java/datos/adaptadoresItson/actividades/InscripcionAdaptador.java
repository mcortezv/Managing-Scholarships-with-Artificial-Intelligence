/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.adaptadoresItson.actividades;

import datos.dominioItson.actividades.Actividad;
import datos.dominioItson.actividades.Estudiante;
import datos.dominioItson.actividades.Grupo;
import datos.dominioItson.actividades.Horario;
import datos.dominioItson.actividades.Inscripcion;
import itson.actividades.InscripcionDTOItson;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionAdaptador {
    
   private LocalDate fechaInscripcion;
  //  private Estado estado;
    //estudiante
    private String matriculaEstudiante;
    private String nombreGrupo;
    private double costo;
    //horario
    private List<String> dias;
    private String horaInicio;
    private String horaFin;
    
    public static Inscripcion toEntity(InscripcionDTOItson inscripcionDTOItson){
        Inscripcion inscripcion= new Inscripcion();
        Grupo grupo= new Grupo();
        inscripcion.setFechaInscripcion(LocalDate.now());
        //estudiante
       Estudiante estudiante= new Estudiante();
       estudiante.setId(new ObjectId(inscripcionDTOItson.getIdEstudiante()));
       estudiante.setMatricula(inscripcionDTOItson.getMatriculaEstudiante());
       inscripcion.setEstudiante(estudiante);
       //actividad
       Actividad actividad= new Actividad();
       actividad.setNombre(inscripcionDTOItson.getNombreGrupo());
       actividad.setCosto(inscripcionDTOItson.getCosto());
       grupo.setActividad(actividad);
       
       
       Horario horario= new Horario();
       horario.setDias(inscripcionDTOItson.getDias());
       horario.setHoraInicio(inscripcionDTOItson.getHoraInicio());
       horario.setHoraFin(inscripcionDTOItson.getHoraFin());
       grupo.setHorario(horario);
       grupo.setId(new ObjectId(inscripcionDTOItson.getIdGrupo()));
       
       inscripcion.setGrupo(grupo);

        return inscripcion;
        
    }
    
    public static InscripcionDTOItson toDTOITSON(Inscripcion inscripcion){
        
        InscripcionDTOItson inscripcionDTOItson= new InscripcionDTOItson();
        inscripcionDTOItson.setIdInscipcion(String.valueOf(inscripcion.getId()));
        inscripcionDTOItson.setFechaInscripcion(inscripcion.getFechaInscripcion());
        inscripcionDTOItson.setMatriculaEstudiante(inscripcion.getEstudiante().getMatricula());
        inscripcionDTOItson.setNombreGrupo(inscripcion.getGrupo().getActividad().getNombre());
        inscripcionDTOItson.setCosto(inscripcion.getGrupo().getActividad().getCosto());
        inscripcionDTOItson.setHoraInicio(inscripcion.getGrupo().getHorario().getHoraInicio());
        inscripcionDTOItson.setHoraFin(inscripcion.getGrupo().getHorario().getHoraFin());
        
        inscripcionDTOItson.setIdGrupo(String.valueOf(inscripcion.getGrupo().getId()));
        inscripcionDTOItson.setIdEstudiante(String.valueOf(inscripcion.getEstudiante().getId()));
        return inscripcionDTOItson;
        
        
    }
}

