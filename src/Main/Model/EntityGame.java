package Main.Model;

import java.awt.*;

/**
 * Clase abstracta para todas las entidades del juego
 */
public abstract class EntityGame {
    protected int x;
    protected int y;
    private int width;
    private int height;
    private int speed;
    protected Image image;

    /**
     * Crea una entidad con ancho, alto, velocidad e imagen
     */
    public EntityGame(int x, int y, int width, int height, int speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
    }

    /**
     * Metodo que detecta las colisiones de las entidades
     *
     * @return retorna la hitbox de la entidad
     */
    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Dibuja la entidad
     * @param g usamos los Graphics para dibujar la entidad en caso de la imagen no este vacia
     */
    public void dibujar(Graphics g){
        if(image != null){
            g.drawImage(image, x, y, width, height, null);
        }
    }

    /**
     * Actualiza el estado de la entidad del juego
     */
    public abstract void update();

    /**
     * Getters y setters
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

