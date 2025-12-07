/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.actividades;

import actividades.dao.interfaces.IInscripcionDAO;
import actividades.dominio.Inscripcion;
import adaptadores.actividades.InscripcionAdaptador;
import dto.actividades.InscripcionDTO;
import interfaces.IFachadaITSON;
import interfaces.actividades.IInscripcionBO;
import itson.actividades.InscripcionDTOItson;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class InscripcionBO implements IInscripcionBO {

    private IFachadaITSON fachadaITSON;
    private final IInscripcionDAO inscripcionDAO;

    public InscripcionBO(IFachadaITSON fachadaITSON, IInscripcionDAO inscripcionDAO) {
        this.fachadaITSON = fachadaITSON;
        this.inscripcionDAO= inscripcionDAO;
    }

    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
            InscripcionDTOItson inscripcionDTOItson = InscripcionAdaptador.toDTOItson(inscripcionDTO);
            InscripcionDTOItson inscripcionDTOIResponse = fachadaITSON.inscribirActividad(inscripcionDTOItson);
            if(inscripcionDTOIResponse!=null){
                Inscripcion inscripcionGuardar= InscripcionAdaptador.toEntity(inscripcionDTO);
                Inscripcion inscripcionGuardada= inscripcionDAO.InscribirGrupo(inscripcionGuardar);
            }
            return InscripcionAdaptador.toDTONegocio(inscripcionDTOIResponse);

        }



}
