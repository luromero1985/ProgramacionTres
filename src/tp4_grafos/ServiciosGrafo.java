package tp4_grafos;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*En esta clase pongo todos los servicios del grafo (Recorridos, camino mas largo, etc).
* Refactorice lo que tenia en grafo dirigido hacia esta clase.
*/

public class ServiciosGrafo <T> {
	private Hashtable<Integer, String> colores;
    private Hashtable<Integer, Integer> tiempoDescubrimiento;
    private Hashtable<Integer, Integer> tiempoFinalizacion;
    private Integer tiempo;
    private Hashtable<Integer, Boolean> visitados;
    private Queue<Integer> fila;
    private boolean hayCiclo; //para la actividad 3
  //para la actividad 4
    private LinkedList<Integer>caminoMayor = new LinkedList<>();  // Mejor camino encontrado
   
    
    
    public ServiciosGrafo() {
        this. colores = new Hashtable<>();
        this.tiempoDescubrimiento = new Hashtable<>();
        this.tiempoFinalizacion = new Hashtable<>();
        this.visitados = new Hashtable<>();
        this.fila = new LinkedList<>();
        this.tiempo = 0;
    }

    
    
    
    /*
     * ¿Por qué usamos Hashtable<Integer, String> colores?

En un recorrido DFS, cada vértice puede estar en uno de 3 estados:

Blanco → no visitado.

Amarillo → descubierto, pero no hemos terminado de explorar sus adyacentes (está “en proceso”).

Negro → completamente visitado.

Entonces, necesitamos asociar cada vértice con un color.
Ejemplo:

vértice 1 → Blanco
vértice 2 → Amarillo
vértice 3 → Negro


Para eso, usamos un mapa (Hashtable o HashMap) donde:

la clave (key) es el ID del vértice (por ejemplo, 1, 2, 3),

el valor (value) es el color de ese vértice (Blanco, Amarillo, Negro).

Esto permite consultar rápido:

if (colores.get(vertice) == "Blanco") { ... }
     
     
     
     ¿Por qué usamos Hashtable<Integer, Integer> tiempoDescubrimiento y tiempoFinalizacion?

DFS no solo recorre, también puede servir para analizar la estructura del grafo (ciclos, componentes, etc.).

tiempoDescubrimiento → el "instante" en que un vértice fue descubierto (pasó de Blanco a Amarillo).

tiempoFinalizacion → el "instante" en que terminamos de explorar todos sus adyacentes (pasó a Negro).

Ejemplo de una corrida DFS:

t = 1 → descubrimos vértice 1
t = 2 → descubrimos vértice 2
t = 3 → descubrimos vértice 3
t = 4 → terminamos vértice 3
t = 5 → terminamos vértice 2
t = 6 → terminamos vértice 1


Esto se guarda así:

tiempoDescubrimiento[1] = 1
tiempoFinalizacion[1] = 6


 Tener estos tiempos permite:

Detectar ciclos.

Encontrar componentes fuertemente conexas.

Construir un orden topológico en grafos dirigidos acíclicos (DAG).



¿Por qué usamos Hashtable<Integer, Boolean> visitados en BFS?

En BFS no hace falta el sistema de colores.

Solo nos interesa saber si un vértice ya fue visitado o no.

Entonces usamos un mapa donde:

clave = vértice,

valor = true (ya visitado) o false (no visitado).

Ejemplo:

visitados[1] = true
visitados[2] = true
visitados[3] = false



¿Por qué usamos Queue<Integer> fila en BFS?

BFS se basa en recorrer por niveles (primero los vecinos cercanos, después los vecinos de los vecinos, etc.).

Para manejar este orden, usamos una cola (queue):

Cuando encontramos un vértice nuevo → lo metemos al final de la cola.

Sacamos siempre el vértice del frente de la cola para explorarlo.

Ejemplo paso a paso BFS desde 1:

Fila: [1]   → visito 1, agrego sus vecinos
Fila: [2,3] → visito 2, agrego sus vecinos
Fila: [3,4] → visito 3, agrego sus vecinos
Fila: [4]   → visito 4...

 Por eso la cola es fundamental en BFS, igual que la recursión es fundamental en DFS.
     
     
     * */
    // ---------------- DFS ----------------
    
