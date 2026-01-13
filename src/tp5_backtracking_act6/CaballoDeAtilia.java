package tp5_backtracking_act6;

import java.util.List;
import java.util.ArrayList;
/*
 * Por donde pisa el caballo de Atila no vuelve a crecer el pasto.
El caballo entró por una casilla al jardín de n x n casillas, empezó su paseo y volvió a casilla de entrada, es decir, hizo un
recorrido cerrado. No visitó dos veces una misma casilla, se movió de una casilla a otra vecina en forma horizontal o vertical,
pero nunca en diagonal. Por donde pisó el caballo, el pasto no volvió a crecer. Luego de terminado el recorrido en algunas
casillas todavía había pasto (señal de que en ellas no había estado el caballo). Escriba un algoritmo que deduzca el
recorrido completo que hizo el caballo.



 * Podas: 
 * no salir del tablero

no pisar una casilla con pasto

no repetir casilla

si no hay movimientos y no se cerró → descartar
 * */

public class CaballoDeAtilia {

    // Método público: punto de entrada
    public List<Posicion> resolver(Estado estadoInicial) {
        return backAtila(estadoInicial, 1);
    }

    // Backtracking
    private List<Posicion> backAtila(Estado e, int nroPisada) {

        // Caso base: no quedan más casillas sin pasto por pisar
        if (!e.hayMovimientos()) {
            if (nroPisada == e.cantSinPasto() && e.vecinaOrigen()) {
                // solución encontrada
                return new ArrayList<>(e.getCamino());
            }
            return null;
        }

        // Movimientos posibles (arriba, abajo, derecha, izquierda)
        int[][] movimientos = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        // Probar todos los movimientos
        for (int[] mov : movimientos) {

            Posicion sig = new Posicion(
                e.getActual().getFila() + mov[0],
                e.getActual().getColumna() + mov[1]
            );

            if (e.esFactible(sig)) {

                Estado nuevo = e.aplicarMov(sig);
                List<Posicion> solucion = backAtila(nuevo, nroPisada + 1);

                if (solucion != null) {
                    return solucion; // corto al encontrar una solución
                }
            }
        }

        return null; // ningún movimiento llevó a solución
    }
}
