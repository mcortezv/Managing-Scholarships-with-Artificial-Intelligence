package controles;

import adaptadores.solicitarBeca.*;
import itson.EstudianteDTOItson;
import excepciones.DocumentoInvalidoException;
import excepciones.SolicitarBecaException;
import interfaces.solicitarBeca.*;
import org.bson.types.ObjectId;
import solicitarBeca.*;
import solicitarBeca.dominio.*;
import solicitarBeca.dominio.enums.Carrera;
import solicitarBeca.dominio.enums.Parentesco;
import solicitarBeca.dominio.enums.TipoVivienda;
import solicitarBeca.repository.documents.DocumentoDocument;
import solicitarBeca.repository.documents.EstudianteDocument;
import solicitarBeca.repository.documents.SolicitudDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Cortez, Manuel;
 */
public class ControlSolicitarBeca {
    private final ISolicitudBO solicitudBO;
    private final IEstudianteBO estudianteBO;
    private final ITutorBO tutorBO;
    private final IBecasFiltradasBO becasFiltradasBO;
    private final IDocumentoBO documentoBO;
    private final IHistorialAcademicoBO historialBO;
    private final IInformacionSocioeconomicaBO socioBO;
    private BecasFiltradas becasFiltradas;
    private Beca becaActual;
    private Estudiante estudiante;
    private Solicitud solicitudActual;

    public ControlSolicitarBeca(ISolicitudBO solicitudBO,
            IEstudianteBO estudianteBO,
            ITutorBO tutorBO,
            IBecasFiltradasBO becaBO,
            IDocumentoBO documentoBO,
            IHistorialAcademicoBO historialBO,
            IInformacionSocioeconomicaBO socioBO) {
        this.solicitudBO = Objects.requireNonNull(solicitudBO);
        this.estudianteBO = Objects.requireNonNull(estudianteBO);
        this.tutorBO = Objects.requireNonNull(tutorBO);
        this.becasFiltradasBO = Objects.requireNonNull(becaBO);
        this.documentoBO = Objects.requireNonNull(documentoBO);
        this.historialBO = Objects.requireNonNull(historialBO);
        this.socioBO = Objects.requireNonNull(socioBO);
    }

