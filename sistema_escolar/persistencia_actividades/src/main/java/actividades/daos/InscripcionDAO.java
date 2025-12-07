/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.daos;

import actividades.dao.interfaces.IInscripcionDAO;
import actividades.dominio.Inscripcion;
import com.mongodb.client.MongoCollection;
import datos.configMongo.MongoClientProvider;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionDAO implements IInscripcionDAO{
    
    private final MongoCollection<Inscripcion> coleccion;

    public InscripcionDAO() {
        this.coleccion = MongoClientProvider.INSTANCE.getCollection("inscripcion", Inscripcion.class);
    }
    
    public Inscripcion InscribirGrupo(Inscripcion inscripcion){
        try{
            coleccion.insertOne(inscripcion);
            return inscripcion;
        } catch(Exception e){
            return null;
        }
    }    
    
    
    
}
