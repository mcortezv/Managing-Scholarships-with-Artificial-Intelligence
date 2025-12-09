/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.adaptadores.tutorias;

import objetosNegocio.adaptadores.tutorias.excepciones.HorarioAdaptadorException;
import dto.tutorias.HorarioDTO;
import tutorias.dominio.Horario;
import tutorias.dominio.enums.EstadoDisponibilidad;

/**
 *
 * @author katia
 */
public class HorarioAdaptador {
    public static Horario toEntity(HorarioDTO dto) {
        if (dto == null) return null;
        try {
            Horario horario = new Horario();
            horario.setId(dto.getId());
            horario.setIdTutor(dto.getIdTutor());
            horario.setFecha(dto.getFecha());
            horario.setHora(dto.getHora());
            if (dto.getEstadoDisponibilidad() != null) {
                horario.setEstadoDisponibilidad(
                        EstadoDisponibilidad.valueOf(dto.getEstadoDisponibilidad().toUpperCase())
                );
            }
            return horario;
        } catch (Exception ex) {
            throw new HorarioAdaptadorException("Error al convertir HorarioDTO a entidad");
        }
    }

    public static HorarioDTO toDTO(Horario horario) {
        if (horario == null) return null;
        try {
            HorarioDTO dto = new HorarioDTO();
            dto.setId(horario.getId());
            dto.setIdTutor(horario.getIdTutor());
            dto.setFecha(horario.getFecha());
            dto.setHora(horario.getHora());
            dto.setEstadoDisponibilidad(
                    horario.getEstadoDisponibilidad() != null
                        ? horario.getEstadoDisponibilidad().name()
                        : null
            );
            return dto;
        } catch (Exception ex) {
            throw new HorarioAdaptadorException("Error al convertir entidad Horario a DTO");
        }
    }

}
