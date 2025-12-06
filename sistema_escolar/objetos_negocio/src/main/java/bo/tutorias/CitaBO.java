/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.tutorias;

import adaptadores.tutorias.CitaAdaptador;
import bo.tutorias.excepciones.CitaInvalidaException;
import dto.tutorias.CitaDTO;
import interfaces.tutorias.ICitaBO;
import interfaces.tutorias.IHorarioBO;
import interfaces.tutorias.IMateriaBO;
import interfaces.tutorias.ITutorBO;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import tutorias.dao.interfaces.ICitaDAO;
import tutorias.dominio.Cita;
import tutorias.dominio.Materia;
import tutorias.dominio.enums.EstadoCita;

/**
 *
 * @author katia
 */
public class CitaBO implements ICitaBO{
    private final ICitaDAO citaDAO;
    private final IHorarioBO horarioBO;
    private final ITutorBO tutorBO;
    private final IMateriaBO materiaBO;
    
    private static final int MAX_CANCELACIONES_POR_MES = 3;

    public CitaBO(ICitaDAO citaDAO, IHorarioBO horarioBO, ITutorBO tutorBO, IMateriaBO materiaBO) {
        this.citaDAO = citaDAO;
        this.horarioBO = horarioBO;
        this.tutorBO = tutorBO;
        this.materiaBO = materiaBO;
    }
    
    @Override
    public CitaDTO agendarCita(CitaDTO citaDTO) {
        validarDatosCita(citaDTO);
        
        if (!puedeAgendarCita(citaDTO.getMatriculaAlumno())) {
            throw new CitaInvalidaException(
                "No puedes agendar citas porque has cancelado 3 o más veces en este mes. " +
                "Intenta nuevamente el próximo mes."
            );
        }
        
        if (citaDTO.getFecha().isBefore(LocalDate.now())) {
            throw new CitaInvalidaException("No se pueden agendar citas en fechas pasadas");
        }
        
        verificarDisponibilidadAlumno(citaDTO.getMatriculaAlumno(), citaDTO.getFecha(), citaDTO.getHora());
        
        try {
            Cita cita = CitaAdaptador.toEntity(citaDTO);
            cita.setEstado(EstadoCita.PENDIENTE);
            
            if (citaDTO.getIdMateria() != null) {
                Materia materia = new Materia();
                materia.setId(citaDTO.getIdMateria());
                cita.setMateria(materia);
            }
            
            Cita citaCreada = citaDAO.crear(cita);
            
            if (citaDTO.getIdHorario() != null) {
                boolean marcado = horarioBO.marcarHorarioComoOcupado(citaDTO.getIdHorario());
                if (!marcado) {
                    throw new CitaInvalidaException("No se pudo marcar el horario como ocupado");
                }
            }    
            
            return CitaAdaptador.toDTO(citaCreada);
            
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al agendar la cita: " + ex.getMessage());
        }
    }

    @Override
    public boolean cancelarCita(Long idCita, Long matriculaAlumno) {
        if (idCita == null || idCita <= 0) {
            throw new CitaInvalidaException("El ID de la cita no puede ser nulo o inválido");
        }
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        
        try {
            List<Cita> citasFuturas = citaDAO.obtenerFuturasPorAlumno(matriculaAlumno);
            Cita cita = citasFuturas.stream()
                .filter(c -> c.getId().equals(idCita))
                .findFirst()
                .orElseThrow(() -> new CitaInvalidaException("No se encontró la cita con ID " + idCita));
            
            LocalDateTime ahora = LocalDateTime.now();
            LocalDateTime horaCita = cita.getFecha().atTime(cita.getHora());
            Duration tiempoRestante = Duration.between(ahora, horaCita);
            
            if (tiempoRestante.toMinutes() < 60) {
                throw new CitaInvalidaException(
                    "No se puede cancelar la cita porque falta menos de 1 hora. " +
                    "Por favor, contacta directamente al tutor."
                );
            }
            Cita citaActualizada = citaDAO.actualizarEstado(idCita, EstadoCita.CANCELADA);
            if (cita.getIdHorario() != null) {
                boolean liberado = horarioBO.liberarHorario(cita.getIdHorario());
                if (!liberado) {
                    System.err.println("No se pudo liberar el horario " + cita.getIdHorario());
                }
            }
            return citaActualizada != null && citaActualizada.getEstado() == EstadoCita.CANCELADA;
        } catch (CitaInvalidaException e) {
            throw e;
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al cancelar la cita");
        }
    }

    @Override
    public boolean puedeAgendarCita(Long matriculaAlumno) {
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        try {
            LocalDate hoy = LocalDate.now();
            int cancelaciones = citaDAO.contarCitasPorAlumnoYEstadoEnMes(
                matriculaAlumno,
                EstadoCita.CANCELADA,
                hoy.getMonthValue(),
                hoy.getYear()
            );
            return cancelaciones < MAX_CANCELACIONES_POR_MES;
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al verificar si puede agendar cita" );
        }
    }

    @Override
    public List<CitaDTO> obtenerCitasActivas(Long matriculaAlumno) {
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        try {
            List<Cita> citasFuturas = citaDAO.obtenerFuturasPorAlumno(matriculaAlumno);
            List<CitaDTO> citasDTO = new ArrayList<>();
            
            for (Cita cita : citasFuturas) {
                if (cita.getEstado() == EstadoCita.PENDIENTE) {
                    CitaDTO dto = convertirCita(cita);
                    citasDTO.add(dto);
                }
            }
            return citasDTO;
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al obtener citas activas" );
        }
    }

