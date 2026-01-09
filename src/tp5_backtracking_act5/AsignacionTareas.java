package tp5_backtracking_act5;

/*
 * Dado un conjunto de tareas, cada una con un tiempo de ejecución, y un conjunto de procesadores, 
 * asignar las tareas a los procesadores de manera que:
 * cada tarea se ejecute en un solo procesador
 * un procesador puede ejecutar varias tareas
 * se minimice el tiempo total de ejecución
 * (tiempo total = máxima carga entre los procesadores)
 * 
 * */


import java.util.Arrays;

public class AsignacionTareas {

    private int m;                     // cantidad de procesadores
    private int[] tareas;              // duracion de tareas
    private int[] mejorAsignacion;     // mejor solucion encontrada
    private int mejorTiempoTotal;

    public AsignacionTareas(int m, int[] tareasOriginales) {
        this.m = m;
        this.tareas = tareasOriginales.clone();
    }

  

    public int[] asignar() {

        ordenarDecreciente(tareas);

        mejorTiempoTotal = suma(tareas);      // cota inicial
        mejorAsignacion = new int[tareas.length];

        int[] cargaActual = new int[m];
        int[] asignacionActual = new int[tareas.length];

        backtracking(0, cargaActual, asignacionActual);

        return mejorAsignacion;
    }

  

    private void backtracking(int i, int[] carga, int[] asignacionActual) {

        if (i == tareas.length) {
            int tiempo = max(carga);
            if (tiempo < mejorTiempoTotal) {
                mejorTiempoTotal = tiempo;
                mejorAsignacion = asignacionActual.clone();
            }
            return;
        }

        for (int p = 0; p < m; p++) {

            // poda por simetría
            if (p > 0 && carga[p] == carga[p - 1])
                continue;

            // poda por cota
            if (carga[p] + tareas[i] < mejorTiempoTotal) {

                // asignar
                carga[p] += tareas[i];
                asignacionActual[i] = p;

                backtracking(i + 1, carga, asignacionActual);

                // deshacer
                carga[p] -= tareas[i];
            }
        }
    }

    // ---------------- AUXILIARES ----------------

    private int suma(int[] v) {
        int s = 0;
        for (int x : v)
            s += x;
        return s;
    }

    private int max(int[] v) {
        int mayor = v[0];
        for (int x : v)
            if (x > mayor)
                mayor = x;
        return mayor;
    }

    private void ordenarDecreciente(int[] v) {
        Arrays.sort(v);
        for (int i = 0; i < v.length / 2; i++) {
            int tmp = v[i];
            v[i] = v[v.length - 1 - i];
            v[v.length - 1 - i] = tmp;
        }
    }
}

