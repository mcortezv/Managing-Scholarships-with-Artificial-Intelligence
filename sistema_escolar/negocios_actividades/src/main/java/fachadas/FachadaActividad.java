/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import controles.ControlActividad;
import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.BajaDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;
import interfaces.IFachadaActividad;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class FachadaActividad implements IFachadaActividad {

    private ControlActividad controlActividad;

    public FachadaActividad(ControlActividad controlActividad) {
        this.controlActividad = controlActividad;
    }

    public ActividadesDTO obtenerActividades() {
        return controlActividad.obtenerActividades();
    }

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO) {
        return controlActividad.obtenerGrupos(actividadDTO);
    }

    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
        return controlActividad.inscribirActividad(inscripcionDTO);
    }

    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO) {
        return controlActividad.obtenerInscripciones(estudianteDTO);
    }

    public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO) {
        return controlActividad.obtenerGrupoInscrito(inscripcionDTO);
    }
    
    public BajaDTO darBajaActividad(BajaDTO baja){
        return controlActividad.darBajaActividad(baja);
    }

}
