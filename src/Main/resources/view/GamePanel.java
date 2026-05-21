package Main.view;

import Main.Model.*;
import Main.resources.ChargerResource;

import javax.swing.*;
import java.awt.*;

/**
 * creamos el Panel principal del juego
 */
public class GamePanel extends JPanel {

    private Game game;

    /**
     * creamos el fondo y la fuente pixeleada
     */
    private final Image fondo;
    private final Font pixelFont;

    /**
     * Constructor que contendra la imagen y la fuente pixeleada
     */
    public GamePanel() {
        setFocusable(true);
        fondo = ChargerResource.chargeImage("/Main/resources/images/background/BackGroundGame.png");
        pixelFont = ChargerResource.pixelFont(18f);
    }

    /**
     * Asocia el modelo que debe dibujar
     */
    public void setGame(Game game) {

        this.game = game;

    }

    /**
     * Dibujamos todos los elementos del juego
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        dibujarFondo(g);

        if (game == null || game.getDiver() == null) {

            return;

        }

        dibujarHUD(g);

        for (Treasure treasure : game.getTreasures()) {

            treasure.dibujar(g);

        }

        for (PowerUp powerUp : game.getPowerUps()) {

            powerUp.dibujar(g);

        }

        for (Enemy enemy : game.getEnemies()) {

            enemy.dibujar(g);

        }

        game.getDiver().dibujar(g);

    }

    /**
     * Dibujamos el fondo
     */
    private void dibujarFondo(Graphics g) {

        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(5, 25, 45));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(20, 60, 100));

            for (int y = 0; y < getHeight(); y += 70) {
                g.drawLine(0, y, getWidth(), y + 30);
            }
        }
    }

    /**
     * se dibuja la parte de las vidas, puntaje, niveles y tiempo
     */
    private void dibujarHUD(Graphics g) {
        Diver diver = game.getDiver();
        g.setColor(new Color(0, 0, 0, 140));
        g.fillRect(0, 0, getWidth(), 40);
        g.setColor(Color.WHITE);
        g.setFont(pixelFont);

        g.drawString("NOMBRE: " + diver.getNamePlayer(), 15, 25);
        g.drawString("PUNTAJE: " + diver.getScore(), 190, 25);
        g.drawString("VIDAS: " + diver.getLifes(), 310, 25);
        g.drawString("NIVEL: " + game.getLevel(), 700, 25);
        g.drawString("PROFUNDIDAD: " + game.getDeep() + "m", 520, 25);
        g.drawString("TIEMPO: " + game.getTime() + "s", 400, 25);
    }
}
