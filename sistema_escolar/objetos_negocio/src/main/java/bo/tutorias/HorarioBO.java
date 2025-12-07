/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.tutorias;

import adaptadores.tutorias.HorarioAdaptador;
import bo.tutorias.excepciones.HorarioException;
import dto.tutorias.HorarioDTO;
import interfaces.tutorias.IHorarioBO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tutorias.dao.interfaces.IHorarioDAO;
import tutorias.dominio.Horario;
import tutorias.dominio.enums.EstadoDisponibilidad;

/**
 *
 * @author katia
 */
public class HorarioBO implements IHorarioBO{
    private final IHorarioDAO horarioDAO;

    public HorarioBO(IHorarioDAO horarioDAO) {
        this.horarioDAO = horarioDAO;
    }

    @Override
    public List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha) {
        if (idTutor == null || idTutor <= 0) {
            throw new HorarioException("El ID del tutor no puede ser nulo o menor/igual a cero");
        }
        if (fecha == null) {
            throw new HorarioException("La fecha no puede ser nula");
        }
        if (fecha.isBefore(LocalDate.now())) {
            throw new HorarioException("No se pueden consultar horarios de fechas pasadas");
        }

        try {
            List<Horario> horarios = horarioDAO.obtenerDisponiblesPorTutorYFecha(idTutor, fecha);
            List<HorarioDTO> horariosDTO = new ArrayList<>();
            for (Horario horario : horarios) {
                HorarioDTO dto = HorarioAdaptador.toDTO(horario);
                System.out.println("  - Horario ID: " + dto.getId() + ", Hora: " + dto.getHora());
                horariosDTO.add(dto);
            }
            return horariosDTO;
        } catch (Exception ex) {
            throw new HorarioException("Error al obtener horarios disponibles: " + ex.getMessage());
        }
    }

    @Override
    public boolean marcarHorarioComoOcupado(Long idHorario) {
        if (idHorario == null || idHorario <= 0) {
            throw new HorarioException("El ID del horario no puede ser nulo o menor/igual a cero.");
        }

        try {
            Horario horario = horarioDAO.obtenerPorId(idHorario);
            if (horario == null) {
                throw new HorarioException("El horario con ID " + idHorario + " no existe");
            }
            if (horario.getEstadoDisponibilidad() != EstadoDisponibilidad.DISPONIBLE) {
                throw new HorarioException("El horario ya no está disponible");
            }
            Horario actualizado = horarioDAO.actualizarEstado(idHorario, EstadoDisponibilidad.OCUPADO);
            return actualizado != null && actualizado.getEstadoDisponibilidad() == EstadoDisponibilidad.OCUPADO;
        } catch (HorarioException e) {
            throw e;
        } catch (Exception ex) {
            throw new HorarioException("Error al marcar horario como ocupado: ");
        }
    }

    @Override
    public boolean liberarHorario(Long idHorario) {
        if (idHorario == null || idHorario <= 0) {
            throw new HorarioException("El ID del horario no puede ser nulo o menor/igual a cero");
        }
        try {
            Horario actualizado = horarioDAO.actualizarEstado(idHorario, EstadoDisponibilidad.DISPONIBLE);
            return actualizado != null && actualizado.getEstadoDisponibilidad() == EstadoDisponibilidad.DISPONIBLE;
            
        } catch (Exception ex) {
            throw new HorarioException("Error al liberar horario.");
        }
    }
}
