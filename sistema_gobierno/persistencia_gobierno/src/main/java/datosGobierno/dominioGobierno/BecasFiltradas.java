/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;
import java.util.List;

/**
 *
 * @author Cortez, Manuel;
 */
public class BecasFiltradas {
    private List<Beca> becas;

    public BecasFiltradas() {}

    public BecasFiltradas(List<Beca> becas) {
        this.becas = becas;
    }

    public List<Beca> getBecas() {
        return becas;
    }

    public void setBecas(List<Beca> becas) {
        this.becas = becas;
    }
}
