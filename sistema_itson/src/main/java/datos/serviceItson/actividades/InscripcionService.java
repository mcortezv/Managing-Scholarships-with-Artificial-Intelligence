/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.serviceItson.actividades;

import datos.adaptadoresItson.actividades.BajaAdaptador;
import datos.adaptadoresItson.actividades.InscripcionAdaptador;
import datos.dominioItson.actividades.Baja;
import datos.dominioItson.actividades.Inscripcion;
import datos.repositoryItson.daoItson.actividades.IBajaDAO;
import datos.repositoryItson.daoItson.actividades.IInscripcionDAO;
import datos.repositoryItson.daoItson.actividades.impl.BajaDAO;
import datos.repositoryItson.daoItson.actividades.impl.InscripcionDAO;
import itson.EstudianteDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionService {
    
    private final IInscripcionDAO inscripcionDAO;
    private final IBajaDAO bajaDAO;

    public InscripcionService() {
        this.inscripcionDAO = new InscripcionDAO();
        this.bajaDAO= new BajaDAO();
    }
    
    public InscripcionDTOItson inscribirActividad(InscripcionDTOItson inscripcionDTO){
        Inscripcion inscripcion= InscripcionAdaptador.toEntity(inscripcionDTO);
        InscripcionDTOItson inscripcionDTOItson= InscripcionAdaptador.toDTOITSON(inscripcionDAO.InscribirGrupo(inscripcion));
        return inscripcionDTOItson;
        
    }
    
    public InscripcionesDTOItson obtenerInscripciones(EstudianteDTOItson estudianteDTO){
        List<Inscripcion> inscripciones= inscripcionDAO.obtenerInscripciones(String.valueOf(estudianteDTO.getMatricula()));
        return InscripcionAdaptador.toDTOItson(inscripciones);
    }
    
    public BajaDTOItson darBajaActividad(BajaDTOItson baja){
        Baja bajaEntity= BajaAdaptador.toEntity(baja);
        Baja bajaGuardada= bajaDAO.guardarBaja(bajaEntity);
        return BajaAdaptador.toDTOItson(bajaEntity);
        
        
    }
    
    public boolean actualizarEstadoInscripcion(String idInscripcion){
        return inscripcionDAO.actualizarEstado(new ObjectId(idInscripcion));
        
    }
    
    
    
}
