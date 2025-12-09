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
import itson.actividades.InscripcionesDTOItson;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public static Inscripcion toEntity(InscripcionDTOItson inscripcionDTOItson) {
        Inscripcion inscripcion = new Inscripcion();
        Grupo grupo = new Grupo();
        inscripcion.setFechaInscripcion(LocalDate.now());
        //estudiante
        Estudiante estudiante = new Estudiante();

        estudiante.setId(new ObjectId(inscripcionDTOItson.getIdEstudiante()));
        estudiante.setMatricula(inscripcionDTOItson.getMatriculaEstudiante());
        inscripcion.setEstudiante(estudiante);
        //actividad
        Actividad actividad = new Actividad();
        actividad.setId(new ObjectId(inscripcionDTOItson.getIdActividad()));
        actividad.setNombre(inscripcionDTOItson.getNombreActividad());
        actividad.setCosto(inscripcionDTOItson.getCosto());
        grupo.setActividad(actividad);
        grupo.setId(new ObjectId(inscripcionDTOItson.getIdGrupo()));
        grupo.setNombreGrupo(inscripcionDTOItson.getNombreGrupo());

        inscripcion.setGrupo(grupo);

        return inscripcion;

    }

    public static InscripcionDTOItson toDTOITSON(Inscripcion inscripcion) {

        InscripcionDTOItson inscripcionDTOItson = new InscripcionDTOItson();
        inscripcionDTOItson.setIdInscipcion(String.valueOf(inscripcion.getId()));
        inscripcionDTOItson.setFechaInscripcion(inscripcion.getFechaInscripcion());
        inscripcionDTOItson.setMatriculaEstudiante(inscripcion.getEstudiante().getMatricula());
        inscripcionDTOItson.setNombreGrupo(inscripcion.getGrupo().getNombreGrupo());
        inscripcionDTOItson.setCosto(inscripcion.getGrupo().getActividad().getCosto());
        inscripcionDTOItson.setNombreActividad(inscripcion.getGrupo().getActividad().getNombre());
        inscripcionDTOItson.setIdGrupo(String.valueOf(inscripcion.getGrupo().getId()));
        inscripcionDTOItson.setIdEstudiante(String.valueOf(inscripcion.getEstudiante().getId()));
        inscripcionDTOItson.setIdActividad(String.valueOf(inscripcion.getGrupo().getActividad().getId()));
        return inscripcionDTOItson;

    }

    public static InscripcionesDTOItson toDTOItson(List<Inscripcion> listaInscripciones) {
        InscripcionesDTOItson inscripcionesDTOItson = new InscripcionesDTOItson();
        List<InscripcionDTOItson> ListaInscripcionesDTOItson = new ArrayList<>();
        for (Inscripcion inscripcion : listaInscripciones) {
            InscripcionDTOItson inscripcionDTOItson = toDTOITSON(inscripcion);
            ListaInscripcionesDTOItson.add(inscripcionDTOItson);
        }
        inscripcionesDTOItson.setInscripciones(ListaInscripcionesDTOItson);
        return inscripcionesDTOItson;

    }

    public static ObjectId toObjectID(InscripcionDTOItson inscripcionDTOItson) {
        return new ObjectId(inscripcionDTOItson.getIdGrupo());

    }

}
