package tp5_backtracking_act6_forma2;

public class Posicion {

    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean esVecina(Posicion otra) {
        int df = Math.abs(this.fila - otra.fila);
        int dc = Math.abs(this.columna - otra.columna);
        return (df + dc) == 1;
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}

