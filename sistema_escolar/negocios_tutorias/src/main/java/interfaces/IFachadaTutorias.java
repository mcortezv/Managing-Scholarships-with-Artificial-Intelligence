/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.MateriaDTO;
import dto.tutorias.TutorDTO;
import excepciones.TutoriasException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IFachadaTutorias {
    CitaDTO agendarCita(CitaDTO citaDTO) throws TutoriasException;
    boolean cancelarCita(Long idCita, Long matriculaAlumno) throws TutoriasException;
//    boolean puedeAgendarCita(Long matriculaAlumno) throws TutoriasException;
    List<CitaDTO> obtenerCitasActivas(Long matriculaAlumno) throws TutoriasException;
    List<CitaDTO> obtenerHistorialCompleto(Long matriculaAlumno) throws TutoriasException;
    List<CitaDTO> obtenerHistorialPorFecha(Long matriculaAlumno, LocalDate fecha) throws TutoriasException;
    List<CitaDTO> obtenerHistorialPorMateria(Long matriculaAlumno, Long idMateria) throws TutoriasException;
//    List<CitaDTO> obtenerHistorialPorFechaYMateria(Long matriculaAlumno, LocalDate fecha, Long idMateria) throws TutoriasException;
    List<TutorDTO> obtenerTodosLosTutores() throws TutoriasException;
//    TutorDTO obtenerTutorPorId(Long idTutor) throws TutoriasException;
    List<MateriaDTO> obtenerTodasLasMaterias() throws TutoriasException;
//    MateriaDTO obtenerMateriaPorId(Long idMateria) throws TutoriasException;
    List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha) throws TutoriasException;
    
}
