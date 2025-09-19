package tp4_grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


	public class GrafoNoDirigido<T> implements Grafo<T> {
	    private HashMap<Integer, LinkedList<Arco<T>>> vertices;

	    public GrafoNoDirigido() {
	        this.vertices = new HashMap<>();
	    }

	    @Override
	    public void agregarVertice(int verticeId) {
	        if (!contieneVertice(verticeId)) {
	            vertices.put(verticeId, new LinkedList<>());
	        }
	    }

	    @Override
	    public void borrarVertice(int verticeId) {
	        if (contieneVertice(verticeId)) {
	            // borro el vértice
	            vertices.remove(verticeId);

	            // elimino todos los arcos que llegaban a él
	            Iterator<Integer> itVertices = obtenerVertices();
	            while (itVertices.hasNext()) {
	                int v = itVertices.next();
	                borrarArco(v, verticeId);
	            }
	        }
	    }

	    @Override
	    public boolean existeArco(int origen, int destino) {
	        return obtenerArco(origen, destino) != null;
	    }

	    @Override
	    public Arco<T> obtenerArco(int origen, int destino) {
	        if (vertices.containsKey(origen)) {
	            for (Arco<T> arco : vertices.get(origen)) {
	                if (arco.getVerticeDestino() == destino) {
	                    return arco;
	                }
	            }
	        }
	        return null;
	    }

	    @Override
	    public void agregarArco(int origen, int destino, T etiqueta) {
	        if (contieneVertice(origen) && contieneVertice(destino)) {
	            if (!existeArco(origen, destino)) {
	                Arco<T> arco1 = new Arco<>(origen, destino, etiqueta);
	                Arco<T> arco2 = new Arco<>(destino, origen, etiqueta); // espejo

	                vertices.get(origen).add(arco1);
	                vertices.get(destino).add(arco2);
	            }
	        }
	    }

	    @Override
	    public boolean borrarArco(int origen, int destino) {
	        boolean borrado = false;

	        if (contieneVertice(origen)) {
	            Iterator<Arco<T>> it = vertices.get(origen).iterator();
	            while (it.hasNext()) {
	                Arco<T> arco = it.next();
	                if (arco.getVerticeDestino() == destino) {
	                    it.remove();
	                    borrado = true;
	                    break;
	                }
	            }
	        }

	        if (contieneVertice(destino)) {
	            Iterator<Arco<T>> it = vertices.get(destino).iterator();
	            while (it.hasNext()) {
	                Arco<T> arco = it.next();
	                if (arco.getVerticeDestino() == origen) {
	                    it.remove();
	                    break;
	                }
	            }
	        }

	        return borrado;
	    }

	    @Override
	    public boolean contieneVertice(int verticeId) {
	        return vertices.containsKey(verticeId);
	    }

	    @Override
	    public int cantidadVertices() {
	        return vertices.size();
	    }

	    @Override
	    public int cantidadArcos() {
	        int cant = 0;
	        for (Integer v : vertices.keySet()) {
	            cant += vertices.get(v).size();
	        }
	        return cant / 2; // cada arco se cuenta dos veces (ida y vuelta)
	    }

	    @Override
	    public Iterator<Integer> obtenerVertices() {
	        return vertices.keySet().iterator();
	    }

	    @Override
	    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
	        LinkedList<Integer> adyacentes = new LinkedList<>();
	        if (contieneVertice(verticeId)) {
	            for (Arco<T> arco : vertices.get(verticeId)) {
	                adyacentes.add(arco.getVerticeDestino());
	            }
	        }
	        return adyacentes.iterator();
	    }

	    @Override
	    public Iterator<Arco<T>> obtenerArcos() {
	        LinkedList<Arco<T>> arcosTotales = new LinkedList<>();

	        Iterator<Integer> itVertices = obtenerVertices();
	        while (itVertices.hasNext()) {
	            Integer v = itVertices.next();
	            for (Arco<T> arco : vertices.get(v)) {
	                int origen = arco.getVerticeOrigen();
	                int destino = arco.getVerticeDestino();

	                // Solo guardo el arco si origen < destino (criterio fijo), para que no haya duplicados
	                if (origen < destino) {
	                    arcosTotales.add(arco);
	                }
	            }
	        }
	        return arcosTotales.iterator();
	    }
	    
	    
	    
	    
	    @Override
	    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
	        if (vertices.containsKey(verticeId)) {
	            return vertices.get(verticeId).iterator();
	        }
	        return new ArrayList<Arco<T>>().iterator();
	    }
	}


