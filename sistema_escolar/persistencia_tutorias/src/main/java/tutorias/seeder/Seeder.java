/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutorias.seeder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import tutorias.dao.CitaDAO;
import tutorias.dao.HorarioDAO;
import tutorias.dao.MateriaDAO;
import tutorias.dao.TutorDAO;
import tutorias.dominio.Cita;
import tutorias.dominio.Horario;
import tutorias.dominio.Materia;
import tutorias.dominio.Tutor;
import tutorias.dominio.enums.EstadoCita;
import tutorias.dominio.enums.EstadoDisponibilidad;
import tutorias.dominio.enums.Modalidad;
import tutorias.excepciones.CitaDAOException;
import tutorias.excepciones.HorarioDAOException;
import tutorias.excepciones.MateriaDAOException;
import tutorias.excepciones.TutorDAOException;
import tutorias.repository.documents.CitaDocument;
import tutorias.repository.documents.HorarioDocument;
import tutorias.repository.documents.MateriaDocument;
import tutorias.repository.documents.TutorDocument;

/**
 *
 * @author katia
 */
public class Seeder {
    public static void main(String[] args) {
//        MateriaDAO materiaDAO = new MateriaDAO();
//        TutorDAO tutorDAO = new TutorDAO();
//        HorarioDAO horarioDAO = new HorarioDAO();
//        CitaDAO citaDAO = new CitaDAO();
//        
//        try {
//            
//            Materia matProg = new Materia();
//            matProg.setId(1L);
//            matProg.setNombre("Programación I");
//            materiaDAO.crear(matProg);
//
//            Materia matCalc = new Materia();
//            matCalc.setId(2L);
//            matCalc.setNombre("Cálculo Diferencial");
//            materiaDAO.crear(matCalc);
//
//            Materia matBd = new Materia();
//            matBd.setId(3L);
//            matBd.setNombre("Bases de Datos");
//            materiaDAO.crear(matBd);
//
//            System.out.println("Materias insertadas.");
//
//            
//            Tutor tutor1 = new Tutor();
//            tutor1.setId(1L);
//            tutor1.setNombre("Karla López");
//            tutor1.setCarrera("Ingeniería en Software");
//            tutor1.setCubiculo("B-201");
//            tutor1.setEnlace("https://meet.google.com/karla");
//            tutorDAO.crear(tutor1);
//
//            Tutor tutor2 = new Tutor();
//            tutor2.setId(2L);
//            tutor2.setNombre("Pedro Escalante");
//            tutor2.setCarrera("Matemáticas");
//            tutor2.setCubiculo("C-305");
//            tutor2.setEnlace("https://meet.google.com/pedro");
//            tutorDAO.crear(tutor2);
//
//            System.out.println("Tutores insertados.");
//
//            
//            LocalDate hoy = LocalDate.now();
//            LocalDate manana = hoy.plusDays(1);
//            LocalDate pasado = hoy.plusDays(2);
//            LocalDate ayer = hoy.minusDays(5);
//
//            Horario horario1 = insertarHorario(horarioDAO, 101L, 1L, manana, LocalTime.of(10, 0), EstadoDisponibilidad.OCUPADO); 
//            Horario horario2 = insertarHorario(horarioDAO, 102L, 1L, manana, LocalTime.of(11, 0), EstadoDisponibilidad.DISPONIBLE);
//            Horario horario3 = insertarHorario(horarioDAO, 103L, 1L, manana, LocalTime.of(12, 0), EstadoDisponibilidad.DISPONIBLE);
//
//            Horario horario4 = insertarHorario(horarioDAO, 104L, 2L, pasado, LocalTime.of(9, 0), EstadoDisponibilidad.DISPONIBLE);
//            Horario horario5 = insertarHorario(horarioDAO, 105L, 2L, pasado, LocalTime.of(10, 0), EstadoDisponibilidad.DISPONIBLE);
//
//            Horario horario6 = insertarHorario(horarioDAO, 106L, 1L, ayer, LocalTime.of(10, 0), EstadoDisponibilidad.DISPONIBLE); 
//            Horario horario7 = insertarHorario(horarioDAO, 107L, 1L, hoy.minusDays(3), LocalTime.of(11, 0), EstadoDisponibilidad.DISPONIBLE); 
//            Horario horario8 = insertarHorario(horarioDAO, 108L, 2L, hoy.minusDays(1), LocalTime.of(9, 0), EstadoDisponibilidad.DISPONIBLE); 
//
//            System.out.println("Horarios insertados.");
//
//       
//            Long matriculaEjemplo = 252855L;
//
//            Cita citaPasada = new Cita();
//            citaPasada.setId(1001L);
//            citaPasada.setMatriculaAlumno(matriculaEjemplo);
//            citaPasada.setIdTutor(1L);
//            citaPasada.setIdHorario(106L);
//            Materia materiaProg = new Materia();
//            materiaProg.setId(1L);
//            citaPasada.setMateria(materiaProg);
//            citaPasada.setTema("Repaso de arreglos");
//            citaPasada.setModalidad(Modalidad.PRESENCIAL);
//            citaPasada.setFecha(hoy.minusDays(5));
//            citaPasada.setHora(LocalTime.of(10, 0));
//            citaPasada.setUbicacion("B-201");
//            citaPasada.setEstado(EstadoCita.ATENDIDA);
//            citaDAO.crear(citaPasada);
//            System.out.println("Cita 1001 atendida. Horario 106");
//
//            Cita citaCancelada1 = new Cita();
//            citaCancelada1.setId(1002L);
//            citaCancelada1.setMatriculaAlumno(matriculaEjemplo);
//            citaCancelada1.setIdTutor(1L);
//            citaCancelada1.setIdHorario(107L);
//            Materia materiaCalc = new Materia();
//            materiaCalc.setId(2L);
//            citaCancelada1.setMateria(materiaCalc);
//            citaCancelada1.setTema("Derivadas básicas");
//            citaCancelada1.setModalidad(Modalidad.VIRTUAL);
//            citaCancelada1.setFecha(hoy.minusDays(3));
//            citaCancelada1.setHora(LocalTime.of(11, 0));
//            citaCancelada1.setUbicacion("https://meet.google.com/karla");
//            citaCancelada1.setEstado(EstadoCita.CANCELADA);
//            citaDAO.crear(citaCancelada1);
//            System.out.println("Cita 1002 cancelada. Horario 107");
//
//            Cita citaCancelada2 = new Cita();
//            citaCancelada2.setId(1003L);
//            citaCancelada2.setMatriculaAlumno(matriculaEjemplo);
//            citaCancelada2.setIdTutor(2L);
//            citaCancelada2.setIdHorario(108L);
//            Materia materiaBD = new Materia();
//            materiaBD.setId(3L);
//            citaCancelada2.setMateria(materiaBD);
//            citaCancelada2.setTema("Normalización");
//            citaCancelada2.setModalidad(Modalidad.PRESENCIAL);
//            citaCancelada2.setFecha(hoy.minusDays(1));
//            citaCancelada2.setHora(LocalTime.of(9, 0));
//            citaCancelada2.setUbicacion("C-305");
//            citaCancelada2.setEstado(EstadoCita.CANCELADA);
//            citaDAO.crear(citaCancelada2);
//            System.out.println("Cita 1003 cancelada. Horario 108");
//
//            Cita citaFutura = new Cita();
//            citaFutura.setId(1004L);
//            citaFutura.setMatriculaAlumno(matriculaEjemplo);
//            citaFutura.setIdTutor(1L);
//            citaFutura.setIdHorario(101L);
//            Materia materiaProg2 = new Materia();
//            materiaProg2.setId(1L);
//            citaFutura.setMateria(materiaProg2);
//            citaFutura.setTema("Listas enlazadas");
//            citaFutura.setModalidad(Modalidad.VIRTUAL);
//            citaFutura.setFecha(manana);
//            citaFutura.setHora(LocalTime.of(10, 0));
//            citaFutura.setUbicacion("https://meet.google.com/karla");
//            citaFutura.setEstado(EstadoCita.PENDIENTE);
//            citaDAO.crear(citaFutura);
//            System.out.println("Cita 1004 pendiente. Horario 101 ocupado");
//
//            System.out.println("Citas insertadas.");
//
//            System.out.println("SeederTutorias finalizado correctamente.");
//        } catch (MateriaDAOException | TutorDAOException | HorarioDAOException | CitaDAOException ex) {
//            System.err.println("Error al cargar datos de tutorías: " + ex.getMessage());
//        } catch (Exception ex) {
//            System.err.println("Error inesperado en SeederTutorias: " + ex.getMessage());
//        }
//    }
//
//    
//    private static Horario insertarHorario(HorarioDAO horarioDAO,
//                                    Long id,
//                                    Long idTutor,
//                                    LocalDate fecha,
//                                    LocalTime hora,
//                                    EstadoDisponibilidad estado){
//        Horario h = new Horario();
//        h.setId(id);
//        h.setIdTutor(idTutor);
//        h.setFecha(fecha);
//        h.setHora(hora);
//        h.setEstadoDisponibilidad(estado);
//        
//        Horario creado = horarioDAO.crear(h);
//        String estadoStr = estado == EstadoDisponibilidad.DISPONIBLE ? "DISPONIBLE" : "OCUPADO";
//        System.out.println("  " + estadoStr + " - Horario " + id + 
//                           ": Tutor " + idTutor + 
//                           " | " + fecha + 
//                           " | " + hora);
//        
//        return creado;
//    }
//
//    private static long contadorHorario = 1L;
//
//    private static synchronized Long generarIdHorario(){
//        return contadorHorario++;
    }
    
}
