package com.ejemplo.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoMatriz {
    private final boolean dirigido;
    private final List<String> vertices;
    private boolean[][] matriz;

    protected GrafoMatriz(boolean dirigido) {
        this.dirigido = dirigido;
        this.vertices = new ArrayList<>();
        this.matriz = new boolean[0][0];
    }

    public boolean agregarVertice(String nombre) {
        String nombreValido = normalizarNombre(nombre);
        if (nombreValido == null || buscarIndice(nombreValido) >= 0) {
            return false;
        }

        int cantidad = vertices.size();
        boolean[][] nuevaMatriz = new boolean[cantidad + 1][cantidad + 1];
        for (int fila = 0; fila < cantidad; fila++) {
            System.arraycopy(matriz[fila], 0, nuevaMatriz[fila], 0, cantidad);
        }
        vertices.add(nombreValido);
        matriz = nuevaMatriz;
        return true;
    }

    public boolean actualizarVertice(String nombreActual, String nombreNuevo) {
        int indice = buscarIndice(nombreActual);
        String nuevoNombreValido = normalizarNombre(nombreNuevo);
        if (indice < 0 || nuevoNombreValido == null) {
            return false;
        }

        int indiceExistente = buscarIndice(nuevoNombreValido);
        if (indiceExistente >= 0 && indiceExistente != indice) {
            return false;
        }
        vertices.set(indice, nuevoNombreValido);
        return true;
    }

    public boolean eliminarVertice(String nombre) {
        int indiceEliminar = buscarIndice(nombre);
        if (indiceEliminar < 0) {
            return false;
        }

        int cantidad = vertices.size();
        boolean[][] nuevaMatriz = new boolean[cantidad - 1][cantidad - 1];
        for (int fila = 0, nuevaFila = 0; fila < cantidad; fila++) {
            if (fila == indiceEliminar) {
                continue;
            }
            for (int columna = 0, nuevaColumna = 0; columna < cantidad; columna++) {
                if (columna == indiceEliminar) {
                    continue;
                }
                nuevaMatriz[nuevaFila][nuevaColumna++] = matriz[fila][columna];
            }
            nuevaFila++;
        }
        vertices.remove(indiceEliminar);
        matriz = nuevaMatriz;
        return true;
    }

    public boolean agregarArista(String origen, String destino) {
        int indiceOrigen = buscarIndice(origen);
        int indiceDestino = buscarIndice(destino);
        if (indiceOrigen < 0 || indiceDestino < 0) {
            return false;
        }

        matriz[indiceOrigen][indiceDestino] = true;
        if (!dirigido) {
            matriz[indiceDestino][indiceOrigen] = true;
        }
        return true;
    }

    public boolean eliminarArista(String origen, String destino) {
        int indiceOrigen = buscarIndice(origen);
        int indiceDestino = buscarIndice(destino);
        if (indiceOrigen < 0 || indiceDestino < 0
                || !matriz[indiceOrigen][indiceDestino]) {
            return false;
        }

        matriz[indiceOrigen][indiceDestino] = false;
        if (!dirigido) {
            matriz[indiceDestino][indiceOrigen] = false;
        }
        return true;
    }

    public List<String> listarVertices() {
        return Collections.unmodifiableList(new ArrayList<>(vertices));
    }

    public List<String> obtenerAdyacentes(String nombre) {
        int indice = buscarIndice(nombre);
        if (indice < 0) {
            return Collections.emptyList();
        }

        List<String> adyacentes = new ArrayList<>();
        for (int columna = 0; columna < vertices.size(); columna++) {
            if (matriz[indice][columna]) {
                adyacentes.add(vertices.get(columna));
            }
        }
        return Collections.unmodifiableList(adyacentes);
    }

    public boolean existeVertice(String nombre) {
        return buscarIndice(nombre) >= 0;
    }

    public boolean esDirigido() {
        return dirigido;
    }

    public void mostrarMatriz() {
        if (vertices.isEmpty()) {
            System.out.println("El grafo no tiene vertices.");
            return;
        }

        System.out.printf("%-14s", "");
        for (String vertice : vertices) {
            System.out.printf("%-14s", vertice);
        }
        System.out.println();
        for (int fila = 0; fila < vertices.size(); fila++) {
            System.out.printf("%-14s", vertices.get(fila));
            for (int columna = 0; columna < vertices.size(); columna++) {
                System.out.printf("%-14s", matriz[fila][columna] ? "1" : "0");
            }
            System.out.println();
        }
    }

    private int buscarIndice(String nombre) {
        String nombreValido = normalizarNombre(nombre);
        return nombreValido == null ? -1 : vertices.indexOf(nombreValido);
    }

    private String normalizarNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        String nombreNormalizado = nombre.trim();
        return nombreNormalizado.isEmpty() ? null : nombreNormalizado;
    }
}
