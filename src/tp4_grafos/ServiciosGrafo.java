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

    // ---------------- DFS ----------------
    
    public LinkedList<Integer> recorridoDfs(GrafoDirigido<T> grafo) {
        LinkedList<Integer> retorno = new LinkedList<>();
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

