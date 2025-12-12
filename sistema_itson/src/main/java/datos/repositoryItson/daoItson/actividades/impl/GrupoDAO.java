/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import datos.configMongoItson.MongoClienteProvider;
import datos.dominioItson.actividades.Actividad;
import datos.dominioItson.actividades.Grupo;
import datos.repositoryItson.daoItson.IGrupoDAO;
import itson.actividades.InscripcionDTOItson;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoDAO implements IGrupoDAO{

    private final MongoCollection<Grupo> col;

    public GrupoDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("grupos", Grupo.class);
    }

    
    // la esta buscando por nombre, no por ID 
    public List<Grupo> obtenerGrupos(Actividad actividad) {
        Bson filtro = Filters.and(
                Filters.eq("actividad.id", actividad.getId()),
                Filters.gt("cupoDisponible", 0),
                Filters.gte("fechaLimiteInscripcion", new Date())
        );

        return col.find(filtro).into(new ArrayList<>());

    }

    public Grupo obtenerGrupoInscrito(ObjectId idGrupo) {
        try {
            return col.find(Filters.eq("_id", idGrupo)).first();
        } catch (Exception e) {
            System.out.println("no se ha podido recuperar el grupo");
        }
        return null;

    }
    
    public boolean actualizarCupo(ObjectId idGrupo){
        try{
            Bson filtro = Filters.and(
                    Filters.eq("_id", idGrupo),
                    Filters.gt("cupoDisponible", 0)
            );
            UpdateResult resultado = col.updateOne(
                    filtro,
                    Updates.inc("cupoDisponible", -1)
            );
            return resultado.getModifiedCount()>0;
        } catch(Exception e){
            System.out.println("error al actualizar"+e.getMessage());
            return false;
        }
    }
    
     public LocalDate revisarFechaLimite(ObjectId idGrupo){
         Grupo grupo = col.find(Filters.eq("_id", idGrupo)).first();
         return grupo.getFechaLimiteInscripcion();
     }
     
     public int revisarCupoDisponible(ObjectId idGrupo){
         Grupo grupo = col.find(Filters.eq("_id", idGrupo)).first();
         return grupo.getCupoDisponible();
     }
       
}
