package tp5_backtracking_act10;

import java.util.ArrayList;

//---------------------------------------------------------------//
	/*
	 * Usando la tecnica Backtracking, escribir un algoritmo que dado un conjunto de números enteros, devuelva (si existen) todos los subconjuntos de tamaño N
	 * (dado como parámetro), cuyas sumas sean exactamente cero. Por ejemplo dado el conjunto {-7,-3,-1,5,8} y N=3, los subconjuntos que suman cero son
	 * {-7,-1,8} y {-3,-2,5}/
//---------------------------------------------------------------//
	*/
public class SumaCero{
private int tamanio;
private ArrayList<Integer>conjunto;
private ArrayList<ArrayList<Integer>>solucion;

public SumaCero() {
	this.conjunto=new ArrayList<>();
}

//pasariamos los numeros del conjunto
public void agregarNumero(int n) {
    conjunto.add(n);
}


public ArrayList<ArrayList<Integer>> buscarSumasNulas(int n){
	this.solucion= new ArrayList<>();
	this.tamanio = n;
	backSumas(new ArrayList<>(), 0, 0);
	return solucion;
}


private void backSumas(ArrayList<Integer> subconjunto, int pos, int sumaActual){
	
	//comentario:  no se inicializa el subconjutno dentro de back, porque romperia el backtracking (no hacer subconjunto = new ArrayList<>();
   //tampoco iniciar la suma( int suma = 0;) porque en cada llamada la pone en cero
	 if (subconjunto.size() == tamanio) {
	        if (sumaActual == 0) {
	            solucion.add(new ArrayList<>(subconjunto));
	        }
	        return;
	    }

	    for (int i = pos; i < conjunto.size(); i++) {
	        subconjunto.add(conjunto.get(i));
	        backSumas(subconjunto, i + 1, sumaActual + conjunto.get(i));
	        subconjunto.remove(subconjunto.size() - 1);  //no eliminar por valor, sino hacerlo por posicion, estaria mal hacer:  subconjunto.remove(conjunto.get(i));
	    }
}
}
