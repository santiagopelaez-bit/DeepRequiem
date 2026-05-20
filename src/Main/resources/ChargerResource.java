package Main.resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ChargerResource {

    public ChargerResource() {
    }

    /**
     * Aquí vamos a cargar una imagen pero si no existe nos devuelve null.
     *
     * @param ruta ejemplo: /src/images/.player.png
     */
    public static Image chargeImage(String ruta) {

        URL image = ChargerResource.class.getResource(ruta);

        if (image == null) {

            System.out.println("Imagen no encontrada: " + ruta);

            return null;

        }

        return new ImageIcon(image).getImage();

    }

    /**
     * En este método cargamos los archivos de audio.
     * Si el sonido no se encuentra o no existe entonces devuelve null.
     *
     * @param ruta
     * @return
     */
    public static AudioInputStream chargeSound(String ruta) {

        URL audio = ChargerResource.class.getResource(ruta);

        if (audio == null) {

            System.out.print("Sonido no encontrado: " + ruta);

            return null;

        }

        try {

            return AudioSystem.getAudioInputStream(audio);

        } catch (Exception e) {

            System.out.print("No se pudo cargar el sonido: " + ruta);
            return null;

        }

    }

    /**
     * En este método vamos a cargar una nueva fuente que utilizaremos en nuestro proyecto.
     * Si la ruta de la fuente no se encuentra o en inválida se devuelve null;
     *
     * @param ruta
     * @return
     */
    public static Font chargeFont(String ruta) {

        URL fontURL = ChargerResource.class.getResource(ruta);

        if (fontURL == null) {

            System.out.println("Fuente no encontrada: " + ruta);

            return null;

        }

        try {

            //Aquí cargamos la fuente que deseamos agregar al proyecto
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream()).deriveFont(50f);

            //Aquí obtenemos el entorno gráfico para poder registrar nuestra nueva fuente
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            //Registramos nuestra nueva fuente en el entorno gráfico de Java
            ge.registerFont(pixelFont);

            return pixelFont;

        } catch (FontFormatException | IOException e) {

            System.out.print("No se pudo cargar la fuente ingresada: " + ruta);
            e.printStackTrace();
            return null;

        }

    }

}
