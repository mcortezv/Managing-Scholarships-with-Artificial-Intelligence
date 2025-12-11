/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.bo.actividades;

import actividades.dominio.Estudiante;
import objetosNegocio.adaptadores.actividades.EstudianteAdaptador;
import dto.actividades.EstudianteDTO;
import interfaces.IFachadaITSON;
import objetosNegocio.bo.actividades.interfaces.IEstudianteBOAct;
import actividades.dao.interfaces.IEstudianteDAOAct;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class EstudianteBOAct implements IEstudianteBOAct{
    
    private IFachadaITSON fachadaITSON;
    private final IEstudianteDAOAct estudianteDAO;

    public EstudianteBOAct(IFachadaITSON fachadaITSON, IEstudianteDAOAct estudianteDAO) {
        this.fachadaITSON = fachadaITSON;
        this.estudianteDAO= estudianteDAO;
    }
    
    public EstudianteDTO obtenerEstudiantePorMatricula(String matricula){
        Estudiante estudiante= estudianteDAO.buscarPorMatricula(matricula);
        if(estudiante==null){
            return null;
        }
        return EstudianteAdaptador.toDTO(estudiante);
    }
    
    public EstudianteDTO guardarEstudiante(String matricula){
        Estudiante estudianteGuardar= EstudianteAdaptador.toEntity(matricula);
        Estudiante estudiante= estudianteDAO.guardarEstudiante(estudianteGuardar);
        return EstudianteAdaptador.toDTO(estudiante);
    }
    

    
    
}
