package final_12_3_26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp4_grafos.GrafoNoDirigido;

/*
 * Partiendo de un conjunto de ciudades C que se encuentran conectadas por rutas (no todas las ciudades están conectadas entre sí)
 *  y un conjunto de claves distintas M. Se desea saber si es posible asignar una clave a cada ciudad de manera tal que dos 
 *  ciudades comunicadas por una ruta no tengan la misma clave y no se usen más de K claves distintas (K < M). Las ciudades NO
 *   comunicadas por una ruta directa pueden tener la misma clave.

a) Explicar cuál sería la estrategia Greedy que seguiría.

La estrategia es: Recorrer las ciudades, ordenadas por mayor grado, y asignar a cada una la menor clave disponible que no esté
 siendo usada por sus ciudades adyacentes.
b) Plantear el algoritmo en Java que lo resuelva mediante dicha estrategia Greedy.
c) ¿Cuál sería la complejidad computacional del algoritmo? Justifique su respuesta.
 * */
public class Ciudades {


	public boolean asignacionClave(GrafoNoDirigido<Integer> ciudades,ArrayList<Integer>clavesM, int K) {

        HashMap<Integer, Integer> asignacion = new HashMap<>();

        // Orden, mayor grado primero
        ArrayList<Integer> orden = ordenarPorGrado(ciudades);

        for (Integer ciudad : orden) {

            // claves usadas por vecinos
            ArrayList<Integer> usadas = new ArrayList<>();
            Iterator<Integer> itAdy = ciudades.obtenerAdyacentes(ciudad);

            while (itAdy.hasNext()) {
                Integer vecino = itAdy.next();
                if (asignacion.containsKey(vecino)) {
                    usadas.add(asignacion.get(vecino));
                }
            }
            boolean asignada = false;
         // solo puedo usar hasta K claves del conjunto M
            for (int i = 0; i < K && i < clavesM.size(); i++) {
                Integer clave = clavesM.get(i);

                if (!usadas.contains(clave)) {
                    asignacion.put(ciudad, clave);
                    asignada = true;
                    break;
                }
            }

            // si no hay clave posible → falla
            if (!asignada) {
                return false;
            }
        }

        return true;
    
}



	 // Ordena ciudades por cantidad de vecinos (mayor a menor)
    private ArrayList<Integer> ordenarPorGrado(GrafoNoDirigido<Integer> ciudades) {

        ArrayList<Integer> lista = new ArrayList<>();

        Iterator<Integer> it = ciudades.obtenerVertices();
        while (it.hasNext()) {
            lista.add(it.next());
        }

        lista.sort((a, b) -> contarVecinos(ciudades, b) - contarVecinos(ciudades, a));

        return lista;
    }
    
    
    // Cuenta vecinos de una ciudad
    private int contarVecinos(GrafoNoDirigido<Integer> ciudades, Integer ciudad) {

        int count = 0;
        Iterator<Integer> it = ciudades.obtenerAdyacentes(ciudad);

        while (it.hasNext()) {
            count++;
            it.next();
        }

        return count;
    }
}
