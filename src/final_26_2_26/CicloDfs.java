package final_26_2_26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp6_greedy_act8.Grafo;

public class CicloDfs {
private HashMap<Integer, String>colores;


public ArrayList<Integer>ciclo(Grafo <Integer>g){
	
	this.colores=new HashMap<>();
	ArrayList<Integer>camino=new ArrayList<>();
	
	//pongo en blanco los vertices
	Iterator <Integer> it=g.obtenerVertices();
	while(it.hasNext()) {
		colores.put(it.next(), "blanco");
	}
	
	
	//recorro los vértices
	it=g.obtenerVertices();
	while(it.hasNext()) {
		Integer vertice=it.next();
		if(this.colores.get(vertice).equals("blanco")) {
			if(dfsVisit(g,vertice,camino)) {
				return camino;
			}
		}
	}
	return null;
}


private boolean dfsVisit(Grafo<Integer>g, Integer v, ArrayList<Integer>camino) {
	this.colores.put(v, "amarillo");
	camino.add(v);
	Iterator<Integer> itAdy=g.obtenerAdyacentes(v);
	
	while(itAdy.hasNext()) {
		Integer vAdy=itAdy.next();
		if(this.colores.get(vAdy).equals("amarillo")) {   //encontre el ciclo
			int idx=0;
			for(int i=0; i<camino.size();i++) {
				if(camino.get(i).equals(vAdy)) {
					idx=i;
					break;
				}
			}
			//borro todo lo que este antes del ciclo
			
			for(int i=0; i<idx; i++) {
				camino.remove(0);
				}
			return true;    //camino es el ciclo
		}
	
		if(colores.get(vAdy).equals("blanco")) {
			if(dfsVisit(g,vAdy,camino)) {
				return true;
			}
		}
	}
		colores.put(v, "negro");
		camino.remove(camino.size()-1);
		return false;
	}


}
