package controles;

import dto.actividades.*;
import excepciones.ActividadesException;
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
        try {
            ActividadesDTO actividadesDTO = actividadBO.obtenerActividades();
            if (actividadesDTO == null || actividadesDTO.getActividades() == null || actividadesDTO.getActividades().isEmpty()) {
                throw new ActividadesException("No hay existencia de actividades");
            }
            return actividadesDTO;
        } catch (Exception ex) {
            throw new ActividadesException(ex.getMessage());
        }
    }

    public GruposResponseDTO obtenerGrupos(ActividadDTO actividadDTO) {
        try {
            GruposResponseDTO gruposDTO = grupoBO.obtenerGrupos(actividadDTO);
            if (gruposDTO == null || gruposDTO.getGrupos() == null || gruposDTO.getGrupos().isEmpty()) {
                throw new ActividadesException("no hay grupos abiertos para esta actividad");
            }
            return gruposDTO;
            } catch(ActividadesException e){
                    throw e;
                    

        } catch (Exception ex) {
            throw new ActividadesException("Error al obtener grupos" + ex.getMessage());
        }
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
        try {
            return inscripcionBO.obtenerInscripciones(estudianteDTO);
        } catch (Exception ex) {
            throw new ActividadesException("Ha ocurrido un error al cargar tus inscripciones");
        }

    }

    public GrupoDTO obtenerGrupoInscrito(InscripcionDTO inscripcionDTO) {
        try {
            return inscripcionBO.obtenerGrupoInscrito(inscripcionDTO);
        } catch (Exception ex) {
            throw new ActividadesException(("Error al obtener grupo"));
        }
    }

    public BajaDTO darBajaActividad(BajaDTO baja) {
        try {
            BajaDTO bajaGuardada = inscripcionBO.darBajaActividad(baja);
            if (bajaGuardada != null) {
                inscripcionBO.actualizarEstadoInscripcion(bajaGuardada.getIdInscripcion());
            } else {
                throw new ActividadesException(("Error al procesar la baja"));
            }
            return bajaGuardada;
        } catch (Exception ex) {
            throw new ActividadesException(ex.getMessage());
        }

    }
}
