/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;
import controles.ControlGobierno;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import gobierno.SolicitudDTOGobierno;
import interfaces.IFachadaGobierno;

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
}
