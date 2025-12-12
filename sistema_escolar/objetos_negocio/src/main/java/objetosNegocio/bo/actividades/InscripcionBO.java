/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.bo.actividades;
import actividades.dao.interfaces.IBajaDAO;
import actividades.dao.interfaces.IInscripcionDAO;
import actividades.dominio.Baja;
import actividades.dominio.Inscripcion;
import adaptadores.actividades.BajaAdaptador;
import dto.actividades.BajaDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;
import interfaces.IFachadaITSON;
import itson.EstudianteDTOItson;
import itson.actividades.BajaDTOItson;
import itson.actividades.GrupoResponseDTOItson;
import itson.actividades.InscripcionDTOItson;
import itson.actividades.InscripcionesDTOItson;
import objetosNegocio.adaptadores.actividades.EstudianteAdaptador;
import objetosNegocio.adaptadores.actividades.GruposAdaptador;
import objetosNegocio.adaptadores.actividades.InscripcionAdaptador;
import objetosNegocio.bo.actividades.interfaces.IInscripcionBO;


/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionBO implements IInscripcionBO {

    private IFachadaITSON fachadaITSON;
    private final IInscripcionDAO inscripcionDAO;
    private final IBajaDAO bajaDAO;

    public InscripcionBO(IFachadaITSON fachadaITSON, IInscripcionDAO inscripcionDAO, IBajaDAO bajaDAO) {
        this.fachadaITSON = fachadaITSON;
        this.inscripcionDAO= inscripcionDAO;
        this.bajaDAO= bajaDAO;
    }

//    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
//            InscripcionDTOItson inscripcionDTOItson = InscripcionAdaptador.toDTOItson(inscripcionDTO);
//            InscripcionDTOItson inscripcionDTOIResponse = fachadaITSON.inscribirActividad(inscripcionDTOItson);
//            if(inscripcionDTOIResponse!=null){
//                Inscripcion inscripcionGuardar= InscripcionAdaptador.toEntity(inscripcionDTO);
//                Inscripcion inscripcionGuardada= inscripcionDAO.InscribirGrupo(inscripcionGuardar);
//            }
//            return InscripcionAdaptador.toDTONegocio(inscripcionDTOIResponse);
//
//        }
    
    public InscripcionDTO inscribirActividadExterno(InscripcionDTO inscripcionDTO) {
        InscripcionDTOItson inscripcionDTOItsonExterno= InscripcionAdaptador.toDTOItson(inscripcionDTO);
        InscripcionDTOItson inscripcionDTOItsonExternoResponse= fachadaITSON.inscribirActividadExterno(inscripcionDTOItsonExterno);
        InscripcionDTO inscripcionDTOExternoResponse= InscripcionAdaptador.toDTONegocio(inscripcionDTOItsonExternoResponse);
        return inscripcionDTOExternoResponse;
    }
    
    public InscripcionDTO inscribirActividadLocal(InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion= InscripcionAdaptador.toEntity(inscripcionDTO);
        Inscripcion inscripcionInscrita= inscripcionDAO.InscribirGrupo(inscripcion);
        return InscripcionAdaptador.EntityToDTONegocio(inscripcionInscrita);
        
    }
    
    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO){
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
    
    public BajaDTO darBajaActividadExterno(BajaDTO baja){
        BajaDTOItson bajaDTOItson= adaptadores.actividades.BajaAdaptador.toDTOItson(baja);
        BajaDTOItson bajaDTOItsonResponse= fachadaITSON.darBajaActividad(bajaDTOItson);
        return adaptadores.actividades.BajaAdaptador.toDTONegocio(bajaDTOItsonResponse);

    }
    
    public BajaDTO darBajaLocal(BajaDTO baja){
        Baja bajaLocal= BajaAdaptador.toEntity(baja);
        Baja bajaGuardada= bajaDAO.guardarBajaLocal(bajaLocal);
        return BajaAdaptador.entityToDTONegocio(bajaGuardada);
    }

    
//    public boolean actualizarEstadoInscripcion(String idInscripcion){
//        return fachadaITSON.actualizarEstadoInscripcion(idInscripcion);
//       
//    }
}
