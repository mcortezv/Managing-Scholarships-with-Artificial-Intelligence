/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.servicesGobierno;
import datosGobierno.daoGobierno.BecaDAO;
import datosGobierno.daoGobierno.excepcionesGobierno.SolicitudDAOException;
import datosGobierno.dominioGobierno.Beca;
import datosGobierno.dominioGobierno.BecasFiltradas;
import gobierno.RequisitosDTOGobierno;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class BecasService {
    private final BecaDAO becaDAO;

    public BecasService() {
        this.becaDAO = new BecaDAO();
    }

    public BecasFiltradas obtenerBecas(RequisitosDTOGobierno requisitos) throws SolicitudDAOException {
        List<Beca> becas = becaDAO.findByRequisitos(requisitos);
        return new BecasFiltradas(becas);
    }
}
