package Main.Model;

import java.awt.*;

/**
 * Entidad recolectable que suma puntos
 */
public class Treasure extends EntityGame{
    private final String type;
    private final int value;

    /**
     * Crea un tesoro segun su tipo perla o cofre
     * @param x posicion X
     * @param y posicion Y
     * @param image imagen del tesoro
     */
    public Treasure(String type, int x, int y, Image image) {
        super(x, y, 42, 42, 3, image);
        this.type = type;
        this.value = "chest".equals(type) ? 30 : 10;
    }
    /**
     * Metodo para actualizar la posicion del objeto
     */
    @Override
    public void update() {
        y -= getSpeed();
    }

    /**
     * Dibuja el tesoro
     * @param g usamos los Graphics para dibujar la entidad en caso de la imagen no este vacia
     */
    public void dibujar(Graphics g){
        if(image != null){
            g.drawImage(image, x, y, getWidth(), getHeight(), null);
        }
    }

    /**
     * Getters
     * @return retorna el valor de la variable
     */
    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
