package Main.Controller;

import Main.Model.Diver;
import Main.Model.Game;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ControllerJoystick implements ControllerInput {

    private static final float DEAD_ZONE = 0.25f;
    private static boolean nativesPrepared;

    private Controller controller;
    private int refreshTicks;

    /**
     * Controlador opcional mediante JInput.
     */
    public ControllerJoystick() {

        refresh();

    }

    /**
     * Indica si hay un joystick real disponible.
     */
    public boolean isConected() {

        return controller != null;

    }

    public void refresh() {

        controller = findController();

        if (controller == null) {

            System.out.println("Joystick no detectado. Se usará teclado.");

        } else {

            System.out.println("Joystick detectado: " + controller.getName());

        }

    }

    /**
     * Mueve el buzo usando los ejes X/Y del joystick detectado.
     *
     * @param diver Jugador que se debe mover
     */
    @Override
    public void reloadMovement(Diver diver) {

        if (diver == null) {

            return;

        }

        if (controller == null) {

            refreshTicks++;

            if (refreshTicks >= 60) {

                refreshTicks = 0;
                controller = findController();

            }

            return;

        }

        if (!controller.poll()) {

            controller = null;
            return;

        }

        float x = readAxis(Component.Identifier.Axis.X);
        float y = readAxis(Component.Identifier.Axis.Y);

        if (Math.abs(x) < DEAD_ZONE) {

            x = 0;

        }

        if (Math.abs(y) < DEAD_ZONE) {

            y = 0;

        }

        if (x == 0 && y == 0) {

            return;

        }

        double length = Math.sqrt(x * x + y * y);
        double speed = diver.getSpeed();
        int dx = (int) Math.round((x / length) * speed);
        int dy = (int) Math.round((y / length) * speed);

        updateSprite(diver, dx, dy);

        diver.move(dx, dy, Game.PANEL_WIDTH, Game.PANEL_HEIGHT);

    }

    private Controller findController() {

        try {

            prepareNativeLibraries();

            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

            for (Controller candidate : controllers) {

                Controller.Type type = candidate.getType();

                if (type == Controller.Type.GAMEPAD || type == Controller.Type.STICK) {

                    return candidate;

                }

            }

        } catch (Throwable e) {

            System.out.println("No se pudo inicializar JInput: " + e.getMessage());

        }

        return null;

    }

    private static void prepareNativeLibraries() throws IOException {

        if (nativesPrepared || System.getProperty("net.java.games.input.librarypath") != null) {

            nativesPrepared = true;
            return;

        }

        Path nativeDirectory = Path.of(System.getProperty("java.io.tmpdir"), "DeepRequiem-jinput-natives");

        Files.createDirectories(nativeDirectory);

        for (String nativeFile : nativeFiles()) {

            copyNativeIfPresent(nativeFile, nativeDirectory);

        }

        System.setProperty("net.java.games.input.librarypath", nativeDirectory.toAbsolutePath().toString());

        nativesPrepared = true;

    }

    private static String[] nativeFiles() {

        return new String[]{
                "libjinput-linux64.so",
                "libjinput-osx.jnilib",
                "jinput-dx8_64.dll",
                "jinput-raw_64.dll",
                "jinput-wintab.dll"
        };

    }

    private static void copyNativeIfPresent(String nativeFile, Path nativeDirectory) throws IOException {

        try (InputStream input = ControllerJoystick.class.getClassLoader().getResourceAsStream(nativeFile)) {

            if (input == null) {

                return;

            }

            Files.copy(input, nativeDirectory.resolve(nativeFile), StandardCopyOption.REPLACE_EXISTING);

        }

    }

    private float readAxis(Component.Identifier.Axis axis) {

        Component component = controller.getComponent(axis);

        if (component == null) {

            return 0;

        }

        return component.getPollData();

    }

    private void updateSprite(Diver diver, int dx, int dy) {

        if (dy < 0 && dx > 0) {

            diver.lookUpRight();

        } else if (dy < 0 && dx < 0) {

            diver.lookUpLeft();

        } else if (dy > 0 && dx < 0) {

            diver.lookDownLeft();

        } else if (dy > 0 && dx > 0) {

            diver.lookDownRight();

        } else if (dy < 0) {

            diver.lookUp();

        } else if (dy > 0) {

            diver.lookDown();

        } else if (dx < 0) {

            diver.lookLeft();

        } else if (dx > 0) {

            diver.lookRight();

        }

    }

}
