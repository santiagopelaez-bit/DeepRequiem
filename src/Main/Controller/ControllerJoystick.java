package Main.Controller;

import Main.Model.Diver;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerJoystick implements ControllerInput {

    private boolean conected;
    private Controller joystick;
    private static final float DEAD_ZONE = 0.25f;

    /**
     * Creamos un controlador de Joystick seguro sin ninguna dependencia.
     */
    public ControllerJoystick() {

        joystick = findJoystick();

        conected = (joystick != null);

        if (conected) {

            System.out.print("Joystick conectado: " + joystick.getName());

        } else {

            System.out.print("Joystick no configurado. Se usará teclado.");

        }

    }

    /**
     *
     * Indica si hay un Joystick real disponible para esta implementación
     *
     * @return false mientras no se integra una librería como JInput.
     */
    public boolean isConected() {

        return conected;

    }

    /**
     * No mueve el buzo en caso de que el Joystick no esté configurado
     *
     * @param diver Jugador que se debe mover
     */
    @Override
    public void reloadMovement(Diver diver) {

        if (!isConected() || diver == null) {

            return;

        }

        joystick.poll();

        float ejeX = 0;
        float ejeY = 0;

        for (Component component : (Component[]) joystick.getComponents()) {

            if (component.getIdentifier() == Component.Identifier.Axis.X) {

                ejeX = component.getPollData();

            }

            if (component.getIdentifier() == Component.Identifier.Axis.Y) {

                ejeY = component.getPollData();

            }

        }

        //Aplicamos la zona muerta
        if (Math.abs(ejeX) < DEAD_ZONE) {

            ejeX = 0;

        }

        if (Math.abs(ejeY) < DEAD_ZONE) {

            ejeY = 0;

        }

        int dx = (int) (ejeX * diver.getSpeed());

        int dy = (int) (ejeY * diver.getSpeed());

        diver.move(dx, dy, 900, 650);

    }

    /**
     * Buscamos un Joystick o gamepad que esté disponible
     */

    private Controller findJoystick() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller controller : controllers) {

            if (controller.getType() == Controller.Type.GAMEPAD || controller.getType() == Controller.Type.STICK) {

                return controller;

            }

        }

        return null;

    }

}
