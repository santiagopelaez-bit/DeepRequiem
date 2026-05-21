package Main;

import Main.Controller.ControllerWindow;

import javax.swing.*;

/**
 * Punto de entrada de Tesoros del Abismo.
 */
public class Main {

    /**
     * Inicia la aplicacion Swing.
     *
     * @param args argumentos de consola no usados.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControllerWindow controllerWindow = new ControllerWindow();
            controllerWindow.show();
        });
    }
}