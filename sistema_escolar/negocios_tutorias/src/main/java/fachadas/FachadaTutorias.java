/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import controles.ControlTutorias;
import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.MateriaDTO;
import dto.tutorias.TutorDTO;
import excepciones.TutoriasException;
import interfaces.IFachadaTutorias;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public class FachadaTutorias implements IFachadaTutorias{
    private final ControlTutorias control;
    
    public FachadaTutorias(ControlTutorias control) {
        this.control = control;
    }
    
    @Override
    public CitaDTO agendarCita(CitaDTO citaDTO) {
        return control.agendarCita(citaDTO);
    }
    
    @Override
    public boolean cancelarCita(Long idCita, Long matriculaAlumno) {
        return control.cancelarCita(idCita, matriculaAlumno);
    }
    
//    @Override
//    public boolean puedeAgendarCita(Long matriculaAlumno) {
//        return control.puedeAgendarCita(matriculaAlumno);
//    }
    
    @Override
    public List<CitaDTO> obtenerCitasActivas(Long matriculaAlumno) {
        return control.obtenerCitasActivas(matriculaAlumno);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialCompleto(Long matriculaAlumno) {
        return control.obtenerHistorialCompleto(matriculaAlumno);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialPorFecha(Long matriculaAlumno, LocalDate fecha) {
        return control.obtenerHistorialPorFecha(matriculaAlumno, fecha);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialPorMateria(Long matriculaAlumno, Long idMateria) {
        return control.obtenerHistorialPorMateria(matriculaAlumno, idMateria);
    }
    
//    @Override
//    public List<CitaDTO> obtenerHistorialPorFechaYMateria(Long matriculaAlumno, LocalDate fecha, Long idMateria) {
//        return control.obtenerHistorialPorFechaYMateria(matriculaAlumno, fecha, idMateria);
//    }
    
    @Override
    public List<TutorDTO> obtenerTodosLosTutores() {
        return control.obtenerTodosLosTutores();
    }
    
//    @Override
//    public TutorDTO obtenerTutorPorId(Long idTutor) {
//        return control.obtenerTutorPorId(idTutor);
//    }
    
    @Override
    public List<MateriaDTO> obtenerTodasLasMaterias() {
        return control.obtenerTodasLasMaterias();
    }
    
//    @Override
//    public MateriaDTO obtenerMateriaPorId(Long idMateria) {
//        return control.obtenerMateriaPorId(idMateria);
//    }
    
    @Override
    public List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha) {
        return control.obtenerHorariosDisponibles(idTutor, fecha);
    }
}
