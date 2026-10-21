# Grafo con matriz de adyacencia

Proyecto Java de consola compatible con NetBeans. Incluye grafos dirigidos y no dirigidos y no requiere bibliotecas externas.

## Abrir en NetBeans

1. Selecciona **Archivo > Abrir proyecto**.
2. Elige la carpeta que contiene este `pom.xml`.
3. Ejecuta el proyecto desde NetBeans o ejecuta la clase `com.ejemplo.grafo.Main`.

El proyecto usa Java 8 o superior.

## Operaciones

- Agregar, listar, actualizar y eliminar vértices.
- Agregar y eliminar aristas.
- Consultar los vecinos de un vértice.
- Mostrar la matriz de adyacencia.

Los vértices se identifican por nombres únicos. En el grafo dirigido, una arista `A -> B` no crea automáticamente `B -> A`. En el no dirigido, la matriz mantiene ambas posiciones.

Las clases `GrafoDirigido` y `GrafoNoDirigido` extienden `GrafoMatriz`; también puedes usarlas directamente desde otro programa Java.
