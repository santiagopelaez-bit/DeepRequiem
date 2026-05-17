package Main.view;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
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