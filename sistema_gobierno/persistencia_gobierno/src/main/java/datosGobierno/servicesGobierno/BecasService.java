/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.servicesGobierno;
import datosGobierno.adaptadoresGobierno.BecaAdaptador;
import datosGobierno.daoGobierno.BecaDAO;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.dominioGobierno.Beca;
import gobierno.BecaDTOGobierno;
import gobierno.BecasDisponiblesDTOGobierno;
import gobierno.RequisitosDTOGobierno;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Becas service.
 *
 * @author janethcristinagalvanquinonez
 */
public class BecasService {
    private final BecaDAO becaDAO;

    /**
     * Instantiates a new Becas service.
     */
    public BecasService() {
        this.becaDAO = new BecaDAO();
    }

    /**
     * Obtener becas becas disponibles dto gobierno.
     *
     * @param requisitos the requisitos
     * @return the becas disponibles dto gobierno
     * @throws SolicitudDAOException the solicitud dao exception
     */
    public BecasDisponiblesDTOGobierno obtenerBecas(RequisitosDTOGobierno requisitos) throws SolicitudDAOException {
        List<BecaDTOGobierno> becaDTOGobiernos = new ArrayList<>();
        List<Beca> becas = becaDAO.filtrarPorRequisitos(requisitos);
        for (Beca beca : becas) {
            becaDTOGobiernos.add(BecaAdaptador.toDTOGobierno(beca));
        }
        return new BecasDisponiblesDTOGobierno(becaDTOGobiernos);
    }
}
