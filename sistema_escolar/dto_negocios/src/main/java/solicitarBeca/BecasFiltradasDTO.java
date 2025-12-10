/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solicitarBeca;
import java.util.List;

/**
 * The type Becas filtradas dto.
 *
 * @author Cortez, Manuel;
 */
public class BecasFiltradasDTO {
    private List<BecaDTO> becas;

    /**
     * Instantiates a new Becas filtradas dto.
     */
    public BecasFiltradasDTO() {}

    /**
     * Instantiates a new Becas filtradas dto.
     *
     * @param becas the becas
     */
    public BecasFiltradasDTO(List<BecaDTO> becas) {
        this.becas = becas;
    }

    /**
     * Gets becas.
     *
     * @return the becas
     */
    public List<BecaDTO> getBecas() {
        return becas;
    }

    /**
     * Sets becas.
     *
     * @param becas the becas
     */
    public void setBecas(List<BecaDTO> becas) {
        this.becas = becas;
    }
}
