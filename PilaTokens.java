public class PilaTokens {

    
    private NodoToken tope;
    private int tamanio;

    public PilaTokens() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void push(char simbolo, int posicion) {
        NodoToken nuevo = new NodoToken(simbolo, posicion);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    public NodoToken pop() {
        if (isVacia()) return null;
        NodoToken aux = tope;
        tope = tope.siguiente;
        tamanio--;
        return aux;
    }

    public NodoToken peek() {
        return tope;
    }

    public boolean isVacia() {
        return tope == null;
    }

    public int tamanio() {
        return tamanio;
    }
}

