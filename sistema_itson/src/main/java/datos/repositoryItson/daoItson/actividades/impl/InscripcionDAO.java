/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades.impl;

import com.mongodb.client.MongoCollection;
import datos.configMongoItson.MongoClienteProvider;
import datos.dominioItson.actividades.Inscripcion;
import datos.repositoryItson.daoItson.actividades.IInscripcionDAO;

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
    
    
    

}
