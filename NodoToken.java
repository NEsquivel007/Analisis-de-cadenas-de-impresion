public class NodoToken {
    char simbolo;  // Carácter del token: (, ), [, ], {, }
    int posicion; // Índice en la cadena original
    NodoToken siguiente; // Referencia al siguiente nodo en la pila
     


    public NodoToken(char simbolo, int posicion) {
        this.simbolo = simbolo;
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return simbolo + "@" + posicion;
    }
}

