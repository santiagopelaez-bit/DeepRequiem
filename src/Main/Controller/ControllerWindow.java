package Main.Controller;

import Main.Model.Game;
import Main.Model.Ranking;
import Main.resources.ReproduceSound;
import Main.view.GameWindow;
import Main.view.WelcomePanel;

public class ControllerWindow {

    private final GameWindow gameWindow;
    private final Game juego;
    private final ControllerGame controllerGaming;
    private final ReproduceSound reproduceSound;

    /**
     * Registramos eventos de los botones y crea el modelo compartido
     */

    public ControllerWindow(WelcomePanel welcome) {

        this.gameWindow = new GameWindow();
        this.juego = new Game(new Ranking());
        this.controllerGaming = new ControllerGame(juego, gameWindow);
        this.reproduceSound = new ReproduceSound();

        registerEvents();

        gameWindow.mostrarPanel(GameWindow.BIENVENIDA);

        reproduceSound.reproduce("src/Main/resources/sounds/SoundTrack.mp3");

    }

    private void registerEvents() {

        gameWindow.getPanelNombreJugador().getBotonEmpezar().addActionListener(e -> {

            gameWindow.getPanelNombreJugador().limpiar();

            gameWindow.mostrarPanel(GameWindow.NOMBRE);

            gameWindow.getPanelNombreJugador().getCampoNombre().requestFocusInWindow();

        });

        gameWindow.getPanelBienvenida().getInstrucciones().addActionListener(e -> gameWindow.mostrarPanel(GameWindow.INSTRUCCIONES));

        gameWindow.getPanelInstrucciones().getBotonVolver()
                .addActionListener(e -> gameWindow.mostrarPanel(GameWindow.BIENVENIDA));

        gameWindow.getPanelNombreJugador().getBotonEmpezar().addActionListener(e -> startGame());

        gameWindow.getPanelNombreJugador().getCampoNombre().addActionListener(e -> startGame());

        gameWindow.getPanelGameOver().getVolverainicio().addActionListener(e -> {

            controllerGaming.detener();
            juego.restart();
            gameWindow.mostrarPanel(GameWindow.BIENVENIDA);

        });

    }

    private void startGame() {

        reproduceSound.detener();
        controllerGaming.start(gameWindow.getPanelNombreJugador().getNombreJugador());
        gameWindow.mostrarPanel(GameWindow.JUEGO);
        gameWindow.getPanelJuego().requestFocusInWindow();

    }


}