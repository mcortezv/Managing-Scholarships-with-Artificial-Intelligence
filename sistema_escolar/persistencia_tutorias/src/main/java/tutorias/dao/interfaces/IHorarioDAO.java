/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tutorias.dao.interfaces;

import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;
import tutorias.dominio.Horario;
import tutorias.dominio.enums.EstadoDisponibilidad;
import tutorias.excepciones.HorarioDAOException;
import tutorias.repository.documents.HorarioDocument;

/**
 *
 * @author katia
 */
public interface IHorarioDAO {
    Horario crear(Horario horario) throws HorarioDAOException;
    List<Horario> obtenerDisponiblesPorTutorYFecha(Long idTutor, LocalDate fecha) throws HorarioDAOException;
    Horario actualizarEstado(Long idHorario, EstadoDisponibilidad nuevoEstado) throws HorarioDAOException;
    Horario obtenerPorId(Long id) throws HorarioDAOException;
}
