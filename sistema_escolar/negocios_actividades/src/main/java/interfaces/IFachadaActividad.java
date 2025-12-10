
package interfaces;


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
public interface IFachadaActividad {

     ActividadesDTO obtenerActividades();
     GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO);
     InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO);
     InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO);
     GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO);
     BajaDTO darBajaActividad(BajaDTO baja);
}