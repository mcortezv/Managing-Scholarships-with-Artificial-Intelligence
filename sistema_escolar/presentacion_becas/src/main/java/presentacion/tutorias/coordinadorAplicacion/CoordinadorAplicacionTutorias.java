/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.tutorias.coordinadorAplicacion;

import dto.tutorias.CitaDTO;
import dto.tutorias.HorarioDTO;
import dto.tutorias.MateriaDTO;
import dto.tutorias.TutorDTO;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import presentacion.CoordinadorAplicacion;
import presentacion.tutorias.coordinadorNegocio.CoordinadorNegocioTutorias;
import presentacion.tutorias.interfaces.ICoordinadorAplicacionTutorias;
import presentacion.tutorias.jframePrincipal.Tutorias;
import presentacion.tutorias.panels.PanelCitasActivas;
import presentacion.tutorias.panels.PanelConfirmacionCita;
import presentacion.tutorias.panels.PanelDetallesSolicitud;
import presentacion.tutorias.panels.PanelHistorial;
import presentacion.tutorias.panels.PanelHorariosDisponibles;
import presentacion.tutorias.panels.PanelTutoresDisponibles;

/**
 *
 * @author katia
 */
public class CoordinadorAplicacionTutorias implements ICoordinadorAplicacionTutorias {
    
    private final CoordinadorAplicacion coordinadorPadre;
    private final CoordinadorNegocioTutorias coordinadorNegocio;
    private Tutorias tutorias;
    private Long idMateriaSeleccionada;
    private String temaSeleccionado;
    private String modalidadSeleccionada;
    private LocalDate fechaSeleccionada;
    private TutorDTO tutorSeleccionado;
    private HorarioDTO horarioSeleccionado;
    
    public CoordinadorAplicacionTutorias(CoordinadorAplicacion coordinadorPadre,
                                         CoordinadorNegocioTutorias coordinadorNegocio) {
        this.coordinadorPadre = coordinadorPadre;
        this.coordinadorNegocio = coordinadorNegocio;
    }
    
    public void iniciarTutorias(Long matriculaAlumno) {
        coordinadorNegocio.setMatriculaAlumno(matriculaAlumno);
        
        if (tutorias != null) {
            tutorias.setVisible(false);
            tutorias.dispose();
        }
        
        tutorias = new Tutorias(this);
        tutorias.setVisible(true);
    }
    
    
    public void regresarAlMenuPrincipal() {
        if (tutorias != null) {
            tutorias.setVisible(false);
            tutorias.dispose();
        }
        coordinadorPadre.mostrarMainFrame();
    }
    
    
    @Override
    public void mostrarMenuTutorias() {
        tutorias.showPanel("menuTutorias");
    }
    
