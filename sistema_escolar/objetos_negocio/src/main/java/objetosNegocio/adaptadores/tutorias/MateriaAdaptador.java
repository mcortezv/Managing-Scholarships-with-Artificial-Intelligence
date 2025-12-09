/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.adaptadores.tutorias;

import objetosNegocio.adaptadores.tutorias.excepciones.MateriaAdaptadorException;
import dto.tutorias.MateriaDTO;
import tutorias.dominio.Materia;

/**
 *
 * @author katia
 */
public class MateriaAdaptador {
    public static Materia toEntity(MateriaDTO dto) {
        if (dto == null) return null;
        
        try {
            Materia materia = new Materia();
            materia.setId(dto.getId());
            materia.setNombre(dto.getNombre());
            return materia;
        } catch (Exception ex) {
            throw new MateriaAdaptadorException("Error al convertir MateriaDTO a entidad");
        }
    }

    public static MateriaDTO toDTO(Materia materia) {
        if (materia == null) return null;
        try {
            MateriaDTO dto = new MateriaDTO();
            dto.setId(materia.getId());
            dto.setNombre(materia.getNombre());
            return dto;
        } catch (Exception ex) {
            throw new MateriaAdaptadorException("Error al convertir entidad Materia a DTO");
        }
    }

//    public static Materia toEntity(MateriaDocument doc) {
//        if (doc == null) return null;
//        try {
//            Materia materia = new Materia();
//            materia.setId(doc.getIdMateria());
//            materia.setNombre(doc.getNombre());
//            return materia;
//        } catch (Exception ex) {
//            throw new MateriaAdaptadorException("Error al convertir MateriaDocument a entidad");
//        }
//    }
//    
//    public static MateriaDocument toDocument(Materia materia) {
//        if (materia == null) return null;
//        try {
//            MateriaDocument doc = new MateriaDocument();
//            doc.set_id(new ObjectId());
//            doc.setIdMateria(materia.getId());
//            doc.setNombre(materia.getNombre());
//            return doc;
//        } catch (Exception ex) {
//            throw new MateriaAdaptadorException("Error al convertir entidad Materia a Document");
//        }
//    }    
}
