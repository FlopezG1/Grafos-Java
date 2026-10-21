package com.ejemplo.grafo;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner ENTRADA = new Scanner(System.in);

    public static void main(String[] args) {
        GrafoMatriz grafo = elegirGrafo();
        System.out.println("Grafo " + (grafo.esDirigido() ? "dirigido" : "no dirigido") + " creado.");

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            if (opcion == 1) {
                informar(grafo.agregarVertice(leerTexto("Nombre del vertice: ")),
                        "Vertice agregado.", "No se pudo agregar. El nombre debe ser unico y no estar vacio.");
            } else if (opcion == 2) {
                mostrarVertices(grafo.listarVertices());
            } else if (opcion == 3) {
                String nombreActual = leerTexto("Nombre actual: ");
                String nombreNuevo = leerTexto("Nombre nuevo: ");
                informar(grafo.actualizarVertice(nombreActual, nombreNuevo),
                        "Vertice actualizado.", "No se pudo actualizar. Revisa los nombres.");
            } else if (opcion == 4) {
                informar(grafo.eliminarVertice(leerTexto("Vertice a eliminar: ")),
                        "Vertice eliminado.", "No existe ese vertice.");
            } else if (opcion == 5) {
                String origen = leerTexto("Vertice de origen: ");
                String destino = leerTexto("Vertice de destino: ");
                informar(grafo.agregarArista(origen, destino),
                        "Arista agregada.", "No se pudo agregar. Verifica que existan ambos vertices.");
            } else if (opcion == 6) {
                String origen = leerTexto("Vertice de origen: ");
                String destino = leerTexto("Vertice de destino: ");
                informar(grafo.eliminarArista(origen, destino),
                        "Arista eliminada.", "No existe esa arista o alguno de los vertices.");
            } else if (opcion == 7) {
                String vertice = leerTexto("Vertice: ");
                if (!grafo.existeVertice(vertice)) {
                    System.out.println("No existe ese vertice.");
                } else {
                    System.out.println("Adyacentes: " + grafo.obtenerAdyacentes(vertice));
                }
            } else if (opcion == 8) {
                grafo.mostrarMatriz();
            } else if (opcion == 0) {
                continuar = false;
            } else {
                System.out.println("Opcion no valida.");
            }
        }
        System.out.println("Programa finalizado.");
    }

    private static GrafoMatriz elegirGrafo() {
        while (true) {
            System.out.println("Seleccione el tipo de grafo:");
            System.out.println("1. Dirigido");
            System.out.println("2. No dirigido");
            int opcion = leerOpcion();
            if (opcion == 1) {
                return new GrafoDirigido();
            }
            if (opcion == 2) {
                return new GrafoNoDirigido();
            }
            System.out.println("Opcion no valida.");
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("1. Agregar vertice");
        System.out.println("2. Listar vertices");
        System.out.println("3. Actualizar vertice");
        System.out.println("4. Eliminar vertice");
        System.out.println("5. Agregar arista");
        System.out.println("6. Eliminar arista");
        System.out.println("7. Consultar adyacentes");
        System.out.println("8. Mostrar matriz de adyacencia");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }

    private static int leerOpcion() {
        String texto = ENTRADA.nextLine().trim();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return ENTRADA.nextLine();
    }

    private static void mostrarVertices(List<String> vertices) {
        if (vertices.isEmpty()) {
            System.out.println("El grafo no tiene vertices.");
            return;
        }
        for (int indice = 0; indice < vertices.size(); indice++) {
            System.out.println((indice + 1) + ". " + vertices.get(indice));
        }
    }

    private static void informar(boolean resultado, String correcto, String error) {
        System.out.println(resultado ? correcto : error);
    }
}
