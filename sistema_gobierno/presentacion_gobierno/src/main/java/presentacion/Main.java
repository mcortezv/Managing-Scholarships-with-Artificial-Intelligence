/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package presentacion;
import controles.ControlEvaluarSolicitudes;
import controles.ControlModeloML;
import controles.ControlModificarResolucion;
import datosGobierno.daoGobierno.BecaDAO;
import datosGobierno.daoGobierno.ResolucionDAO;
import datosGobierno.daoGobierno.SolicitudDAO;
import datosGobierno.daoGobierno.interfacesGobierno.IBecaDAO;
import datosGobierno.daoGobierno.interfacesGobierno.IResolucionDAO;
import datosGobierno.daoGobierno.interfacesGobierno.ISolicitudDAO;
import fachadas.FachadaEvaluarSolicitudes;
import fachadas.FachadaModeloML;
import fachadas.FachadaModificarResolucion;
import interfaces.IFachadaEvaluarSolicitudes;
import interfaces.IFachadaModeloML;
import interfaces.IFachadaModificarResolucion;
import objetosNegocioGobierno.bo.BecaBO;
import objetosNegocioGobierno.bo.ResolucionBO;
import objetosNegocioGobierno.bo.SolicitudBO;
import objetosNegocioGobierno.bo.interfaces.IBecaBO;
import objetosNegocioGobierno.bo.interfaces.IResolucionBO;
import objetosNegocioGobierno.bo.interfaces.ISolicitudBO;
import presentacion.coordinacion.CoordinadorAplicacion;
import presentacion.coordinacion.CoordinadorNegocio;

/**
 *
 * @author Cortez, Manuel;
 */
public class Main {

    public static void main(String[] args) {


        ControlModeloML controlModeloML = new ControlModeloML();
        IResolucionDAO resolucionDAO = new ResolucionDAO();
        ISolicitudDAO solicitudDAO = new SolicitudDAO();
        IBecaDAO becaDAO = new BecaDAO();
        IFachadaModeloML fachadaModeloML = new FachadaModeloML(controlModeloML);
        IResolucionBO resolucionBO = new ResolucionBO(resolucionDAO, fachadaModeloML);
        ISolicitudBO solicitudBO = new SolicitudBO(solicitudDAO);
        IBecaBO becaBO = new BecaBO(becaDAO);

        ControlModificarResolucion controlModificarResolucion = new ControlModificarResolucion(resolucionBO, solicitudBO);
        ControlEvaluarSolicitudes controlEvaluarSolicitudes = new ControlEvaluarSolicitudes(resolucionBO, solicitudBO, becaBO);
        IFachadaModificarResolucion fachadaModificarResolucion = new FachadaModificarResolucion(controlModificarResolucion);
        IFachadaEvaluarSolicitudes fachadaEvaluarSolicitudes = new FachadaEvaluarSolicitudes(controlEvaluarSolicitudes);

        CoordinadorNegocio coordinadorNegocio = new CoordinadorNegocio(fachadaEvaluarSolicitudes, fachadaModificarResolucion);
        CoordinadorAplicacion coordinadorAplicacion = new CoordinadorAplicacion(coordinadorNegocio);
    }
}
