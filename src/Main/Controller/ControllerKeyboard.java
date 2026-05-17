package Main.Controller;

import Main.Model.Diver;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Esta clase nos ayudará a administrar el movimiento por medio de la entrada del teclado de nuestro pc.
 */
public class ControllerKeyboard extends KeyAdapter implements ControllerInput {


    private boolean up, down, right, left;

    private boolean pause;

    /**
     * Este método nos ayuda a cambiar de estado una tecla a "false" o a "true"
     *
     * @param KeyCode
     * @param active
     */
    private void changeState(int KeyCode, boolean active) {

        if (KeyCode == KeyEvent.VK_W || KeyCode == KeyEvent.VK_UP) {

            up = active;

        } else if (KeyCode == KeyEvent.VK_S || KeyCode == KeyEvent.VK_DOWN) {

            down = active;

        } else if (KeyCode == KeyEvent.VK_A || KeyCode == KeyEvent.VK_LEFT) {

            left = active;

        } else if (KeyCode == KeyEvent.VK_D || KeyCode == KeyEvent.VK_RIGHT) {

            right = active;

        } else if (KeyCode == KeyEvent.VK_ESCAPE) {

            pause = active;

        }

    }


    /**
     * Getter para saber si el juego entra en pausa o no
     *
     * @return
     */
    public boolean isPause() {

        return pause;

    }

    /**
     * Método que identifica si una tecla se presioan y que tecla es la que se está presionando y cambia de estado.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        changeState(e.getKeyCode(), true);

    }

    /**
     * Método que identifica que tecla se deja de presionar y cambia de estado
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        changeState(e.getKeyCode(), false);

    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    /**
     * Método que administra el movimiento del jugador en las direcciones que se presionen con el teclado.
     *
     * @param diver Jugador que se debe mover
     */
    @Override
    public void reloadMovement(Diver diver) {

        if (diver == null) {

            return;

        }

        double dx = 0;
        double dy = 0;
        double speed = diver.getSpeed();

        if (up) {
            dy -= speed;
        }

        if (down) {
            dy += speed;
        }

        if (left) {
            dx -= speed;
        }

        if (right) {
            dx += speed;
        }

        if (dx != 0 && dy != 0) {

            dx /= Math.sqrt(2);

            dy /= Math.sqrt(2);

        }


        diver.move(dx, dy, Game.WIDHT_PANEL, Game.HEIGHT_PANEL);

    }
}
