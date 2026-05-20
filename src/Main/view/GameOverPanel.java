package Main.view;

import Main.Model.PlayerRegister;
import Main.Model.Ranking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Pantalla de Game Over
 */
public class GameOverPanel extends JPanel {
    /**
     * creamos resumen, titulo, boton volverainicio, el panel del ranking y el fondo
     */
    private final JButton volverainicio;
    private final JLabel resumen;
    private final JLabel titulo;
    private final JPanel rankingPanel;
    private final Image fondo;

    /**
     * Constructor que añadira el fondo y la letra pixeleada
     */
    public GameOverPanel() {
        setLayout(new BorderLayout());
        fondo = new ImageIcon(getClass().getResource("/images/BackGroundMenu.png")).getImage();
        Font pixelTitle = new Font("Monospaced", Font.BOLD, 38);
        Font pixelText = new Font("Monospaced", Font.BOLD, 18);

        /**
         * Ponemos el titulo de game over
         */
        titulo = new JLabel("GAME OVER", JLabel.CENTER);
        titulo.setFont(pixelTitle);
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        /**
         * creamos el panel central
         */
        JPanel centro = new JPanel(new GridLayout(2, 1));
        centro.setOpaque(false);

        /**
         * con esto mostramos el resumen de la partida
         */
        resumen = new JLabel("", JLabel.CENTER);
        resumen.setForeground(Color.WHITE);
        resumen.setFont(pixelText);

        /**
         * creamos el panel con la informacion del ranking
         */
        rankingPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        rankingPanel.setOpaque(false);
        centro.add(resumen);
        centro.add(rankingPanel);
        add(centro, BorderLayout.CENTER);

        /**
         * boton para volver al inicio con la fuente pixeleada
         */
        volverainicio = new JButton("VOLVER AL INICIO");
        volverainicio.setFont(pixelText);
        volverainicio.setFocusPainted(false);
        add(volverainicio, BorderLayout.SOUTH);
    }

    /**
     * Actualiza los resultados finales
     */
    public void showResults(String nombre, int score, int time, int deep, Ranking ranking) {
        resumen.setText("Jugador: " + nombre + " | Puntaje: " + score + " | Tiempo: " + time + "s" + " | Profundidad: " + deep + "m");
        rankingPanel.removeAll();
        rankingPanel.add(etiqueta("TOP 3"));
        List<PlayerRegister> top = ranking.top();
        for (int i = 0; i < top.size(); i++) {
            PlayerRegister registro = top.get(i);
            rankingPanel.add(etiqueta((i + 1) + ". " + registro.getNombre() + " - " + registro.getPuntaje() + " puntos"));
        }
        while (rankingPanel.getComponentCount() < 4) {
            rankingPanel.add(etiqueta("-"));
        }
        revalidate();
        repaint();
    }

    /**
     * Con esto creamos las etiquetas del ranking
     */
    private JLabel etiqueta(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        return label;
    }

    /**
     * Se dibuja el fondo
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(8, 30, 55));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Getter del boton para volver al inicio
     */
    public JButton getVolverainicio() {
        return volverainicio;
    }
}