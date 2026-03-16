package final_26_2_26;

	import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

	public class SalaConferencias {
		private Evento [] eventosOrdenados;
		private int maxEventos;
		private ArrayList<ArrayList<Evento>> solucion;
	
	    // ===== BACKTRACKING =====
	    public void resolver(Evento[] eventos) {
	        eventosOrdenados = ordenarEventos(eventos); 
	        ArrayList<Evento> seleccion = new ArrayList<>();
	        solucion = new ArrayList<>();
	        maxEventos=0;

	        backtrack(0, seleccion, Integer.MIN_VALUE);

	        
	    }

	    private void backtrack(int idx,ArrayList<Evento> seleccion, int fin) {
	        // caso base: se procesaron todos los eventos
	        if (idx == eventosOrdenados.length) {
	           int cant=seleccion.size();
	           if(cant>maxEventos) {
	        	   //nueva mejor cantidad (descartamos las anteriores)
	        	   
	        	   maxEventos=cant;
	        	   solucion.clear();
	        	   solucion.add(new ArrayList<>(seleccion));
	           } else  if(cant==maxEventos && maxEventos >0) {
	        	   //igual cantidad, agrego como solucion alternativa
	        	   solucion.add(new ArrayList<>(seleccion));
	           }
	           return;
	        	   
	           
	        }  

	        Evento actual = eventosOrdenados[idx];
	        
	        //opcion 1:incluir el evento actual
	        //es valido si no solapa con el ultimo seleccionado
	   
	        if (actual.getInicio() >= fin) {
	            seleccion.add(actual);
	            backtrack(idx + 1, seleccion, actual.getFin());
	            seleccion.remove(seleccion.size() - 1); // backtrack
	        }
//si solapa hay poda
	        
	        // opcion 2: excluir elevento actual
	        backtrack( idx + 1, seleccion,fin);
	    }
	
		
	    // ===== OPCIÓN 1: con Arrays.sort =====
	    private Evento[] ordenarEventos(Evento[] eventosEntrada) {
	        Evento[] ordenado = eventosEntrada.clone();
	        Arrays.sort(ordenado, Comparator.comparingInt(e -> e.fin));
	        return ordenado;
	    }
/*
	    // ===== OPCIÓN 2: con Bubble Sort manual =====
	    private Evento[] ordenarEventos(Evento[] eventosEntrada) {
	        Evento[] ordenado = eventosEntrada.clone();

	        for (int i = 0; i < ordenado.length - 1; i++) {
	            for (int j = 0; j < ordenado.length - 1 - i; j++) {
	                if (ordenado[j].fin > ordenado[j + 1].fin) {
	                    Evento temp = ordenado[j];
	                    ordenado[j] = ordenado[j + 1];
	                    ordenado[j + 1] = temp;
	                }
	            }
	        }
	        return ordenado;
	    }
*/
	    
	    
	    
	    
	    static class Evento {
	        int inicio;
	        int fin;

	        public Evento(int inicio, int fin) {
	            this.inicio = inicio;
	            this.fin = fin;
	        }

	        public int getInicio() {
				return inicio;
			}

			public void setInicio(int inicio) {
				this.inicio = inicio;
			}

			public int getFin() {
				return fin;
			}

			public void setFin(int fin) {
				this.fin = fin;
			}

			@Override
	        public String toString() {
	            return "[" + inicio + "-" + fin + "]";
	        }
	    }
}
