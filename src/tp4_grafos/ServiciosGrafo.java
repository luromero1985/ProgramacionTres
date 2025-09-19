package tp4_grafos;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


//En esta clase pongo todos los servicios del grafo (Recorridos, camino mas largo, etc).Refactorice lo que tenia en grafo dirigido hacia esta clase.
public class ServiciosGrafo <T> {

	private Hashtable<Integer, String> colores;
	private Hashtable<Integer, Integer> tiempoDescubrimiento;
	private Hashtable<Integer, Integer> tiempoFinalizacion;
	private Hashtable<Integer, Boolean> visitados;
	private Integer tiempo;
	private Queue<Integer> fila;
	
	public ServiciosGrafo() {
		this.colores = new Hashtable<Integer, String>();              //Tengo la duda si recibir el grafo por constructor o en los metodos.
		this.tiempoDescubrimiento = new Hashtable<Integer, Integer>();
		this.tiempoFinalizacion = new Hashtable<Integer, Integer>();
		this.visitados = new Hashtable<Integer, Boolean>();
		this.fila = new LinkedList<Integer>();
		this.tiempo = 0;
	}
	
	//--------------------------------------------------------
	//---- RECORRIDO DFS
	
	public LinkedList<Integer> recorridoDfs(GrafoDirigido<T> grafo) {
		LinkedList<Integer>retorno = new LinkedList<Integer>();
		this.tiempoDescubrimiento.clear(); 
		this.tiempoFinalizacion.clear();   //Hay que limpiar estos tiempos siempre que haga un nuevo recorrido
		this.tiempo = 0; 
		
		Iterator<Integer> itVertices = grafo.obtenerVertices();
		while(itVertices.hasNext()) {
			Integer key = itVertices.next();
			colores.put(key, "Blanco"); //Lo agrego al hashtable de colores
		}
	    itVertices = grafo.obtenerVertices(); //Para volver a iterar sobre los vertices
		while(itVertices.hasNext()) {
			Integer vertice = itVertices.next();
			if(colores.get(vertice).equals("Blanco")) {
				retorno.addAll(DFS_Visit(grafo, vertice));  //RECURSION
			}
		}
		return retorno;
	}
	
	public LinkedList<Integer> DFS_Visit(GrafoDirigido<T>grafo, int vertice) {
		LinkedList<Integer>retorno = new LinkedList<Integer>();

		colores.put(vertice, "Amarillo");
		retorno.add(vertice); //LE AGREGO EL ID DE CADA VERTICE
		tiempo = tiempo + 1;

		tiempoDescubrimiento.put(vertice, tiempo);
		Iterator <Integer> itAdyacentes = grafo.obtenerAdyacentes(vertice);
		while(itAdyacentes.hasNext()) {
			Integer vAdyacente = itAdyacentes.next();

			if(colores.get(vAdyacente).equals("BLANCO")) {
				retorno.addAll(DFS_Visit(grafo, vAdyacente)); //ACA SE AGREGAN TODOS LOS ID DE LOS VERTICES DE LA RECURSION
			}
		}
		colores.put(vertice, "Negro");
		tiempo = tiempo + 1; 	
		tiempoFinalizacion.put(vertice, tiempo);

		return retorno;
	}
	
	//-----RECORRIDO BFS
	

	public LinkedList<Integer> recorridoBfs(GrafoDirigido<T>grafo){
		LinkedList<Integer>retorno  = new LinkedList<Integer>();
		
		this.fila.clear();
		this.visitados.clear();  //NO OLVIDAR DE LIMPIAR ESTO
		
		Iterator<Integer> itVertices = grafo.obtenerVertices();
		while(itVertices.hasNext()) {
			Integer vertice = itVertices.next();
			visitados.put(vertice, false);
		}
		itVertices = grafo.obtenerVertices();
		while(itVertices.hasNext()) {
			Integer vertice = itVertices.next();
			if(visitados.get(vertice) == false) {
				retorno.addAll(BFS_Visit(grafo, vertice));
			}
		}
		return retorno;
	}
	
	
	public LinkedList<Integer> BFS_Visit(GrafoDirigido<T> grafo, int vertice){
		LinkedList<Integer>retorno  = new LinkedList<Integer>();
		visitados.put(vertice, true);
		retorno.add(vertice);  //Lo agrego a la linkedList y a la fila
		fila.add(vertice);
		while(!fila.isEmpty()) {
			Integer verticeX = fila.remove(); //VERTICE QUE ESTA EN EL TOPE DE LA FILA
			Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(verticeX); //La primera vez va a ser el que me mandan por parametro
			while(itAdyacentes.hasNext()) {
				Integer vAdyacente = itAdyacentes.next();
				if(visitados.get(vAdyacente)== false) {
					visitados.put(vAdyacente, true);
					retorno.add(vAdyacente); //LO AGREGO A LA FILA Y A LA LISTA 
					fila.add(vAdyacente);
				}
			}
		}
		return retorno;
	}
	//AGREGO ADEMAS DE A LA FILA A LA LISTA PARA PODER RETORNAR EL RECORRIDO EN UNA LISTA!
	
	
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

