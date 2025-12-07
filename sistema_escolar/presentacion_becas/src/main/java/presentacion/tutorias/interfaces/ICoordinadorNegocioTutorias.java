/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.tutorias.interfaces;

import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.MateriaDTO;
import dto.tutorias.TutorDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface ICoordinadorNegocioTutorias {
    List<MateriaDTO> obtenerMaterias();
    List<TutorDTO> obtenerTutores();
    List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha);
    CitaDTO agendarCita(CitaDTO citaDTO);
    List<CitaDTO> obtenerCitasActivas();
    boolean cancelarCita(Long idCita, Long matriculaAlumno);
    List<CitaDTO> obtenerHistorialCompleto();
    List<CitaDTO> obtenerHistorialPorFecha(LocalDate fecha);
    List<CitaDTO> obtenerHistorialPorMateria(Long idMateria);
    boolean puedeAgendarCita();
}
