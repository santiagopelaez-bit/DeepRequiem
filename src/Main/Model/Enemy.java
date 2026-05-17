package Main.Model;

import java.awt.*;

/**
 * Clase de los enemigos del juego
 */
public class Enemy extends EntityGame{
    private final String type;
    private final int damage;

    /**
     * Crea una entidad con ancho, alto, velocidad e imagen
     * @param x
     * @param y
     * @param speed
     * @param image
     */
    public Enemy(String type, int x, int y, int speed, Image image) {
        super(x, y, 52, 52, speed, image);
        this.type = type;
        this.damage = 1;
    }

    @Override
    public void update() {
        moveUpdate();
    }

    /**
     * Mueve el enemigo hacia arriba para simular que el buzo se sumerge
     */
    public void moveUpdate(){
        y -= getSpeed();
    }

    /**
     * Dibuja al enemigo del juego
     * @param g usamos los Graphics para dibujar la entidad en caso de la imagen no este vacia
     */
    public void dibujar(Graphics g){
        if(image != null){
            g.drawImage(image, x, y, getWidth(), getHeight(), null);
        }
    }

    /**
     * Getters
     */
    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }
}
