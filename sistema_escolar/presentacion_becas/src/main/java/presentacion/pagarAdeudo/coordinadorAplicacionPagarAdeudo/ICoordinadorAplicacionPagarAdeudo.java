package presentacion.pagarAdeudo.coordinadorAplicacionPagarAdeudo;

import dto.pagarAdeudo.ClaseDTO;
import dto.pagarAdeudo.PrestamoDTO;
import presentacion.pagarAdeudo.PagarAdeudo;
import solicitarBeca.EstudianteDTO;

public interface ICoordinadorAplicacionPagarAdeudo {
    void pagarAdeudo();
    void regresarAlMenuPrincipal();
    void seleccionarAdeudoBiblioteca(EstudianteDTO estudianteDTO);
    void seleccionarAdeudoColegiatura(EstudianteDTO estudianteDTO);
    void irADetallePrestamo(PrestamoDTO prestamoSeleccionado);
    void irADetalleClase(ClaseDTO claseSeleccionada);
    void seleccionarRealizarPago();
    void seleccionarMetodoPago(String metodoPago);
    void setPagarAdeudo(PagarAdeudo pagarAdeudo);
    void setTipoAdeudo(String tipo);
    String getTipoAdeudo();
}