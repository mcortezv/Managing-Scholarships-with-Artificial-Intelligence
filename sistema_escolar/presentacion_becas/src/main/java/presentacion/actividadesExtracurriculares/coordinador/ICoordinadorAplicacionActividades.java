/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.actividadesExtracurriculares.coordinador;

import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.BajaDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;
import presentacion.actividadesExtracurriculares.panels.ActividadesExtracurriculares;
import solicitarBeca.LoginDTO;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface ICoordinadorAplicacionActividades {
    
    void iniciarGUI();
    void inscribirActividad();
    ActividadesDTO obtenerActividades();
    ActividadDTO obtenerActividadPorNombre(String nombre);
    void recuperarLogin(LoginDTO loginDTO);
    InscripcionesDTO obtenerInscripciones(EstudianteDTO estudiante);
    GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO);
    void mostrarMenu();
    void procesarActividadSeleccionada(ActividadDTO actividad);
    void mostrarGrupos();
    void mostrarGrupo(GrupoDTO grupoDTO);
    InscripcionDTO inscribirActividadAlumno();
    ActividadesExtracurriculares getActividades();
    void setActividades(ActividadesExtracurriculares actividades);
    GruposResponseDTO getGruposResponseDTO();
    void setGruposResponseDTO(GruposResponseDTO gruposResponseDTO);
    void mostarListaInscripciones();
    GrupoDTO inscripcionSeleccionada(InscripcionDTO inscripcion);
    BajaDTO darBajaActividad(String motivo);
    void mostrarDetallesActividad();
    void mostrarDetallesExtraBaja();
    
    
    
    
}