    @Override
    public List<CitaDTO> obtenerHistorialCompleto(Long matriculaAlumno) {
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        try {
            List<Cita> citas = citaDAO.obtenerHistorialCompletoAlumno(matriculaAlumno);
            return convertirListaCitas(citas);
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al obtener el historial completo: " + ex.getMessage());
        }
    }

    @Override
    public List<CitaDTO> obtenerHistorialPorFecha(Long matriculaAlumno, LocalDate fecha) {
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        if (fecha == null) {
            throw new CitaInvalidaException("La fecha no puede ser nula");
        }
        try {
            List<Cita> citas = citaDAO.obtenerHistorialPorFecha(matriculaAlumno, fecha);
            return convertirListaCitas(citas);
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al obtener el historial por fecha");
        }
    }

    @Override
    public List<CitaDTO> obtenerHistorialPorMateria(Long matriculaAlumno, Long idMateria) {
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        if (idMateria == null) {
            throw new CitaInvalidaException("La materia no puede ser nula");
        }
        try {
            List<Cita> citas = citaDAO.obtenerHistorialPorMateria(matriculaAlumno, idMateria);
            return convertirListaCitas(citas);
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al obtener el historial por materia");
        }
    }

    @Override
    public List<CitaDTO> obtenerHistorialPorFechaYMateria(Long matriculaAlumno, LocalDate fecha, Long idMateria) {
        if (matriculaAlumno == null) {
            throw new CitaInvalidaException("La matrícula del alumno no puede ser nula");
        }
        if (fecha == null || idMateria == null) {
            throw new CitaInvalidaException("La fecha y la materia no pueden ser nulas");
        }
        try {
            List<Cita> citas = citaDAO.obtenerHistorialPorFechaYMateria(matriculaAlumno, fecha, idMateria);
            return convertirListaCitas(citas);
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al obtener el historial por fecha y materia");
        }
    }

    
    private void validarDatosCita(CitaDTO citaDTO) {
        if (citaDTO == null) {
            throw new CitaInvalidaException("Los datos de la cita no pueden ser nulos");
        }
        if (citaDTO.getMatriculaAlumno() == null) {
            throw new CitaInvalidaException("La matrícula del alumno es requerida");
        }
        if (citaDTO.getIdTutor() == null) {
            throw new CitaInvalidaException("El tutor es requerido");
        }      
        if (citaDTO.getIdMateria() == null) {
            throw new CitaInvalidaException("La materia es requerida");
        }      
        if (citaDTO.getTema() == null || citaDTO.getTema().trim().isEmpty()) {
            throw new CitaInvalidaException("El tema es requerido");
        }
        if (citaDTO.getModalidad() == null || citaDTO.getModalidad().trim().isEmpty()) {
            throw new CitaInvalidaException("La modalidad es requerida");
        }
        if (citaDTO.getFecha() == null) {
            throw new CitaInvalidaException("La fecha es requerida");
        }
        if (citaDTO.getHora() == null) {
            throw new CitaInvalidaException("La hora es requerida.");
        }
    }
    
    private void verificarDisponibilidadAlumno(Long matriculaAlumno, LocalDate fecha, LocalTime hora) {
        try {
            List<Cita> citasMismoDia = citaDAO.obtenerHistorialPorFecha(matriculaAlumno, fecha);
            for (Cita cita : citasMismoDia) {
                if (cita.getEstado() == EstadoCita.PENDIENTE && cita.getHora().equals(hora)) {
                    throw new CitaInvalidaException(
                        "Ya tienes una cita agendada para el " + fecha + " a las " + hora
                    );
                }
            }
        } catch (CitaInvalidaException e) {
            throw e;
        } catch (Exception ex) {
            throw new CitaInvalidaException("Error al verificar disponibilidad" );
        }
    }
    
    private CitaDTO convertirCita(Cita cita) {
        CitaDTO dto = CitaAdaptador.toDTO(cita);
        if (cita.getIdTutor() != null) {
            try {
                dto.setNombreTutor(tutorBO.obtenerTutorPorId(cita.getIdTutor()).getNombre());
            } catch (Exception e) {
                dto.setNombreTutor("Tutor no disponible");
            }
        }
        if (cita.getMateria() != null && cita.getMateria().getId() != null) {
            try {
                dto.setNombreMateria(materiaBO.obtenerMateriaPorId(cita.getMateria().getId()).getNombre());
            } catch (Exception e) {
                dto.setNombreMateria("Materia no disponible");
            }
        }
        return dto;
    }
    
    private List<CitaDTO> convertirListaCitas(List<Cita> citas) {
        List<CitaDTO> resultado = new ArrayList<>();
        if (citas == null || citas.isEmpty()) {
            return resultado;
        }
        for (Cita cita : citas) {
            try {
                CitaDTO dto = convertirCita(cita);
                resultado.add(dto);
            } catch (Exception e) {
                System.err.println("Error al convertir cita " + cita.getId() + ": " + e.getMessage());
            }
        }
        return resultado;
    }

}