    public LinkedList<Integer> recorridoDfs(GrafoDirigido<T> grafo) {
        LinkedList<Integer> retorno = new LinkedList<>();
        
        //Nos aseguramos de que no quede estado de recorridos anteriores porque llamamos varias veces al mismo grafo.
        this.colores.clear();
        this.tiempoDescubrimiento.clear();
        this.tiempoFinalizacion.clear();
        this.tiempo = 0;
        //para la actividad 3, de deteccion de ciclos
        this.hayCiclo = false; // inicializo ciclo como falso

        // Inicializar todos los vértices como Blanco
        Iterator<Integer> itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            this.colores.put(itVertices.next(), "Blanco");
        }

        // Recorrer todos los vértices
        itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            Integer vertice = itVertices.next();
            if (this.colores.get(vertice).equals("Blanco")) {
            	//lo mando a la recursividad
                dfsVisit(grafo, vertice, retorno);
            }
        }
        
        if (this.hayCiclo) {
            System.out.println("El grafo contiene un ciclo");
        } else {
            System.out.println("El grafo es acíclico");
        }

        return retorno;
    }

    private void dfsVisit(GrafoDirigido<T> grafo, int vertice, LinkedList<Integer> retorno) {
        this.colores.put(vertice, "Amarillo");
        retorno.add(vertice);
        this.tiempo++;
        this.tiempoDescubrimiento.put(vertice, tiempo);

        Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(vertice);
        while (itAdyacentes.hasNext()) {
            Integer vAdyacente = itAdyacentes.next();
            if (this.colores.get(vAdyacente).equals("Blanco")) {
                dfsVisit(grafo, vAdyacente, retorno);
            }
            else if (this.colores.get(vAdyacente).equals("Amarillo")) {
                // Si llego a un nodo amarillo → ciclo
                this.hayCiclo = true;
            }
        }

        this.colores.put(vertice, "Negro");
        this.tiempo++;
        this.tiempoFinalizacion.put(vertice, tiempo);
    }

    
    
    
    // ---------------- BFS ----------------
    
    public LinkedList<Integer> recorridoBfs(GrafoDirigido<T> grafo) {
        LinkedList<Integer> retorno = new LinkedList<>();
        this.visitados.clear();
        this.fila.clear();

        // Inicializar todos los vértices como no visitados
        Iterator<Integer> itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            this.visitados.put(itVertices.next(), false);
        }

        // Recorrer todos los vértices
        itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            Integer vertice = itVertices.next();
            if (!this.visitados.get(vertice)) {
                bfsVisit(grafo, vertice, retorno);
            }
        }

        return retorno;
    }

    private void bfsVisit(GrafoDirigido<T> grafo, int vertice, LinkedList<Integer> retorno) {
        this.visitados.put(vertice, true);
        this.fila.add(vertice);
        retorno.add(vertice);

        while (!this.fila.isEmpty()) {
            Integer verticeX = this.fila.remove();
            
            Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(verticeX);
            
            while (itAdyacentes.hasNext()) {
                Integer vAdyacente = itAdyacentes.next();
                if (!this.visitados.get(vAdyacente)) {
                    this.visitados.put(vAdyacente, true);
                    this.fila.add(vAdyacente);
                    retorno.add(vAdyacente);
                }
            }
        }
    }

	
	
	//-------EJERCICIO 4 --------
    
 
    public LinkedList<Integer> obtenerCaminoMasLargo(GrafoDirigido<T> grafo, int origen, int destino) {
        // Limpiamos estado previo
        this.visitados.clear();
        this.caminoMayor.clear();

        // Inicializamos visitados en false
        Iterator<Integer> itVertices = grafo.obtenerVertices();
        while(itVertices.hasNext()) {
            this.visitados.put(itVertices.next(), false);
        }

        // Creamos camino parcial e iniciamos la recursión
        LinkedList<Integer> caminoParcial = new LinkedList<>();
        caminoParcial.add(origen);
        this.visitados.put(origen, true);

        buscarCaminoMasLargo(grafo, origen, destino, caminoParcial);

        return this.caminoMayor;
    }

    // Método recursivo de backtracking
    private void buscarCaminoMasLargo(GrafoDirigido<T> grafo, int actual, int destino, LinkedList<Integer> caminoParcial) {
        if(actual == destino) {
            // Si llegamos al destino, verificamos si es el camino más largo
            if(caminoParcial.size() > this.caminoMayor.size()) {
                this.caminoMayor.clear();
                this.caminoMayor.addAll(caminoParcial);
            }
            return;
        }

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
        while(adyacentes.hasNext()) {
            int v = adyacentes.next();
            if(!this.visitados.get(v)) {
                // Avanzamos: agregamos vértice al camino y marcamos como visitado
                caminoParcial.add(v);
                this.visitados.put(v, true);

                // Recursión
                buscarCaminoMasLargo(grafo, v, destino, caminoParcial);

                // Backtracking: deshacemos la acción
                caminoParcial.removeLast();
                this.visitados.put(v, false);
            }
        }
    }

	/*public LinkedList<Integer> obtenerRecorridoMasLargo(GrafoDirigido<T> grafo, Integer verticeOrigen, Integer verticeDestino){
		if(!grafo.contieneVertice(verticeOrigen) || !grafo.contieneVertice(verticeDestino)){ //Hacemos dos metodos,
			return new LinkedList<Integer>();												//para que no pregunte siempre en la recursion
		}
		return recorridoMasLargo(grafo, verticeOrigen, verticeDestino);
	}

	private LinkedList<Integer> recorridoMasLargo(GrafoDirigido<T> grafo, Integer verticeOrigen, Integer verticeDestino){
		LinkedList<Integer> res = new LinkedList<Integer>();  
		LinkedList<Integer> resParcial = new LinkedList<Integer>();
		if(verticeOrigen.equals(verticeDestino)){
			res.add(verticeOrigen);
			return res;
		}
		Iterator<Integer> arcos = grafo.obtenerAdyacentes(verticeOrigen);
		while(arcos.hasNext()){														//Si hago add se acumula a los que ya tenia
			Integer verticeAdyacente = arcos.next();								//ResParcial va teniendo la lista de cada adyacente
			resParcial = obtenerRecorridoMasLargo(grafo, verticeAdyacente, verticeDestino);// Agrego pisando lo que ya tenia 
			if (resParcial.size() > res.size()){
				res = new LinkedList<Integer>(resParcial);  //Aca se crea una nueva lista con los valores que tiene resParcial, porque si hacemos
			}												// res = resParcial apuntaria siempre a lo mismo que resParcial y se actualizarian las 2.
		}
		if(res.size() > 0){
			res.add(0, verticeOrigen); //Agregamos al principio de la lista el vertice origen.
		}
		return res;
	}
	*/
    
    
    
    
    
	//----------EJERCICIO 5 -------
    
   	
