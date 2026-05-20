package Main.view;

import Main.resources.ChargerResource;
import javax.swing.*;
import java.awt.*;

/**
 * creamos una clase que contendrá las instrucciones del juego
 */
public class InstructionsPanel extends JPanel {
    /**
     * creamos un boton para volver ademas de una fuente pixeleada para los distintos textos del juego
     */
    private final JButton botonvolver;
    private final Font pixelFont;

    public InstructionsPanel() {

        setLayout(new BorderLayout());
        setBackground(new Color(12, 87, 132));

        /**
         * cargamos la fuente
         */
        pixelFont = ChargerResource.chargeFont("/fonts/pixel.ttf");

        /*
         * Creamos el titulo del juego
         */
        JLabel titulo = new JLabel("Instrucciones del juego", JLabel.CENTER);

        if (pixelFont != null) {

            titulo.setFont(pixelFont.deriveFont(Font.BOLD, 34f));

        } else {

            titulo.setFont(new Font("Arial", Font.BOLD, 34));

        }

        titulo.setForeground(Color.WHITE);

        add(titulo, BorderLayout.NORTH);

        /*
         * Creamos las reglas del juego
         */
        JPanel reglas = new JPanel(new GridLayout(7, 1, 6, 6));

        reglas.setOpaque(false);

        reglas.add(createLabel("- Mover al buzo con flechas"));
        reglas.add(createLabel("- Recoger perlas y cofres para sumar puntos"));
        reglas.add(createLabel("- Evitar medusas y peces globo"));
        reglas.add(createLabel("- Recoger burbujas de oxigeno para recuperar vida"));
        reglas.add(createLabel("- La profundidad aumenta con el tiempo"));
        reglas.add(createLabel("- Cada 100 metros sube el nivel"));
        reglas.add(createLabel("- Si las vidas llegan a 0, termina la partida"));

        add(reglas, BorderLayout.CENTER);

        /*
         * Añadimos el boton volver
         */
        botonvolver = new JButton("Volver");

        if (pixelFont != null) {

            botonvolver.setFont(pixelFont.deriveFont(Font.PLAIN, 18f));

        }

        add(botonvolver, BorderLayout.SOUTH);

    }

    /**
     * Metodo para crear labels usando la fuente pixelada
     */
    private JLabel createLabel(String texto) {

        JLabel label = new JLabel(texto, JLabel.CENTER);

        label.setForeground(Color.WHITE);

        if (pixelFont != null) {

            label.setFont(pixelFont.deriveFont(Font.PLAIN, 20f));

        } else {

            label.setFont(new Font("Arial", Font.PLAIN, 20));

        }

        return label;

    }

    public JButton getBotonVolver() {

        return botonvolver;

    }

}
