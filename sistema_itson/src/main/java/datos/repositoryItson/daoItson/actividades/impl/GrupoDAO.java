/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import datos.configMongoItson.MongoClienteProvider;
import datos.dominioItson.actividades.Actividad;
import datos.dominioItson.actividades.Grupo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class GrupoDAO {
    
    private final MongoCollection<Grupo> col;

    public GrupoDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("grupos", Grupo.class);
    }
    
    public List<Grupo> obtenerGrupos(Actividad actividad){
        Bson filtro = Filters.and(
                Filters.eq("actividad.nombre", actividad.getNombre()),
                Filters.gt("cupoDisponible", 0),
                Filters.gte("fechaLimiteInscripcion", new Date())
        );
      //  Bson filtro = Filters.eq("actividad.nombre", actividad.getNombre());
        return col.find(filtro).into(new ArrayList<>());
        
        
    }
    
    
    
    
    
    
    
    
    
}
