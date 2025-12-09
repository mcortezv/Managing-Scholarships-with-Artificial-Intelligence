package controles;

import interfaces.apelarResultado.IApelacionBO;
import interfaces.solicitarBeca.ISolicitudBO;

public class ControlApelacion {
    private ISolicitudBO iSolicitudBO;
    private IApelacionBO iApelacionBO;

    public ControlApelacion(ISolicitudBO solicitudBO, IApelacionBO iApelacionBO){
        this.iSolicitudBO = solicitudBO;
        this.iApelacionBO = iApelacionBO;
    }
}
