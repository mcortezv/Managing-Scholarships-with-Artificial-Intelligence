/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.styles;
import presentacion.coordinacion.MainFrame;
import javax.swing.*;

/**
 * The type Dialog.
 *
 * @author Cortez, Manuel;
 */
public abstract class Dialog extends JDialog {
    /**
     * The Center panel.
     */
    protected JPanel centerPanel;
    /**
     * The South panel.
     */
    protected JPanel southPanel;
    /**
     * The Main frame.
     */
    protected MainFrame mainFrame;

    /**
     * Instantiates a new Dialog.
     *
     * @param owner the owner
     * @param title the title
     * @param modal the modal
     */
    public Dialog(MainFrame owner, String title, boolean modal) {
        super();
        mainFrame = owner;
        centerPanel = new JPanel();
        southPanel = new JPanel();
    }
}