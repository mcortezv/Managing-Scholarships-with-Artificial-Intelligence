package objetosNegocio.bo.pagarAdeudo.interfaces;

import datos.dominioItson.pagarAdeudo.Clase;
import datos.dominioItson.pagarAdeudo.Prestamo;
import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import itson.pagarAdeudo.SolicitudPagoDTOI;

import java.util.List;

/**
 * Interfaz de Negocio para la Gestión de Adeudos.
 * <p>
 * Define las operaciones disponibles para consultar deudas y registrar pagos en el sistema.
 * Actúa como una capa intermedia entre el Controlador y la Persistencia (o APIs externas),
 * garantizando que la lógica de negocio sea agnóstica a la fuente de datos.
 */
public interface IAdeudoBO {

    /**
     * Consulta los préstamos de biblioteca asociados a un estudiante.
     * Recupera la información de libros prestados y posibles multas.
     *
     * @param matricula El identificador único del estudiante.
     * @return Una lista de objetos de transferencia (DTO) con los detalles de los préstamos.
     */
    List<PrestamoDTO> obtenerDetallePrestamo(Long matricula);

    /**
     * Consulta las clases o materias que presentan adeudos de colegiatura.
     *
     * @param matricula El identificador único del estudiante.
     * @return Una lista de objetos de transferencia (DTO) con los detalles de las materias.
     */
    List<ClaseDTO> obtenerDetalleClase(Long matricula);

    /**
     * Registra y notifica el pago de un adeudo en el sistema central.
     * Este método se invoca una vez que la transacción bancaria ha sido aprobada.
     *
     * @param solicitudPagoDTOI El objeto de solicitud con los datos del pago confirmado.
     */
    void enviarSolicitudPago(SolicitudPagoDTOI solicitudPagoDTOI);
}