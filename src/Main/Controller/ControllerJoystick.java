package Main.Controller;

import Main.Model.Diver;

public class ControllerJoystick implements ControllerInput {

    /**
     * Controlador opcional. El proyecto no declara la dependencia JInput,
     * por eso la entrada funcional por defecto es el teclado.
     */
    public ControllerJoystick() {

        System.out.println("Joystick no configurado. Se usará teclado.");

    }

    /**
     *
     * Indica si hay un Joystick real disponible para esta implementación
     *
     * @return false mientras no se integra una librería como JInput.
     */
    public boolean isConected() {

        return false;

    }

    /**
     * No mueve el buzo en caso de que el Joystick no esté configurado
     *
     * @param diver Jugador que se debe mover
     */
    @Override
    public void reloadMovement(Diver diver) {

        // Sin dependencia JInput declarada, no hay movimiento por joystick.

    }

}