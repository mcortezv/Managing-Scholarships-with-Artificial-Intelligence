/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.daos;

import actividades.dominio.Estudiante;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import datos.configMongo.MongoClientProvider;
import org.bson.conversions.Bson;
import actividades.dao.interfaces.IEstudianteDAOAct;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class EstudianteDAOAct implements IEstudianteDAOAct{
    
    private final MongoCollection<Estudiante> coleccion;

    public EstudianteDAOAct() {
       this.coleccion = MongoClientProvider.INSTANCE.getCollection("estudiantes", Estudiante.class);
    }
    
    public Estudiante buscarPorMatricula(String matricula) {
        Bson filtro = Filters.eq("matricula", matricula);
        return coleccion.find(filtro).first();
    }
    
    public Estudiante guardarEstudiante(Estudiante estudiante){
        coleccion.insertOne(estudiante);
        return estudiante;
    }
    
    
    
    
    
    
    
}
