package Main.view;

import Main.Model.Ranking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/***
 * Creamos una clase para la pantalla de game over
 */
public class GameOverPanel extends JPanel {

    private final JButton volverainicio;
    private final JLabel titulo;
    private final Image fondo;

    /**
     * Constructor del panel que contendra la fuente pixeleada y el boton
     */
    public GameOverPanel() {

        setLayout(new BorderLayout());

        fondo = new ImageIcon(getClass().getResource("BackGroundMenu.png")).getImage();


        Font pixelFont = new Font("Monospaced", Font.BOLD, 28);
        titulo = new JLabel("GAME OVER", JLabel.CENTER);
        titulo.setFont(pixelFont);
        titulo.setForeground(Color.WHITE);

        add(titulo, BorderLayout.CENTER);
        volverainicio = new JButton("Volver al inicio");
        volverainicio.setFont(new Font("Monospaced", Font.BOLD, 18));

        add(volverainicio, BorderLayout.SOUTH);
    }

    /**
     * Actualiza datos finales de la partida
     */
    public void showResults(String nombre, int score, int time, int deep, Ranking ranking) {

        resumen.setText("Jugador: " + nombre + "\nPuntaje: " + score + "\nTiempo: " + time + "s\nProfundidad: " + deep + "m");

        RakingPanel.removeAll();

        RankingPanel.add(etiqueta("Ranking Top 3"));

        List<JugadorRegistro> top = ranking.getTop3();

        for (int i = 0; i < top.size(); i++) {

            JugadorRegistro registro = top.get(i);

            RankingPanel.add(etiqueta((i + 1) + ". " + registro.getNombre() + " - " + registro.getScore() + " puntos."));

        }

        while (RankingPanel.getComponentCount() < 4) {

            RankingPanel.add(etiqueta("-"));

        }

        revalidate();
        repaint();

    }

    private JLabel etiqueta(String text) {

        JLabel label = new JLabel(text, JLabel.CENTER);

        label.setForeground(Color.WHITE);
        label.setFont(new Font("", Font.PLAIN, 18));

        return label;

    }

    /**
     * Dibujamos el fondo
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (fondo != null) {

            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);

        } else {

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public JButton getVolverainicio() {
        return volverainicio;
    }


}