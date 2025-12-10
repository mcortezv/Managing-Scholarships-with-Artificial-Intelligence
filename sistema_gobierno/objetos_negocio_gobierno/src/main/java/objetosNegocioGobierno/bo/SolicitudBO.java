package objetosNegocioGobierno.bo;
import datosGobierno.adaptadoresGobierno.SolicitudAdaptador;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.SolicitudDTO;
import objetosNegocioGobierno.bo.excepciones.SolicitudBOException;
import objetosNegocioGobierno.bo.interfaces.ISolicitudBO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Business Object para gestión de solicitudes
 * Implementa validaciones de reglas de negocio y transiciones de estado
 *
 * @author Cortez, Manuel
 */
public class SolicitudBO implements ISolicitudBO {
    private final ISolicitudDAO solicitudDAO;

    /**
     * Instantiates a new Solicitud bo.
     *
     * @param solicitudDAO the solicitud dao
     */
    public SolicitudBO(ISolicitudDAO solicitudDAO) {
        if (solicitudDAO == null) {
            throw new IllegalArgumentException("SolicitudDAO no puede ser nulo");
        }
        this.solicitudDAO = solicitudDAO;
    }

    @Override
    public boolean cambiarEstado(int id, EstadoSolicitud nuevoEstado) {
        try {
            // Validaciones
            validarId(id);
            validarEstado(nuevoEstado);

            // Obtener solicitud actual
            Solicitud solicitud = solicitudDAO.obtenerPorId(id);

            if (solicitud == null) {
                throw new SolicitudBOException("No se encontró solicitud con ID: " + id);
            }

            // Validar transición de estado
            validarTransicionEstado(solicitud.getEstado(), nuevoEstado);

            // Cambiar estado
            return solicitudDAO.cambiarEstado(id, nuevoEstado);

        } catch (SolicitudBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SolicitudBOException("Error al cambiar estado de solicitud: " + ex.getMessage());
        }
    }

    @Override
    public List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca) {
        try {
            validarTipoBeca(tipoBeca);

            List<SolicitudDTO> solicitudes = new ArrayList<>();

            for (Solicitud solicitud : solicitudDAO.obtenerListadoSolicitudes(tipoBeca)){
                solicitudes.add(SolicitudAdaptador.toDTO(solicitud));
            }

            if (solicitudes == null || solicitudes.isEmpty()) {
                throw new SolicitudBOException("No hay solicitudes para el tipo de beca: " + tipoBeca);
            }

            return solicitudes;

        } catch (SolicitudBOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new SolicitudBOException("Error al obtener listado de solicitudes: " + ex.getMessage());
        }
    }

    /**
     * Valida que el ID sea válido
     */
    private void validarId(int id) {
        if (id <= 0) {
            throw new SolicitudBOException("El ID debe ser mayor a 0");
        }
    }

    /**
     * Valida que el estado no sea nulo
     */
    private void validarEstado(EstadoSolicitud estado) {
        if (estado == null) {
            throw new SolicitudBOException("El estado no puede ser nulo");
        }
    }

    /**
     * Valida el tipo de beca
     */
    private void validarTipoBeca(String tipoBeca) {
        if (tipoBeca == null || tipoBeca.trim().isEmpty()) {
            throw new SolicitudBOException("El tipo de beca no puede estar vacío");
        }

        List<String> tiposValidos = Arrays.asList("EXCELENCIA_ACADEMICA", "ESCASOS_RECURSOS",
                "CONSTANCIA", "ESTUDIANTE_TRABAJO"
        );

        if (!tiposValidos.contains(tipoBeca.toUpperCase())) {
            throw new SolicitudBOException("Tipo de beca inválido: " + tipoBeca);
        }
    }

    /**
     * Valida la transición de estado según las reglas de negocio
     *
     * Reglas
     * - ACTIVA puede pasar a ACEPTADA, RECHAZADA, DEVUELTA
     * - DEVUELTA puede pasar a ACTIVA, ACEPTADA, RECHAZADA
     * - ACEPTADA solo puede cambiar a RECHAZADA
     * - RECHAZADA solo puede cambiar a ACEPTADA
     */
    private void validarTransicionEstado(EstadoSolicitud estadoActual,
                                         EstadoSolicitud nuevoEstado) {
        // No se puede mantener el mismo estado
        if (estadoActual == nuevoEstado) {
            throw new SolicitudBOException("La solicitud ya está en estado: " + estadoActual);
        }

        switch (estadoActual) {
            case ACTIVA:
                validarTransicionDesdeActiva(nuevoEstado);
                break;

            case DEVUELTA:
                validarTransicionDesdeDevuelta(nuevoEstado);
                break;

            case ACEPTADA:
                validarTransicionDesdeAceptada(nuevoEstado);
                break;

            case RECHAZADA:
                validarTransicionDesdeRechazada(nuevoEstado);
                break;

            default:
                throw new SolicitudBOException("Estado actual no reconocido: " + estadoActual);
        }
    }

    /**
     * Valida transiciones desde estado ACTIVA
     */
    private void validarTransicionDesdeActiva(EstadoSolicitud nuevoEstado) {
        List<EstadoSolicitud> estadosValidos = Arrays.asList(
                EstadoSolicitud.ACEPTADA,
                EstadoSolicitud.RECHAZADA,
                EstadoSolicitud.DEVUELTA
        );

        if (!estadosValidos.contains(nuevoEstado)) {
            throw new SolicitudBOException(String.format("Transición inválida: ACTIVA -> %s. " +
                            "Estados válidos: ACEPTADA, RECHAZADA, DEVUELTA", nuevoEstado));
        }
    }

    /**
     * Valida transiciones desde estado DEVUELTA
     */
    private void validarTransicionDesdeDevuelta(EstadoSolicitud nuevoEstado) {
        List<EstadoSolicitud> estadosValidos = Arrays.asList(
                EstadoSolicitud.ACTIVA,
                EstadoSolicitud.ACEPTADA,
                EstadoSolicitud.RECHAZADA
        );

        if (!estadosValidos.contains(nuevoEstado)) {
            throw new SolicitudBOException(String.format("Transición inválida: DEVUELTA → %s. " +
                            "Estados válidos: ACTIVA, ACEPTADA, RECHAZADA", nuevoEstado));
        }
    }

    /**
     * Valida transiciones desde estado ACEPTADA
     * Solo se permite cambiar a RECHAZADA en casos excepcionales (modificación)
     */
    private void validarTransicionDesdeAceptada(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado != EstadoSolicitud.RECHAZADA) {
            throw new SolicitudBOException(String.format("Transición inválida: ACEPTADA → %s. " +
                                    "Solo se puede cambiar a RECHAZADA mediante modificación de resolución", nuevoEstado));
        }
    }

    /**
     * Valida transiciones desde estado RECHAZADA
     * Solo se permite cambiar a ACEPTADA en casos excepcionales (modificación)
     */
    private void validarTransicionDesdeRechazada(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado != EstadoSolicitud.ACEPTADA) {
            throw new SolicitudBOException(String.format("Transición inválida: RECHAZADA -> %s. " +
                                    "Solo se puede cambiar a ACEPTADA mediante modificación de resolución", nuevoEstado));
        }
    }
}
