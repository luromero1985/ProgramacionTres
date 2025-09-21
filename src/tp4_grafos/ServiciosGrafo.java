package tp4_grafos;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
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

👉 Esto permite consultar rápido:

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


👉 Tener estos tiempos permite:

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
        }

        this.colores.put(vertice, "Negro");
        this.tiempo++;
        this.tiempoFinalizacion.put(vertice, tiempo);
    }

    
    
    
    // ---------------- BFS ----------------
    
    public LinkedList<Integer> recorridoBfs(GrafoDirigido<T> grafo) {
        LinkedList<Integer> retorno = new LinkedList<>();
        visitados.clear();
        fila.clear();

        // Inicializar todos los vértices como no visitados
        Iterator<Integer> itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            visitados.put(itVertices.next(), false);
        }

        // Recorrer todos los vértices
        itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            Integer vertice = itVertices.next();
            if (!visitados.get(vertice)) {
                bfsVisit(grafo, vertice, retorno);
            }
        }

        return retorno;
    }

    private void bfsVisit(GrafoDirigido<T> grafo, int vertice, LinkedList<Integer> retorno) {
        visitados.put(vertice, true);
        fila.add(vertice);
        retorno.add(vertice);

        while (!fila.isEmpty()) {
            Integer verticeX = fila.remove();
            Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(verticeX);
            while (itAdyacentes.hasNext()) {
                Integer vAdyacente = itAdyacentes.next();
                if (!visitados.get(vAdyacente)) {
                    visitados.put(vAdyacente, true);
                    fila.add(vAdyacente);
                    retorno.add(vAdyacente);
                }
            }
        }
    }

	
	
	//-------EJERCICIO 4 --------
	public LinkedList<Integer> obtenerRecorridoMasLargo(GrafoDirigido<T> grafo, Integer verticeOrigen, Integer verticeDestino){
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
	
	//----------EJERCICIO 5 -------
	
	public HashSet<Integer>obtenerVerticesOrigenCamino(GrafoDirigido<T> grafo, int verticeDestino){
		HashSet<Integer> resultado = new HashSet<Integer>(); //No puede haber valores repetidos
		
		if(!grafo.contieneVertice(verticeDestino)) {
			return resultado;
		}
		 // Si contiene el vertice destino, limpio las estructuras de datos y luego        
      // itero los vertices del grafo y los inicializo como no visitados
		this.visitados.clear();
		
		Iterator<Integer> itVertices = grafo.obtenerVertices();
		while(itVertices.hasNext()) {
			Integer vertice = itVertices.next();
			visitados.put(vertice, false);//Los vertices en un principio los voy a setear a todos en no visitados
		}
		itVertices = grafo.obtenerVertices();
		while(itVertices.hasNext()) {
			Integer vOrigen = itVertices.next();
			if(!visitados.get(vOrigen)) { //Si no esta visitado, lo visita
				resultado.addAll(verticesOrigenConCaminoADestino(grafo, vOrigen,verticeDestino));
			}
		}
		return resultado;
	}


	private HashSet<Integer> verticesOrigenConCaminoADestino(GrafoDirigido<T>grafo, int verticeOrigen, int verticeDestino) {
	    HashSet<Integer> res = new HashSet<Integer>(); //Para que no agregue repetidos de gusto  
	    
	    visitados.put(verticeOrigen, true); //Lo pongo como visitado al vertice para que no lo visite mas
	    
	    //Hacer un if aca para retornar res como punto de corte esta mal, porque tengo que seguir por los demas adyacentes
	    
	    Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(verticeOrigen);
	    while(itAdyacentes.hasNext()) {
	    	HashSet<Integer> resParcial = new HashSet<>(); //LA INICIALIZO ACA PARA QUE SE ACTUALICE CUANDO ITERA CON OTROS ADYACENTES
	    	Integer vAdyacente = itAdyacentes.next();
	    	if(vAdyacente.equals(verticeDestino)) {  //ESTE ES EL CASO DE CORTE, SINO DEVUELVE UN HASHSET VACIO
				resParcial.add(verticeOrigen); //Aca tiene que agregar los anteriores si llego al destino --ERROR SI AGREGABA EL vAdyacente ERA EL MISMO
	    	}
	    	resParcial.addAll(verticesOrigenConCaminoADestino(grafo, vAdyacente, verticeDestino));
	    	if(resParcial.size()>0) { //Si llego al destino va a tener al menos un elemento, por eso es clave agregar cuando verticeOrigen == Destino.
	    		res.add(verticeOrigen); //Aca tiene que agregar los anteriores si llego al destino --ERROR SI AGREGABA EL vAdyacente ERA EL MISMO
				res.addAll(resParcial); //NO OLVIDAR ESTO NUNCA
	    	}
	    }
	    
	    return res; // Retorno el hashset vacío si no se encontró ningún camino
	}
		
	

}

