/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.actividadesExtracurriculares.coordinador;

import dto.actividades.ActividadDTO;
import dto.actividades.ActividadesDTO;
import dto.actividades.EstudianteDTO;
import dto.actividades.GrupoDTO;
import dto.actividades.GruposResponseDTO;
import dto.actividades.InscripcionDTO;
import dto.actividades.InscripcionesDTO;



import interfaces.IFachadaActividad;
import itson.LoginDTOItson;
import java.util.ArrayList;
import java.util.List;

import presentacion.CoordinadorAplicacion;
import presentacion.actividadesExtracurriculares.coordNegocio.CoordinadorNegocioActividades;
import presentacion.actividadesExtracurriculares.panels.ActividadesExtracurriculares;
import presentacion.actividadesExtracurriculares.panels.DetalleGrupo;
import presentacion.actividadesExtracurriculares.panels.ListaInscripciones;
import presentacion.actividadesExtracurriculares.panels.ResumenClases;
import presentacion.bajaActividades.panels.DetallesActividadBaja;
import presentacion.bajaActividades.panels.DetallesExtraBaja;


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
    private EstudianteDTO estudiante;
    private InscripcionDTO inscripcionElegida;
    private GrupoDTO grupoActividadDTO;
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
    
    public void recuperarLogin(LoginDTOItson loginDTO){
        this.loginDTO= loginDTO;
        this.estudiante= new EstudianteDTO();
        this.estudiante.setMatricula(String.valueOf(loginDTO.getUsuario()));
        ListaInscripciones listaInscripciones = (ListaInscripciones) actividades.getPanel("ListaInscripciones");
        listaInscripciones.recuperarEstudiante(loginDTO);
        
    }
    
    
    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudiante){
       
        return coordinadorNegocioActividades.obtenerInscripciones(estudiante);
    }
    

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO){
       
        return coordinadorNegocioActividades.obtenerGrupos(actividadDTO);
       
    }
    

    
    public void mostrarMenu(){
        actividades.showPanel("MenuOpciones");
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
        inscripcionDTO.setIdActividad(actividadSeleccionada.getId());
        //la cambio a idestudiante
        inscripcionDTO.setMatriculaEstudiante(String.valueOf(loginDTO.getUsuario()));
        
        inscripcionDTO.setNombreGrupo(grupoSeleccionado.getNombreGrupo());
        inscripcionDTO.setNombreActividad(grupoSeleccionado.getNombreActividad());
        inscripcionDTO.setCosto(grupoSeleccionado.getCosto());
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
    
    public void mostarListaInscripciones(){
        ListaInscripciones panel = (ListaInscripciones) actividades.getPanel("ListaInscripciones");
        panel.cargarElementos();
        actividades.showPanel("ListaInscripciones");
    }
    

    
    public GrupoDTO inscripcionSeleccionada(InscripcionDTO inscripcion){
        this.inscripcionElegida= inscripcion;
        System.out.println("cordapl"+inscripcion.getIdGrupo());
        InscripcionDTO inscripcionGrupo = new InscripcionDTO();
        inscripcionGrupo.setIdGrupo(inscripcion.getIdGrupo());
        GrupoDTO grupoInscrito= coordinadorNegocioActividades.obtenerGrupoInscrito(inscripcionGrupo);
        this.grupoActividadDTO= grupoInscrito;
        return grupoInscrito;
        
    }
    
//
//    
//    public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcion){
//        System.out.println(inscripcion.getIdGrupo());
//        GrupoDTO grupo= coordinadorNegocioActividades.obtenerGrupoInscrito(inscripcion);
//        this.grupoActividadDTO= grupo;
//        System.out.println("presentacion");
//        System.out.println(grupo.getNombreResponsable());
//        return grupo;
//    }
    
      public void mostrarDetallesActividad(){
        DetallesActividadBaja panel= (DetallesActividadBaja) actividades.getPanel("DetallesActividadBaja");
        panel.cargarInscripcion(inscripcionElegida, grupoActividadDTO);
        actividades.showPanel("DetallesActividadBaja");
    }
      
      public void mostrarDetallesExtraBaja(){
          DetallesExtraBaja panel= (DetallesExtraBaja) actividades.getPanel("DetallesExtraBaja");
          panel.cargarInscripcion(inscripcionElegida, grupoActividadDTO);
          actividades.showPanel("DetallesExtraBaja");
      }
    
    
    
//    public void abrirDetallesActividadBaja(){
//        DetallesActividadBaja panel = (DetallesActividadBaja) actividades.getPanel("DetallesActividadBaja");
//        panel
//        
//    }
    
    
    
    
    
}

