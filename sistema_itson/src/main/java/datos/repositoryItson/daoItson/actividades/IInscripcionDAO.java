/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades;

import datos.dominioItson.actividades.Baja;
import datos.dominioItson.actividades.Inscripcion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IInscripcionDAO {
    Inscripcion InscribirGrupo(Inscripcion inscripcion);
    List<Inscripcion> obtenerInscripciones(String matricula);
    boolean actualizarEstado(ObjectId idInscripcion);
}
