package datosGobierno.daoGobierno;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import datosGobierno.adaptadoresGobierno.*;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.documents.SolicitudDocument;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.EstudianteDTO;
import dtoGobierno.SolicitudDTO;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * SolicitudDAO corregido para manejar correctamente IDs y filtrar solicitudes activas
 * @author Cortez, Manuel;
 */
public class SolicitudDAO implements ISolicitudDAO {
    private final MongoCollection<SolicitudDocument> col;

    public SolicitudDAO(){
        this.col = MongoClienteProvider.INSTANCE.getCollection("solicitudes", SolicitudDocument.class);
    }

    @Override
    public boolean guardarSolicitud(SolicitudDocument solicitud){
        try {
            if (solicitud.get_id() == null) {
                solicitud.set_id(new ObjectId());
            }
            entity.setCreadoEn(Instant.now());
            col.insertOne(entity);
            return entity.get_id();
        } catch (MongoException ex) {
            throw new solicitarBeca.excepciones.SolicitudDAOException("Error al insertar Solicitud");
        }}
    }

    @Override
    public List<SolicitudDTO> obtenerListadoSolicitudes(String tipoBeca) {
        try {
            System.out.println("DEBUG DAO: Obteniendo solicitudes para tipo: " + tipoBeca);

            MongoCollection<SolicitudDocument> col = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);

            List<SolicitudDocument> solicitudes = new ArrayList<>();

            // CORREGIDO: Filtrar solo solicitudes ACTIVAS
            Bson filtro = Filters.and(
                    Filters.eq("beca.tipo", tipoBeca),
                    Filters.eq("estado", "ACTIVA")
            );

            col.find(filtro).into(solicitudes);

            System.out.println("DEBUG DAO: Solicitudes ACTIVAS encontradas: " + solicitudes.size());

            MongoCollection<EstudianteDTO> colEst = MongoClienteProvider.INSTANCE
                    .getCollection("estudiantes", EstudianteDTO.class);

            List<SolicitudDTO> resultado = new ArrayList<>();

            for (SolicitudDocument sol : solicitudes) {
                EstudianteDTO estudiante =
                        colEst.find(Filters.eq("_id", sol.getEstudiante())).first();

                if (estudiante != null) {
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
            }

            System.out.println("DEBUG DAO: DTOs creados: " + resultado.size());
            return resultado;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SolicitudDAOException("Error al obtener las solicitudes por tipo de beca: " + ex.getMessage());
        }
    }

    @Override
    public boolean cambiarEstado(int id, EstadoSolicitud estado) {
        try {
            System.out.println("DEBUG DAO: Cambiando estado de solicitud " + id + " a " + estado);

            if (estado == null) {
                throw new SolicitudDAOException("El estado no puede ser nulo");
            }

            // CORREGIDO: Buscar por el campo "id" (Long), NO por "_id" (ObjectId)
            Bson filtro = Filters.eq("id", (long) id);
            SolicitudDocument solicitud = col.find(filtro).first();

            if (solicitud == null) {
                System.err.println("ERROR DAO: No se encontró solicitud con id: " + id);
                throw new SolicitudDAOException("No se encontró solicitud con ID " + id);
            }

            System.out.println("DEBUG DAO: Solicitud encontrada, estado actual: " + solicitud.getEstado());

            // Validar transición de estado válida
            validarTransicionEstado(solicitud.getEstado(), estado);

            // Actualizar el estado
            Bson update = Updates.set("estado", estado);
            col.updateOne(filtro, update);

            System.out.println("DEBUG DAO: Estado actualizado exitosamente");
            return true;

        } catch (SolicitudDAOException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SolicitudDAOException(
                    "Error al cambiar el estado de la solicitud: " + ex.getMessage());
        }
    }

    /**
     * Valida que la transición de estado sea válida según reglas de negocio
     */
    private void validarTransicionEstado(EstadoSolicitud estadoActual, EstadoSolicitud nuevoEstado) {
        System.out.println("DEBUG DAO: Validando transición de " + estadoActual + " a " + nuevoEstado);

        // No validar transiciones para modificaciones - permitir cualquier cambio
        // Las validaciones de negocio deben estar en el BO, no en el DAO
        if (estadoActual == nuevoEstado) {
            System.out.println("DEBUG DAO: Estados iguales, no hay cambio");
        }
    }

    @Override
    public Solicitud obtenerPorId(int idSolicitud) {
        try {
            System.out.println("DEBUG DAO: Obteniendo solicitud por ID: " + idSolicitud);

            MongoCollection<SolicitudDocument> colDoc = MongoClienteProvider.INSTANCE
                    .getCollection("solicitudes", SolicitudDocument.class);

            // CORREGIDO: Buscar por "id" (Long), NO por "_id"
            Bson filtro = Filters.eq("id", (long) idSolicitud);
            SolicitudDocument solicitudDoc = colDoc.find(filtro).first();

            if (solicitudDoc == null) {
                throw new SolicitudDAOException("No se encontró solicitud con ID " + idSolicitud);
            }

            System.out.println("DEBUG DAO: Solicitud encontrada: " + solicitudDoc.getId());

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

            System.out.println("DEBUG DAO: Solicitud completa construida");
            return solicitud;

        } catch (SolicitudDAOException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SolicitudDAOException(
                    "Error al obtener la solicitud por ID: " + ex.getMessage());
        }
    }

    @Override
    public boolean actualizar(Solicitud solicitud) {
        try {
            System.out.println("DEBUG DAO: Actualizando solicitud ID: " + solicitud.getId());

            if (solicitud == null) {
                throw new SolicitudDAOException("La solicitud no puede ser nula");
            }

            // CORREGIDO: Buscar por "id" (Long), NO por "_id"
            Bson filtro = Filters.eq("id", solicitud.getId());

            // Verificar que existe
            SolicitudDocument existente = col.find(filtro).first();
            if (existente == null) {
                throw new SolicitudDAOException(
                        "No existe solicitud con ID " + solicitud.getId());
            }

            System.out.println("DEBUG DAO: Solicitud existente encontrada");

            // Actualizar campos
            Bson update = Updates.combine(
                    Updates.set("beca", solicitud.getBeca()),
                    Updates.set("informacionSocioeconomica", solicitud.getInformacionSocioeconomica()),
                    Updates.set("historialAcademico", solicitud.getHistorialAcademico()),
                    Updates.set("estado", solicitud.getEstado()),
                    Updates.set("fecha", solicitud.getFecha())
            );

            col.updateOne(filtro, update);
            System.out.println("DEBUG DAO: Solicitud actualizada exitosamente");
            return true;

        } catch (SolicitudDAOException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SolicitudDAOException(
                    "Error al actualizar la solicitud: " + ex.getMessage());
        }
    }
}