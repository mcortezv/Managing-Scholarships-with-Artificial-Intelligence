package controles;

import dto.actividades.*;
import objetosNegocio.bo.actividades.interfaces.IActividadBO;
import objetosNegocio.bo.actividades.interfaces.IGrupoBO;
import objetosNegocio.bo.actividades.interfaces.IInscripcionBO;
//import interfaces.actividades.IInscripcionBO;

import java.util.Objects;
import objetosNegocio.bo.actividades.interfaces.IEstudianteBOAct;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class ControlActividad {

    private final IActividadBO actividadBO;
    private final IGrupoBO grupoBO;
    private final IInscripcionBO inscripcionBO;
    private final IEstudianteBOAct estudianteBO;

    public ControlActividad(IActividadBO actividadBO, IGrupoBO grupoBO, IInscripcionBO inscripcionBO, IEstudianteBOAct estudianteBO) {
        this.actividadBO = Objects.requireNonNull(actividadBO);
        this.grupoBO = Objects.requireNonNull(grupoBO);
        this.inscripcionBO = Objects.requireNonNull(inscripcionBO);
        this.estudianteBO = Objects.requireNonNull(estudianteBO);

    }

    public ActividadesDTO obtenerActividades() {
        return actividadBO.obtenerActividades();
    }

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO) {
        return grupoBO.obtenerGrupos(actividadDTO);
    }

    public InscripcionDTO inscribirActividad(InscripcionDTO inscripcionDTO) {
        EstudianteDTO estudianteDTO = new EstudianteDTO(inscripcionDTO.getMatriculaEstudiante());
        EstudianteDTO estudianteEncontradoDTO = estudianteBO.obtenerEstudiante(estudianteDTO);
        EstudianteDTO estudianteGuardar;
        if (estudianteEncontradoDTO == null) {
            estudianteGuardar = estudianteBO.guardarEstudiante(estudianteDTO);
        } else {
            estudianteGuardar = estudianteEncontradoDTO;
        }

        inscripcionDTO.setIdEstudiante(estudianteGuardar.getId());
        return inscripcionBO.inscribirActividad(inscripcionDTO);

    }

    public InscripcionesDTO obtenerInscripciones(EstudianteDTO estudianteDTO) {
        return inscripcionBO.obtenerInscripciones(estudianteDTO);
    }

    public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO) {
        return inscripcionBO.obtenerGrupoInscrito(inscripcionDTO);
    }
    
    public BajaDTO darBajaActividad(BajaDTO baja){
        BajaDTO bajaGuardada= inscripcionBO.darBajaActividad(baja);
        if(bajaGuardada != null){
             inscripcionBO.actualizarEstadoInscripcion(bajaGuardada.getIdInscripcion());
        }
        return bajaGuardada;
    }

}
