package presentacion;
import bo.solicitarBeca.*;
import controles.ControlGobierno;
import controles.*;
import fachadas.*;
import interfaces.actividades.IActividadBO;
import interfaces.pagarAdeudo.IAdeudoBO;
import interfaces.solicitarBeca.*;
import bo.pagarAdeudo.AdeudoBO;
import objetosNegocio.actividades.ActividadBO;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import fachadas.FachadaGobierno;
import interfaces.*;
import presentacion.tutorias.coordinadorAplicacion.CoordinadorAplicacionTutorias;
import presentacion.tutorias.coordinadorNegocio.CoordinadorNegocioTutorias;
import solicitarBeca.repository.dao.interfaces.IDocumentoDAO;
import solicitarBeca.repository.dao.interfaces.IEstudianteDAO;
import solicitarBeca.repository.dao.interfaces.ISolicitudDAO;
import solicitarBeca.repository.dao.DocumentoDAO;
import solicitarBeca.repository.dao.EstudianteDAO;
import solicitarBeca.repository.dao.SolicitudDAO;

/**
 *
 * @author Cortez, Manuel;
 */
public class Main {

    public static void main(String[] args) {

        // Controles unicos para mantenerl el singleton
        ControlItson controlItson = new ControlItson();
        ControlGobierno controlGobierno = new ControlGobierno();
        ControlBanco controlBanco = new ControlBanco();
        ControlPayPal controlPayPal = new ControlPayPal();

        // creacion de fachadas
        IFachadaBanco fachadaBanco = new FachadaBanco(controlBanco);
        IFachadaPayPal fachadaPayPal = new FachadaPayPal(controlPayPal);
        IFachadaITSON fachadaITSON = new FachadaItson(controlItson);
        IFachadaGobierno fachadaGobierno = new FachadaGobierno(controlGobierno);
        //  Caso pagar Adeudo
        IAdeudoBO adeudoBO = new AdeudoBO(fachadaITSON);
        IFachadaPago fachadaPago = new FachadaPago(new ControlPago(adeudoBO, fachadaBanco, fachadaPayPal));
        // caso act extra
        IActividadBO actividadBO = new ActividadBO(fachadaITSON);
        IFachadaActividad fachadaAct = new FachadaActividad(new ControlActividad(actividadBO));

        // creacion de daos
        ISolicitudDAO solicitudDAO = new SolicitudDAO();
        IEstudianteDAO estudianteDAO = new EstudianteDAO();
        IDocumentoDAO documentoDAO = new DocumentoDAO();

        // creacion de las bo
        IBecasFiltradasBO becasFiltradasBO = new BecasFiltradasBO(fachadaGobierno);
        IDocumentoBO documentoBO = new DocumentoBO(documentoDAO);
        IEstudianteBO estudianteBO = new EstudianteBO(fachadaITSON, estudianteDAO);
        IHistorialAcademicoBO historialAcademicoBO = new HistorialAcademicoBO(fachadaITSON);
        IInformacionSocioeconomicaBO infoSocioBO = new InformacionSocioeconomicaBO();
        ISolicitudBO solicitudBO = new SolicitudBO(fachadaGobierno, solicitudDAO);
        ITutorBO tutorBO = new TutorBO();


        IFachadaInicioSesion fachadaInicioSesion = new FachadaInicioSesion(new ControlInicioSesion(estudianteBO));
        IFachadaSolicitarBeca fachadaSolicitarBeca = new FachadaSolicitarBeca(new ControlSolicitarBeca(solicitudBO, estudianteBO, tutorBO, becasFiltradasBO, documentoBO, historialAcademicoBO, infoSocioBO));

        
        //---------------TUTORÍAS------------
        ControlTutorias controlTutorias = new ControlTutorias();
        IFachadaTutorias fachadaTutorias = new FachadaTutorias(controlTutorias);
        CoordinadorNegocioTutorias coordinadorNegocioTutorias = new CoordinadorNegocioTutorias(fachadaTutorias);
        
        
        CoordinadorAplicacion coordinadorAplicacion =
                new CoordinadorAplicacion(fachadaInicioSesion, fachadaSolicitarBeca);

        CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo =
                new CoordinadorAplicacionPagarAdeudo(fachadaPago, coordinadorAplicacion);

        CoordinadorAplicacionActividades coordinadorAplicacionActividades =
                new CoordinadorAplicacionActividades(fachadaAct, coordinadorAplicacion);
        
        CoordinadorAplicacionTutorias coordinadorAplicacionTutorias = 
            new CoordinadorAplicacionTutorias(coordinadorAplicacion, coordinadorNegocioTutorias);
        
        coordinadorAplicacion.setCoordinadorAplicacionPagarAdeudo(coordinadorAplicacionPagarAdeudo);
        coordinadorAplicacion.setCoordinadorAplicacionActividades(coordinadorAplicacionActividades);
        coordinadorAplicacion.setCoordinadorAplicacionTutorias(coordinadorAplicacionTutorias);
        
        coordinadorAplicacion.iniciarGUI();
    }
}