/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;
import controles.ControlGobierno;
import dto.apelacionResultado.ApelacionDTO;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import interfaces.IFachadaGobierno;
import solicitarBeca.EstudianteDTO;

import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class FachadaGobierno implements IFachadaGobierno{
    private final ControlGobierno controlGobierno;

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
    public List<SolicitudDTOGobierno> obtenerSolicitudesPorEstudiante(EstudianteDTO estudianteDTO) {
        return controlGobierno.obtenerSolicitudesPorEstudiante(estudianteDTO);
    }

    @Override
    public boolean registrarApelacion(ApelacionDTO apelacionDTO) {
        return controlGobierno.registrarApelacion(apelacionDTO);
    }

}
