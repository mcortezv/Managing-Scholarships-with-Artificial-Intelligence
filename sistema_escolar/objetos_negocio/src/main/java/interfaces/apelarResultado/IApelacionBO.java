package interfaces.apelarResultado;

import dto.apelacionResultado.ApelacionDTO;

public interface IApelacionBO {
    boolean registrarApelacion(ApelacionDTO apelacionDTO);
}
