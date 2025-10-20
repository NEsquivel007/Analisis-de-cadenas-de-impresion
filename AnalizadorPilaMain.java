import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnalizadorPilaMain {

    // --------- Utilidades de análisis ---------
    private static boolean esApertura(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean esCierre(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean coinciden(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
               (apertura == '[' && cierre == ']') ||
               (apertura == '{' && cierre == '}');
    }

    // Analiza la cadena usando la pila e imprime el proceso
    private static void analizarCadena(String cadena) {
        PilaTokens pila = new PilaTokens();

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if (esApertura(c)) {
                pila.push(c, i);
                System.out.println("Se apila '" + c + "' en posición " + i);
            } else if (esCierre(c)) {
                if (pila.isVacia()) {
                    System.out.println("Error: símbolo de cierre '" + c +
                            "' en posición " + i + " sin apertura correspondiente.");
                    return;
                }

                NodoToken abierto = pila.pop();
                System.out.println("Se desapila '" + abierto.simbolo + "' para cerrar con '" + c + "'");

                if (!coinciden(abierto.simbolo, c)) {
                    System.out.println("Error: se abrió con '" + abierto.simbolo +
                            "' en posición " + abierto.posicion +
                            " y se intentó cerrar con '" + c + "' en posición " + i + ".");
                    return;
                }
            }
        }

        if (!pila.isVacia()) {
            System.out.print("Error: faltan cierres para: ");
            while (!pila.isVacia()) {
                NodoToken restante = pila.pop();
                System.out.print("'" + restante.simbolo + "'@" + restante.posicion + " ");
            }
            System.out.println();
        } else {
            System.out.println("CADENA CORRECTA: todos los símbolos están balanceados.");
        }
    }

    // Lee una línea de entrada
    private static String leerLinea(BufferedReader br) throws IOException {
        String linea = br.readLine();
        if (linea == null) return ""; // si EOF, devolver vacío para no romper
        return linea;
    }

    // Menú principal 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n=== Analizador de Cadenas con Pila ===");
            System.out.println("1) Llenado de la pila (ingresar y analizar cadena)");
            System.out.println("2) Salir");
            System.out.print("Seleccione una opción: ");

            String opcionStr = leerLinea(br).trim();
            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr); 
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido (1 o 2).");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la cadena o expresión a analizar: ");
                    String entrada = leerLinea(br);
                    analizarCadena(entrada);
                }
                case 2 -> {
                    System.out.println("Saliendo... ¡Gracias!");
                    return;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

