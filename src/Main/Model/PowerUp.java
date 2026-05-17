package Main.Model;

import java.awt.*;

/**
 * Entidad del juego que contendra los power ups del juego
 */
public class PowerUp extends EntityGame{
    private final String type;

    /**
     * Crea una entidad con ancho, alto, velocidad e imagen
     * @param type tipo de entidad
     * @param x posicion X
     * @param y posicion Y
     * @param image imagen del powerUP
     */
    public PowerUp(String type, int x, int y, Image image) {
        super(x, y, 40, 40, 3, image);
        this.type = type;
    }

    @Override
    public void update() {
        y -= getSpeed();
    }

    public void apply(Diver diver){
        if("oxigeno".equals(type)){
            diver.lifeRecover();
        }
    }

    /**
     * Metodo para dibujar el powerUP
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
}
