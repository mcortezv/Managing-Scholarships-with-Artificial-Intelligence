package adaptadoresPagoAdeudo;

import dto.pagarAdeudo.SolicitudPagoDTO;
import itson.pagarAdeudo.SolicitudPagoDTOI;

/**
 * Adaptador de Datos para Solicitudes de Pago.
 * <p>
 * Esta clase implementa el patrón de diseño Adapter (o Mapper).
 * Su responsabilidad es convertir objetos de transferencia de datos (DTO) entre
 * el formato interno de la aplicación y el formato requerido por el subsistema externo (ITSON/Banco).
 * <p>
 * Esto permite desacoplar la lógica de negocio de las estructuras de datos específicas
 * de las librerías externas.
 */
public class SolicitudPagoAdapdator {

    /**
     * Convierte un DTO del formato externo (Interfaz/Librería) al formato interno de la aplicación.
     * Realiza un mapeo campo a campo de la información.
     *
     * @param dtoI El objeto de solicitud de pago en formato externo (DTOI).
     * @return El objeto equivalente en formato interno (DTO), o null si la entrada es nula.
     */
    public static SolicitudPagoDTO toDTO(SolicitudPagoDTOI dtoI) {
        if (dtoI == null) return null;

        SolicitudPagoDTO dto = new SolicitudPagoDTO();

        dto.setIdEstudiante(dtoI.getIdEstudiante());
        dto.setReferenciaPago(dtoI.getReferenciaPago());
        dto.setFechaPago(dtoI.getFechaPago());
        dto.setMontoPagado(dtoI.getMontoPagado());
        dto.setMetodoPago(dtoI.getMetodoPago());
        dto.setEstatusPago(dtoI.getEstatusPago());
        dto.setTipoAdeudo(dtoI.getTipoAdeudo());
        return dto;
    }

    /**
     * Convierte un DTO del formato interno de la aplicación al formato externo (Interfaz/Librería).
     * Prepara los datos para ser enviados fuera del sistema actual.
     *
     * @param dto El objeto de solicitud de pago en formato interno.
     * @return El objeto equivalente en formato externo (DTOI), o null si la entrada es nula.
     */
    public static SolicitudPagoDTOI toDTOI(SolicitudPagoDTO dto) {
        if (dto == null) return null;

        SolicitudPagoDTOI dtoI = new SolicitudPagoDTOI();

        dtoI.setIdEstudiante(dto.getIdEstudiante());
        dtoI.setReferenciaPago(dto.getReferenciaPago());
        dtoI.setFechaPago(dto.getFechaPago());
        dtoI.setMontoPagado(dto.getMontoPagado());
        dtoI.setMetodoPago(dto.getMetodoPago());
        dtoI.setEstatusPago(dto.getEstatusPago());
        dtoI.setTipoAdeudo(dto.getTipoAdeudo());

        return dtoI;
    }
}