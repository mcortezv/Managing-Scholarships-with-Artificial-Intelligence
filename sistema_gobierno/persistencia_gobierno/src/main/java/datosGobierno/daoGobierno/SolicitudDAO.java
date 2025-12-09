package datosGobierno.daoGobierno;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datosGobierno.adaptadoresGobierno.*;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.daoGobierno.documents.SolicitudDocument;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.EstudianteDTO;
import dtoGobierno.SolicitudDTO;
import gobierno.SolicitudDTOGobierno;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class SolicitudDAO implements ISolicitudDAO {
    private final MongoCollection<SolicitudDocument> col;

    public SolicitudDAO(){
        this.col = MongoClienteProvider.INSTANCE.getCollection("solicitudes", SolicitudDocument.class);
    }

    @Override
    public boolean guardarSolicitud(SolicitudDTOGobierno solicitud){
        try {
            return true;
        } catch (Exception sinUso){
            throw new SolicitudDAOException("Error al obtener las solicitudes por convocatoria");
        }
    }

    @Override
    public List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca) {
        try {
            MongoCollection<SolicitudDocument> col = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);
            List<SolicitudDocument> solicitudes = new ArrayList<>();
            col.find(Filters.eq("beca.tipo", tipoBeca))
                    .into(solicitudes);
            MongoCollection<EstudianteDTO> colEst = MongoClienteProvider.INSTANCE
                    .getCollection("estudiantes", EstudianteDTO.class);
            List<SolicitudDTO> resultado = new ArrayList<>();
            for (SolicitudDocument sol : solicitudes) {
                EstudianteDTO estudiante =
                        colEst.find(Filters.eq("_id", sol.getEstudiante())).first();
                SolicitudDTO dto = new SolicitudDTO();
                dto.setId(sol.getId());
                dto.setFecha(sol.getFecha());
                dto.setEstado(sol.getEstado().toString());
                dto.setBeca(BecaAdaptador.toDTO(sol.getBeca()));
                dto.setEstudiante(estudiante);
                dto.setHistorialAcademico(
                        HistorialAcademicoAdaptador.toDTO(sol.getHistorialAcademico())
                );
                dto.setInformacionSocioeconomica(
                        InformacionSocioeconomicaAdaptador.toDTO(sol.getInformacionSocioeconomica())
                );
                resultado.add(dto);
            }
            return resultado;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SolicitudDAOException("Error al obtener las solicitudes por tipo de beca");
        }
    }



    // Agregar este método al SolicitudDAO existente

    @Override
    public boolean cambiarEstado(int id, EstadoSolicitud estado) {
        try {
            if (estado == null) {
                throw new SolicitudDAOException("El estado no puede ser nulo");
            }

            // Buscar la solicitud
            Bson filtro = Filters.eq("id", (long) id);
            SolicitudDocument solicitud = col.find(filtro).first();

            if (solicitud == null) {
                throw new SolicitudDAOException("No se encontró solicitud con ID " + id);
            }

            // Validar transición de estado válida
            validarTransicionEstado(solicitud.getEstado(), estado);

            // Actualizar el estado
            Bson update = Updates.set("estado", estado);
            col.updateOne(filtro, update);

            return true;

        } catch (Exception ex) {
            throw new SolicitudDAOException(
                    "Error al cambiar el estado de la solicitud: " + ex.getMessage());
        }
    }

    /**
     * Valida que la transición de estado sea válida según reglas de negocio
     */
    private void validarTransicionEstado(EstadoSolicitud estadoActual, EstadoSolicitud nuevoEstado) {
        // ACTIVA puede pasar a: ACEPTADA, RECHAZADA, DEVUELTA
        if (estadoActual == EstadoSolicitud.ACTIVA) {
            if (nuevoEstado != EstadoSolicitud.ACEPTADA &&
                    nuevoEstado != EstadoSolicitud.RECHAZADA &&
                    nuevoEstado != EstadoSolicitud.DEVUELTA) {
                throw new SolicitudDAOException(
                        "Transición de estado inválida: ACTIVA -> " + nuevoEstado);
            }
            return;
        }

        // DEVUELTA puede volver a ACTIVA o pasar a ACEPTADA/RECHAZADA
        if (estadoActual == EstadoSolicitud.DEVUELTA) {
            if (nuevoEstado != EstadoSolicitud.ACTIVA &&
                    nuevoEstado != EstadoSolicitud.ACEPTADA &&
                    nuevoEstado != EstadoSolicitud.RECHAZADA) {
                throw new SolicitudDAOException(
                        "Transición de estado inválida: DEVUELTA -> " + nuevoEstado);
            }
            return;
        }

        // ACEPTADA y RECHAZADA son estados finales, solo pueden modificarse en casos especiales
        if (estadoActual == EstadoSolicitud.ACEPTADA || estadoActual == EstadoSolicitud.RECHAZADA) {
            // Solo se permite cambiar entre ACEPTADA y RECHAZADA (modificación de resolución)
            if (!(estadoActual == EstadoSolicitud.ACEPTADA && nuevoEstado == EstadoSolicitud.RECHAZADA) &&
                    !(estadoActual == EstadoSolicitud.RECHAZADA && nuevoEstado == EstadoSolicitud.ACEPTADA)) {
                throw new SolicitudDAOException(
                        "No se puede cambiar el estado de una solicitud finalizada a " + nuevoEstado);
            }
        }
    }

    @Override
    public Solicitud obtenerPorId(int idSolicitud) {
        try {
            MongoCollection<SolicitudDocument> colDoc = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);

            Bson filtro = Filters.eq("id", idSolicitud);
            SolicitudDocument solicitudDoc = colDoc.find(filtro).first();
            if (solicitudDoc == null) {
                throw new SolicitudDAOException("No se encontró solicitud con ID " + idSolicitud);
            }

            // Obtener estudiante completo
            MongoCollection<EstudianteDTO> colEst = MongoClienteProvider.INSTANCE
                    .getCollection("estudiantes", EstudianteDTO.class);
            EstudianteDTO estudiante = colEst.find(
                    Filters.eq("_id", solicitudDoc.getEstudiante())).first();

            if (estudiante == null) {
                throw new SolicitudDAOException("No se encontró el estudiante asociado");
            }

            // Construir entidad Solicitud completa
            Solicitud solicitud = new Solicitud();
            solicitud.setId(solicitudDoc.getId());
            solicitud.setBeca(solicitudDoc.getBeca());
            solicitud.setEstudiante(EstudianteAdaptador.toEntity(estudiante));
            solicitud.setInformacionSocioeconomica(solicitudDoc.getInformacionSocioeconomica());
            solicitud.setHistorialAcademico(solicitudDoc.getHistorialAcademico());
            solicitud.setFecha(solicitudDoc.getFecha());
            solicitud.setEstado(solicitudDoc.getEstado());

            return solicitud;

        } catch (Exception ex) {
            throw new SolicitudDAOException(
                    "Error al obtener la solicitud por ID: " + ex.getMessage());
        }
    }

    @Override
    public boolean actualizar(Solicitud solicitud) {
        try {
            if (solicitud == null) {
                throw new SolicitudDAOException("La solicitud no puede ser nula");
            }

            Bson filtro = Filters.eq("_id", solicitud.getId());

            // Verificar que existe
            SolicitudDocument existente = col.find(filtro).first();
            if (existente == null) {
                throw new SolicitudDAOException(
                        "No existe solicitud con ID " + solicitud.getId());
            }

            // Actualizar campos
            Bson update = Updates.combine(
                    Updates.set("beca", solicitud.getBeca()),
                    Updates.set("informacionSocioeconomica", solicitud.getInformacionSocioeconomica()),
                    Updates.set("historialAcademico", solicitud.getHistorialAcademico()),
                    Updates.set("estado", solicitud.getEstado()),
                    Updates.set("fecha", solicitud.getFecha())
            );

            col.updateOne(filtro, update);
            return true;

        } catch (Exception ex) {
            throw new SolicitudDAOException(
                    "Error al actualizar la solicitud: " + ex.getMessage());
        }
    }
}
