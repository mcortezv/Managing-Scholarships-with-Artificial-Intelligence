/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos.repositoryItson.daoItson.actividades;

import datos.dominioItson.actividades.Inscripcion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IInscripcionDAO {
    public Inscripcion InscribirGrupo(Inscripcion inscripcion);
    public List<Inscripcion> obtenerInscripciones(String matricula);
    public boolean actualizarEstado(ObjectId idInscripcion);
}
