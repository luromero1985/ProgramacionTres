package tp2_arboles;

import java.util.ArrayList;


public class Tree {

	private Node raiz; 
	
	public Tree() {
		this.raiz=null;
	}
	

	
	public Integer getRaiz() {
		return (this.raiz!=null)? this.raiz.getValor():null;
		
		/* (condicion) ? rta si es verdad: reta si es falso*/
	}
	
	//la anterior es la forma abreviada de 
/*	public Integer getRaiz() {
		if(this.raiz!=null) {
			return this.raiz.getValor();
		}else {
			 return null;
	}
	}
	*/
	
	//ADD SI TENGO UN PUNTERO AL ANTERIOR, TENGO QUE IR ACTUALIZANDOLO SIEMPRE!
	
			public void add(Integer valor) {
				if (this.raiz == null) {
					this.raiz = new Node(valor);
				} else {
					this.add(this.raiz,valor);
			}
			}
			private void add(Node actual, Integer valor) {
				if (actual.getValor() > valor) {
					if (actual.getIzq() == null) { 
						Node temp = new Node(valor);
						actual.setIzq(temp);
						temp.setAnterior(actual); //Le seteo el anterior
					} else {
						add(actual.getIzq(),valor);
					}
				} else if (actual.getValor() < valor) {
					if (actual.getDer() == null) { 
						Node temp = new Node(valor);
						actual.setDer(temp);
						temp.setAnterior(actual); //Le seteo el anterior
					} else {
						add(actual.getDer(),valor);
					}
				}
			}
			
			
			public boolean isEmpty() {
				return this.raiz==null;
			}
			
			
			public boolean hasElem(Integer valor) {
			    return buscar(this.raiz, valor);
			}

			private boolean buscar(Node actual, Integer valor) {
			    if (actual == null) {
			        return false;
			    }
			    if (actual.getValor().equals(valor)) {
			        return true;
			    } else if (valor < actual.getValor()) {
			        return buscar(actual.getIzq(), valor);
			    } else {
			        return buscar(actual.getDer(), valor);
			    }
			}
			
			public int getAltura() {
			    if (this.raiz == null) {
			        return 0;
			    }
			    return calcularAltura(this.raiz) - 1;
			}

			private int calcularAltura(Node nodo) {
			    if (nodo == null) {
			        return 0;
			    }

			    int alturaIzq = calcularAltura(nodo.getIzq());
			    int alturaDer = calcularAltura(nodo.getDer());

			    int mayorAltura;
			    if (alturaIzq > alturaDer) {
			        mayorAltura = alturaIzq;
			    } else {
			        mayorAltura = alturaDer;
			    }

			    return 1 + mayorAltura;
			}

			
			public ArrayList<Integer> getLongestBranch() {
			    ArrayList<Integer> resultado = new ArrayList<>();
			    if (this.raiz != null) {
			        resultado = getLongestBranch(this.raiz);
			    }
			    return resultado;
			}

			private ArrayList<Integer> getLongestBranch(Node nodo) {
			    ArrayList<Integer> camino = new ArrayList<>();
			    camino.add(nodo.getValor());

			    // Caso base: es una hoja
			    if (nodo.getIzq() == null && nodo.getDer() == null) {
			        return camino;
			    }

			    // Obtener caminos de izquierda y derecha
			    ArrayList<Integer> caminoIzq = new ArrayList<>();
			    ArrayList<Integer> caminoDer = new ArrayList<>();

			    if (nodo.getIzq() != null) {
			        caminoIzq = getLongestBranch(nodo.getIzq());
			    }

			    if (nodo.getDer() != null) {
			        caminoDer = getLongestBranch(nodo.getDer());
			    }

			    // Agregar al camino el más largo
			    if (caminoIzq.size() > caminoDer.size()) {
			        camino.addAll(caminoIzq);
			    } else {
			        camino.addAll(caminoDer);
			    }

			    return camino;
			}

