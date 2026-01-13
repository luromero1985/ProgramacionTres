package tp5_backtracking_act6_forma2;


import java.util.ArrayList;
import java.util.List;

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
        return df + dc == 1;
    }

    public List<Posicion> vecinos() {
        List<Posicion> v = new ArrayList<>();
        v.add(new Posicion(fila + 1, columna));
        v.add(new Posicion(fila - 1, columna));
        v.add(new Posicion(fila, columna + 1));
        v.add(new Posicion(fila, columna - 1));
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Posicion)) return false;
        Posicion p = (Posicion) o;
        return fila == p.fila && columna == p.columna;
    }
}
