package bo.apelarResultado;

import dto.apelacionResultado.ApelacionDTO;
import interfaces.IFachadaGobierno;
import interfaces.IFachadaITSON;
import interfaces.apelarResultado.IApelacionBO;

public class ApelacionBO implements IApelacionBO {

    private final IFachadaGobierno fachadaGobierno;
    public ApelacionBO(IFachadaGobierno fachadaGobierno){
        this.fachadaGobierno = fachadaGobierno;
    }

    @Override
    public boolean registrarApelacion(ApelacionDTO apelacionDTO) {
        return fachadaGobierno.registrarApelacion(apelacionDTO);
    }
}
