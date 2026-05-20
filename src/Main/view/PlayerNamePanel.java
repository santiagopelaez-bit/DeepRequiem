package Main.view;

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
    private final Image fondo;
    private final JTextField campoNombre;
    private final JButton botonEmpezar;
    private final Font pixelFont;

    /**
     * Constructor que contendra la fuente pixeleada y la decoracion de la ventana de nombre
     * el GridBagLayout cumple la misma funcion que el BorderLayout
     * tambien tendra los botones de empezar y el campo donde se pondra el nombre
     */
    public PlayerNamePanel() {
        pixelFont = new Font("Monospaced", Font.BOLD, 24);
        setLayout(new GridBagLayout());
        fondo = ChargerResource.chargeImage("/Main/resources/images/background/BackGroundGame.png");
        setBackground(new Color(10, 55, 90));
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
        campoNombre.setFont(new Font("Monospaced", Font.BOLD, 18));
        campoNombre.setBackground(Color.BLACK);
        campoNombre.setForeground(Color.GREEN);
        campoNombre.setCaretColor(Color.WHITE);

        add(campoNombre, gbc);

        gbc.gridy++;

        botonEmpezar = new JButton("EMPEZAR PARTIDA");
        botonEmpezar.setFont(new Font("Monospaced", Font.BOLD, 18));
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

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JButton getBotonEmpezar() {

        return botonEmpezar;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),  this);
    }
}