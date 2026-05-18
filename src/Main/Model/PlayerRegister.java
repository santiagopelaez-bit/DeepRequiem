package Main.Model;

/**
 * Creamos puntaje, tiempo, profundidad y nombre del jugador
 */

public class PlayerRegister {
    private final String nombre;
    private final int puntaje;
    private final int tiempo;
    private final int profundidad;

    /**
     * creamos un constructor que almacenara cada uno
     * @param nombre
     * @param puntaje
     * @param tiempo
     * @param profundidad
     */
    public PlayerRegister(String nombre, int puntaje, int tiempo, int profundidad) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
        this.profundidad = profundidad;
    }

    /**
     * Y aca ponemos getters y setters
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getProfundidad() {
        return profundidad;
    }
}
