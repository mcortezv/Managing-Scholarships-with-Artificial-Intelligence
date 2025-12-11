/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.adaptadores.actividades;

import actividades.dominio.Actividad;
import actividades.dominio.Estudiante;
import actividades.dominio.Grupo;
import actividades.dominio.Horario;
import actividades.dominio.Inscripcion;
import dto.actividades.GrupoDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;
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

    public static InscripcionDTOItson toDTOItson(InscripcionDTO inscripcionDTO) {
        InscripcionDTOItson inscripcionDTOItson = new InscripcionDTOItson();
        //el id del estudiante
        if (inscripcionDTO.getId() != null) {
            inscripcionDTOItson.setIdEstudiante(inscripcionDTO.getIdEstudiante());
        }

        inscripcionDTOItson.setFechaInscripcion(inscripcionDTO.getFechaInscripcion());
        inscripcionDTOItson.setMatriculaEstudiante(inscripcionDTO.getMatriculaEstudiante());
        //id grupo
        inscripcionDTOItson.setIdActividad(inscripcionDTO.getIdActividad());
        inscripcionDTOItson.setIdGrupo(inscripcionDTO.getIdGrupo());
        inscripcionDTOItson.setNombreGrupo(inscripcionDTO.getNombreGrupo());
        inscripcionDTOItson.setNombreActividad(inscripcionDTO.getNombreActividad());
        inscripcionDTOItson.setCosto(inscripcionDTO.getCosto());
        return inscripcionDTOItson;
    }

    public static InscripcionDTO toDTONegocio(InscripcionDTOItson inscripcionDTOItson) {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        inscripcionDTO.setId(inscripcionDTOItson.getIdInscipcion());
        // el del estudiante
        inscripcionDTO.setIdEstudiante(inscripcionDTOItson.getIdEstudiante());
        inscripcionDTO.setFechaInscripcion(inscripcionDTOItson.getFechaInscripcion());
        inscripcionDTO.setMatriculaEstudiante(inscripcionDTOItson.getMatriculaEstudiante());
        //grupi
        inscripcionDTO.setIdActividad(inscripcionDTOItson.getIdActividad());
        inscripcionDTO.setIdGrupo(inscripcionDTOItson.getIdGrupo());
        inscripcionDTO.setNombreGrupo(inscripcionDTOItson.getNombreGrupo());
        inscripcionDTO.setNombreActividad(inscripcionDTOItson.getNombreActividad());
        inscripcionDTO.setCosto(inscripcionDTOItson.getCosto());
        inscripcionDTO.setDias(inscripcionDTOItson.getDias());
        inscripcionDTO.setHoraInicio(inscripcionDTOItson.getHoraInicio());
        inscripcionDTO.setHoraFin(inscripcionDTOItson.getHoraFin());
        return inscripcionDTO;
    }

    public static InscripcionDTOItson toDTOItsonID(InscripcionDTO inscripcion) {
        InscripcionDTOItson inscripcionDTOItson = new InscripcionDTOItson();
        inscripcionDTOItson.setIdGrupo(inscripcion.getIdGrupo());
        return inscripcionDTOItson;
    }

    public static InscripcionesDTO toDTONegocio(InscripcionesDTOItson inscripcionesItson) {
        InscripcionesDTO inscripcionesDTO = new InscripcionesDTO();
        List<InscripcionDTO> listaInscripciones = new ArrayList<>();
        for (InscripcionDTOItson inscripcion : inscripcionesItson.getInscripciones()) {
            listaInscripciones.add(toDTONegocio(inscripcion));
        }
        inscripcionesDTO.setInscripciones(listaInscripciones);
        return inscripcionesDTO;

    }

    public static Inscripcion toEntity(InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = new Inscripcion();
        Grupo grupo = new Grupo();
        inscripcion.setFechaInscripcion(LocalDate.now());
        //estudiante
        Estudiante estudiante = new Estudiante();
        estudiante.setId(new ObjectId(inscripcionDTO.getIdEstudiante()));
        estudiante.setMatricula(inscripcionDTO.getMatriculaEstudiante());
        inscripcion.setEstudiante(estudiante);
        //actividad
        Actividad actividad = new Actividad();
        actividad.setNombre(inscripcionDTO.getNombreActividad());
        actividad.setCosto(inscripcionDTO.getCosto());
        grupo.setActividad(actividad);

        Horario horario = new Horario();
        horario.setDias(inscripcionDTO.getDias());
        horario.setHoraInicio(inscripcionDTO.getHoraInicio());
        horario.setHoraFin(inscripcionDTO.getHoraFin());
        grupo.setHorario(horario);
        grupo.setId(new ObjectId(inscripcionDTO.getIdGrupo()));

        inscripcion.setGrupo(grupo);

        return inscripcion;

    }

    public static InscripcionDTO EntityToDTONegocio(Inscripcion inscripcion) {
        InscripcionDTO inscripcionDTO = new InscripcionDTO();
        //el id del estudiante
        if (inscripcionDTO.getId() != null) {
            inscripcionDTO.setIdEstudiante(String.valueOf(inscripcion.getId()));
        }

        inscripcionDTO.setFechaInscripcion(inscripcion.getFechaInscripcion());
        inscripcionDTO.setMatriculaEstudiante(inscripcion.getEstudiante().getMatricula());
        //id grupo
        inscripcionDTO.setIdActividad(String.valueOf(inscripcion.getGrupo().getActividad().getId()));
        inscripcionDTO.setIdGrupo(String.valueOf(inscripcion.getGrupo().getId()));
        inscripcionDTO.setNombreGrupo(inscripcion.getGrupo().getNombreGrupo());
        inscripcionDTO.setNombreActividad(inscripcion.getGrupo().getActividad().getNombre());
        inscripcionDTO.setCosto(inscripcion.getGrupo().getActividad().getCosto());
        return inscripcionDTO;
    }
}
