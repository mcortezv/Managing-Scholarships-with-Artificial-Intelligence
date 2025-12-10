/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio.bo.tutorias.interfaces;

import dto.tutorias.CitaDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface ICitaBO {
    CitaDTO agendarCita(CitaDTO citaDTO);
    boolean cancelarCita(Long idCita, Long matriculaAlumno);
    boolean puedeAgendarCita(Long matriculaAlumno);
    List<CitaDTO> obtenerCitasActivas(Long matriculaAlumno);
    List<CitaDTO> obtenerHistorialCompleto(Long matriculaAlumno);
    List<CitaDTO> obtenerHistorialPorFecha(Long matriculaAlumno, LocalDate fecha);
    List<CitaDTO> obtenerHistorialPorMateria(Long matriculaAlumno, Long idMateria);
    List<CitaDTO> obtenerHistorialPorFechaYMateria(Long matriculaAlumno, LocalDate fecha, Long idMateria);
}
