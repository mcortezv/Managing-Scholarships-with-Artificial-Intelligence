package gobierno;
import java.util.List;

/**
 * The type Becas disponibles dto gobierno.
 *
 * @author Escalante, Sebastian.
 */
public class BecasDisponiblesDTOGobierno {
    /**
     * The Becas.
     */
    public List<BecaDTOGobierno> becas;

    /**
     * Instantiates a new Becas disponibles dto gobierno.
     */
    public BecasDisponiblesDTOGobierno(){}

    /**
     * Instantiates a new Becas disponibles dto gobierno.
     *
     * @param becas the becas
     */
    public BecasDisponiblesDTOGobierno(List<BecaDTOGobierno> becas) {
        this.becas = becas;
    }

    /**
     * Gets becas.
     *
     * @return the becas
     */
    public List<BecaDTOGobierno> getBecas() {
        return becas;
    }

    /**
     * Sets becas.
     *
     * @param becas the becas
     */
    public void setBecas(List<BecaDTOGobierno> becas) {
        this.becas = becas;
    }
}
