/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.bo.tutorias;

import objetosNegocio.adaptadores.tutorias.MateriaAdaptador;
import objetosNegocio.bo.tutorias.excepciones.MateriaException;
import dto.tutorias.MateriaDTO;
import objetosNegocio.bo.tutorias.interfaces.IMateriaBO;
import java.util.ArrayList;
import java.util.List;
import tutorias.dao.interfaces.IMateriaDAO;
import tutorias.dominio.Materia;

/**
 *
 * @author katia
 */
public class MateriaBO implements IMateriaBO{
    private final IMateriaDAO materiaDAO;

    public MateriaBO(IMateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }

    @Override
    public List<MateriaDTO> obtenerTodasLasMaterias() {
        try {
            List<Materia> materias = materiaDAO.obtenerMaterias();
            List<MateriaDTO> materiasDTO = new ArrayList<>();
            for (Materia materia : materias) {
                MateriaDTO dto = MateriaAdaptador.toDTO(materia);
                materiasDTO.add(dto);
            }
            return materiasDTO;
        } catch (Exception ex) {
            throw new MateriaException("Error al obtener todas las materias.");
        }
    }

    @Override
    public MateriaDTO obtenerMateriaPorId(Long idMateria) {
        if (idMateria == null || idMateria <= 0) {
            throw new MateriaException("El ID de la materia no puede ser nulo o menor/igual a cero.");
        }
        try {
            Materia materia = materiaDAO.obtenerPorId(idMateria);
            return MateriaAdaptador.toDTO(materia);
        } catch (Exception ex) {
            throw new MateriaException("Error al obtener la materia por id.");
        }
    }
}
