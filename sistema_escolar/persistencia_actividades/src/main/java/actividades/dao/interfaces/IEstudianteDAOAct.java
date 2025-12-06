/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package actividades.dao.interfaces;

import actividades.dominio.Estudiante;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IEstudianteDAOAct {
    
    public Estudiante buscarPorMatricula(String matricula);
     public Estudiante guardarEstudiante(Estudiante estudiante);
}
