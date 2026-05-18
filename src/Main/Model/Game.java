package Main.Model;

import Main.resources.ChargerResource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Modelo principal que contiene todo el funcionamiento del juego
 */
public class Game {
    public static final int PANEL_WIDTH = 900;
    public static final int PANEL_HEIGHT = 650;

    private final Random random;
    private Diver diver;
    private final List<Treasure> treasures;
    private int time;
    private int deep;
    private int level;
    private int ticks;
    private boolean end;

    public Game(){
        this.random = new Random();
        this.treasures = new ArrayList<>();
        restart();
    }

    public void startGame(String namePlayer){
        String name = namePlayer == null || namePlayer.trim().isEmpty() ? "PLAYER" : namePlayer.trim();
        Image image = ChargerResource.chargeImage("/images/diver.png");
        diver = new Diver(name, PANEL_WIDTH / 2 - 29, PANEL_HEIGHT - 120, image);
        treasures.clear();
        time = 0;
        level  = 1;
        ticks = 0;
        end = false;
    }

    /**
     * Actualiza el estado del juego
     */
    public void update(){
        if(end || diver == null){
            return;
        }
        ticks++;
        if(ticks % 60 == 0){
            time++;
            deep += 5;
            level = deep/100 + 1;
        }

        generateObjects();
    }

    /**
     * Metodo para generar los objetos
     */
    public void generateObjects(){
        // Ajustes de dificultad
        if(ticks % 40 == 0){
            String type = random.nextInt(4) == 0 ? "jellyfish" : "pez_globo";
            Image image = ChargerResource.chargeImage("/images" + type + ".png");
            treasures.add(new Treasure(type, randomX(42), PANEL_HEIGHT+ 20, image ) );
        }
    }

    /**
     * Verifica las colisiones del juego
     */
    public void checkCollisions(){
        Iterator<Treasure> iTreasures = treasures.iterator();
        while (iTreasures.hasNext()){
            Treasure treasure = iTreasures.next();
            if(diver.getBound().intersects(treasure.getBound())){
                diver.sumPoints(treasure.getValue());
                iTreasures.remove();
            }
        }
    }

    /**
     * Metodo para actualizar las entidades del juego
     */
    public void updateEntity(){
        for(Treasure treasure : treasures){
            treasure.update();
        }
    }

    /**
     * Metodo para limpiar objetos cuando salgan de pantalla
      */
    private void cleanObjects(){
        treasures.removeIf(treasure -> treasure.getY() + treasure.getHeight() < 40);
    }

    /**
     * Metodo para reiniciar el juego sin salirse
     */
    public void restart(){
        diver = null;
        treasures.clear();
        time = 0;
        deep = 0;
        level = 1;
        ticks = 0;
        end = false;
    }

    /**
     * Metodo para generar numeros aleatorios en una posicion X
     * @param widthObject recibe el ancho del objeto
     * @return retorna la posicion aleatoria entre 0 y el ancho del panel menos el ancho del objeto
     */
    public int randomX(int widthObject){
        return random.nextInt(Math.max(1, PANEL_WIDTH - widthObject));
    }

    /**
     * Getters
     */
    public Diver getDiver() {
        return diver;
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public int getTime() {
        return time;
    }

    public int getDeep() {
        return deep;
    }

    public int getLevel() {
        return level;
    }

    public int getTicks() {
        return ticks;
    }

    public boolean isEnd() {
        return end;
    }
}
