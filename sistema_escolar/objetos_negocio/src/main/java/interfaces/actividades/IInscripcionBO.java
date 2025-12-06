/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces.actividades;

import dto.actividades.InscripcionDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IInscripcionBO {
    public boolean buscarEstudiantePorMatricula(InscripcionDTO inscripcionDTO);
    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO);
}
