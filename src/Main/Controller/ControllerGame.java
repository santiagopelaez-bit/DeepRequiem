package Main.Controller;

import Main.Model.Diver;
import Main.Model.Game;
import Main.resources.ChargerResource;
import Main.resources.ReproduceSound;
import Main.view.GameOverPanel;
import Main.view.GamePanel;
import Main.view.GameWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Conecta el model Juego con el panel de dibujo y el timer principal.
 */
public class ControllerGame {

    private final Game game;
    private final GamePanel gamePanel;
    private final GameOverPanel panelGameOver;
    private final GameWindow gameWindow;
    private final ControllerKeyboard controllerKeyboard;
    private final List<ControllerInput> inputs;
    private final ReproduceSound music;
    private final ReproduceSound effect;
    private Timer timer;
    private Thread threadAnimation;
    private volatile boolean animationActive;

    /**
     * Crea el controlador del ciclo del juego.
     */

    public ControllerGame(Game game, GameWindow gameWindow) {

        this.game = game;
        this.gameWindow = gameWindow;
        this.gamePanel = gameWindow.getPanelJuego();
        this.panelGameOver = gameWindow.getPanelGameOver();
        this.controllerKeyboard = new ControllerKeyboard();
        this.inputs = new ArrayList<>();
        this.music = new ReproduceSound();
        this.effect = new ReproduceSound();
        gamePanel.setGame(game);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(controllerKeyboard);
        addInput(controllerKeyboard);
        addInput(new ControllerJoystick());


    }

    /**
     * Agrega una entrada de movimiento al ciclo principal
     *
     * @param input teclado, joystick u otra entrada compleja
     */
    public void addInput(ControllerInput input) {

        if (input != null) {

            inputs.add(input);

        }

    }

    /**
     * Actualiza todas las entradas registradas antes de avanzar el modelo.
     */
    public void updateInputs() {

        for (ControllerInput input : inputs) {

            input.reloadMovement(game.getDiver());

        }

    }

    /**
     * Inicia timer, sonido y thread secundario de animación
     */
    public void start(String playerName) {

        detener();

        game.startGame(playerName);

        var sound = ChargerResource.chargeSound("/src/Main/resources/sounds/SoundTrack.mp3");

        if (sound != null) {

            music.reproduceLoop(sound.toString());

        }

        startThreadAnimation();

        timer = new Timer(16, e -> {

            updateInputs();

            game.update();

            gamePanel.repaint();

            if (game.isEnd()) {

                finishGame();

            }

        });

        timer.start();

        gamePanel.requestFocusInWindow();

    }

    /**
     * Detiene timer, musica e hilo secundario.
     */

    public void detener() {

        if (timer != null) {

            timer.stop();
            timer = null;

        }

        animationActive = false;

        if (threadAnimation != null) {

            threadAnimation.interrupt();

            threadAnimation = null;

        }

        music.detener();

    }

    private void finishGame() {

        detener();

        effect.reproduce(ChargerResource.chargeSound("src/Main/resources/sounds/GameOver.mp3").toString());

        Diver diver = game.getDiver();

        panelGameOver.showResults(
                diver.getNamePlayer(),
                diver.getScore(),
                diver.getTime(),
                diver.getDeep(),
                diver.getRanking());

        gameWindow.mostrarPanel(GameWindow.GAMEOVER);

    }

    private void startThreadAnimation() {

        animationActive = true;

        threadAnimation = new Thread(() -> {

            boolean visible = true;

            while (animationActive) {

                Diver diver = game.getDiver();

                if (diver != null && diver.isEfectoPowerActivo()) {

                    boolean state = visible;

                    SwingUtilities.invokeLater(() -> {

                        diver.setEfectoPowerActivo(state);

                        gamePanel.repaint();

                    });

                    visible = !visible;

                }

                try {

                    Thread.sleep(250);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    break;
                }

            }

        }, "AnimationDiverPowerUp");

        threadAnimation.start();

    }

}
