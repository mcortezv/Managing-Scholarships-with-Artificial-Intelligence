package controles;
import datosGobierno.dominioGobierno.Resolucion;
import datosGobierno.dominioGobierno.Solicitud;
import datosGobierno.dominioGobierno.enums.EstadoSolicitud;
import dtoGobierno.BecaDTO;
import gobierno.ResolucionDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import dtoGobierno.ResolucionDTO;
import dtoGobierno.SolicitudDTO;
import objetosNegocioGobierno.adaptadores.ResolucionAdaptador;
import objetosNegocioGobierno.adaptadores.SolicitudAdaptador;
import objetosNegocioGobierno.bo.interfaces.IBecaBO;
import objetosNegocioGobierno.bo.interfaces.IResolucionBO;
import objetosNegocioGobierno.bo.interfaces.ISolicitudBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class ControlEvaluarSolicitudes {
    private final IResolucionBO resolucionBO;
    private final ISolicitudBO solicitudBO;
    private final IBecaBO becaBO;

    public List<BecaDTO> obtenerListadoBecas(){
        return becaBO.obtenerListadoBecas();
    }

    public ControlEvaluarSolicitudes(IResolucionBO resolucionBO, ISolicitudBO solicitudBO, IBecaBO becaBO) {
        this.resolucionBO = resolucionBO;
        this.solicitudBO = solicitudBO;
        this.becaBO = becaBO;
    }

    public void evaluarConvocatoria(int idConvocatoria){
        // No recuerdo para que lo cree
    }

    public ResolucionDTO evaluacionAutomatica(SolicitudDTO solicitudDTO){
        Solicitud solicitud = SolicitudAdaptador.toEntity(solicitudDTO);
        SolicitudDTOGobierno solicitudInfraestructuraDTO = SolicitudAdaptador.toInfraestructuraDTO(solicitud);
        ResolucionDTOGobierno resolucionInfraestructuraDTO = resolucionBO.crearResolucionAutomatica(solicitudInfraestructuraDTO);
        Resolucion resolucion = ResolucionAdaptador.toEntity(resolucionInfraestructuraDTO);
        return ResolucionAdaptador.toDTO(resolucion);
    }

    public boolean resolverSolicitudManual(ResolucionDTO resolucionDTO){
        if (cambiarEstadoSolicitud((int) resolucionDTO.getSolicitud().getId(), resolucionDTO.getSolicitud().getEstado())){
            return resolucionBO.resolver(ResolucionAdaptador.toEntity(resolucionDTO));
        }
        return false;
    }

    public boolean resolver(ResolucionDTO resolucionDTO){
        if (cambiarEstadoSolicitud((int) resolucionDTO.getSolicitud().getId(), resolucionDTO.getSolicitud().getEstado())){
            return resolucionBO.resolver(ResolucionAdaptador.toEntity(resolucionDTO));
        }
        return false;
    }

    public boolean cambiarEstadoSolicitud(int id, String nuevoEstado){
        return solicitudBO.cambiarEstado(id, EstadoSolicitud.valueOf(nuevoEstado));
    }

    public List<SolicitudDTO> obtenerSolicitudes(String tipoBeca){
        return solicitudBO.obtenerListadoSolicitudes(tipoBeca);
    }
}
