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
import datos.excepciones.DaoException;
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
    public List<Grupo> obtenerGrupos(Actividad actividad) throws DaoException{
        try{
            Bson filtro = Filters.and(
                    Filters.eq("actividad.id", actividad.getId()),
                    Filters.gt("cupoDisponible", 0),
                    Filters.gte("fechaLimiteInscripcion", new Date())
            );

            return col.find(filtro).into(new ArrayList<>());
        } catch(DaoException e){
            throw new DaoException("Error al obtener grupos en la base de datos");
        }

    }

    public Grupo obtenerGrupoInscrito(ObjectId idGrupo) throws DaoException{
        try {
            return col.find(Filters.eq("_id", idGrupo)).first();
        } catch (DaoException e) {
            throw new DaoException("Error al obtener grupos inscritos en la base de datos");
        }

    }
    
    public boolean actualizarCupo(ObjectId idGrupo) throws DaoException{
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
        } catch(DaoException e){
            throw new DaoException("Error al actualizar cupo del grupo en la base de datos");
        }
    }
    
     public LocalDate revisarFechaLimite(ObjectId idGrupo) throws DaoException{
         try{
            Grupo grupo = col.find(Filters.eq("_id", idGrupo)).first();
            return grupo.getFechaLimiteInscripcion();
         } catch(DaoException e){
             throw new DaoException("Error al obtener fecha limite de inscripcion en la base de datps");
         }
     }
     
     public int revisarCupoDisponible(ObjectId idGrupo) throws DaoException{
         try{
            Grupo grupo = col.find(Filters.eq("_id", idGrupo)).first();
            return grupo.getCupoDisponible();
         } catch(DaoException e){
             throw new DaoException("Error al intentar obtener cupo disponible de la base de datos");
         }
     }
       
}
