/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.actividades;

import actividades.dao.interfaces.IInscripcionDAO;
import actividades.dominio.Inscripcion;
import adaptadores.actividades.EstudianteAdaptador;
import adaptadores.actividades.GruposAdaptador;
import adaptadores.actividades.InscripcionAdaptador;
import datos.adaptadoresItson.actividades.GrupoAdaptador;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;
import interfaces.IFachadaITSON;
import interfaces.actividades.IInscripcionBO;

import itson.EstudianteDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;


/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionBO implements IInscripcionBO {

    private IFachadaITSON fachadaITSON;
    private final IInscripcionDAO inscripcionDAO;

    public InscripcionBO(IFachadaITSON fachadaITSON, IInscripcionDAO inscripcionDAO) {
        this.fachadaITSON = fachadaITSON;
        this.inscripcionDAO= inscripcionDAO;
    }

    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
            InscripcionDTOItson inscripcionDTOItson = InscripcionAdaptador.toDTOItson(inscripcionDTO);
            InscripcionDTOItson inscripcionDTOIResponse = fachadaITSON.inscribirActividad(inscripcionDTOItson);
            if(inscripcionDTOIResponse!=null){
                Inscripcion inscripcionGuardar= InscripcionAdaptador.toEntity(inscripcionDTO);
                Inscripcion inscripcionGuardada= inscripcionDAO.InscribirGrupo(inscripcionGuardar);
            }
            return InscripcionAdaptador.toDTONegocio(inscripcionDTOIResponse);

        }
    
    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO){
        System.out.println(estudianteDTO.getMatricula());
        EstudianteDTOItson estudianteDTOItson= EstudianteAdaptador.toDTOItson(estudianteDTO);
        InscripcionesDTOItson inscripcionesDTOItson= fachadaITSON.obtenerInscripciones(estudianteDTOItson);
        
        InscripcionesDTO inscripcionesDTO= InscripcionAdaptador.toDTONegocio(inscripcionesDTOItson);
        return inscripcionesDTO;
    }
    
    public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO){
        InscripcionDTOItson inscripcionDTOItson= InscripcionAdaptador.toDTOItsonID(inscripcionDTO);
        GrupoResponseDTOItson grupoDTOItson= fachadaITSON.obtenerGrupoInscrito(inscripcionDTOItson);
        GrupoDTO grupo= GruposAdaptador.DTOItsonToDTOActividades(grupoDTOItson);
        return grupo;
    }



}
