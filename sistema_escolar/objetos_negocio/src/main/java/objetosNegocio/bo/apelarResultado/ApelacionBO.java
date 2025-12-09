package objetosNegocio.bo.apelarResultado;

import interfaces.IFachadaGobierno;
import objetosNegocio.bo.apelarResultado.interfaces.IApelacionBO;

public class ApelacionBO implements IApelacionBO {

    private final IFachadaGobierno fachadaGobierno;
    public ApelacionBO(IFachadaGobierno fachadaGobierno){
        this.fachadaGobierno = fachadaGobierno;
    }
}