			public ArrayList<Integer> getFrontera() {
			    ArrayList<Integer> resultado = new ArrayList<>();
			    if (this.raiz != null) {
			        resultado.addAll(getFrontera(this.raiz));
			    }
			    return resultado;
			}

			private ArrayList<Integer> getFrontera(Node nodo) {
			    ArrayList<Integer> resultado = new ArrayList<>();
			    
			    if (nodo.getIzq() == null && nodo.getDer() == null) {
			        // Es hoja
			        resultado.add(nodo.getValor());
			    } else {
			        if (nodo.getIzq() != null) {
			            resultado.addAll(getFrontera(nodo.getIzq()));
			        }
			        if (nodo.getDer() != null) {
			            resultado.addAll(getFrontera(nodo.getDer()));
			        }
			    }
			    
			    return resultado;
			}

			
			public Integer getMaxElem() {
				Integer elemento=null;
				if(this.raiz!=null) {
					elemento=getMaxElem(this.raiz);
				}
				return elemento;
			}
			
			private Integer getMaxElem(Node nodo) {
				Integer elemento= nodo.getValor();
				if(nodo.getDer()!=null) {
					elemento=getMaxElem(nodo.getDer());
				}
				return elemento;
			}
			
			
			
			public ArrayList<Integer> getElementLevel(int level){
				ArrayList<Integer>resultado = new ArrayList<Integer>();
				if(this.raiz !=null) {
					resultado.addAll(getElementLevel(this.raiz,level));
				}
				return resultado;
			}
			
			private ArrayList<Integer>getElementLevel(Node nodo, int level){
				ArrayList<Integer>resultado = new ArrayList<Integer>();

				if(level == 0) {  //Condicion de corte
					resultado.add(nodo.getValor());
				}
				else {
					if(nodo.getIzq()!=null) {
						resultado.addAll(getElementLevel(nodo.getIzq(), level - 1)); //para llevar el valor a 0 y entraria al primer if
					}
					if(nodo.getDer()!= null) {
						resultado.addAll(getElementLevel(nodo.getDer(), level -1)); //no olvidar el resultado.addAll
					}
				}
				return resultado;
			}
			
			public void printPreOrder() {
				if(this.raiz!=null) {
					printPreOrder(this.raiz);
				}
			}
			
			private void printPreOrder(Node nodo) {
				if(nodo!=null) {
				System.out.println(nodo.getValor());
				printPreOrder(nodo.getIzq());
				printPreOrder(nodo.getDer());					
				}
							}
			
			
			public void printOrder() {
				if(this.raiz!=null) {
					printOrder(this.raiz);
				}
			}
			private void printOrder(Node nodo) {
				
				if(nodo!=null) {
					printOrder(nodo.getIzq());
					System.out.println(nodo.getValor());
					printOrder(nodo.getDer());
				}
			}
			
			
			public void printPosOrder() {
				if(this.raiz!=null) {
					printPosOrder(this.raiz);
				}
			}
			
			private void printPosOrder(Node nodo) {
				if(nodo!=null) {
					printPosOrder(nodo.getIzq());
					printPosOrder(nodo.getDer());
					System.out.println(nodo.getValor());
					}
			}
			
			public boolean delete(int valor) {
				if(this.raiz != null) {
				return delete(this.raiz, valor);
			}
				return false;
			}
			
			
			
