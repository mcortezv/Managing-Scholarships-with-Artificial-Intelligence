/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.adaptadoresItson.actividades;

import datos.dominioItson.actividades.Baja;
import itson.actividades.BajaDTOItson;
import org.bson.types.ObjectId;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BajaAdaptador {
    
    public static Baja toEntity(BajaDTOItson bajaDTOItson){
       Baja baja= new Baja();
       baja.setIdInscripcion(new ObjectId(bajaDTOItson.getIdInscripcion()));
       baja.setMotivo(bajaDTOItson.getMotivo());
       return baja;
    
    }
    
    public static BajaDTOItson toDTOItson(Baja baja){
        BajaDTOItson bajaDTOItson= new BajaDTOItson();
        bajaDTOItson.setIdInscripcion(String.valueOf(baja.getIdInscripcion()));
        bajaDTOItson.setMotivo(baja.getMotivo());
        bajaDTOItson.setFechaBaja(baja.getFecha());
        return bajaDTOItson;
        
    }
}
