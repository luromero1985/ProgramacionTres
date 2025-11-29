package parcial_11_6_24_Backtracking_palabras;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



/* ejercicios resueltos con pseudo-java
 * 
Dado un conjunto de C letras (que tiene mas de 4 elementos), se desean generar todas las palabras validas que contengan exactamente 4 letras sin repetir y no empiecen con una vocal. Se supone que  contamos con una clase Diccionario que nos permite verificar  si una secuencia de letras es una palabra valida.  <Diccionario.esPalabraValida(String)>
Por ejemplo, con C={E,R;N,O,M,A,T} se deberia generar una solucion compuesta por {MANO, MONA,REMO,MORA,RAMO,ROEN,TOMA,ROTA,etc}
a) dibujar el arbol de exploracion del algoritmo, indicando que decisiones se toman en cada paso y que informacion se lleva en los estados
b)escribir el algoritmo en pseudo-java que resuelva el problema mediante la tecnica de backtracking
c) ¿se podria plantear alguna poda que minimice la cantidad de estados generados por el backtracking?
      Poda por letras repetidas, Poda por longitud,Poda por restricción de primera letra
*/
public class BacktrackingLetras {

    private ArrayList<String>soluciones;
   	private Diccionario diccionario;
    	
    public BacktrackingLetras(Diccionario d) {
    	this.diccionario=d;
    }
    	
    
    public boolean esVocal(String letra) {
    	return (letra.equals("a")||letra.equals("e")||letra.equals("i")||letra.equals("o")||letra.equals("u"));
    }
    
    
    
    public List<String>encontrarPalabras(ArrayList<String>letrasDisponibles){
    	
    	this.soluciones = new ArrayList<>();
    	
    	  for (int i = 0; i < letrasDisponibles.size(); i++) {
    		   String letra = letrasDisponibles.get(i);
    		   //la primer letra no puede ser vocal
    		   if(!esVocal(letra)) {
    			  String palabra=letra;
    			  //copia de la lista sin la letra usada
    			  ArrayList<String> nuevasDisponibles = new ArrayList<>(letrasDisponibles);
    		     nuevasDisponibles.remove(i);
    		     
    		     //llamada a BAck
    	    this.encontrarPalabrasBack(palabra, nuevasDisponibles);   // palabra vacía, todas las letras disponibles
    	   }
    	  }
    	    return soluciones;
    	}

    
    
    	private void encontrarPalabrasBack(String palabra, ArrayList<String> disponibles) {
    		HashSet<String> usadasEnEsteNivel = new HashSet<>();  //controla que no haya ramas repetidas
    		//estado final
    		if(palabra.length()==4) {   
    			if(diccionario.esPalabraValida(palabra)) {
    				this.soluciones.add(palabra);
    			}
    			return;
    		}
    		//caso recursivo
    		
    				for(int i = 0; i < disponibles.size(); i++) {
    					String letra= disponibles.get(i);
    					if (!usadasEnEsteNivel.contains(letra)) {
    						usadasEnEsteNivel.add(letra);
    					
    						String nuevaPalabra=palabra+letra;
    						ArrayList<String> nuevasDisponibles=new ArrayList<>(disponibles);
    						nuevasDisponibles.remove(i);
    						
    						encontrarPalabrasBack(nuevaPalabra, nuevasDisponibles);
    					}
    					
    				}
    	}
}

