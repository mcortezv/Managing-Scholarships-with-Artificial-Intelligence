package presentacion;

import actividades.dao.interfaces.IEstudianteDAOAct;
import actividades.dao.interfaces.IInscripcionDAO;
import actividades.daos.EstudianteDAOAct;
import actividades.daos.InscripcionDAO;
import bo.apelarResultado.ApelacionBO;
import bo.solicitarBeca.*;
import controles.ControlGobierno;
import controles.*;
import fachadas.*;
import interfaces.actividades.IActividadBO;
import interfaces.apelarResultado.IApelacionBO;
import interfaces.pagarAdeudo.IAdeudoBO;
import interfaces.solicitarBeca.*;
import bo.pagarAdeudo.AdeudoBO;
import objetosNegocio.actividades.ActividadBO;
import presentacion.actividadesExtracurriculares.coordinador.CoordinadorAplicacionActividades;
import presentacion.apelarResultado.ApelarResultado;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.CoordinadorAplicacionApelarResultado;
import presentacion.apelarResultado.coordinadorAplicacionApelarResultado.ICoordinadorAplicacionApelarResultado;
import presentacion.apelarResultado.coordinadorNegocioApelarResultado.CoordinadorNegocioApelarResultado;
import presentacion.apelarResultado.coordinadorNegocioApelarResultado.ICoordinadorNegocioApelarResultado;
import presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo.CoordinadorAplicacionPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo.CoordinadorNegocioPagarAdeudo;
import presentacion.pagarAdeudo.coordinadorNegocioPagarAdeudo.ICoordinadorNegocioPagarAdeudo;
import presentacion.interfaces.ICoordinadorNegocio;
import fachadas.FachadaGobierno;
import interfaces.*;
import interfaces.actividades.IEstudianteBOAct;
import interfaces.actividades.IGrupoBO;
import interfaces.actividades.IInscripcionBO;
import objetosNegocio.actividades.EstudianteBOAct;
import objetosNegocio.actividades.GrupoBO;
import objetosNegocio.actividades.InscripcionBO;
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

        //CASO PAGAR ADEUDO
        IAdeudoBO adeudoBO = new AdeudoBO(fachadaITSON);
        IFachadaPago fachadaPago = new FachadaPago(new ControlPago(adeudoBO, fachadaBanco, fachadaPayPal));
        ICoordinadorNegocioPagarAdeudo coordinadorNegocioPagarAdeudo = new CoordinadorNegocioPagarAdeudo(fachadaPago);




        //ACT EXTRA
        IActividadBO actividadBO = new ActividadBO(fachadaITSON);
        IGrupoBO grupoBO= new GrupoBO(fachadaITSON);
        IEstudianteDAOAct estudianteDAOAct= new EstudianteDAOAct();
        IEstudianteBOAct estudianteBOAct= new EstudianteBOAct(fachadaITSON, estudianteDAOAct);
        IInscripcionDAO inscripcionDAO= new InscripcionDAO();
        IInscripcionBO inscripcionBO= new InscripcionBO(fachadaITSON, inscripcionDAO);
        ControlActividad controlActividad= new ControlActividad(actividadBO, grupoBO, inscripcionBO, estudianteBOAct);
        IFachadaActividad fachadaAct = new FachadaActividad(controlActividad);

        ISolicitudDAO solicitudDAO = new SolicitudDAO();
        IEstudianteDAO estudianteDAO = new EstudianteDAO();
        IDocumentoDAO documentoDAO = new DocumentoDAO();

        IBecasFiltradasBO becasFiltradasBO = new BecasFiltradasBO(fachadaGobierno);
        IDocumentoBO documentoBO = new DocumentoBO(documentoDAO);
        IEstudianteBO estudianteBO = new EstudianteBO(fachadaITSON, estudianteDAO);
        IHistorialAcademicoBO historialAcademicoBO = new HistorialAcademicoBO(fachadaITSON);
        IInformacionSocioeconomicaBO infoSocioBO = new InformacionSocioeconomicaBO();
        ISolicitudBO solicitudBO = new SolicitudBO(fachadaGobierno, solicitudDAO);
        ITutorBO tutorBO = new TutorBO();

        IFachadaInicioSesion fachadaInicioSesion = new FachadaInicioSesion(new ControlInicioSesion(estudianteBO));
        IFachadaSolicitarBeca fachadaSolicitarBeca = new FachadaSolicitarBeca(new ControlSolicitarBeca(solicitudBO, estudianteBO, tutorBO, becasFiltradasBO, documentoBO, historialAcademicoBO, infoSocioBO));


        //cordinadores generales
        ICoordinadorNegocio coordinadorNegocioGeneral = new CoordinadorNegocio(fachadaInicioSesion, fachadaSolicitarBeca);
        CoordinadorAplicacion coordinadorAplicacion = new CoordinadorAplicacion(coordinadorNegocioGeneral);

        //pagar adeudo
        CoordinadorAplicacionPagarAdeudo coordinadorAplicacionPagarAdeudo = new CoordinadorAplicacionPagarAdeudo(coordinadorNegocioPagarAdeudo, coordinadorAplicacion);

        //apelar resultado
        IApelacionBO apelacionBO = new ApelacionBO(fachadaGobierno);
        IFachadaApelacion iFachadaApelacion = new FachadaApelacion(new ControlApelacion(solicitudBO,apelacionBO));
        ICoordinadorNegocioApelarResultado iCoordinadorNegocioApelarResultado = new CoordinadorNegocioApelarResultado(iFachadaApelacion);
        CoordinadorAplicacionApelarResultado coordinadorAplicacionApelarResultad = new CoordinadorAplicacionApelarResultado(iCoordinadorNegocioApelarResultado,coordinadorAplicacion);

        //actividades
        CoordinadorAplicacionActividades coordinadorAplicacionActividades = new CoordinadorAplicacionActividades(fachadaAct, coordinadorAplicacion);

        //---------------TUTORÍAS------------
        ControlTutorias controlTutorias = new ControlTutorias();
        IFachadaTutorias fachadaTutorias = new FachadaTutorias(controlTutorias);
        CoordinadorNegocioTutorias coordinadorNegocioTutorias = new CoordinadorNegocioTutorias(fachadaTutorias);


        CoordinadorAplicacionTutorias coordinadorAplicacionTutorias =
            new CoordinadorAplicacionTutorias(coordinadorAplicacion, coordinadorNegocioTutorias);


        coordinadorAplicacion.setCoordinadorAplicacionPagarAdeudo(coordinadorAplicacionPagarAdeudo);

        coordinadorAplicacion.setCoordinadorAplicacionApelarResultado(coordinadorAplicacionApelarResultad);

        coordinadorAplicacion.setCoordinadorAplicacionActividades(coordinadorAplicacionActividades);

        coordinadorAplicacion.setCoordinadorAplicacionTutorias(coordinadorAplicacionTutorias);

        coordinadorAplicacion.setCoordinadorAplicacionTutorias(coordinadorAplicacionTutorias);

        coordinadorAplicacion.iniciarGUI();
    }
}