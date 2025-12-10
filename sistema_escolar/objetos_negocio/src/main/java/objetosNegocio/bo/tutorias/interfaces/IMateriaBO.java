/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio.bo.tutorias.interfaces;

import dto.tutorias.MateriaDTO;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IMateriaBO {
    List<MateriaDTO> obtenerTodasLasMaterias();
    MateriaDTO obtenerMateriaPorId(Long idMateria);
}
