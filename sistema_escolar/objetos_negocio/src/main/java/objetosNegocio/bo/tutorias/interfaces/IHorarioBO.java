/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio.bo.tutorias.interfaces;

import dto.tutorias.HorarioDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IHorarioBO {
    List<HorarioDTO> obtenerHorariosDisponibles(Long idTutor, LocalDate fecha);
    boolean marcarHorarioComoOcupado(Long idHorario);
    boolean liberarHorario(Long idHorario);
}
