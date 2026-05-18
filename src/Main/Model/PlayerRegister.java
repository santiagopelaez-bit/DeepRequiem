package Main.Model;

public class PlayerRegister {
    private final String nombre;
    private final int puntaje;
    private final int tiempo;
    private final int profundidad;

    public PlayerRegister(String nombre, int puntaje, int tiempo, int profundidad) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
        this.profundidad = profundidad;
    }

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
