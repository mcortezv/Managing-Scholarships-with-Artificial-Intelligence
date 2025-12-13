/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades.impl;

import com.mongodb.client.MongoCollection;
import datos.configMongoItson.MongoClienteProvider;
import datos.dominioItson.actividades.Baja;
import datos.excepciones.DaoException;
import datos.repositoryItson.daoItson.actividades.IBajaDAO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BajaDAO implements IBajaDAO{
    
    private final MongoCollection<Baja> col;

    public BajaDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("bajaActividades", Baja.class);
    }
    
    public Baja guardarBaja(Baja baja) throws DaoException{
        try{
            col.insertOne(baja);
            return baja;
        } catch(DaoException e){
            throw new DaoException("Error al guardar baja en la base de datos");
        }
        
    }
    
    
    
    
    
    
}
