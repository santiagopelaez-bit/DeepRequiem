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
    private final List<Enemy> enemies;
    private final List<PowerUp> powerUps;
    private int time;
    private int deep;
    private int level;
    private int ticks;
    private boolean end;

    public Game(){
        this.random = new Random();
        this.treasures = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        restart();
    }

    /**
     * Metodo que inicia la logica del juego con los ppuntajes en 0
     * @param namePlayer recibe el nombre del jugador para en caso de estar vacio poner por defecto PLAYER
     */
    public void startGame(String namePlayer){
        String name = namePlayer == null || namePlayer.trim().isEmpty() ? "PLAYER" : namePlayer.trim();
        Image image = ChargerResource.chargeImage("/images/diver.png");
        diver = new Diver(name, PANEL_WIDTH / 2 - 29, PANEL_HEIGHT - 120, image);
        treasures.clear();
        enemies.clear();
        powerUps.clear();
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
        checkCollisions();
        updateEntity();
        cleanObjects();
    }

    /**
     * Metodo para generar los objetos
     */
    public void generateObjects(){
        // Ajustes de dificultad
        if(ticks % 45 == 0){
            String type = random.nextBoolean() ? "jellyfish" : "fish";
            Image image = ChargerResource.chargeImage("/images/" + type + ".png");
            enemies.add(new Enemy(type, randomX(52), PANEL_HEIGHT + 20, 2+ level, image));
        }

        if(ticks % 40 == 0){
            String type = random.nextInt(4) == 0 ? "chest" : "pearl";
            Image image = ChargerResource.chargeImage("/images/" + type + ".png");
            treasures.add(new Treasure(type, randomX(42), PANEL_HEIGHT+ 20, image ) );
        }

        if(ticks % 6000 == 0){
            Image image = ChargerResource.chargeImage("/images/powerup.png" );
            powerUps.add(new PowerUp("Oxygen", randomX(40), PANEL_HEIGHT + 20, image));
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

        Iterator<Enemy> iEnemy = enemies.iterator();
        while(iEnemy.hasNext()){
            Enemy enemy = iEnemy.next();
            if(diver.getBound().intersects(enemy.getBound())){
                diver.loseLife(enemy.getDamage());
                iEnemy.remove();
            }
        }

        Iterator<PowerUp> iPowerUP = powerUps.iterator();
        while (iPowerUP.hasNext()){
            PowerUp powerUp = iPowerUP.next();
            if(diver.getBound().intersects(powerUp.getBound())){
                powerUp.apply(diver);
                iPowerUP.remove();
            }
        }
    }

    /**
     * Metodo para reiniciar el juego sin salirse
     */
    public void restart(){
        diver = null;
        treasures.clear();
        enemies.clear();
        powerUps.clear();
        time = 0;
        deep = 0;
        level = 1;
        ticks = 0;
        end = false;
    }

    /**
     * Metodo para actualizar las entidades del juego
     */
    public void updateEntity(){
        for(Treasure treasure : treasures){
            treasure.update();
        }
        for(Enemy enemy : enemies){
            enemy.setSpeed(2 + level);
            enemy.update();
        }
        for (PowerUp powerUp : powerUps){
            powerUp.update();
        }
    }

    /**
     * Metodo para limpiar objetos cuando salgan de pantalla
      */
    private void cleanObjects(){
        treasures.removeIf(treasure -> treasure.getY() + treasure.getHeight() < 40);
        enemies.removeIf(enemy -> enemy.getY() + enemy.getHeight() < 40);
        powerUps.removeIf(powerUp -> powerUp.getY() + powerUp.getHeight() < 40);
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

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
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
