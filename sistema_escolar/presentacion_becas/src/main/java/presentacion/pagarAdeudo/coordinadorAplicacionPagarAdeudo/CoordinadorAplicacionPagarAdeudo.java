package presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import excepciones.NegociosSolicitarPagoException;
import presentacion.CoordinadorAplicacion;
import presentacion.pagarAdeudo.PagarAdeudo;
import presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo.ICoordinadorNegocioPagarAdeudo;
import presentacion.pagarAdeudo.mainFraimePagarAdeudo.MainFramePagarAdeudo;
import presentacion.pagarAdeudo.panels.DetalleClase;
import presentacion.pagarAdeudo.panels.DetallePrestamo;
import presentacion.pagarAdeudo.panels.ListaClasesColegiatura;
import presentacion.pagarAdeudo.panels.ListaPrestamosBiblioteca;
import solicitarBeca.EstudianteDTO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CoordinadorAplicacionPagarAdeudo implements ICoordinadorAplicacionPagarAdeudo {
    private final CoordinadorAplicacion coordinadorPadre;
    private final MainFramePagarAdeudo mainFrame;
    private final ICoordinadorNegocioPagarAdeudo coordinadorNegocioPagarAdeudo;
    private PagarAdeudo pagarAdeudo;
    private String tipoAdeudo;
    private List<PrestamoDTO> prestamos;
    private List<ClaseDTO> clases;
    private Double adeudoBibliotecaCache;
    private Double adeudoColegiaturaCache;

    private SolicitudPagoDTO solicitudPagoDTO;

    public CoordinadorAplicacionPagarAdeudo(ICoordinadorNegocioPagarAdeudo negocio, CoordinadorAplicacion padre) {
        this.coordinadorPadre = padre;
        this.coordinadorNegocioPagarAdeudo = negocio;
        this.mainFrame = null;
        this.solicitudPagoDTO = new SolicitudPagoDTO();
    }

    public void pagarAdeudo() {
        if (mainFrame != null) {
            mainFrame.setVisible(false);
        }
        pagarAdeudo = new PagarAdeudo(coordinadorPadre, this);
        pagarAdeudo.setVisible(true);
    }

    public void regresarAlMenuPrincipal() {
        if (pagarAdeudo != null) {
            pagarAdeudo.dispose();
        }
        limpiarCache();
        coordinadorPadre.mostrarMainFrame();
    }

    @Override
    public void seleccionarAdeudoBiblioteca(EstudianteDTO estudianteDTO) {
        this.setTipoAdeudo("Biblioteca");
        if (this.prestamos == null) {
            try{
                this.prestamos = coordinadorNegocioPagarAdeudo.obtenerListaPrestamos(estudianteDTO);
                this.adeudoBibliotecaCache = coordinadorNegocioPagarAdeudo.calcularTotalPrestamos(this.prestamos);
            }catch (NegociosSolicitarPagoException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
        }
        ListaPrestamosBiblioteca panel = (ListaPrestamosBiblioteca) pagarAdeudo.getPanel("listaPrestamosBiblioteca");
        panel.setAdeudo(this.adeudoBibliotecaCache);
        panel.setPrestamos(this.prestamos);
        pagarAdeudo.showPanel("listaPrestamosBiblioteca");
    }

    @Override
    public void seleccionarAdeudoColegiatura(EstudianteDTO estudianteDTO) {
        this.setTipoAdeudo("Colegiatura");
        if (this.clases == null) {
            try{
                this.clases = coordinadorNegocioPagarAdeudo.obtenerListaClases(estudianteDTO);
                this.adeudoColegiaturaCache = coordinadorNegocioPagarAdeudo.calcularTotalClases(this.clases);
            }catch (NegociosSolicitarPagoException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
        }
        ListaClasesColegiatura panel = (ListaClasesColegiatura) pagarAdeudo.getPanel("listaClasesColegiatura");
        panel.setAdeudo(this.adeudoColegiaturaCache);
        panel.setClases(this.clases);
        pagarAdeudo.showPanel("listaClasesColegiatura");
    }

    @Override
    public void irADetallePrestamo(PrestamoDTO prestamoSeleccionado) {
        DetallePrestamo panel = (DetallePrestamo) pagarAdeudo.getPanel("detallePrestamo");
        panel.setPrestamo(prestamoSeleccionado);
        pagarAdeudo.showPanel("detallePrestamo");
    }
    
    @Override
    public void irADetalleClase(ClaseDTO claseSeleccionada) {
        DetalleClase panel = (DetalleClase) pagarAdeudo.getPanel("detalleClase");
        panel.setClase(claseSeleccionada);
        pagarAdeudo.showPanel("detalleClase");
    }

    @Override
    public void seleccionarRealizarPago() {
        pagarAdeudo.showPanel("metodosDePago");
    }

    @Override
    public void seleccionarMetodoPago(String metodoPago) {
        if ("BANCO".equals(metodoPago)) {
            abrirPasarelaBanco();
        }
        if ("PAYPAL".equals(metodoPago)){
            abrirPasarelaPaypal();
        }
    }

    private void abrirPasarelaBanco() {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "Al dar click en continuar será redirigido a un sistema de pago externo, ¿desea continuar?",
                "Sistema de Pago Externo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            ActionListener listenerBotonPagarDelBanco = e -> procesarPagoBanco();
            coordinadorNegocioPagarAdeudo.mostrarVentanaPago(listenerBotonPagarDelBanco);
        }
    }

    private void procesarPagoBanco() {
        try {
            solicitudPagoDTO.setEstatusPago("Pendiente");
            solicitudPagoDTO.setMetodoPago("BANCO");
            if ("Biblioteca".equals(tipoAdeudo)) {
                solicitudPagoDTO.setMontoPagado(adeudoBibliotecaCache);
                solicitudPagoDTO.setTipoAdeudo("Biblioteca");
            } else if ("Colegiatura".equals(tipoAdeudo)) {
                solicitudPagoDTO.setMontoPagado(adeudoColegiaturaCache);
                solicitudPagoDTO.setTipoAdeudo("Colegiatura");
            }

            SolicitudPagoDTO resultado = coordinadorNegocioPagarAdeudo.realizarPago(solicitudPagoDTO);

            if (resultado != null && "Pagado".equalsIgnoreCase(resultado.getEstatusPago())) {
                coordinadorNegocioPagarAdeudo.notificarLiquidacion(resultado);
                coordinadorNegocioPagarAdeudo.cerrarVentanaBanco();
                JOptionPane.showMessageDialog(null, "¡Pago realizado con éxito!");
                limpiarCache();
                regresarAlMenuPrincipal();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el sistema de pago: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error procesando pago: " + ex.getMessage());
        }
    }


    private void abrirPasarelaPaypal() {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "Será redirigido al inicio de sesión de PayPal, ¿desea continuar?",
                "Pago con PayPal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            ActionListener listenerExito = e -> procesarPagoPaypal();
            double monto = 0;
            if ("Biblioteca".equals(tipoAdeudo)) {
                monto = adeudoBibliotecaCache != null ? adeudoBibliotecaCache : 0.0;
            } else if ("Colegiatura".equals(tipoAdeudo)) {
                monto = adeudoColegiaturaCache != null ? adeudoColegiaturaCache : 0.0;
            }
            String concepto = "Pago de Adeudo - " + tipoAdeudo;
            coordinadorNegocioPagarAdeudo.mostrarVentanaPaypal(monto, concepto, listenerExito);
        }
    }

    private void procesarPagoPaypal() {
        try {
            solicitudPagoDTO.setEstatusPago("Pagado");
            solicitudPagoDTO.setMetodoPago("PAYPAL");

            if ("Biblioteca".equals(tipoAdeudo)) {
                solicitudPagoDTO.setMontoPagado(adeudoBibliotecaCache);
                solicitudPagoDTO.setTipoAdeudo("Biblioteca");
            } else if ("Colegiatura".equals(tipoAdeudo)) {
                solicitudPagoDTO.setMontoPagado(adeudoColegiaturaCache);
                solicitudPagoDTO.setTipoAdeudo("Colegiatura");
            }

            SolicitudPagoDTO resultado = coordinadorNegocioPagarAdeudo.realizarPagoPaypal(solicitudPagoDTO);

            if (resultado != null && "Pagado".equalsIgnoreCase(resultado.getEstatusPago())) {
                coordinadorNegocioPagarAdeudo.notificarLiquidacion(resultado);
                coordinadorNegocioPagarAdeudo.cerrarVentanaPaypal();
                JOptionPane.showMessageDialog(null, "Pago con PayPal registrado exitosamente");
                limpiarCache();
                regresarAlMenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(null, "El pago se realizó, pero hubo un error al notificar al sistema escolar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el pago de PayPal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCache() {
        this.prestamos = null;
        this.clases = null;
        this.adeudoBibliotecaCache = null;
        this.adeudoColegiaturaCache = null;
        this.solicitudPagoDTO = new SolicitudPagoDTO();
    }

    public void setPagarAdeudo(PagarAdeudo pagarAdeudo) {
        this.pagarAdeudo = pagarAdeudo;
    }
    public void setTipoAdeudo(String tipo){
        this.tipoAdeudo = tipo;
    }
    public String getTipoAdeudo(){
        return tipoAdeudo;
    }
}