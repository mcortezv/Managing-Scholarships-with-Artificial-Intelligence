/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.actividades;

import actividades.dominio.Estudiante;
import adaptadores.actividades.EstudianteAdaptador;
import dto.actividades.EstudianteDTO;
import interfaces.IFachadaITSON;
import interfaces.actividades.IEstudianteBOAct;
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
    
    public EstudianteDTO obtenerEstudiante(EstudianteDTO estudianteDTO){
        Estudiante estudiante= estudianteDAO.buscarPorMatricula(estudianteDTO.getMatricula());
        if(estudiante==null){
            return null;
        }
        return EstudianteAdaptador.toDTO(estudiante);
    }
    
    public EstudianteDTO guardarEstudiante(EstudianteDTO estudianteDTO){
        Estudiante estudianteGuardar= EstudianteAdaptador.toEntity(estudianteDTO);
        Estudiante estudiante= estudianteDAO.guardarEstudiante(estudianteGuardar);
        return EstudianteAdaptador.toDTO(estudiante);
    }
    

    
    
}
