package datosGobierno.daoGobierno;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import datosGobierno.adaptadoresGobierno.BecaAdaptador;
import datosGobierno.adaptadoresGobierno.HistorialAcademicoAdaptador;
import datosGobierno.adaptadoresGobierno.InformacionSocioeconomicaAdaptador;
import datosGobierno.adaptadoresGobierno.SolicitudAdaptador;
import datosGobierno.configMongoGobierno.MongoClienteProvider;
import datosGobierno.daoGobierno.documents.SolicitudDocument;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import datosGobierno.dominioGobierno.Beca;
import datosGobierno.dominioGobierno.Estudiante;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.EstudianteDTO;
import dtoGobierno.InformacionSocioeconomicaDTO;
import dtoGobierno.SolicitudDTO;
import gobierno.SolicitudDTOGobierno;

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



    @Override
    public Solicitud obtenerPorId(int idSolicitud){
        try {
            return null;
        } catch (Exception sinUso){
            throw new SolicitudDAOException("Error al obtener la solicitud por id");
        }
    }

    @Override
    public Solicitud obtenerPorFiltro(String tipoFiltro, String filtro){
        try {
            return null;
        } catch (Exception sinUso){
            throw new SolicitudDAOException("Error al obtener la solicitud por filtro");
        }
    }

    @Override
    public boolean actualizar(Solicitud solicitud){
        try {
            return true;
        } catch (Exception sinUso){
            throw new SolicitudDAOException("Error al actualizar la solicitud");
        }
    }

    @Override
    public boolean cambiarEstado(int id, EstadoSolicitud estado){
        try {
            return true;
        } catch (Exception sinUso){
            throw new SolicitudDAOException("Error al cambiar el estado de la solicitud");
        }
    }
}
