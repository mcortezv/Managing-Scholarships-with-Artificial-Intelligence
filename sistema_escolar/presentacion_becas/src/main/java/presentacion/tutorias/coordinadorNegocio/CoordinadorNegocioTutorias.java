/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.coordinadorNegocio;

import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.MateriaDTO;
import dto.tutorias.TutorDTO;
import interfaces.IFachadaTutorias;
import java.time.LocalDate;
import java.util.List;
import presentacion.tutorias.interfaces.ICoordinadorNegocioTutorias;

/**
 *
 * @author katia
 */
public class CoordinadorNegocioTutorias implements ICoordinadorNegocioTutorias{
    private final IFachadaTutorias fachada;
    private Long matriculaAlumno;
    
    public CoordinadorNegocioTutorias(IFachadaTutorias fachada) {
        this.fachada = fachada;
    }
    
    public void setMatriculaAlumno(Long matricula) {
        this.matriculaAlumno = matricula;
    }
    
    public Long getMatriculaAlumno(){
        return this.matriculaAlumno;
    }
    
    @Override
    public List<MateriaDTO> obtenerMaterias() {
        return fachada.obtenerTodasLasMaterias();
    }
    
    @Override
    public List<TutorDTO> obtenerTutores() {
        return fachada.obtenerTodosLosTutores();
    }
    
    @Override
    public List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha) {
        return fachada.obtenerHorariosDisponibles(idTutor, fecha);
    }
    
    @Override
    public CitaDTO agendarCita(CitaDTO citaDTO) {
        citaDTO.setMatriculaAlumno(matriculaAlumno);
        return fachada.agendarCita(citaDTO);
    }
    
    @Override
    public List<CitaDTO> obtenerCitasActivas() {
        return fachada.obtenerCitasActivas(matriculaAlumno);
    }
    
    @Override
    public boolean cancelarCita(Long idCita, Long matriculaAlumno) {
        return fachada.cancelarCita(idCita, matriculaAlumno);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialCompleto() {
        return fachada.obtenerHistorialCompleto(matriculaAlumno);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialPorFecha(LocalDate fecha) {
        return fachada.obtenerHistorialPorFecha(matriculaAlumno, fecha);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialPorMateria(Long idMateria) {
        return fachada.obtenerHistorialPorMateria(matriculaAlumno, idMateria);
    }
    
    @Override
    public List<CitaDTO> obtenerHistorialPorFechaYMateria(LocalDate fecha, Long idMateria){
        return fachada.obtenerHistorialPorFechaYMateria(matriculaAlumno, fecha, idMateria);
    }
    
    @Override
    public boolean puedeAgendarCita() {
        return fachada.puedeAgendarCita(matriculaAlumno);
    }
}
