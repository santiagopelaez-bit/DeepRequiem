package Main.resources.view;

import Main.resources.ChargerResource;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla para ingresar el nombre del jugador
 */
public class PlayerNamePanel extends JPanel {
    /**
     *Aca creamos el boton para empezar, la fuente pixeleada y el campo donde se ingresara el nombre del jugador
     */
    private final JTextField campoNombre;
    private final JButton botonEmpezar;
    private final Font pixelFont;
    private final Image fondo;

    /**
     * Constructor que contendra la fuente pixeleada y la decoracion de la ventana de nombre
     * el GridBagLayout cumple la misma funcion que el BorderLayout
     * tambien tendra los botones de empezar y el campo donde se pondra el nombre
     */
    public PlayerNamePanel() {
        pixelFont = ChargerResource.pixelFont(24f);
        setLayout(new GridBagLayout());
        setBackground(new Color(10, 55, 90));
        fondo = ChargerResource.chargeImage("/Main/resources/images/background/BackGroundGame.png");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel titulo = new JLabel("NOMBRE DEL JUGADOR");
        titulo.setFont(pixelFont);
        titulo.setForeground(Color.WHITE);
        add(titulo, gbc);
        gbc.gridy++;
        campoNombre = new JTextField(20);
        campoNombre.setFont(ChargerResource.pixelFont(18f));
        campoNombre.setBackground(Color.BLACK);
        campoNombre.setForeground(Color.GREEN);
        campoNombre.setCaretColor(Color.WHITE);

        add(campoNombre, gbc);

        gbc.gridy++;

        botonEmpezar = new JButton("EMPEZAR PARTIDA");
        botonEmpezar.setFont(ChargerResource.pixelFont(18f));
        botonEmpezar.setFocusPainted(false);
        botonEmpezar.setBackground(Color.DARK_GRAY);
        botonEmpezar.setForeground(Color.WHITE);
        add(botonEmpezar, gbc);
    }

    /**
     * Retorna el nombre que se haya escrito
     */
    public String getNombreJugador() {

        return campoNombre.getText();
    }

    /**
     * Limpia el campo
     */
    public void limpiar() {
        campoNombre.setText("");
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

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JButton getBotonEmpezar() {

        return botonEmpezar;
    }
}
