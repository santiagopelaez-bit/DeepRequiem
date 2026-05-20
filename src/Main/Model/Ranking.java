package Main.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * un ranking que guardara las mejores partidas jugadas
 */
public class Ranking {
    /**
     * creamos un arreglo que almacene la informacion del jugador
     */
    private final List<PlayerRegister> registers;

    /**
     * creamos una lista vacia
     */

    public Ranking() {
        registers = new ArrayList<>();
    }

    /**
     * agregamos los datos de una partida al historial de la memoria
     */
    public void addPlayer(PlayerRegister register) {
        registers.add(register);
    }

    public List<PlayerRegister> getRegisters() {
        return registers;
    }

    /**
     * devuelve los tres mejores puntajes y se asegura de que vayan de mayor a menor
     * ademas de que solo se muestren tres puntajes
     */
    public List<PlayerRegister> top() {
        List<PlayerRegister> copia = new ArrayList<>(registers);
        copia.sort(Comparator.comparingInt(PlayerRegister::getPuntaje).reversed());
        return copia.subList(0, Math.min(3, copia.size()));
    }

    public void addPlayer(Diver diver) {
    }
}
