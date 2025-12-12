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
        Actividad actividadEntity= ActividadAdaptador.toEntity(actividad);
        List<Grupo> grupos= grupoDAO.obtenerGrupos(actividadEntity);
        return GrupoAdaptador.toDTOLista(grupos);
        
    }
    
    public GrupoResponseDTOItson obtenerGrupoInscrito(InscripcionDTOItson inscripcionDTOItson){

        ObjectId idGrupo= InscripcionAdaptador.toObjectID(inscripcionDTOItson);
        Grupo grupo= grupoDAO.obtenerGrupoInscrito(idGrupo);
        return GrupoAdaptador.toDTO(grupo);
        
        
    }
    
    public boolean actualizarCupoDisponible(String idGrupo){
        return grupoDAO.actualizarCupo(new ObjectId(idGrupo));
    }
    
    public LocalDate revisarFechaLimite(String idGrupo){
        return grupoDAO.revisarFechaLimite(new ObjectId(idGrupo));
        }
    
    public int revisarCupoDisponible(String idGrupo){
        return grupoDAO.revisarCupoDisponible(new ObjectId(idGrupo));
    }
    
    
    
    
    
}
