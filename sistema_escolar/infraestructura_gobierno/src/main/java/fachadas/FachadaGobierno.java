/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;
import controles.ControlGobierno;
import dto.apelacionResultado.ApelacionDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.EstudianteDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import gobierno.apelacion.ApelacionDTOGobierno;
import interfaces.IFachadaGobierno;
import java.util.List;

/**
 * The type Fachada gobierno.
 *
 * @author janethcristinagalvanquinonez
 */
public class FachadaGobierno implements IFachadaGobierno{
    private final ControlGobierno controlGobierno;

    /**
     * Instantiates a new Fachada gobierno.
     *
     * @param controlGobierno the control gobierno
     */
    public FachadaGobierno(ControlGobierno controlGobierno) {
        this.controlGobierno = controlGobierno;
    }

    @Override
    public BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitosDTO) {
        return controlGobierno.solicitarBecas(requisitosDTO);
    }

    @Override
    public boolean enviarSolicitud(SolicitudDTOGobierno solicitudDTO) {
        return controlGobierno.enviarSolicitud(solicitudDTO);
    }


    //apelacion resultados
    @Override
    public List<SolicitudDTOGobierno> obtenerSolicitudesPorEstudiante(EstudianteDTOGobierno estudianteDTO) {
        return controlGobierno.obtenerSolicitudesPorEstudiante(estudianteDTO);
    }

    @Override
    public boolean registrarApelacion(ApelacionDTOGobierno apelacionDTO) {
        return controlGobierno.registrarApelacion(apelacionDTO);
    }

}
