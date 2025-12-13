/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.tutorias.interfaces;

import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.TutorDTO;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public interface ICoordinadorAplicacionTutorias {
    void mostrarMenuTutorias();
    void intentarMostrarDetallesSolicitud();
    void mostrarTutoresDisponibles();
    void mostrarHorariosDisponibles();
    void mostrarConfirmacionCita();
    void mostrarCitasActivas();
    void mostrarHistorial();
    void guardarDetallesSolicitud(Long idMateria, String tema, String modalidad, LocalDate fecha);
    void seleccionarTutor(TutorDTO tutor);
    void seleccionarHorario(HorarioDTO horario);
    void confirmarYAgendarCita();
    void cancelarCita(Long idCita);
    void filtrarHistorialPorFecha(LocalDate fecha);
    void filtrarHistorialPorMateria(Long idMateria);
    void filtrarHistorialPorFechaYMateria(LocalDate fecha, Long idMateria);
    boolean intentarMostrarHorariosDisponibles();
}
