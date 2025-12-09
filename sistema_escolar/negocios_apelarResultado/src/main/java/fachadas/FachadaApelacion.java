package fachadas;

import controles.ControlApelacion;
import interfaces.IFachadaApelacion;

public class FachadaApelacion implements IFachadaApelacion {
    private final ControlApelacion controlApelacion;

    public FachadaApelacion(ControlApelacion controlApelacion) {
        this.controlApelacion = controlApelacion;
    }
}
