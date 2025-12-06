/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.coordinador;

import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;



import interfaces.IFachadaActividad;
import itson.LoginDTOItson;
import java.util.ArrayList;
import java.util.List;

import presentacion.CoordinadorAplicacion;
import presentacion.actividadesExtracurriculares.coordNegocio.CoordinadorNegocioActividades;
import presentacion.actividadesExtracurriculares.panels.ActividadesExtracurriculares;
import presentacion.actividadesExtracurriculares.panels.DetalleGrupo;
import presentacion.actividadesExtracurriculares.panels.ResumenClases;


/**
 *
 * @author janethcristinagalvanquinonez
 */
public class CoordinadorAplicacionActividades implements ICoordinadorAplicacionActividades{
    
    
  //  private MainFrameActividades mainFrameActividades;
    private ActividadesExtracurriculares actividades;
    private final CoordinadorNegocioActividades coordinadorNegocioActividades;
    private final CoordinadorAplicacion coordinadorPadre;
    private ActividadDTO actividadSeleccionada;
    private GruposResponseDTO gruposResponseDTO;
    private GrupoDTO grupoSeleccionado;
    private LoginDTOItson loginDTO;
//    private InscripcionDTO inscripcionDTO;
    
    public CoordinadorAplicacionActividades(IFachadaActividad fachadaActividad, CoordinadorAplicacion coordinadorAplicacion){
        this.coordinadorPadre = coordinadorAplicacion;
        coordinadorNegocioActividades= new CoordinadorNegocioActividades(fachadaActividad);

    }
    
    public void iniciarGUI(){
        if(actividades==null){
            actividades= new ActividadesExtracurriculares(this);
        }
        actividades.setVisible(true);
    }
    public void regresarAlMenuPrincipal() {
        if (actividades != null) {
            actividades.setVisible(false);
        }
        coordinadorPadre.mostrarMainFrame();
    }
    
    
    
//    public void iniciarGUI(){
//        if(mainFrameActividades==null){
//            mainFrameActividades= new MainFrameActividades(this);
//        }
//        mainFrameActividades.setVisible(true);
//    }
    
    public void inscribirActividad(){
     //   ActividadesDTO actividadeDTOs= coordinadorNegocioActividades.obtenerActividades();
        actividades.showPanel("ListaActividades");
    }
    
    //quizas mover esto 
    public ActividadesDTO obtenerActividades(){
        return coordinadorNegocioActividades.obtenerActividades();
    }
    

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO){
       
        return coordinadorNegocioActividades.obtenerGrupos(actividadDTO);
       
    }
    
    public void recuperarLogin(LoginDTOItson loginDTO){
        this.loginDTO= loginDTO;
    }
    
    
    
    
    public void procesarActividadSeleccionada(ActividadDTO actividad){
        this.actividadSeleccionada= actividad;
        this.gruposResponseDTO= coordinadorNegocioActividades.obtenerGrupos(actividad);
        System.out.println(loginDTO.getUsuario());
        mostrarGrupos();
        
    }
    
//    public void procesarGrupo(GrupoDTO grupoDTO){
//        this.grupoSeleccionado= grupoDTO;
//        
//    }
    
    public void mostrarGrupos(){
        ResumenClases resumenClases = (ResumenClases) actividades.getPanel("ResumenClases");
        resumenClases.obtenerGrupos(gruposResponseDTO);
        actividades.showPanel("ResumenClases");
    }
    
    public void mostrarGrupo(GrupoDTO grupoDTO){
        this.grupoSeleccionado= grupoDTO;
        DetalleGrupo detalleGrupo= (DetalleGrupo) actividades.getPanel("DetalleGrupo");
        detalleGrupo.cargarGrupo(grupoSeleccionado);
        actividades.showPanel("DetalleGrupo");
    }
    
    
    public InscripcionDTO inscribirActividadAlumno(){
        InscripcionDTO inscripcionDTO= new InscripcionDTO();
        inscripcionDTO.setIdGrupo(grupoSeleccionado.getId());
        inscripcionDTO.setMatriculaEstudiante(String.valueOf(loginDTO.getUsuario()));
        inscripcionDTO.setNombreGrupo(grupoSeleccionado.getNombreActividad());
        inscripcionDTO.setCosto(grupoSeleccionado.getCosto());
        inscripcionDTO.setDias(grupoSeleccionado.getDias());
        inscripcionDTO.setHoraInicio(grupoSeleccionado.getHoraInicio());
        inscripcionDTO.setHoraFin(grupoSeleccionado.getHoraFin());
        
        return coordinadorNegocioActividades.inscribirActividad(inscripcionDTO);
        
    }
    
    public ActividadesExtracurriculares getActividades() {
        return actividades;
    } 
   
    
    public void setActividades(ActividadesExtracurriculares actividades){
        this.actividades= actividades;
    }

    public GruposResponseDTO getGruposResponseDTO() {
        return gruposResponseDTO;
    }

    public void setGruposResponseDTO(GruposResponseDTO gruposResponseDTO) {
        this.gruposResponseDTO = gruposResponseDTO;
    }
    
    
    
}

