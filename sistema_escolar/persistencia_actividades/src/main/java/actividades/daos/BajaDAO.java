/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.daos;

import actividades.dao.interfaces.IBajaDAO;
import actividades.dominio.Baja;
import com.mongodb.client.MongoCollection;
import datos.configMongo.MongoClientProvider;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BajaDAO implements IBajaDAO{
    
    private final MongoCollection<Baja> col;

    public BajaDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("bajas", Baja.class);
    }    
    
    
    public Baja guardarBajaLocal(Baja baja){
        col.insertOne(baja);
        return baja;
        
    }
    
    
}
