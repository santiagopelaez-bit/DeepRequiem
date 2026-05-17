package Main.view;

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
    private final WelcomePanel welcomePanel;
    private final InstructionsPanel instructionsPanel;
    private final PlayerNamePanel playerNamePanel;
    private final GamePanel gamePanel;
    private final GameOverPanel gameOverPanel;


    /**
     * ahora creamos un constructor que creara la ventana con sus distintos componentes
     */
    public GameWindow() {
        setTitle("Tesoros del Abismo");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        welcomePanel = new WelcomePanel();
        instructionsPanel = new InstructionsPanel();
        playerNamePanel = new PlayerNamePanel();
        gamePanel = new GamePanel();
        gameOverPanel = new GameOverPanel();

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

    public WelcomePanel getPanelBienvenida() {
        return welcomePanel;
    }

    public InstructionsPanel getPanelInstrucciones() {
        return instructionsPanel;
    }

    public PlayerNamePanel getPanelNombreJugador() {
        return playerNamePanel;
    }

    public GamePanel getPanelJuego() {
        return gamePanel;
    }

    public GameOverPanel getPanelGameOver() {
        return gameOverPanel;
    }
}