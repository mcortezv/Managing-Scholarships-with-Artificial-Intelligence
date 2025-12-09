/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio.bo.actividades.interfaces;

import dto.actividades.EstudianteDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IEstudianteBOAct {
    public EstudianteDTO obtenerEstudiante(EstudianteDTO estudianteDTO);
    public EstudianteDTO guardarEstudiante(EstudianteDTO estudianteDTO);
    
}
