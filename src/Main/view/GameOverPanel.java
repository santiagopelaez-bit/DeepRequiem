package Main.view;

import javax.swing.*;
import java.awt.*;

/***
 * Creamos una clase para la pantalla de game over
 */
public class GameOverPanel extends JPanel {

    private final JButton volverainicio;
    private final JLabel titulo;
    private final Image fondo;

    /**
     * Constructor del panel que contendra la fuente pixeleada y el boton
     */
    public GameOverPanel() {

        setLayout(new BorderLayout());

        fondo = new ImageIcon(getClass().getResource("BackGroundMenu.png")).getImage();


        Font pixelFont = new Font("Monospaced", Font.BOLD, 28);
        titulo = new JLabel("GAME OVER", JLabel.CENTER);
        titulo.setFont(pixelFont);
        titulo.setForeground(Color.WHITE);

        add(titulo, BorderLayout.CENTER);
        volverainicio = new JButton("Volver al inicio");
        volverainicio.setFont(new Font("Monospaced", Font.BOLD, 18));

        add(volverainicio, BorderLayout.SOUTH);
    }

    /**
     * Dibujamos el fondo
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (fondo != null) {

            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);

        } else {

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public JButton getVolverainicio() {
        return volverainicio;
    }
}