			private boolean delete(Node nodo, int valor) {
			    if (nodo == null) {
			    	return false;
			    }

			    if (valor < nodo.getValor() && nodo.getIzq() != null) {
			        return delete(nodo.getIzq(), valor);
			    } else if (valor > nodo.getValor() && nodo.getDer() != null) {
			        return delete(nodo.getDer(), valor);
			    } else if (valor == nodo.getValor()) {

			        // CASO 1: HOJA
			        if (nodo.getIzq() == null && nodo.getDer() == null) {
			            if (nodo.getAnterior() == null) {
			                this.raiz = null;
			            } else if (nodo.getAnterior().getIzq() == nodo) {
			                nodo.getAnterior().setIzq(null);
			            } else {
			                nodo.getAnterior().setDer(null);
			            }
			            return true;
			        }

			        // CASO 2: UN SOLO HIJO
			        if (nodo.getIzq() == null || nodo.getDer() == null) {
			            Node hijo = (nodo.getIzq() != null) ? nodo.getIzq() : nodo.getDer();

			            if (nodo.getAnterior() == null) {
			                this.raiz = hijo;
			                hijo.setAnterior(null);
			            } else if (nodo.getAnterior().getIzq() == nodo) {
			                nodo.getAnterior().setIzq(hijo);
			                hijo.setAnterior(nodo.getAnterior());
			            } else {
			                nodo.getAnterior().setDer(hijo);
			                hijo.setAnterior(nodo.getAnterior());
			            }

			            return true;
			        }

			        // CASO 3: DOS HIJOS
			        int nmd = getMaxElem(nodo.getIzq());
			        nodo.setValor(nmd);
			        return delete(nodo.getIzq(), nmd);
			    }

			    return false;
			}

			
			
			
			//ejercicio 2
			
			/*
			 * Dado un arbol binario de busqueda que almacena numeros
			 *  enteros , implementar un algoritmo que retorne la suma
			 *   de todos los nodos internos del arbol*/
			//NOTA: nodo interno, es aquel que tiene al menos un hijo
			
			
			public int sumaNodosInternos() {
				if(this.raiz==null) {
					return 0;
				}
				return sumaNodosInternos(this.raiz);
			}
			
			
		private int sumaNodosInternos(Node nodo) {
			int suma=0;
			if(nodo.getIzq()!=null || nodo.getDer()!=null) {
			suma+=nodo.getValor();
			}
			if (nodo.getIzq() != null) {
				suma += sumaNodosInternos(nodo.getIzq()); 
			}
			if (nodo.getDer() != null) {
				suma += sumaNodosInternos(nodo.getDer());
			}
			return suma;
		}
			
			
		//ejercicio 3
		
		public ArrayList<Integer> getMayorAk(int k){
		    ArrayList<Integer> rta = new ArrayList<>();
		    if (this.raiz != null) {
		        rta.addAll(getMayorAk(this.raiz, k));
		    }
		    return rta;
		}

		private ArrayList<Integer> getMayorAk(Node nodo, int k) {
		    ArrayList<Integer> rta = new ArrayList<>();

		    if (nodo.getIzq() == null && nodo.getDer() == null) {
		        if (nodo.getValor() > k) {
		            rta.add(nodo.getValor());
		        }
		    } else {
		        if (nodo.getIzq() != null) {
		            rta.addAll(getMayorAk(nodo.getIzq(), k));
		        }
		        if (nodo.getDer() != null) {
		            rta.addAll(getMayorAk(nodo.getDer(), k));
		        }
		    }

		    return rta;
		}

		
		
		//ejercicio 4
		
		public void completarNodoEnArbolBinario() {
			if(this.raiz!=null) {
				completarNodoEnArbolBinario(this.raiz);
			}
		}
		

		private int completarNodoEnArbolBinario(Node nodo) {
		    // Caso base: si es hoja, devolvemos su valor
		    if (nodo.getIzq() == null && nodo.getDer() == null) {
		        return nodo.getValor();
		    }

		    // Obtenemos valor del hijo izquierdo, o 0 si no existe
		    int izq = 0;
		    if (nodo.getIzq() != null) {
		        izq = completarNodoEnArbolBinario(nodo.getIzq());
		    }

		    // Obtenemos valor del hijo derecho, o 0 si no existe
		    int der = 0;
		    if (nodo.getDer() != null) {
		        der = completarNodoEnArbolBinario(nodo.getDer());
		    }

		    // Calculamos la resta y asignamos el valor
		    int valorNodo = der - izq;
		    nodo.setValor(valorNodo);
		    return valorNodo;
		}


}
