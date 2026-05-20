package Main.Model;

import Main.resources.ChargerResource;

import java.awt.*;

/**
 * Clase del jugador del juego
 */
public class Diver extends EntityGame {
    private final static int MAX_LIFES = 5;
    private int lifes;
    private int score;
    private final String namePlayer;
    private boolean efectoPowerActivo;

    private Image diverUp;
    private Image diverDown;
    private Image diverRight;
    private Image diverLeft;

    private Image diverUpRight;
    private Image diverUpLeft;
    private Image diverDownLeft;
    private Image diverDownRight;

    private Image currentSprite;


    /**
     * Crea un jugador con 3 vidas y puntaje en cero
     *
     * @param namePlayer nombre del jugador
     * @param x          posicion X inicial
     * @param y          posicion Y inicial
     * @param image      imagen del jugador
     */
    public Diver(String namePlayer, int x, int y, Image image) {

        super(x, y, 58, 58, 6, image);
        this.namePlayer = namePlayer;
        this.lifes = 3;
        this.score = 0;

        diverDown = ChargerResource.chargeImage("/Main/resources/images/player/playerDown.png");
        diverUp = ChargerResource.chargeImage("/Main/resources/images/player/playerUp.png");
        diverLeft = ChargerResource.chargeImage("/Main/resources/images/player/playerLeft.png");
        diverRight = ChargerResource.chargeImage("/Main/resources/images/player/playerRight.png");
        diverDownLeft = ChargerResource.chargeImage("/Main/resources/images/player/playerDownLeft.png");
        diverDownRight = ChargerResource.chargeImage("/Main/resources/images/player/playerDownRight.png");
        diverUpLeft = ChargerResource.chargeImage("/Main/resources/images/player/playerUp_Left.png");
        diverUpRight = ChargerResource.chargeImage("/Main/resources/images/player/playerUp_Right.png");
        currentSprite = diverDown;


    }

    public Image getCurrentSprite() {

        return currentSprite;

    }

    public void lookRight() {

        currentSprite = diverRight;

    }

    public void lookLeft() {

        currentSprite = diverLeft;

    }

    public void lookDown() {

        currentSprite = diverDown;

    }

    public void lookUp() {

        currentSprite = diverUp;

    }

    public void lookUpRight() {

        currentSprite = diverUpRight;

    }

    public void lookUpLeft() {

        currentSprite = diverUpLeft;

    }

    public void lookDownLeft() {

        currentSprite = diverDownLeft;

    }

    public void lookDownRight() {

        currentSprite = diverDownRight;

    }


    public void move(int dx, int dy, int limiteAncho, int limiteAlto) {
        x = Math.max(0, Math.min(limiteAncho - getWidth(), x + dx));
        y = Math.max(40, Math.min(limiteAlto - getHeight(), y + dy));
    }

    /**
     * Metodo para sumar los puntos al jugador
     *
     * @param points puntos del juego
     */
    public void sumPoints(int points) {
        score += points;
    }

    /**
     * Metodo para que el jugador pierda vidas
     *
     * @param amount cantidad de vidas a perder
     */
    public void loseLife(int amount) {
        lifes = Math.max(0, lifes - amount);
    }


    public void lifeRecover() {
        lifes = Math.min(MAX_LIFES, lifes + 1);
        efectoPowerActivo = true;
    }

    @Override
    public void update() {
        // El movimiento se controla desde otra clase
    }

    /**
     * Dibuja al buzo del juego
     *
     * @param g usamos los Graphics para dibujar la entidad en caso de la imagen no este vacia
     */
    public void dibujar(Graphics g) {
        Image sprite = currentSprite != null ? currentSprite : image;

        if (sprite != null) {
            g.drawImage(sprite, x, y, getWidth(), getHeight(), null);
        }
    }

    /**
     * Getters y setters
     */
    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public boolean isEfectoPowerActivo() {
        return efectoPowerActivo;
    }

    public void setEfectoPowerActivo(boolean efectoPowerActivo) {
        this.efectoPowerActivo = efectoPowerActivo;
    }

}