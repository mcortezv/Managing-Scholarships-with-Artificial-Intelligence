package bo.apelarResultado;

import interfaces.IFachadaGobierno;
import interfaces.IFachadaITSON;
import interfaces.apelarResultado.IApelacionBO;

public class ApelacionBO implements IApelacionBO {

    private final IFachadaGobierno fachadaGobierno;
    public ApelacionBO(IFachadaGobierno fachadaGobierno){
        this.fachadaGobierno = fachadaGobierno;
    }
}
