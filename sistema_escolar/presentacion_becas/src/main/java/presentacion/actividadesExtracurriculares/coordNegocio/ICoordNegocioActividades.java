/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.actividadesExtracurriculares.coordNegocio;

import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.BajaDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface ICoordNegocioActividades {
    ActividadesDTO obtenerActividades();
    ActividadDTO obtenerActividadPorNombre(ActividadDTO actividadDTO);
    GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO);
    InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO);
     InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO);
     GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO);
     BajaDTO darBajaActividad(BajaDTO baja);
     
}
