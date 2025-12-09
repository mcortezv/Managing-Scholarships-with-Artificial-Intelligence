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
import datos.dominioItson.actividades.Inscripcion;
import datos.repositoryItson.daoItson.actividades.IInscripcionDAO;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionDAO implements IInscripcionDAO{
    
    private final MongoCollection<Inscripcion> col;

    public InscripcionDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("inscripcion", Inscripcion.class);
    }
    
    public Inscripcion InscribirGrupo(Inscripcion inscripcion){
        try{
            col.insertOne(inscripcion);
            return inscripcion;
        } catch(Exception e){
            return null;
        }
    }
    
     public List<Inscripcion> obtenerInscripciones(String matricula){
         List<Inscripcion> listaInscripciones = new ArrayList<>();
         try{
             col.find(Filters.eq("estudiante.matricula", matricula))
                     .into(listaInscripciones);
         } catch(Exception e){
             System.out.println("error al obtener inscripciones"+e.getMessage());
         }
         return listaInscripciones;
                 
         
     }
     
     public boolean actualizarEstado(ObjectId idInscripcion){
         UpdateResult resultado= col.updateOne(
                 Filters.eq("_id", idInscripcion),
                 Updates.set("estado", "baja")
         );
         return resultado.getModifiedCount() > 0;
     }
     
     

    
    
    

}
