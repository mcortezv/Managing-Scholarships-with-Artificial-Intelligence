package datosGobierno.adaptadoresGobierno;
import datos.adaptadoresItson.excepciones.EstudianteAdaptadorException;
import datosGobierno.dominioGobierno.Estudiante;
import datosGobierno.dominioGobierno.enums.Carrera;
import dtoGobierno.EstudianteDTO;


public class EstudianteAdaptador {

    public static Estudiante toEntity(EstudianteDTO dto) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setCarrera(Carrera.valueOf(dto.getCarrera()));
            estudiante.setNombre(dto.getNombre());
            estudiante.setCorreo(dto.getCorreo());
            estudiante.setTelefono(dto.getTelefono());
            estudiante.setDireccion(dto.getDireccion());
            estudiante.setMatricula(dto.getMatricula());
            return estudiante;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir EstudianteDTO a Estudiante");
        }
    }

    public static EstudianteDTO toDTO(Estudiante estudiante) {
        try {
            EstudianteDTO dto = new EstudianteDTO();
            dto.setCarrera(estudiante.getCarrera().toString());
            dto.setNombre(estudiante.getNombre());
            dto.setCorreo(estudiante.getCorreo());
            dto.setTelefono(estudiante.getTelefono());
            dto.setDireccion(estudiante.getDireccion());
            dto.setMatricula(estudiante.getMatricula());
            return dto;
        } catch (Exception ex) {
            throw new EstudianteAdaptadorException("Error al convertir Estudiante a EstudianteDTO");
        }
    }
}
