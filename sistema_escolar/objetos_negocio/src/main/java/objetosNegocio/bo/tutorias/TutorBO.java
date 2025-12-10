/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.bo.tutorias;

import objetosNegocio.adaptadores.tutorias.TutorAdaptador;
import objetosNegocio.bo.tutorias.excepciones.TutorException;
import dto.tutorias.TutorDTO;
import objetosNegocio.bo.tutorias.interfaces.ITutorBO;
import java.util.ArrayList;
import java.util.List;
import tutorias.dao.interfaces.ITutorDAO;
import tutorias.dominio.Tutor;

/**
 *
 * @author katia
 */
public class TutorBO implements ITutorBO{
    private final ITutorDAO tutorDAO;

    public TutorBO(ITutorDAO tutorDAO) {
        this.tutorDAO = tutorDAO;
    }

    @Override
    public List<TutorDTO> obtenerTodosLosTutores() {
        try {
            List<Tutor> tutores = tutorDAO.obtenerTutores();
            List<TutorDTO> tutoresDTO = new ArrayList<>();
            for (Tutor tutor : tutores) {
                TutorDTO dto = TutorAdaptador.toDTO(tutor);
                tutoresDTO.add(dto);
            }
            return tutoresDTO;
        } catch (Exception ex) {
            throw new TutorException("Error al obtener los tutores.");
        }
    }

    @Override
    public TutorDTO obtenerTutorPorId(Long idTutor) {
        if (idTutor == null || idTutor <= 0) {
            throw new TutorException("El ID del tutor no puede ser nulo o menor/igual a cero");
        }
        try {
            Tutor tutor = tutorDAO.obtenerPorId(idTutor);
            return TutorAdaptador.toDTO(tutor);
            
        } catch (Exception ex) {
            throw new TutorException("Error al obtener el tutor con ID " + idTutor + ": " + ex.getMessage());
        }
    }
}
