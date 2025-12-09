package controles;
import excepciones.DocumentoInvalidoException;
import excepciones.SolicitarBecaException;
import objetosNegocio.bo.solicitarBeca.intefaces.*;
import solicitarBeca.*;
import java.util.List;
import java.util.Objects;

/**
 * The type Control solicitar beca.
 *
 * @author Cortez, Manuel;
 */
public class ControlSolicitarBeca {
    private final ISolicitudBO solicitudBO;
    private final IEstudianteBO estudianteBO;
    private final IBecasFiltradasBO becasFiltradasBO;
    private final IDocumentoBO documentoBO;
    private final IHistorialAcademicoBO historialBO;
    private BecasFiltradasDTO becasFiltradas;
    private BecaDTO becaActual;
    private EstudianteDTO estudiante;
    private SolicitudDTO solicitudActual;

    /**
     * Instantiates a new Control solicitar beca.
     *
     * @param solicitudBO  the solicitud bo
     * @param estudianteBO the estudiante bo
     * @param becaBO       the beca bo
     * @param documentoBO  the documento bo
     * @param historialBO  the historial bo
     */
    public ControlSolicitarBeca(ISolicitudBO solicitudBO,
            IEstudianteBO estudianteBO,
            IBecasFiltradasBO becaBO,
            IDocumentoBO documentoBO,
            IHistorialAcademicoBO historialBO) {
        this.solicitudBO = Objects.requireNonNull(solicitudBO);
        this.estudianteBO = Objects.requireNonNull(estudianteBO);
        this.becasFiltradasBO = Objects.requireNonNull(becaBO);
        this.documentoBO = Objects.requireNonNull(documentoBO);
        this.historialBO = Objects.requireNonNull(historialBO);
    }

    /**
     * Iniciar solicitud.
     *
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public void iniciarSolicitud() throws SolicitarBecaException {
        try {
            this.solicitudActual = solicitudBO.crearSolicitud(becaActual);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Sets beca actual.
     *
     * @param becaActual the beca actual
     */
    public void setBecaActual(BecaDTO becaActual) {
        this.becaActual = becaActual;
    }

    /**
     * Obtener becas filtradas becas filtradas dto.
     *
     * @param requisitosDTO the requisitos dto
     * @return the becas filtradas dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public BecasFiltradasDTO obtenerBecasFiltradas(RequisitosDTO requisitosDTO) throws SolicitarBecaException {
        try {
            this.becasFiltradas = becasFiltradasBO.obtenerBecasFiltradas(requisitosDTO);
            return becasFiltradas;
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Obtener beca por id beca dto.
     *
     * @param id the id
     * @return the beca dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public BecaDTO obtenerBecaPorId(Long id) throws SolicitarBecaException {
        try {
            becaActual = becasFiltradasBO.obtenerBecaPorCodigo(id, becasFiltradas);
            return becaActual;
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Obtener estudiante estudiante dto.
     *
     * @param id the id
     * @return the estudiante dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public EstudianteDTO obtenerEstudiante(Long id) throws SolicitarBecaException {
        try {
            estudiante = estudianteBO.crearEstudiante(id);
            return estudiante;
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Asignar historial.
     *
     * @param historialAcademicoDTO the historial academico dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public void asignarHistorial(HistorialAcademicoDTO historialAcademicoDTO) throws SolicitarBecaException {
        try {
            HistorialAcademicoDTO historialAcademico = historialBO.crearHistorial(historialAcademicoDTO.getMatriculaEstudiante());
            solicitudActual.setHistorialAcademico(historialAcademico);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Asignar tutor.
     *
     * @param tutorDTO the tutor dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public void asignarTutor(TutorDTO tutorDTO) throws SolicitarBecaException {
        try {
            if (tutorDTO.getNombre() == null || tutorDTO.getNombre().equals("")) {
            }
            estudiante.setTutor(tutorDTO);
            solicitudActual.setEstudiante(estudiante);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Sets informacion socioeconomica.
     *
     * @param infoSoDTO the info so dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public void setInformacionSocioeconomica(InformacionSocioeconomicaDTO infoSoDTO) throws SolicitarBecaException {
        try {
            if (infoSoDTO == null) {
                throw new SolicitarBecaException("No se han adjuntado documentos para asignar.");

            }
            solicitudActual.setInformacionSocioeconomica(infoSoDTO);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Asignar documentos.
     *
     * @param documentosDTO the documentos dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public void asignarDocumentos(List<DocumentoDTO> documentosDTO) throws SolicitarBecaException {
        if (documentosDTO == null || documentosDTO.isEmpty()) {
            throw new SolicitarBecaException("No se han adjuntado documentos para asignar.");
        }
        try {
            for (DocumentoDTO dto : documentosDTO) {
                // validarDocumento(dto);
            }
            solicitudActual.setDocumentos(documentosDTO);
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

    /**
     * Obtener solicitud actual solicitud dto.
     *
     * @return the solicitud dto
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public SolicitudDTO obtenerSolicitudActual() throws SolicitarBecaException {
        try {
            return solicitudActual;
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Guardar solicitud boolean.
     *
     * @return the boolean
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public boolean guardarSolicitud() throws SolicitarBecaException {
        try {
            solicitudBO.validarSolicitudCompleta(solicitudActual);
            solicitudBO.enviarSolicitud(solicitudActual);
            estudianteBO.guardarEstudiante(estudiante);
            for (DocumentoDTO documento : solicitudActual.getDocumentos()) {
                documentoBO.guardarDocumento(documento);
            }
            solicitudBO.guardarSolicitud(solicitudActual);
            return true;
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }

    /**
     * Cancelar solicitud.
     *
     * @throws SolicitarBecaException the solicitar beca exception
     */
    public void cancelarSolicitud() throws SolicitarBecaException {
        try {
            solicitudActual.setBeca(null);
        } catch (Exception ex) {
            throw new SolicitarBecaException(ex.getMessage());
        }
    }
}
