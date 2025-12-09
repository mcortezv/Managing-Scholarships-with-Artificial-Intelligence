/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.dominioGobierno.Beca;
import gobierno.RequisitosDTOGobierno;
import java.util.List;

/**
 * The interface Beca dao.
 *
 * @author janethcristinagalvanquinonez
 */
public interface IBecaDAO {
    /**
     * Filtrar por requisitos list.
     *
     * @param requisitos the requisitos
     * @return the list
     */
    List<Beca> filtrarPorRequisitos(RequisitosDTOGobierno requisitos);

    /**
     * Obtener becas con solicitudes list.
     *
     * @return the list
     */
    List<Beca> obtenerBecasConSolicitudes();
}
