/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.dominioGobierno;
import java.util.List;

/**
 * The type Becas filtradas.
 *
 * @author Cortez, Manuel;
 */
public class BecasFiltradas {
    private List<Beca> becas;

    /**
     * Instantiates a new Becas filtradas.
     */
    public BecasFiltradas() {}

    /**
     * Instantiates a new Becas filtradas.
     *
     * @param becas the becas
     */
    public BecasFiltradas(List<Beca> becas) {
        this.becas = becas;
    }

    /**
     * Gets becas.
     *
     * @return the becas
     */
    public List<Beca> getBecas() {
        return becas;
    }

    /**
     * Sets becas.
     *
     * @param becas the becas
     */
    public void setBecas(List<Beca> becas) {
        this.becas = becas;
    }
}
