/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades;

import datos.dominioItson.actividades.Actividad;
import datos.dominioItson.actividades.Grupo;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IGrupoDAO {
    
    List<Grupo> obtenerGrupos(Actividad actividad);
    Grupo obtenerGrupoInscrito(ObjectId idGrupo);
    boolean actualizarCupo(ObjectId idGrupo);
    LocalDate revisarFechaLimite(ObjectId idGrupo);
    int revisarCupoDisponible(ObjectId idGrupo);
}
