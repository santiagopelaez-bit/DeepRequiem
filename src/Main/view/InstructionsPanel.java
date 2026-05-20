package Main.view;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que contiene las instrucciones del juego
 */
public class InstructionsPanel extends JPanel {

    /**
     * creamos el boton de volver y el estilo de letra pixeleado
     */
    private final JButton botonVolver;
    private final Font pixelTitle;
    private final Font pixelText;

    /**
     * en este constructor crearemos la fuente pixeleada
     * el titulo que llevara esa seccion
     * y las instrucciones del juego
     * y por ultimo el boton para poder volver al menu de inicio
     */
    public InstructionsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(12, 87, 132));
        pixelTitle = new Font("Monospaced", Font.BOLD, 34);
        pixelText = new Font("Monospaced", Font.BOLD, 20);

        JLabel titulo = new JLabel("INSTRUCCIONES", JLabel.CENTER);
        titulo.setFont(pixelTitle);
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        JPanel reglas = new JPanel(new GridLayout(7, 1, 6, 6));
        reglas.setOpaque(false);
        reglas.add(etiqueta("- MOVER AL BUZO CON FLECHAS"));
        reglas.add(etiqueta("- RECOGER PERLAS Y COFRES"));
        reglas.add(etiqueta("- EVITAR MEDUSAS Y PECES GLOBO"));
        reglas.add(etiqueta("- RECOGER OXIGENO PARA CURARSE"));
        reglas.add(etiqueta("- LA PROFUNDIDAD AUMENTA"));
        reglas.add(etiqueta("- CADA 100M SUBE EL NIVEL"));
        reglas.add(etiqueta("- SI LAS VIDAS LLEGAN A 0 PIERDES"));
        add(reglas, BorderLayout.CENTER);

        botonVolver = new JButton("VOLVER");
        botonVolver.setFont(pixelText);
        botonVolver.setFocusPainted(false);
        botonVolver.setBackground(Color.DARK_GRAY);
        botonVolver.setForeground(Color.WHITE);
        add(botonVolver, BorderLayout.SOUTH);
    }

    /**
     * Metodo para crear etiquetas estilizadas
     */
    private JLabel etiqueta(String texto) {
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(pixelText);
        return label;
    }

    /**
     * Getter del boton para volver
     */
    public JButton getBotonVolver() {
        return botonVolver;
    }
}