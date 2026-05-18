package Main.Model;

/**
 * Modelo principal que contiene todo el funcionamiento del juego
 */
public class Game {
    public static final int PANEL_WIDTH = 900;
    public static final int PANEL_HEIGHT = 650;

    private Diver diver;
    private int time;
    private int deep;
    private int level;
    private int ticks;
    private boolean end;

    public void startGame(){
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
    }
}
