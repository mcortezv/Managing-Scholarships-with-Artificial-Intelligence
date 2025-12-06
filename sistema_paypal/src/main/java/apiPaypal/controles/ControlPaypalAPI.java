package apiPaypal.controles;

import datos.servicePaypal.TransaccionService;
import datos.servicePaypal.UsuarioService;

public class ControlPaypalAPI {
    private UsuarioService usuarioService;
    private TransaccionService transaccionService;

    public ControlPaypalAPI(){
        this.usuarioService = new UsuarioService();
        this.transaccionService = new TransaccionService();
    }


}
