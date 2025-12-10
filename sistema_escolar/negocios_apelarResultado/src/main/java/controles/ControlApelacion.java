package controles;

import objetosNegocio.bo.apelarResultado.interfaces.IApelacionBO;
import objetosNegocio.bo.solicitarBeca.intefaces.ISolicitudBO;

public class ControlApelacion {
    private ISolicitudBO iSolicitudBO;
    private IApelacionBO iApelacionBO;

    public ControlApelacion(ISolicitudBO solicitudBO, IApelacionBO iApelacionBO){
        this.iSolicitudBO = solicitudBO;
        this.iApelacionBO = iApelacionBO;
    }

}
