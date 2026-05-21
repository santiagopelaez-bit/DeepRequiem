package Main.resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ChargerResource {

    private static final String PIXEL_FONT_PATH = "/Main/resources/fonts/PixelFont.ttf";
    private static Font pixelFont;

    public ChargerResource() {
    }

    /**
     * Aquí vamos a cargar una imagen pero si no existe nos devuelve null.
     *
     * @param ruta ejemplo: /src/images/.player.png
     */
    public static Image chargeImage(String ruta) {

        URL image = getResource(ruta);

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

        URL audio = getResource(ruta);

        if (audio == null) {

            System.out.println("Sonido no encontrado: " + ruta);

            return null;

        }

        try {

            return AudioSystem.getAudioInputStream(audio);

        } catch (Exception e) {

            System.out.println("No se pudo cargar el sonido: " + ruta + " (" + e.getMessage() + ")");
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

        URL fontURL = getResource(ruta);

        if (fontURL == null) {

            System.out.println("Fuente no encontrada: " + ruta);

            return null;

        }

        try {

            //Aquí cargamos la fuente que deseamos agregar al proyecto
            Font loadedFont;

            try (InputStream input = fontURL.openStream()) {

                loadedFont = Font.createFont(Font.TRUETYPE_FONT, input);

            }

            //Aquí obtenemos el entorno gráfico para poder registrar nuestra nueva fuente
            try {

                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

                //Registramos nuestra nueva fuente en el entorno gráfico de Java
                ge.registerFont(loadedFont);

            } catch (Throwable e) {

                System.out.println("No se pudo registrar la fuente en el entorno gráfico: " + ruta + " (" + e.getMessage() + ")");

            }

            return loadedFont;

        } catch (FontFormatException | IOException e) {

            System.out.print("No se pudo cargar la fuente ingresada: " + ruta);
            e.printStackTrace();
            return null;

        }

    }

    public static Font pixelFont(float size) {

        return pixelFont(size, Font.BOLD);

    }

    public static Font pixelFont(float size, int style) {

        Font baseFont = loadPixelFont();

        return baseFont.deriveFont(style, size);

    }

    private static Font loadPixelFont() {

        if (pixelFont == null) {

            pixelFont = chargeFont(PIXEL_FONT_PATH);

        }

        if (pixelFont == null) {

            return new Font("Monospaced", Font.BOLD, 18);

        }

        return pixelFont;

    }

    public static URL getResource(String ruta) {

        if (ruta == null || ruta.isBlank()) {

            return null;

        }

        String normalized = ruta.startsWith("/") ? ruta : "/" + ruta;

        return ChargerResource.class.getResource(normalized);

    }

}
