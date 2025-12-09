/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio.bo.actividades.interfaces;

import dto.actividades.EstudianteDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IInscripcionBO {
  
    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO);
    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO);
}
