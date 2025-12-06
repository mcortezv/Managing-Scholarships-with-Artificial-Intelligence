
package interfaces;


import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IFachadaActividad {

     ActividadesDTO obtenerActividades();
     GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO);
     InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO);
}