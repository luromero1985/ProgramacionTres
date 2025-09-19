package tp2_arboles;

public class MainArboles {

	public static void main(String[] args) {
	
		  Tree arbol = new Tree();

	        // Agregamos algunos elementos
	        arbol.add(10);
	        arbol.add(5);
	        arbol.add(15);
	        arbol.add(3);
	        arbol.add(8);
	        arbol.add(12);
	        arbol.add(18);
	        arbol.add(1);

	        // getRaiz
	        System.out.println("Raíz: " + arbol.getRaiz());

	        // isEmpty
	        System.out.println("¿Está vacío?: " + arbol.isEmpty());

	        // hasElem
	        System.out.println("¿Contiene el 8?: " + arbol.hasElem(8));
	        System.out.println("¿Contiene el 20?: " + arbol.hasElem(20));

	        // getAltura
	        System.out.println("Altura del árbol: " + arbol.getAltura());

	        // getLongestBranch
	        System.out.println("Rama más larga: " + arbol.getLongestBranch());

	        // getFrontera
	        System.out.println("Frontera (hojas): " + arbol.getFrontera());

	        // getMaxElem
	        System.out.println("Elemento máximo: " + arbol.getMaxElem());

	        // getElementLevel
	        System.out.println("Elementos en el nivel 2: " + arbol.getElementLevel(2));

	        // Recorridos
	        System.out.println("\nRecorrido en PreOrder:");
	        arbol.printPreOrder();

	        System.out.println("\nRecorrido en InOrder:");
	        arbol.printOrder();

	        System.out.println("\nRecorrido en PostOrder:");
	        arbol.printPosOrder();

	        // delete
	        System.out.println("\nEliminando 5...");
	        boolean eliminado = arbol.delete(5);
	        System.out.println("¿Eliminado?: " + eliminado);
	        System.out.println("Nuevo InOrder:");
	        arbol.printOrder();

	        // sumaNodosInternos
	        System.out.println("\nSuma de nodos internos: " + arbol.sumaNodosInternos());

	        // getMayorAk
	        int k = 10;
	        System.out.println("Elementos mayores a " + k + ": " + arbol.getMayorAk(k));
	    }
	}


