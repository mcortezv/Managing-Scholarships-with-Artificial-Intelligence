/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.adaptadores.tutorias;

import objetosNegocio.adaptadores.tutorias.excepciones.CitaAdaptadorException;
import dto.tutorias.CitaDTO;
import tutorias.dominio.Cita;
import tutorias.dominio.Materia;
import tutorias.dominio.enums.EstadoCita;
import tutorias.dominio.enums.Modalidad;

/**
 *
 * @author katia
 */
public class CitaAdaptador {
    
    public static Cita toEntity(CitaDTO dto) {
        try {
            if (dto == null) return null;

            Cita cita = new Cita();
            cita.setId(dto.getId());
            cita.setMatriculaAlumno(dto.getMatriculaAlumno());
            cita.setIdTutor(dto.getIdTutor());
            cita.setIdHorario(dto.getIdHorario());
            cita.setTema(dto.getTema());

            if (dto.getModalidad() != null && !dto.getModalidad().trim().isEmpty()) {
                try{
                    cita.setModalidad(Modalidad.valueOf(dto.getModalidad().toUpperCase()));
                } catch(IllegalArgumentException e){
                    throw new CitaAdaptadorException("Modalidad inválida");
                }
                
            }
            cita.setFecha(dto.getFecha());
            cita.setHora(dto.getHora());
            cita.setUbicacion(dto.getUbicacion());

            if (dto.getEstado() != null && !dto.getEstado().trim().isEmpty()) {
                try {
                    cita.setEstado(EstadoCita.valueOf(dto.getEstado().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    throw new CitaAdaptadorException(
                        "Estado inválido: " + dto.getEstado() + 
                        ". Valores permitidos: PENDIENTE, ATENDIDA, CANCELADA"
                    );
                }
            }
    
            if (dto.getIdMateria() != null) {
                Materia materia = new Materia();
                materia.setId(dto.getIdMateria());
                materia.setNombre(dto.getNombreMateria());
                cita.setMateria(materia);
            }

            return cita;
        } catch (Exception ex) {
            throw new CitaAdaptadorException("Error al convertir CitaDTO a entidad Cita");
        }
    }

    public static CitaDTO toDTO(Cita cita) {
        try {
            if (cita == null) return null;

            CitaDTO dto = new CitaDTO();
            dto.setId(cita.getId());
            dto.setMatriculaAlumno(cita.getMatriculaAlumno());
            dto.setIdTutor(cita.getIdTutor());
            dto.setIdHorario(cita.getIdHorario());
            dto.setTema(cita.getTema());

            if (cita.getModalidad() != null) {
                dto.setModalidad(cita.getModalidad().name());
            }

            dto.setFecha(cita.getFecha());
            dto.setHora(cita.getHora());
            dto.setUbicacion(cita.getUbicacion());

            if (cita.getEstado() != null) {
                dto.setEstado(cita.getEstado().name());
            }

            if (cita.getMateria() != null) {
                dto.setIdMateria(cita.getMateria().getId());
                dto.setNombreMateria(cita.getMateria().getNombre());
            }

            return dto;
        } catch (Exception ex) {
            throw new CitaAdaptadorException("Error al convertir entidad Cita a CitaDTO");
        }
    }
}
