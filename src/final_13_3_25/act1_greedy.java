package final_13_3_25;

import java.util.ArrayList;
import java.util.Collections;

public class act1_greedy {

	private ArrayList<Integer> fuerzaAcumulada = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> greedy(ArrayList<Integer> pers, int cc) {

        ArrayList<ArrayList<Integer>> solucion = new ArrayList<>();
        ArrayList<Integer> personasDisp = new ArrayList<>(pers);
        Collections.sort(personasDisp, Collections.reverseOrder()); // ordeno de mayor a menor

        int p = personasDisp.size();
        int conjDisp = cc;
        ArrayList<Integer> usados = new ArrayList<>();

        while (p > 0 && conjDisp > 0) { // mientras haya personas y conjuntos disponibles

            if (conjDisp == cc) { // primer conjunto: agrego solo el de mayor fuerza

                int f = 0;
                for (int i = 0; i < p; i++) {
                    int fActual = personasDisp.get(i);
                    if (fActual > f && !usados.contains(fActual)) {
                        f = fActual;
                    }
                }
                usados.add(f); // marco como usado
                ArrayList<Integer> solParcial = new ArrayList<>();
                solParcial.add(f);
                solucion.add(solParcial);
                fuerzaAcumulada.add(f);
                conjDisp--;

            } else if (conjDisp == 1) { // ultimo conjunto: agrego todas las personas restantes

                int fuerza = 0;
                ArrayList<Integer> solParcial = new ArrayList<>();
                int indice = p - 1;
                while (indice >= 0 && !usados.contains(personasDisp.get(indice))) {
                    fuerza += personasDisp.get(indice);
                    solParcial.add(personasDisp.get(indice)); // agrego el valor, no el indice
                    usados.add(personasDisp.get(indice));
                    indice--;
                }
                solucion.add(solParcial);
                fuerzaAcumulada.add(fuerza);
                conjDisp--;

            } else { // conjuntos del medio: mayor fuerza + menores fuerzas hasta maxPers

                ArrayList<Integer> solParcial = new ArrayList<>();
                int fFinal = 0;
                int pMaxF = 0;
                int pMinF = Integer.MAX_VALUE;

                // calculo cuantas personas entran en este conjunto
                int maxPers = p / conjDisp;
                if (p % conjDisp > 0) {
                    maxPers = maxPers + 1; // si hay resto tomo el entero siguiente
                }

                // selecciono la persona con mayor fuerza disponible
                for (int i = 0; i < p; i++) {
                    int fpd = personasDisp.get(i);
                    if (!usados.contains(fpd) && fpd > pMaxF) {
                        pMaxF = fpd;
                    }
                }
                fFinal += pMaxF;
                usados.add(pMaxF);
                solParcial.add(pMaxF);
                maxPers--;

                // completo el conjunto con las personas de menor fuerza disponible
                while (maxPers > 0) {
                    for (int i = 0; i < p; i++) {
                        int fpd = personasDisp.get(i);
                        if (!usados.contains(fpd) && fpd < pMinF) {
                            pMinF = fpd;
                        }
                    }
                    usados.add(pMinF);
                    solParcial.add(pMinF);
                    pMinF = Integer.MAX_VALUE; // reseteo para la proxima iteracion
                    maxPers--;
                }

                // calculo la fuerza acumulada: maximo menos suma de minimos
                for (int i = 1; i < solParcial.size(); i++) {
                    fFinal -= solParcial.get(i);
                }

                solucion.add(solParcial);
                fuerzaAcumulada.add(fFinal);
                conjDisp--;
            }
        }

        return solucion;
    }

    public int totalFuerzaAcumulada() {
        int fuerza = 0;
        int indice = fuerzaAcumulada.size() - 1;
        while (indice >= 0) {
            fuerza += fuerzaAcumulada.get(indice);
            indice--;
        }
        return fuerza;
    }
}