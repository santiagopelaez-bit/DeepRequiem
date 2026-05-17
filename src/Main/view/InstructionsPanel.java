package Main.view;

import Main.Util.ChargerResources;

import javax.swing.*;
import java.awt.*;

/**
 * creamos una clase que contendrá las instrucciones del juego
 */
public class InstructionsPanel extends JPanel {

    //ChargerResources  chargerResources;

    //Font text = new Font(ChargerResources.)

    /**
     * creamos un boton para volver
     */
    private final JButton botonvolver;

    public InstructionsPanel() {
        setLayout(new BorderLayout());
        //el fondo se cambiara despues

        JLabel titulo = new JLabel("Instrucciones del juego", JLabel.CENTER);
        //luego se añadira una fuente de texto
        add(titulo, BorderLayout.NORTH);

        JPanel reglas = new JPanel(new GridLayout(7, 1, 6, 6));
    }
}
