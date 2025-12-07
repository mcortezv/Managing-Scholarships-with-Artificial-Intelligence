/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio.actividades;

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

    public InscripcionBO(IFachadaITSON fachadaITSON) {
        this.fachadaITSON = fachadaITSON;
    }

    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
        InscripcionDTOItson inscripcionDTOItson = InscripcionAdaptador.toDTOItson(inscripcionDTO);
        System.out.println(inscripcionDTOItson.getIdEstudiante());
        System.out.println(inscripcionDTOItson.getIdGrupo());
        System.out.println("hola");
        InscripcionDTOItson inscripcionDTOIResponse = fachadaITSON.inscribirActividad(inscripcionDTOItson);
        System.out.println(inscripcionDTOIResponse.getIdInscipcion());
        return InscripcionAdaptador.toDTONegocio(inscripcionDTOIResponse);

    }

    @Override
    public boolean buscarEstudiantePorMatricula(InscripcionDTO inscripcionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
