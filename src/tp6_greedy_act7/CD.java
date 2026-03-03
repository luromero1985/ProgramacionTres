package tp6_greedy_act7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CD{

	private static final int MAXIMOCANTGENERO = 3;
	
	
	public ArrayList<Cancion> cdmaximizado(ArrayList<Cancion>canciones, int m, boolean descendente){
		int espacioUsado=0;
		ArrayList<Cancion> cancionOrdenada = ordenarDescendente(canciones);

		if(!descendente) {
		    Collections.reverse(cancionOrdenada);
		}
			
		
		ArrayList<Cancion> listaMaximizada= new ArrayList<>();
		  Map<String, Integer> contadorGenero = new HashMap<>();
		  
		  
		for(Cancion cancion : cancionOrdenada) {
			int cantidadGenero;
			if (contadorGenero.containsKey(cancion.getGenero())) {
			    cantidadGenero = contadorGenero.get(cancion.getGenero());
			} else {
			    cantidadGenero = 0;
			}
			
			if(cancion.getTamanio()+espacioUsado<=m && cantidadGenero<MAXIMOCANTGENERO) {
				contadorGenero.put(cancion.getGenero(), cantidadGenero + 1);
				listaMaximizada.add(cancion);
				espacioUsado+=cancion.getTamanio();
				}
			}

		return listaMaximizada;
		
	}
	
	
	public ArrayList<Cancion> ordenarDescendente(ArrayList<Cancion> canciones){

	    ArrayList<Cancion> copia = new ArrayList<>(canciones);
	    ArrayList<Cancion> ordenadas = new ArrayList<>();

	    while(!copia.isEmpty()){

	        Cancion mayor = copia.get(0);

	        for(Cancion c : copia){
	            if(c.getTamanio() > mayor.getTamanio()){
	                mayor = c;
	            }
	        }

	        ordenadas.add(mayor);
	        copia.remove(mayor);
	    }

	    return ordenadas;
	}
	
}
