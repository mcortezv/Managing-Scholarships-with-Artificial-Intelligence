/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.serviceItson;
import datos.adaptadoresItson.actividades.ActividadAdaptador;
import datos.adaptadoresItson.actividades.GrupoAdaptador;
import datos.dominioItson.actividades.Actividad;
import datos.dominioItson.actividades.Grupo;
import datos.adaptadoresItson.actividades.InscripcionAdaptador;
import datos.excepciones.DaoException;
import datos.excepciones.ServiceException;
import datos.repositoryItson.daoItson.actividades.impl.GrupoDAO;
import itson.actividades.ActividadDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.GruposResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoService {
    
    private final GrupoDAO grupoDAO;

    public GrupoService() {
        this.grupoDAO = new GrupoDAO();
    }
    
    public GruposResponseDTOItson obtenerGrupos(ActividadDTOItson actividad){
        try{
            Actividad actividadEntity= ActividadAdaptador.toEntity(actividad);
            List<Grupo> grupos= grupoDAO.obtenerGrupos(actividadEntity);
            return GrupoAdaptador.toDTOLista(grupos);
        } catch(DaoException e){
            throw new ServiceException("Error al obtener la lista de grupos disponibles", e);
        
        }
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcionDTOItson){
        try{
            ObjectId idGrupo= InscripcionAdaptador.toObjectID(inscripcionDTOItson);
            Grupo grupo= grupoDAO.obtenerGrupoInscrito(idGrupo);
            return GrupoAdaptador.toDTO(grupo);
        } catch(DaoException e){
            throw new ServiceException("Error al recuperar el grupo inscrito.", e);
        }
        
        
    }
    
    public boolean actualizarCupoDisponible(String idGrupo){
        try{
          return grupoDAO.actualizarCupo(new ObjectId(idGrupo));
        } catch(DaoException e){
            throw new ServiceException("Error al intentar actualizar el cupo del grupo.", e);
        }
    }
    
    public LocalDate revisarFechaLimite(String idGrupo){
        try{
         return grupoDAO.revisarFechaLimite(new ObjectId(idGrupo));
        } catch(DaoException e){
            throw new ServiceException("Error al verificar la fecha límite del grupo.", e);
        }
      }
    
    public int revisarCupoDisponible(String idGrupo){
        try{
         return grupoDAO.revisarCupoDisponible(new ObjectId(idGrupo));
        } catch(DaoException e){
            throw new ServiceException("Error al revisar el cupo disponible del grupo", e);
        }
    }
    
    
    
    
    
}
