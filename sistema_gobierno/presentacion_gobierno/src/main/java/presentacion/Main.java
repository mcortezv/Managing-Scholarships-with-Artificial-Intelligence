/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package presentacion;
import presentacion.coordinacion.CoordinadorAplicacion;
import presentacion.coordinacion.MainFrame;

/**
 *
 * @author Cortez, Manuel;
 */
public class Main {

    public static void main(String[] args) {
        CoordinadorAplicacion coordinadorAplicacion = new CoordinadorAplicacion();
        MainFrame  mainFrame = new MainFrame(coordinadorAplicacion);
        mainFrame.setVisible(true);
    }
}
