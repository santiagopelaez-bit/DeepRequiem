package Main.Controller;

import Main.Model.Diver;

/**
 * Contrato común para cualquier entrada que pueda mover el buzo.
 */
public interface ControllerInput {

    /**
     * Actualiza el movimiento del buzo según el estado de la entrada.
     *
     * @param diver Jugador que se debe mover
     */
    void reloadMovement(Diver diver);

}
