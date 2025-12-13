/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositoryItson.daoItson.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import datos.configMongoItson.MongoClienteProvider;
import datos.dominioItson.actividades.Actividad;
import datos.excepciones.DaoException;
import datos.repositoryItson.daoItson.IActividadDAO;
import itson.actividades.ActividadDTOItson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ActividadDAO implements IActividadDAO {
    
    private final MongoCollection<Actividad> coleccion;

    public ActividadDAO() {
        this.coleccion = MongoClienteProvider.INSTANCE.getCollection("actividades", Actividad.class);
    }
    
      public List<Actividad> obtenerActividades() {
          try{
            List<Actividad> actividades= coleccion.find().into(new ArrayList<>());
            return actividades;
          } catch(DaoException e){
              throw new DaoException("Error al obtener inscripciones de la base de datos");
          }
          
      }
      
      public Actividad obtenerActividaddPorNombre(String nombre){
          try{
             return coleccion.find(Filters.eq("nombre", nombre)).first();
          } catch(DaoException e){
              throw new DaoException("Error al obtener actividades por nombre de la base de datos");
          }
      }
}
