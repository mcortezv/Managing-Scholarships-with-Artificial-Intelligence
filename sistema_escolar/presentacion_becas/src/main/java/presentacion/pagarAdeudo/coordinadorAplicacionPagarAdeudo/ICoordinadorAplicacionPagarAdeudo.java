package presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import presentacion.pagarAdeudo.PagarAdeudo;
import solicitarBeca.EstudianteDTO;

/**
 * Interfaz que define las operaciones del Coordinador de Aplicación para el módulo de Pagar Adeudo.
 * <p>
 * Establece el contrato de comunicación entre la Vista (Ventana PagarAdeudo) y el Controlador.
 * Permite que la interfaz gráfica solicite acciones de navegación, carga de datos
 * y procesamiento de pagos sin conocer la implementación interna de la lógica.
 */
public interface ICoordinadorAplicacionPagarAdeudo {

    /**
     * Cierra el flujo actual de pago de adeudos y retorna al menú principal de la aplicación.
     */
    void regresarAlMenuPrincipal();

    /**
     * Inicia el flujo para visualizar y pagar adeudos de biblioteca.
     * Carga los préstamos asociados al estudiante y actualiza la vista.
     *
     * @param estudianteDTO El estudiante del cual se consultarán los adeudos.
     */
    void seleccionarAdeudoBiblioteca(EstudianteDTO estudianteDTO);

    /**
     * Inicia el flujo para visualizar y pagar adeudos de colegiatura (materias).
     * Carga las clases asociadas al estudiante y actualiza la vista.
     *
     * @param estudianteDTO El estudiante del cual se consultarán los adeudos.
     */
    void seleccionarAdeudoColegiatura(EstudianteDTO estudianteDTO);

    /**
     * Navega a la vista de detalle de un préstamo de libro específico.
     *
     * @param prestamoSeleccionado El objeto DTO con la información del préstamo a visualizar.
     */
    void irADetallePrestamo(PrestamoDTO prestamoSeleccionado);

    /**
     * Navega a la vista de detalle de una clase o materia específica.
     *
     * @param claseSeleccionada El objeto DTO con la información de la clase a visualizar.
     */
    void irADetalleClase(ClaseDTO claseSeleccionada);

    /**
     * Cambia la vista actual para mostrar las opciones de métodos de pago disponibles.
     */
    void seleccionarRealizarPago();

    /**
     * Procesa la selección de un método de pago específico por parte del usuario.
     *
     * @param metodoPago Cadena que identifica el método seleccionado (ej. "BANCO", "PAYPAL").
     */
    void seleccionarMetodoPago(String metodoPago);

    /**
     * Inyecta la referencia de la ventana principal de este módulo (Vista) en el coordinador.
     * Necesario para que el coordinador pueda manipular los paneles de la ventana.
     *
     * @param pagarAdeudo Instancia de la ventana principal PagarAdeudo.
     */
    void setPagarAdeudo(PagarAdeudo pagarAdeudo);

    /**
     * Establece el tipo de adeudo que se está procesando actualmente en el flujo.
     *
     * @param tipo Cadena que indica el tipo (ej. "Biblioteca", "Colegiatura").
     */
    void setTipoAdeudo(String tipo);

    /**
     * Obtiene el tipo de adeudo que se está procesando actualmente.
     *
     * @return Cadena con el tipo de adeudo actual.
     */
    String getTipoAdeudo();
}