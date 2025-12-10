package solicitarBeca;
import org.bson.types.ObjectId;
import solicitarBeca.dominio.enums.Parentesco;
import solicitarBeca.repository.dao.DocumentoDAO;
import solicitarBeca.repository.dao.EstudianteDAO;
import solicitarBeca.repository.dao.SolicitudDAO;
import solicitarBeca.repository.documents.DocumentoDocument;
import solicitarBeca.repository.documents.EstudianteDocument;
import solicitarBeca.repository.documents.SolicitudDocument;
import solicitarBeca.dominio.Tutor;
import solicitarBeca.dominio.enums.Carrera;
import solicitarBeca.dominio.enums.EstadoSolicitud;
import solicitarBeca.dominio.enums.TipoDocumento;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class Seeder {

    public static void main(String[] args) {
        try {
            EstudianteDAO estudianteDAO = new EstudianteDAO();
            DocumentoDAO documentoDAO = new DocumentoDAO();
            SolicitudDAO solicitudDAO = new SolicitudDAO();

            EstudianteDocument estudiante = new EstudianteDocument();
            estudiante.set_id(new ObjectId());
            estudiante.setMatricula(258835L);
            estudiante.setNombre("Manuel Cortez");
            estudiante.setCarrera(Carrera.INGENIERIA);
            estudiante.setCorreo("mdjesuscv@gmail.com");
            estudiante.setTelefono("6441976108");
            estudiante.setDireccion("San Jose de Bacum, Son.");
            estudiante.setContrasenia("Manuel123");
            estudiante.setTutor(new Tutor("Dora Imelda", Parentesco.MADRE, "6441434194", "Cd. Obregon", "dimelda@gmail.com"));
            estudiante.setCreadoEn(Instant.now());
            ObjectId estudianteId = estudianteDAO.guardar(estudiante);


            DocumentoDocument doc = new DocumentoDocument();
            doc.set_id(new ObjectId());
            doc.setContenido("Algo mas que bien".getBytes());
            doc.setTipo(TipoDocumento.INE);
            doc.setIdentificador(1L);
            doc.setEstudiante(estudianteId);
            doc.setCreadoEn(Instant.now());
            ObjectId documentoId = documentoDAO.guardar(doc);


            SolicitudDocument sol = new SolicitudDocument();
            sol.set_id(new ObjectId());
            sol.setId(1001L);
            sol.setEstudiante(estudianteId);
            sol.setDocumentos(List.of(documentoId));
            sol.setFecha(LocalDate.now());
            sol.setEstado(EstadoSolicitud.ACEPTADA);
            sol.setCreadoEn(Instant.now());
            ObjectId solicitudId = solicitudDAO.create(sol);


        } catch (Exception ex) {
            System.err.println("Error al cargar datos en la base de datos" + ex.getMessage());
        }
    }
}