    public void iniciarSolicitud() throws SolicitarBecaException {
        try {
            this.solicitudActual = solicitudBO.crearSolicitud(becaActual);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public void setBecaActual(BecaDTO becaActual) {
        this.becaActual = BecaAdaptador.toEntity(becaActual);
    }

    public BecasFiltradasDTO obtenerBecasFiltradas(RequisitosDTO requisitosDTO) throws SolicitarBecaException {
        try {
            this.becasFiltradas = BecasFiltradasAdaptador
                    .toEntity(becasFiltradasBO.obtenerBecasFiltradas(RequisitosAdaptador.toDTOGobierno(requisitosDTO)));
            return BecasFiltradasAdaptador
                    .toDTO(becasFiltradasBO.obtenerBecasFiltradas(RequisitosAdaptador.toDTOGobierno(requisitosDTO)));
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public BecaDTO obtenerBecaPorId(Long id) throws SolicitarBecaException {
        try {
            becaActual = becasFiltradasBO.obtenerBecaPorCodigo(id, becasFiltradas);
            return BecaAdaptador.toDTO(becaActual);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public EstudianteDTO obtenerEstudiante(Long id) throws SolicitarBecaException {
        try {
            EstudianteDTOItson estudianteResponseDTO = estudianteBO.crearEstudiante(id);
            estudiante = EstudianteAdaptador.toEntity(estudianteResponseDTO);
            return EstudianteAdaptador.toDTO(estudianteResponseDTO);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public void asignarHistorial(HistorialAcademicoDTO historialAcademicoDTO) throws SolicitarBecaException {
        try {
            HistorialAcademico historialAcademico = HistorialAcademicoAdaptador
                    .toEntity(historialBO.crearHistorial(historialAcademicoDTO.getMatriculaEstudiante()));
            historialAcademico.setCarrera(Carrera.valueOf(historialAcademicoDTO.getCarrera()));
            historialAcademico.setCargaAcademica(historialAcademicoDTO.getCargaAcademica());
            historialAcademico.setSemestre(historialAcademicoDTO.getSemestre());
            solicitudActual.setHistorialAcademico(historialAcademico);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public void asignarTutor(TutorDTO tutorDTO) throws SolicitarBecaException {
        try {
            if (tutorDTO.getNombre() == null || tutorDTO.getNombre().equals("")) {
            }
            Tutor tutor = tutorBO.crearTutor(tutorDTO.getNombre(), Parentesco.valueOf(tutorDTO.getParentesco()),
                    tutorDTO.getTelefono(), tutorDTO.getDireccion(), tutorDTO.getCorreo());
            estudiante.setTutor(tutor);
            solicitudActual.setEstudiante(estudiante);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public void setInformacionSocioeconomica(InformacionSocioeconomicaDTO infoSoDTO) throws SolicitarBecaException {
        try {
            if (infoSoDTO == null) {
                throw new SolicitarBecaException("No se han adjuntado documentos para asignar.");

            }
            InformacionSocioeconomica infoSocio = socioBO.crearInformacionSocioeconomica(
                    infoSoDTO.getIngresoTotalFamilarMensual(), TipoVivienda.valueOf(infoSoDTO.getTipoVivienda()),
                    infoSoDTO.isTrabajo(), infoSoDTO.isDeudas());
            solicitudActual.setInformacionSocioeconomica(infoSocio);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public void asignarDocumentos(List<DocumentoDTO> documentosDTO) throws SolicitarBecaException {
        if (documentosDTO == null || documentosDTO.isEmpty()) {
            throw new SolicitarBecaException("No se han adjuntado documentos para asignar.");
        }
        List<Documento> documentosEntidad = new ArrayList<>();
        try {
            for (DocumentoDTO dto : documentosDTO) {
                // validarDocumento(dto);
                Documento documento = DocumentoAdaptador.toEntity(dto);
                documentosEntidad.add(documento);
            }
            solicitudActual.setDocumentos(documentosEntidad);
        } catch (DocumentoInvalidoException ex) {
            throw new SolicitarBecaException("Error en los documentos: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SolicitarBecaException("Ocurrió un error inesperado al procesar los archivos.");
        }
    }

    private void validarDocumento(DocumentoDTO dto) throws DocumentoInvalidoException {
        String nombreDoc = (dto.getTipo() != null) ? dto.getTipo() : "Sin Tipo";
        byte[] contenido = dto.getContenido();
        if (contenido == null || contenido.length == 0) {
            throw new DocumentoInvalidoException("El documento de tipo '" + nombreDoc + "' está vacío 0 kb");
        }
        int maxBytes = 5 * 1024 * 1024;
        if (contenido.length > maxBytes) {
            throw new DocumentoInvalidoException(
                    "El documento '" + nombreDoc + "' excede el tamaño máximo permitido 5MB");
        }
        if (contenido.length < 5) {
            throw new DocumentoInvalidoException("El documento '" + nombreDoc + "' es inválido (muy corto).");
        }
        if (contenido[0] != 0x25 ||
                contenido[1] != 0x50 ||
                contenido[2] != 0x44 ||
                contenido[3] != 0x46 ||
                contenido[4] != 0x2D) {
            throw new DocumentoInvalidoException("Error en el documento");
        }
    }

    public SolicitudDTO obtenerSolicitudActual() throws SolicitarBecaException {
        try {
            return SolicitudAdaptador.toDTO(solicitudActual);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public boolean guardarSolicitud() throws SolicitarBecaException {
        try {
            solicitudBO.validarSolicitudCompleta(solicitudActual);
            solicitudBO.enviarSolicitud(SolicitudAdaptador.toDTOGobierno(solicitudActual));
            EstudianteDocument estudianteDocument = EstudianteAdaptador.toDocument(solicitudActual.getEstudiante());
            estudianteBO.guardarEstudiante(estudianteDocument);
            List<ObjectId> documentos = new ArrayList<>();
            for (Documento documento : solicitudActual.getDocumentos()) {
                DocumentoDocument documentoDocument = DocumentoAdaptador.toDocument(documento,
                        estudianteDocument.get_id());
                documentos.add(documentoDocument.get_id());
                documentoBO.guardarDocumento(documentoDocument);
            }
            SolicitudDocument solicitudDocument = SolicitudAdaptador.toDocument(solicitudActual,
                    estudianteDocument.get_id(), documentos);
            solicitudBO.guardarSolicitud(solicitudDocument);
            return true;
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    public void cancelarSolicitud() throws SolicitarBecaException {
        try {
            solicitudActual.setBeca(null);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }
}
