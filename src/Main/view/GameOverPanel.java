package Main.view;

import javax.swing.*;
import java.awt.*;

/***
 * Creamos una clase para la pantalla de game over en caso de que el jugador pierda
 */
public class GameOverPanel extends JPanel {
    /**
     * creamos el boton de volver al menu de inicio, las imagenes de
     */
    private final JButton volverainicio;
  //  private final JPanel Ranking;
  //  private final JLabel resumen;
    private final Image fondo;

    /**
     * esto es para cargar el fondo y los componentes de resources
     */
    public GameOverPanel() {
        setLayout(new BorderLayout());
        fondo = new ImageIcon(getClass().getResource("NombreTemporal.png")).getImage();

        volverainicio = new JButton("Volver a la pantalla de carga");
        add(volverainicio, BorderLayout.SOUTH);
    }

    /**
     * Luego con esto nos encargamos de dibujar tanto el fondo como los distintos botones
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(fondo == null){
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }
    }
    public JButton getVolverainicio() {return volverainicio;}
}
