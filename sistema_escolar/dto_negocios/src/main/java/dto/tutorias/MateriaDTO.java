package dto.tutorias;

/**
 *
 * @author katia
 */
public class MateriaDTO {
    private Long id;
    private String nombre;

    public MateriaDTO() {
    }

    public MateriaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return nombre;
    }

    
    
    
}
