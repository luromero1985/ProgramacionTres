package Final_18_12_25;
/*
 * Ejercicio 3
 * 	Dado el mismo problema del ejercicio 2, resuélvalo mediante la técnica Backtracking.
 * 
 * PROBLEMA:
 * Dado un conjunto de n ciudades y conociendo las distancias entre cada par de ellas (decida Ud. en qué estructura/s de datos
 * le vendrá dada esta información), se desea encontrar un circuito cerrado que:
 * 	comience y termine en la misma ciudad,
 * 	visite cada ciudad exactamente una vez (debe visitar todas),
 * 	tenga distancia total mínima.
 * El algoritmo debe devolver la lista de ciudades que deberá visitar, desde una ciudad origen A dada como parámetro. 
 * Se supone que termina el viaje yendo desde la última ciudad de la lista hacia la ciudad A cerrando el circuito requerido.
 * 
 * 
 * 		a) ¿Es de alguna utilidad el resultado que da el algoritmo greedy para ser aplicado al algoritmo que lo resuelve mediante Backtracking? Explique.
 * 	
 * El resultado del greedy es útil como cota superior inicial para la poda del backtracking. Si el greedy encontró un circuito de distancia X, 
 * durante el backtracking podemos podar cualquier rama cuya distancia parcial ya supere X, ya que nunca podrá mejorar la solución conocida. 
 * Esto reduce significativamente el espacio de búsqueda. Sin embargo el greedy por sí solo no garantiza el óptimo, por eso se necesita el 
 * backtracking para explorar todas las posibilidades.		
 * 
 * b) Dibuje el árbol de búsqueda con todas las marcaciones que considere necesarias.
 * 
 * 		[ A | B |  C|  D]
 * 	 A  [ 0 | 1 | 5 | 2 ]
 * 	 B  [ 1 | 0 | 10| 12]        con greedy el camino solución es : [A-B-C-D-A]=1+10+20+2=33
 * 	 C  [ 5 | 10| 0 | 20]
 * 	 D  [ 2 | 12| 20| 0 ]
 * cota inicial=33
 				     	A 
 				      (d=0)
          +1/           |+5              \+2
           B		    C			       D
         (d=1)         (d=5)  	         (d=2)
    +10 /    \+12    +10/   \+20     +12 /    \+20
       C      D        B      D          B     C
     (d=11) (d=13)   (d=15) (d=25)✗  (d=14)   (d=22)
   +20 |    +20|    +12|     +12|     +10|    +10|
       D       C       D        B        C       B
     d=31    d=33     d=27    d=37✗    ✓d=24     d=32✗
             poda             poda    cota=24      poda 
     +2|              +2|                +5|
       A                A                  A
     d=33             d=29 (cota=29)     d=29✗
    ✓                                     poda
    
    mejor respuesta:d=29      recorrido[A-C-B-D-A]->  distancia=5+10+12+2=29
    
 * 
 * 
 * 
 * 		c) Escriba el código JAVA que resuelva el problema mediante Backtracking. El algoritmo debe tener una estrategia de poda.
 * */


public class ViajanteBacktracking {
	
	private int cota;
	private int[][]mat;
	
	

}
