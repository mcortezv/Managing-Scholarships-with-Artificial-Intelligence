package presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import dto.pagarAdeudo.SolicitudPagoDTO;
import excepciones.NegociosSolicitarPagoException;
import objetosNegocio.adaptadores.pagarAdeudo.excepciones.AdeudoException;
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

/**
 * Coordinador de la Aplicación para el módulo de Pagar Adeudo.
 * Esta clase actúa como controlador principal entre la interfaz gráfica (Vistas)
 * y la lógica de negocio. Gestiona la navegación entre paneles, almacena temporalmente
 * la información de adeudos y coordina el flujo de los pagos.
 */
public class CoordinadorAplicacionPagarAdeudo implements ICoordinadorAplicacionPagarAdeudo {

    // Referencia al coordinador principal de toda la aplicación (para navegación global)
    private final CoordinadorAplicacion coordinadorPadre;
    private final MainFramePagarAdeudo mainFrame;

    // Interfaz para comunicarse con la capa de negocio (backend/lógica)
    private final ICoordinadorNegocioPagarAdeudo coordinadorNegocioPagarAdeudo;

    // Ventana principal de este módulo
    private PagarAdeudo pagarAdeudo;

    // Variables de estado y caché para no consultar la BD repetidamente
    private String tipoAdeudo;
    private List<PrestamoDTO> prestamos;
    private List<ClaseDTO> clases;
    private Double adeudoBibliotecaCache;
    private Double adeudoColegiaturaCache;

    private SolicitudPagoDTO solicitudPagoDTO;

    /**
     * Constructor del coordinador.
     * @param negocio Instancia de la capa de negocio.
     * @param padre Instancia del coordinador principal de la app.
     */
    public CoordinadorAplicacionPagarAdeudo(ICoordinadorNegocioPagarAdeudo negocio, CoordinadorAplicacion padre) {
        this.coordinadorPadre = padre;
        this.coordinadorNegocioPagarAdeudo = negocio;
        this.mainFrame = null;
        this.solicitudPagoDTO = new SolicitudPagoDTO();
    }

    /**
     * Inicializa el módulo de pagos. Oculta la ventana principal y muestra la de pagos.
     */
    public void pagarAdeudo() {
        if (mainFrame != null) {
            mainFrame.setVisible(false);
        }
        pagarAdeudo = new PagarAdeudo(coordinadorPadre, this);
        pagarAdeudo.setVisible(true);
    }

    /**
     * Cierra el módulo de pagos, limpia los datos en memoria y regresa al menú principal.
     */
    public void regresarAlMenuPrincipal() {
        if (pagarAdeudo != null) {
            pagarAdeudo.dispose();
        }
        limpiarCache();
        coordinadorPadre.mostrarMainFrame();
    }

    /**
     * Gestiona la selección de adeudos de biblioteca.
     * Si no hay datos en caché, los solicita al negocio. Luego actualiza la vista.
     */
    @Override
    public void seleccionarAdeudoBiblioteca(EstudianteDTO estudianteDTO) {
        this.setTipoAdeudo("Biblioteca");

        if (this.prestamos == null) {
            try{
                this.prestamos = coordinadorNegocioPagarAdeudo.obtenerListaPrestamos(estudianteDTO);
                this.adeudoBibliotecaCache = coordinadorNegocioPagarAdeudo.calcularTotalPrestamos(this.prestamos);
            } catch (NegociosSolicitarPagoException | AdeudoException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
        }

        ListaPrestamosBiblioteca panel = (ListaPrestamosBiblioteca) pagarAdeudo.getPanel("listaPrestamosBiblioteca");
        panel.setAdeudo(this.adeudoBibliotecaCache);
        panel.setPrestamos(this.prestamos);
        pagarAdeudo.showPanel("listaPrestamosBiblioteca");
    }

    /**
     * Gestiona la selección de adeudos de colegiatura.
     * Funciona igual que el método de biblioteca: carga datos si es necesario y muestra el panel.
     */
    @Override
    public void seleccionarAdeudoColegiatura(EstudianteDTO estudianteDTO) {
        this.setTipoAdeudo("Colegiatura");
        if (this.clases == null) {
            try{
                this.clases = coordinadorNegocioPagarAdeudo.obtenerListaClases(estudianteDTO);
                this.adeudoColegiaturaCache = coordinadorNegocioPagarAdeudo.calcularTotalClases(this.clases);
            } catch (NegociosSolicitarPagoException | AdeudoException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
        }
        ListaClasesColegiatura panel = (ListaClasesColegiatura) pagarAdeudo.getPanel("listaClasesColegiatura");
        panel.setAdeudo(this.adeudoColegiaturaCache);
        panel.setClases(this.clases);
        pagarAdeudo.showPanel("listaClasesColegiatura");
    }

    /**
     * Navega al panel de detalle de un préstamo específico.
     */
    @Override
    public void irADetallePrestamo(PrestamoDTO prestamoSeleccionado) {
        DetallePrestamo panel = (DetallePrestamo) pagarAdeudo.getPanel("detallePrestamo");
        panel.setPrestamo(prestamoSeleccionado);
        pagarAdeudo.showPanel("detallePrestamo");
    }

    /**
     * Navega al panel de detalle de una clase específica.
     */
    @Override
    public void irADetalleClase(ClaseDTO claseSeleccionada) {
        DetalleClase panel = (DetalleClase) pagarAdeudo.getPanel("detalleClase");
        panel.setClase(claseSeleccionada);
        pagarAdeudo.showPanel("detalleClase");
    }

    /**
     * Navega al panel de selección de métodos de pago.
     */
    @Override
    public void seleccionarRealizarPago() {
        pagarAdeudo.showPanel("metodosDePago");
    }

    /**
     * Redirige al flujo correspondiente según el método de pago elegido.
     * @param metodoPago String identificador ("BANCO" o "PAYPAL").
     */
    @Override
    public void seleccionarMetodoPago(String metodoPago) {
        if ("BANCO".equals(metodoPago)) {
            abrirPasarelaBanco();
        }
        if ("PAYPAL".equals(metodoPago)){
            abrirPasarelaPaypal();
        }
    }


    /**
     * Simula la redirección al sistema bancario y asigna el listener para procesar el pago.
     */
    private void abrirPasarelaBanco(){
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

    /**
     * Lógica para registrar el pago bancario en el sistema.
     * Construye el DTO, llama al negocio y maneja la respuesta.
     */
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

    /**
     * Simula la redirección a PayPal y asigna el listener de éxito.
     */
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

    /**
     * Lógica para registrar el pago de PayPal. Similar a Banco pero con manejo específico de ventanas.
     */
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

    /**
     * Reinicia todas las variables de caché para asegurar que la próxima transacción
     * comience con datos limpios y actualizados.
     */
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