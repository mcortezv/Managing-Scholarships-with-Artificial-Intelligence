/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoGobierno;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class BecasFiltradasDTO {
    private List<BecaDTO> becas;

    public BecasFiltradasDTO() {}

    public BecasFiltradasDTO(List<BecaDTO> becas) {
        this.becas = becas;
    }

    public List<BecaDTO> getBecas() {
        return becas;
    }

    public void setBecas(List<BecaDTO> becas) {
        this.becas = becas;
    }
}
