/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controles;

import bo.tutorias.CitaBO;
import bo.tutorias.HorarioBO;
import bo.tutorias.MateriaBO;
import bo.tutorias.TutorBO;
import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.MateriaDTO;
import dto.tutorias.TutorDTO;
import interfaces.tutorias.ICitaBO;
import interfaces.tutorias.IHorarioBO;
import interfaces.tutorias.IMateriaBO;
import interfaces.tutorias.ITutorBO;
import java.time.LocalDate;
import java.util.List;
import tutorias.dao.CitaDAO;
import tutorias.dao.HorarioDAO;
import tutorias.dao.MateriaDAO;
import tutorias.dao.TutorDAO;
import tutorias.dao.interfaces.ICitaDAO;
import tutorias.dao.interfaces.IHorarioDAO;
import tutorias.dao.interfaces.IMateriaDAO;
import tutorias.dao.interfaces.ITutorDAO;

/**
 *
 * @author katia
 */
public class ControlTutorias {
    private final ICitaDAO citaDAO;
    private final ITutorDAO tutorDAO;
    private final IMateriaDAO materiaDAO;
    private final IHorarioDAO horarioDAO;
    
    private final ICitaBO citaBO;
    private final ITutorBO tutorBO;
    private final IMateriaBO materiaBO;
    private final IHorarioBO horarioBO;
    
    public ControlTutorias() {
        this.citaDAO = new CitaDAO();
        this.tutorDAO = new TutorDAO();
        this.materiaDAO = new MateriaDAO();
        this.horarioDAO = new HorarioDAO();
        this.tutorBO = new TutorBO(tutorDAO);
        this.materiaBO = new MateriaBO(materiaDAO);
        this.horarioBO = new HorarioBO(horarioDAO);
        this.citaBO = new CitaBO(citaDAO, horarioBO, tutorBO, materiaBO);
    }
    
    public CitaDTO agendarCita(CitaDTO citaDTO) {
        return citaBO.agendarCita(citaDTO);
    }
    
    public boolean cancelarCita(Long idCita, Long matriculaAlumno) {
        return citaBO.cancelarCita(idCita, matriculaAlumno);
    }
    
    public boolean puedeAgendarCita(Long matriculaAlumno) {
        return citaBO.puedeAgendarCita(matriculaAlumno);
    }
    
    public List<CitaDTO> obtenerCitasActivas(Long matriculaAlumno) {
        return citaBO.obtenerCitasActivas(matriculaAlumno);
    }
    
    public List<CitaDTO> obtenerHistorialCompleto(Long matriculaAlumno) {
        return citaBO.obtenerHistorialCompleto(matriculaAlumno);
    }
    
    public List<CitaDTO> obtenerHistorialPorFecha(Long matriculaAlumno, LocalDate fecha) {
        return citaBO.obtenerHistorialPorFecha(matriculaAlumno, fecha);
    }
    
    public List<CitaDTO> obtenerHistorialPorMateria(Long matriculaAlumno, Long idMateria) {
        return citaBO.obtenerHistorialPorMateria(matriculaAlumno, idMateria);
    }
    
    public List<CitaDTO> obtenerHistorialPorFechaYMateria(Long matriculaAlumno, LocalDate fecha, Long idMateria) {
        return citaBO.obtenerHistorialPorFechaYMateria(matriculaAlumno, fecha, idMateria);
    }
    
    public List<TutorDTO> obtenerTodosLosTutores() {
        return tutorBO.obtenerTodosLosTutores();
    }
    
    public TutorDTO obtenerTutorPorId(Long idTutor) {
        return tutorBO.obtenerTutorPorId(idTutor);
    }
    
    public List<MateriaDTO> obtenerTodasLasMaterias() {
        return materiaBO.obtenerTodasLasMaterias();
    }
    
    public MateriaDTO obtenerMateriaPorId(Long idMateria) {
        return materiaBO.obtenerMateriaPorId(idMateria);
    }
    
    public List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha) {
        return horarioBO.obtenerHorariosDisponibles(idTutor, fecha);
    }
    
}
