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
import datos.excepciones.DaoException;
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
public class InscripcionDAO implements IInscripcionDAO {

    private final MongoCollection<Inscripcion> col;

    public InscripcionDAO() {
        this.col = MongoClienteProvider.INSTANCE.getCollection("inscripcion", Inscripcion.class);
    }

    public Inscripcion InscribirGrupo(Inscripcion inscripcion) throws DaoException{
        try {
            col.insertOne(inscripcion);
            return inscripcion;
        } catch (DaoException e) {
             throw new DaoException("Error al inscribir grupo en la base de datos");
        }
    }

    public List<Inscripcion> obtenerInscripciones(String matricula)throws DaoException {
        try{
            List<Inscripcion> listaInscripciones = new ArrayList<>();
            col.find(Filters.and(
                    Filters.eq("estudiante.matricula", matricula),
                    Filters.eq("estado", "ACTIVA")
            )).into(listaInscripciones);
            return listaInscripciones;
        } catch (DaoException e) {
            throw new DaoException("Error al obtener inscripciones en la base de datos");
        }
    }

    public boolean actualizarEstado(ObjectId idInscripcion) {
        try{
            UpdateResult resultado = col.updateOne(
                    Filters.eq("_id", idInscripcion),
                    Updates.set("estado", "BAJA")
            );
            return resultado.getModifiedCount() > 0;
        } catch(DaoException e){
            throw new DaoException("Error al actualizar estado de inscripcion de la base de datos");
        }
    }

}