    @Override
    public void intentarMostrarDetallesSolicitud() {
        try {
            boolean puedeAgendar = coordinadorNegocio.puedeAgendarCita();
            
            if (!puedeAgendar) {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "Has alcanzado el límite de 3 cancelaciones este mes.\n" +
                    "No podrás agendar tutorías hasta el próximo mes.",
                    "No puedes agendar",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            mostrarDetallesSolicitud();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                tutorias,
                "Error al verificar tu historial: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
   
    private void mostrarDetallesSolicitud() {
        List<MateriaDTO> materias = coordinadorNegocio.obtenerMaterias();
        PanelDetallesSolicitud panel = (PanelDetallesSolicitud) tutorias.getPanel("detallesSolicitud");
        panel.setMaterias(materias);
        tutorias.showPanel("detallesSolicitud");
    }
    
    @Override
    public void mostrarTutoresDisponibles() {
        List<TutorDTO> tutores = coordinadorNegocio.obtenerTutores();
        PanelTutoresDisponibles panel = (PanelTutoresDisponibles) tutorias.getPanel("tutoresDisponibles");
        panel.setTutores(tutores);
        tutorias.showPanel("tutoresDisponibles");
    }
    
    @Override
    public void mostrarHorariosDisponibles() {
        List<HorarioDTO> horarios = coordinadorNegocio.obtenerHorariosDisponibles(
            tutorSeleccionado.getId(), 
            fechaSeleccionada
        );
        
        PanelHorariosDisponibles panel = (PanelHorariosDisponibles) tutorias.getPanel("horariosDisponibles");
        panel.setNombreTutor(tutorSeleccionado.getNombre());
        panel.setHorarios(horarios);
        
        tutorias.showPanel("horariosDisponibles");
    }
    
    @Override
    public void mostrarConfirmacionCita() {
        CitaDTO citaTemporal = construirCitaTemporal();
        
        PanelConfirmacionCita panel = (PanelConfirmacionCita) tutorias.getPanel("confirmacionCita");
        panel.cargarDatosCita(citaTemporal);
        
        tutorias.showPanel("confirmacionCita");
    }
    
    @Override
    public void mostrarCitasActivas() {
        List<CitaDTO> citas = coordinadorNegocio.obtenerCitasActivas();
        
        PanelCitasActivas panel = (PanelCitasActivas) tutorias.getPanel("citasActivas");
        panel.setCitas(citas);
        
        tutorias.showPanel("citasActivas");
    }
    
    @Override
    public void mostrarHistorial() {
        List<CitaDTO> historial = coordinadorNegocio.obtenerHistorialCompleto();
        List<MateriaDTO> materias = coordinadorNegocio.obtenerMaterias();
        
        PanelHistorial panel = (PanelHistorial) tutorias.getPanel("historial");
        panel.setHistorial(historial);
        panel.setMaterias(materias);
        
        tutorias.showPanel("historial");
    }
    
    
    @Override
    public void guardarDetallesSolicitud(Long idMateria, String tema, String modalidad, LocalDate fecha) {
        this.idMateriaSeleccionada = idMateria;
        this.temaSeleccionado = tema;
        this.modalidadSeleccionada = modalidad;
        this.fechaSeleccionada = fecha;
    }
    
    @Override
    public void seleccionarTutor(TutorDTO tutor) {
        this.tutorSeleccionado = tutor;
    }
    
    @Override
    public void seleccionarHorario(HorarioDTO horario) {
        this.horarioSeleccionado = horario;
    }
    
    @Override
    public void confirmarYAgendarCita() {
        try {
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setIdMateria(idMateriaSeleccionada);
            citaDTO.setIdTutor(tutorSeleccionado.getId());
            citaDTO.setIdHorario(horarioSeleccionado.getId());
            citaDTO.setTema(temaSeleccionado);
            citaDTO.setModalidad(modalidadSeleccionada.toUpperCase());
            citaDTO.setFecha(fechaSeleccionada);
            citaDTO.setHora(horarioSeleccionado.getHora());
            
            String ubicacion;
            if (modalidadSeleccionada.equalsIgnoreCase("Presencial")) {
                ubicacion = "Cubículo: " + tutorSeleccionado.getCubiculo();
                citaDTO.setUbicacion(tutorSeleccionado.getCubiculo());
            } else {
                ubicacion = "Enlace: " + tutorSeleccionado.getEnlace();
                citaDTO.setUbicacion(tutorSeleccionado.getEnlace());
            }
            
            CitaDTO citaCreada = coordinadorNegocio.agendarCita(citaDTO);
            
            if (citaCreada != null) {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "Cita agendada exitosamente.\n\n" +
                    ubicacion + "\n\n" +
                    "Se ha enviado una notificación al correo del tutor.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
                );
                mostrarMenuTutorias();
            } else {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "No se pudo agendar la cita",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                tutorias,
                "Error al agendar cita: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    @Override
    public void cancelarCita(Long idCita) {
        try {
            Long matriculaAlumno = coordinadorNegocio.getMatriculaAlumno();
            boolean cancelada = coordinadorNegocio.cancelarCita(idCita, matriculaAlumno);
            if (cancelada) {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "Tu cita ha sido cancelada.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
                );
                mostrarCitasActivas();
            } else {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "No se pudo cancelar la cita",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {
            String mensaje = ex.getMessage();
            if (mensaje != null && mensaje.contains("menos de una hora")) {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "Tu cita no ha podido ser cancelada debido a que falta menos de una hora para que se lleve a cabo.\n" +
                    "Favor de comunicarte con el tutor correspondiente.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    tutorias,
                    "Error: " + mensaje,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    
    @Override
    public void filtrarHistorialPorFecha(LocalDate fecha) {
        List<CitaDTO> historial = coordinadorNegocio.obtenerHistorialPorFecha(fecha);
        
        PanelHistorial panel = (PanelHistorial) tutorias.getPanel("historial");
        panel.setHistorial(historial);
    }
    
    @Override
    public void filtrarHistorialPorMateria(Long idMateria) {
        List<CitaDTO> historial = coordinadorNegocio.obtenerHistorialPorMateria(idMateria);
        
        PanelHistorial panel = (PanelHistorial) tutorias.getPanel("historial");
        panel.setHistorial(historial);
    }
    

    private CitaDTO construirCitaTemporal() {
        CitaDTO cita = new CitaDTO();
        
        List<MateriaDTO> materias = coordinadorNegocio.obtenerMaterias();
        MateriaDTO materia = materias.stream()
            .filter(m -> m.getId().equals(idMateriaSeleccionada))
            .findFirst()
            .orElse(null);
        
        if (materia != null) {
            cita.setNombreMateria(materia.getNombre());
        }
        
        cita.setIdMateria(idMateriaSeleccionada);
        cita.setTema(temaSeleccionado);
        cita.setFecha(fechaSeleccionada);
        cita.setNombreTutor(tutorSeleccionado.getNombre());
        cita.setHora(horarioSeleccionado.getHora());
        cita.setModalidad(modalidadSeleccionada);
        
        return cita;
    }
    
    public void setTutorias(Tutorias tutorias) {
        this.tutorias = tutorias;
    }
    
    public boolean intentarMostrarHorariosDisponibles(){
        try{
            List<HorarioDTO> horarios = coordinadorNegocio.obtenerHorariosDisponibles(tutorSeleccionado.getId(), fechaSeleccionada);
            if (horarios == null || horarios.isEmpty()){
                JOptionPane.showMessageDialog(tutorias, "No hay horarios disponibles para este tutor en la fecha seleccionada",
                "Sin horarios", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            PanelHorariosDisponibles panel = (PanelHorariosDisponibles) tutorias.getPanel("horariosDisponibles");
            panel.setNombreTutor(tutorSeleccionado.getNombre());
            panel.setHorarios(horarios);
            tutorias.showPanel("horariosDisponibles");
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(
            tutorias, "Error al obtener horarios: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        return false;
        }
    }
}
