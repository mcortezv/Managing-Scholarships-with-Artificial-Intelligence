/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio.bo.actividades.interfaces;

import dto.actividades.ActividadDTO;
import dto.actividades.GruposResponseDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IGrupoBO {
    
     GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO);
    
}
