package Final_12_2_26;

import java.util.ArrayList;
/*
 * Ejercicio 2
 * 
 * Se quiere organizar la distribución de invitados a las mesas en una fiesta de casamiento.
 * Hay N invitados, y M mesas de K lugares cada mesa (siendo M*K >= N).
 * 
 * Se dispone de una función de afinidad que dada una lista de entre 1 y K invitados que se sentarán a una mesa, devuelve un valor entero 
 * positivo de afinidad entre esas personas, a mayor valor devuelva, mayor afinidad entre ellas.
 * 
 * public int calcularAfinidad(List<Invitado> grupo)
 * 
 * Escriba un algoritmo en JAVA mediante la técnica Backtracking que resuelva el problema considerando:
 * Debe retornar la asignación de invitados a las mesas que maximice el valor total de afinidad.
 * Pueden quedar mesas vacías (tendrán afinidad cero) o incompletas, pero todos los invitados deben tener una mesa asignada.
 * 
 * 	a) Dibuje el árbol de exploración del algoritmo, indicando qué decisiones se toman en cada paso y qué información se lleva en los estados.
 * 	b) Escriba el algoritmo en JAVA solicitado.
 * 	c) ¿Se puede plantear una poda para este ejercicio? De ser posible, explicarla con sus palabras.
 * 
 * Poda de factibilidad: si una mesa ya tiene K invitados, no se puede asignar nadie más a ella. Esta poda es directa y se implementa con el
 *  chequeo contadorMesa[mesa] >= K.
 * 
 * */
public class InvitadosBack {

	public class Invitado{
		private String  nombre;
		
		public Invitado(String n) {
			this.nombre=n;
		}
		
		public String getNombre() {
			return nombre;
		}
		
	}
	

	    private int M, K, N;
	    private ArrayList<Invitado> invitados;
	    private int[] mejorAsignacion;
	    private int mejorAfinidad;

	    public int[] resolver(ArrayList<Invitado> invitados, int M, int K) {
	        this.invitados = new ArrayList<> (invitados);
	        this.M = M;
	        this.K = K;
	        this.N = invitados.size();
	        this.mejorAfinidad = -1;
	        this.mejorAsignacion = new int[N];

	        int[] asignacion = new int[N];      // asignacion[i] = mesa del invitado i
	        int[] contadorMesa = new int[M];    // cantidad de personas en cada mesa
	        
	        ArrayList<ArrayList<Invitado>> gruposEnMesa = new ArrayList<>();
	        
	        for (int m = 0; m < M; m++) {
	        	gruposEnMesa.add(new ArrayList<>());
	        }
	        
	        backtrack(0, asignacion, contadorMesa, gruposEnMesa);
	        return mejorAsignacion;
	    }

	    
	    private void backtrack(int indice, int[] asignacion, int[] contadorMesa,ArrayList<ArrayList<Invitado>> gruposEnMesa) {

	    	 if (indice == N) {
	             int afinidadTotal = 0;
	             for (int m = 0; m < M; m++) {
	                 if (!gruposEnMesa.get(m).isEmpty()) {
	                     afinidadTotal += calcularAfinidad(gruposEnMesa.get(m));
	                 }
	             }
	             if (afinidadTotal > mejorAfinidad) {
	                 mejorAfinidad = afinidadTotal;
	                 for (int i = 0; i < N; i++) {
	                     mejorAsignacion[i] = asignacion[i];
	                 }
	             }
	             return;
	         }

	         Invitado invitado = invitados.get(indice);

	         for (int mesa = 0; mesa < M; mesa++) {

	             // Poda: mesa llena
	             if (contadorMesa[mesa] >= K) continue;

	             // Asignar
	             gruposEnMesa.get(mesa).add(invitado);
	             asignacion[indice] = mesa;
	             contadorMesa[mesa]++;

	             backtrack(indice + 1, asignacion, contadorMesa, gruposEnMesa);

	             // Deshacer
	             contadorMesa[mesa]--;
	             gruposEnMesa.get(mesa).remove(gruposEnMesa.get(mesa).size() - 1);
	         }
	    }

	    public int calcularAfinidad(ArrayList<Invitado> gruposEnMesa) {
	        // Implementada externamente
	        return 0;
	    }
	}
	
	

