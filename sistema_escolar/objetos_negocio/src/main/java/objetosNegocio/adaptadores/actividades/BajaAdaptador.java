/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores.actividades;

import actividades.dominio.Baja;
import dto.actividades.BajaDTO;
import itson.actividades.BajaDTOItson;
import org.bson.types.ObjectId;

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
    
    public static Baja toEntity(BajaDTO bajaDTO){
        Baja bajaEntity= new Baja();
        bajaEntity.setFecha(bajaDTO.getFechaBaja());
        bajaEntity.setIdInscripcion((new ObjectId(bajaDTO.getIdInscripcion())));
        bajaEntity.setMotivo(bajaDTO.getMotivo());
        return bajaEntity;
    }
    
    public static BajaDTO entityToDTONegocio(Baja baja){
        BajaDTO bajaDTO= new BajaDTO();
        bajaDTO.setFechaBaja(baja.getFecha());
        bajaDTO.setIdInscripcion(String.valueOf(baja.getIdInscripcion()));
        bajaDTO.setIdBaja(String.valueOf(baja.getIdBaja()));
        return bajaDTO;
        
        
    }
    
}
