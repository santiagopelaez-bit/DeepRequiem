package Main.view;

import Main.resources.ChargerResource;

import javax.swing.*;
import java.awt.*;

/**
 * Creamos la clase que mostrara todo los componentes del menu de inicio
 */
public class WelcomePanel extends JPanel {
    /**
     * Aca nos encargamos de crear los botones para iniciar el juego y revisar las instrucciones del juego
     */
    private final JButton instrucciones;
    private final JButton iniciar;
    private final Image background = ChargerResource.chargeImage("src/Main/resources/images/background/BackGroundMenu.png");
    private final Image logoUam = ChargerResource.chargeImage("src/Main/resources/images/background/BackGroundGame.png");

    /**
     * con este metodo nos encargamos de que en pantalla se muestre el titulo del juego, la materia del proyecto
     * los integrantes del grupo, el nombre de la universidad y una breve descripcion del objetivo del juego
     * ademas de los botones para iniciar y las instrucciones
     */
    public WelcomePanel() {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Deep Requiem", JLabel.CENTER);


        JPanel PanelCentro = new JPanel(new GridLayout(5, 1, 8, 8));
        PanelCentro.setOpaque(false);
        PanelCentro.add(etiqueta("Materia: Programacion Orientada a Objetos"));
        // TODO: Reemplazar por nombres reales de integrantes.
        PanelCentro.add(etiqueta("Integrantes: Santiago Pelaez Velez - Brayan Sanchez Amariles - Andres Londoño Tavares"));
        PanelCentro.add(etiqueta("Universidad Autonoma de Manizales"));
        PanelCentro.add(etiqueta("Adentrate en el lugar mas profundo de la tierra y encuentra valiosos tesoros"));

        JPanel botones = new JPanel();
        botones.setOpaque(false);
        iniciar = new JButton(" Iniciar la aventura ");
        instrucciones = new JButton(" ¿Como jugar? ");
        botones.add(iniciar);
        botones.add(instrucciones);
        PanelCentro.add(botones);
        add(PanelCentro, BorderLayout.CENTER);
    }

    /**
     * con esto nos encargamos de crear la etiqueta del texto de cada uno de los botones e informacion del prouecto
     * que pusimos antes
     *
     * @param texto
     * @return
     */
    private JLabel etiqueta(String texto) {
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setForeground(Color.WHITE);

        return label;
    }

    /**
     * con esto nos encargamos de que se muestre la imagen y el logo de la universidad en pantalla
     * ademas de agregar los botones definitivamente
     *
     * @param g the <code>Graphics</code> object to protect
     */
    ChargerResource resources;

    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
        if (logoUam != null) {
            g.drawImage(logoUam, 30, 30, 100, 80, null);
        }
    }

    public JButton getIniciar() {
        return iniciar;
    }

    public JButton getInstrucciones() {
        return instrucciones;
    }
}