//actividad 5: pensando en un grafo que puede ser ciclico
	
	public ArrayList<Integer> obtenerCaminoDeVerticesHaciaVerticeDestino(GrafoDirigido<T> grafo, int verticeDestino) {
	    ArrayList<Integer> camino = new ArrayList<>();

	    if (!grafo.contieneVertice(verticeDestino)) {
	        return camino; // lista vacía
	    }

	    this.visitados.clear();

	    // Inicializo visitados
	    Iterator<Integer> itVertices = grafo.obtenerVertices();
	    while (itVertices.hasNext()) {
	        Integer vertice = itVertices.next();
	        visitados.put(vertice, false);
	    }

	    itVertices = grafo.obtenerVertices();
	    while (itVertices.hasNext()) {
	        Integer vOrigen = itVertices.next();
	        ArrayList<Integer> caminoEncontrado = caminoDeVerticesHaciaVerticeDestino(grafo, vOrigen, verticeDestino, new ArrayList<>());
	        if (!caminoEncontrado.isEmpty()) {
	            return caminoEncontrado; // devuelvo el primero que encuentre (si necesitás todos, guardás todos)
	        }
	    }

	    return camino; // lista vacía si no encontró ninguno
	}

	private ArrayList<Integer> caminoDeVerticesHaciaVerticeDestino(GrafoDirigido<T> grafo, int vOrigen, int vDestino,  ArrayList<Integer> caminoActual) {
	    // Evito ciclos
	    if (this.visitados.get(vOrigen)) {
	        return new ArrayList<>();
	    }

	    this.visitados.put(vOrigen, true);
	    caminoActual.add(vOrigen);

	    // Caso base: llegué al destino
	    if (vOrigen == vDestino) {
	        return new ArrayList<>(caminoActual); // copio el camino actual
	    }

	    // Recorrer adyacentes
	    Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(vOrigen);
	    while (itAdyacentes.hasNext()) {
	        Integer vAdyacente = itAdyacentes.next();
	        ArrayList<Integer> caminoRec = caminoDeVerticesHaciaVerticeDestino(grafo, vAdyacente, vDestino, caminoActual);
	        if (!caminoRec.isEmpty()) {
	            return caminoRec; // devuelvo el primer camino válido
	        }
	    }

	    // Backtracking
	    caminoActual.remove(caminoActual.size() - 1);
	    visitados.put(vOrigen, false);

	    return new ArrayList<>();
	}

	
	
	//actividad 5 con devolución de todos los caminos encontrados y no solo el primero
	
	// Declaraciones que van en la clase
	private List<LinkedList<Integer>> todosLosCaminos = new ArrayList<>();

	// Método público que inicializa el proceso
	public List<LinkedList<Integer>> obtenerTodosLosCaminos(GrafoDirigido<T> grafo, int origen, int destino) {
	    this.todosLosCaminos.clear();
	    this.visitados.clear();

	    // Inicializamos visitados en false
	    Iterator<Integer> itVertices = grafo.obtenerVertices();
	    while(itVertices.hasNext()) {
	        this.visitados.put(itVertices.next(), false);
	    }

	    // Creamos camino parcial e iniciamos la recursión
	    LinkedList<Integer> caminoParcial = new LinkedList<>();
	    caminoParcial.add(origen);
	    this.visitados.put(origen, true);

	    buscarTodosLosCaminos(grafo, origen, destino, caminoParcial);

	    return this.todosLosCaminos;
	}

	// Método recursivo de backtracking
	private void buscarTodosLosCaminos(GrafoDirigido<T> grafo, int actual, int destino, LinkedList<Integer> caminoParcial) {
	    if(actual == destino) {
	        // Llegamos al destino: guardamos una copia del camino parcial
	        this.todosLosCaminos.add(new LinkedList<>(caminoParcial));
	        return;
	    }

	    Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
	    while(adyacentes.hasNext()) {
	        int v = adyacentes.next();
	        if(!this.visitados.get(v)) {
	            // Avanzamos: agregamos vértice al camino y marcamos como visitado
	            caminoParcial.add(v);
	            this.visitados.put(v, true);

	            // Recursión
	            buscarTodosLosCaminos(grafo, v, destino, caminoParcial);

	            // Backtracking: deshacemos la acción
	            caminoParcial.removeLast();
	            this.visitados.put(v, false);
	        }
	    }
	}
}
