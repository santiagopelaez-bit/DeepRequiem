package Main.resources.view;

import javax.swing.*;
import java.awt.*;

/**
 * creamos una clase que contendra todo lo necesario para mostrar la ventana del juego
 */
public class GameWindow extends JFrame {
    /**
     * creamos el texto de las distintas partes del juego como el game over, la bienvenida, nombres e instrucciones
     */
    public static final String BIENVENIDA = "bienvenida";
    public static final String INSTRUCCIONES = "instrucciones";
    public static final String NOMBRE = "nombre";
    public static final String JUEGO = "juego";
    public static final String GAMEOVER = "GameOver";

    private final CardLayout cardLayout;
    private final JPanel contenedor;
    private final Main.resources.view.WelcomePanel welcomePanel;
    private final Main.resources.view.InstructionsPanel instructionsPanel;
    private final Main.resources.view.PlayerNamePanel playerNamePanel;
    private final Main.resources.view.GamePanel gamePanel;
    private final Main.resources.view.GameOverPanel gameOverPanel;


    /**
     * ahora creamos un constructor que creara la ventana con sus distintos componentes
     */
    public GameWindow() {
        setTitle("DeepRequiem");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        welcomePanel = new Main.resources.view.WelcomePanel();
        instructionsPanel = new Main.resources.view.InstructionsPanel();
        playerNamePanel = new Main.resources.view.PlayerNamePanel();
        gamePanel = new Main.resources.view.GamePanel();
        gameOverPanel = new Main.resources.view.GameOverPanel();

        contenedor.add(welcomePanel, BIENVENIDA);
        contenedor.add(instructionsPanel, INSTRUCCIONES);
        contenedor.add(playerNamePanel, NOMBRE);
        contenedor.add(gamePanel, JUEGO);
        contenedor.add(gameOverPanel, GAMEOVER);
        add(contenedor);
    }

    /**
     * aca creamos otros constructores que nos ayudaran a mostrar la ventana del juego,
     * la ventana de bienvenida, las instrucciones, el nombre de los jugadores y la pantalla de gameover
     * @param nombrePanel
     */
    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(contenedor, nombrePanel);
    }

    public Main.resources.view.WelcomePanel getPanelBienvenida() {
        return welcomePanel;
    }

    public Main.resources.view.InstructionsPanel getPanelInstrucciones() {
        return instructionsPanel;
    }

    public Main.resources.view.PlayerNamePanel getPanelNombreJugador() {
        return playerNamePanel;
    }

    public Main.resources.view.GamePanel getPanelJuego() {
        return gamePanel;
    }

    public Main.resources.view.GameOverPanel getPanelGameOver() {
        return gameOverPanel;
    }
}