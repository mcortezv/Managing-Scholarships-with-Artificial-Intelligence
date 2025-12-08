/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.serviceItson.actividades;

import datos.adaptadoresItson.actividades.InscripcionAdaptador;
import datos.dominioItson.actividades.Inscripcion;
import datos.repositoryItson.daoItson.actividades.IInscripcionDAO;
import datos.repositoryItson.daoItson.actividades.impl.InscripcionDAO;
import itson.EstudianteDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionService {
    
    private final IInscripcionDAO inscripcionDAO;

    public InscripcionService() {
        this.inscripcionDAO = new InscripcionDAO();
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
    
    
}
