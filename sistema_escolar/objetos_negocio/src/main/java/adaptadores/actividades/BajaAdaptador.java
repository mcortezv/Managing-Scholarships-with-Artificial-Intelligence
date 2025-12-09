/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;

import dto.actividades.BajaDTO;
import itson.actividades.BajaDTOItson;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BajaAdaptador {
    
    public static BajaDTOItson toDTOItson(BajaDTO baja){
        BajaDTOItson bajaDTOItson= new BajaDTOItson();
        bajaDTOItson.setIdInscripcion(baja.getIdInscripcion());
        bajaDTOItson.setMotivo(baja.getMotivo());
        return bajaDTOItson;
    }
    
    public static BajaDTO toDTONegocio(BajaDTOItson bajaDTOItson){
        BajaDTO bajaDTO= new BajaDTO();
        bajaDTO.setFechaBaja(bajaDTOItson.getFechaBaja());
        bajaDTO.setIdInscripcion(bajaDTOItson.getIdInscripcion());
        bajaDTO.setMotivo(bajaDTOItson.getMotivo());
        return bajaDTO;
    }
    
}
