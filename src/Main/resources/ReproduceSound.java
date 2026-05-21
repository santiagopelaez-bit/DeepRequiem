package Main.resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ReproduceSound {

    private Clip clip;

    /**
     * Con este método prodremos reproducir un archivo de audio
     * una sola vez
     *
     * @param rute
     */
    public void reproduce(String rute) {

        //Aquí detendremos algún audio que tengamos en repdoducción
        detener();

        //Aquí cargaremos y reproduciremos el audio de forma única osea una sola vez
        reproduceIntern(rute, false);

    }

    /**
     * Con este método podremos reproducir archivos de audio en loop.
     * (Que se reproduzca de forma indefinida)
     *
     * @param rute
     */
    public void reproduceLoop(String rute) {

        //Primero detenemos el audio que tenemos en reproduciendo
        detener();

        //Aquí el audio lo cargaremos y diremos que si se reproduzca en loop.
        reproduceIntern(rute, true);

    }

    /**
     * Con este método podremos parar la reproducción del sonido actual
     */
    public void detener() {

        if (clip != null) {

            //Aquí haremos que el audio en cuestión se detenga.
            clip.stop();

            //Aquí el audio se cierra
            clip.close();

            //Aquí el audio se devuelve a null (No existe)
            clip = null;

        }

    }

    /**
     * Con este método cargaremos primero el archivo de sonido que utilizaremos.
     * Despues se evaluará que el archivo no sea "null"
     * Y se evaluará si el archivo en cuestión deberá reproducirse una sola vez o en loop.
     *
     * @param rute
     * @param loop
     */
    public void reproduceIntern(String rute, boolean loop) {

        try {

            //Aquí cargamos el audio que queremos reproducir utilizando el método
            //Que tenemos en nuestra clase "ChargerResource"
            AudioInputStream audio = ChargerResource.chargeSound(rute);

            //Evaluaremos si el audio si se encontró o que si exista
            if (audio == null) {

                return;

            }

            AudioInputStream decodedAudio = decodeToPcm(audio);

            //Si el audio si existe entonces el sistema lo almacena y lo lee.
            clip = AudioSystem.getClip();

            clip.open(decodedAudio);

            //Evaluamos si el audio en cuestión lo utilizaremos como loop o no.
            if (loop) {

                clip.loop(Clip.LOOP_CONTINUOUSLY);

            } else {

                clip.start();

            }

        } catch (Exception e) {

            //En caso de no encontrarse el archivo de sonido se imprime el error en la consola.
            System.out.println("No se logró reproducir el sonido: " + rute + " (" + e.getMessage() + ")");

        }

    }

    private AudioInputStream decodeToPcm(AudioInputStream audio) {

        AudioFormat baseFormat = audio.getFormat();

        if (AudioFormat.Encoding.PCM_SIGNED.equals(baseFormat.getEncoding())) {

            return audio;

        }

        AudioFormat decodedFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false
        );

        return AudioSystem.getAudioInputStream(decodedFormat, audio);

    }


}
