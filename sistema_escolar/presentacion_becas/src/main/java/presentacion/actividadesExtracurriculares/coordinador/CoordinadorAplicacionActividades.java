/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import excepciones.ActividadesException;



import interfaces.IFachadaActividad;
import itson.LoginDTOItson;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import presentacion.CoordinadorAplicacion;
import presentacion.actividadesExtracurriculares.coordNegocio.CoordinadorNegocioActividades;
import presentacion.actividadesExtracurriculares.panels.ActividadesExtracurriculares;
import presentacion.actividadesExtracurriculares.panels.DetalleGrupo;
import presentacion.actividadesExtracurriculares.panels.ListaInscripciones;
import presentacion.actividadesExtracurriculares.panels.ResumenClases;
import presentacion.bajaActividades.panels.DetallesActividadBaja;
import presentacion.bajaActividades.panels.DetallesExtraBaja;
import solicitarBeca.LoginDTO;


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
    private LoginDTO loginDTO;
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
    public ActividadDTO obtenerActividadPorNombre(String nombre){
        if(nombre==null || nombre.trim().isEmpty()){     
            throw new IllegalArgumentException("el nombre de la actividad no puede estar vacio");
        }
        String nombreLimpio = nombre.trim();
        String nombreFormateado = nombreLimpio.substring(0, 1).toUpperCase()
                + nombreLimpio.substring(1).toLowerCase(); 
        ActividadDTO actividadDTO= new ActividadDTO(nombreFormateado);
        return coordinadorNegocioActividades.obtenerActividadPorNombre(actividadDTO);
    }
      
    
    public void recuperarLogin(LoginDTO loginDTO){
        this.loginDTO= loginDTO;
        this.estudiante= new EstudianteDTO();
        this.estudiante.setMatricula(String.valueOf(loginDTO.getUsuario()));
        ListaInscripciones listaInscripciones = (ListaInscripciones) actividades.getPanel("ListaInscripciones");
        listaInscripciones.recuperarEstudiante(loginDTO);
    }
    
    
    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudiante){
        System.out.println("ins"+estudiante.getMatricula());
       try{
        return coordinadorNegocioActividades.obtenerInscripciones(estudiante);
       } catch(ActividadesException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage(), "No se han encontrado inscripciones", JOptionPane.ERROR_MESSAGE);
       }
       return null;
    }
    

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO){
       try{
            return coordinadorNegocioActividades.obtenerGrupos(actividadDTO);      
       } catch(ActividadesException ex){
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
      return null;
    }
        
    

    
    public void mostrarMenu(){
        actividades.showPanel("MenuOpciones");
    }
    
    
    
    public void procesarActividadSeleccionada(ActividadDTO actividad){
        try{
            //quizas dejar de guardarla en actividad selecciomada porque al inscribir ya la tego en grupo el id de la actividad 
            this.actividadSeleccionada= actividad;
            this.gruposResponseDTO= coordinadorNegocioActividades.obtenerGrupos(actividad);
            mostrarGrupos();
        } catch(ActividadesException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "No se ha podido cargar el grupo", JOptionPane.ERROR_MESSAGE);
        }
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
        try{
            InscripcionDTO inscripcionResponse= new InscripcionDTO();
            InscripcionDTO inscripcionDTO= new InscripcionDTO();
            
            inscripcionDTO.setIdGrupo(grupoSeleccionado.getId());
            inscripcionDTO.setFechaInicio(grupoSeleccionado.getFechaInicio());
            inscripcionDTO.setFechaFin(grupoSeleccionado.getFechaFin());
            inscripcionDTO.setFechaLimiteInscripcion(grupoSeleccionado.getFechaLimiteInscripcion());
            inscripcionDTO.setCupoTotal(grupoSeleccionado.getCupoTotal());
            inscripcionDTO.setCupoDisponible(grupoSeleccionado.getCupoDisponible());
            inscripcionDTO.setIdActividad(actividadSeleccionada.getId());
            
            //la cambio a idestudiante
            inscripcionDTO.setMatriculaEstudiante(String.valueOf(loginDTO.getUsuario()));
            inscripcionDTO.setNombreGrupo(grupoSeleccionado.getNombreGrupo());
            inscripcionDTO.setNombreActividad(grupoSeleccionado.getNombreActividad());
            inscripcionDTO.setCosto(grupoSeleccionado.getCosto());
            inscripcionResponse= coordinadorNegocioActividades.inscribirActividad(inscripcionDTO);
            System.out.println("en cord apl"+inscripcionDTO.getFechaInicio());
             System.out.println("en cord apl"+inscripcionDTO.getFechaFin());
              System.out.println("en cord apl"+inscripcionDTO.getFechaLimiteInscripcion());
              System.out.println("en cord apl ins"+inscripcionDTO.getFechaInscripcion());
            if(inscripcionResponse!=null){
                JOptionPane.showMessageDialog(null, "Inscripcion guardada con exito");
                mostrarMenu();
                return inscripcionResponse;
            } 
        } catch(ActividadesException ex){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al inscribir"+ ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
            return new InscripcionDTO();
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
        InscripcionDTO inscripcionGrupo = new InscripcionDTO();
        inscripcionGrupo.setIdGrupo(inscripcion.getIdGrupo());
        GrupoDTO grupoInscrito= coordinadorNegocioActividades.obtenerGrupoInscrito(inscripcionGrupo);
        this.grupoActividadDTO= grupoInscrito;
        return grupoInscrito;
        
    }
    
        
    public BajaDTO darBajaActividad(String motivo){
      try{
        BajaDTO bajaDTO= new BajaDTO(inscripcionElegida.getId(), motivo);
        BajaDTO bajaResponse= coordinadorNegocioActividades.darBajaActividad(bajaDTO);
        
        
        
        if(bajaResponse!=null){
            JOptionPane.showMessageDialog(null, "Baja de actividad guardada con exito");
            mostrarMenu();
            return bajaResponse;
        }
      } catch(ActividadesException ex){
          JOptionPane.showMessageDialog(null, "Ha ocurrido un error al dar de baja la actividad"+ ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      return new BajaDTO();
    }
    
//    public BajaDTO darBajaLocal(String motivo){
//        try{
//            BajaDTO bajaDTO= new BajaDTO(inscripcionElegida.getId(), motivo);
//            BajaDTO bajaResponse= coordinadorNegocioActividades.darBajaActividad(bajaDTO);;
//        } catch(ActividadesException ex){
//            
//        }
//    }
    
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

