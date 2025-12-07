package dto.tutorias;

/**
 *
 * @author katia
 */
public class TutorDTO {
    private Long id;
    private String nombre;
    private String carrera;
    private String cubiculo;
    private String enlace;

    public TutorDTO() {
    }

    public TutorDTO(Long id, String nombre, String carrera, String cubiculo, String enlace) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.cubiculo = cubiculo;
        this.enlace = enlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(String cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
