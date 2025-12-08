/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datosGobierno.daoGobierno.interfacesGobierno;
import datosGobierno.dominioGobierno.Beca;
import gobierno.RequisitosDTOGobierno;
import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public interface IBecaDAO {
    List<Beca> findByRequisitos(RequisitosDTOGobierno requisitos);

    List<Beca> obtenerBecasConSolicitudes();
}
