# Acava: Graphs Documentation

## Creating a graph
### Mutable graph
```java
Graph<Number> graph = Graph.newBuilder()
    .setHeight(15) // Height of the graph
    .setMaxDisplayValue(15) // Max value to display
    .setMutability(Mutability.MUTABLE) // Mutability of the graph
    .build(); // Build the graph
```

### Immutable graph
Throws `IllegalArgumentException: Cannot build immutable graph without nodes. Use addImmutableNodes(...)` if no nodes were added.
```java
 Graph<Number> graph = Graph.newBuilder()
    .setHeight(15) // Height of the graph
    .setMaxDisplayValue(15) // Max value to display
    .setImmutable() // Mutability of the graph
    .addImmutableNodes(1, 2, 3, 4) // Required if the graph is immutable
    .build(); // Build the graph
```

### Fixed size graph
```java
Graph<Number> graph = Graph.newBuilder()
    .setHeight(15) // Height of the graph - optional
    .setMaxDisplayValue(15) // Max value to display - optional
    .setFixedSize(20) // The max size of the graph
    .build(); // Build the graph
```

## Adding values to the graph
```java
graph.addNode(1);
graph.addNodes(Arrays.asList(1, 2, 3, 4, 5, 6));
graph.addNodes(4, 2, 1, 4, 5, 6, 2, 4, 1, 2, 4, 5, 6, 2, 1, 4, 5, 6, 2, 4, 2, 1, 4, 2, 1, 2, 4, 5)
```

## Visualization of the Graph
### Getting the graph as a string
```java
String result = graph.getVisualizer().toString();
```
### Output
```
  6 ┤░ ░ ░ ░ ░ █ ░ ░ ░ ░ ░ ░ █ ░ ░ ░ ░ █ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ 
    │░ ░ ░ ░ ░ █ ░ ░ ░ ░ ░ ░ █ ░ ░ ░ ░ █ ░ ░ ░ ░ ░ ░ ░ ░ ░ ░ 
    │░ ░ ░ ░ ▓ █ ░ ░ ░ ░ ░ ▓ █ ░ ░ ░ ▓ █ ░ ░ ░ ░ ░ ░ ░ ░ ░ ▓ 
    │░ ░ ░ ░ █ █ ░ ░ ░ ░ ░ █ █ ░ ░ ░ █ █ ░ ░ ░ ░ ░ ░ ░ ░ ░ █ 
    │▒ ░ ░ ▒ █ █ ░ ▒ ░ ░ ▒ █ █ ░ ░ ▒ █ █ ░ ▒ ░ ░ ▒ ░ ░ ░ ▒ █ 
    │█ ░ ░ █ █ █ ░ █ ░ ░ █ █ █ ░ ░ █ █ █ ░ █ ░ ░ █ ░ ░ ░ █ █ 
    │█ ░ ░ █ █ █ ░ █ ░ ░ █ █ █ ░ ░ █ █ █ ░ █ ░ ░ █ ░ ░ ░ █ █ 
    │█ ░ ░ █ █ █ ░ █ ░ ░ █ █ █ ░ ░ █ █ █ ░ █ ░ ░ █ ░ ░ ░ █ █ 
    │█ ░ ░ █ █ █ ░ █ ░ ░ █ █ █ ░ ░ █ █ █ ░ █ ░ ░ █ ░ ░ ░ █ █ 
    │█ ▒ ░ █ █ █ ▒ █ ░ ▒ █ █ █ ▒ ░ █ █ █ ▒ █ ▒ ░ █ ▒ ░ ▒ █ █ 
    │█ █ ░ █ █ █ █ █ ░ █ █ █ █ █ ░ █ █ █ █ █ █ ░ █ █ ░ █ █ █ 
    │█ █ ░ █ █ █ █ █ ░ █ █ █ █ █ ░ █ █ █ █ █ █ ░ █ █ ░ █ █ █ 
    │█ █ ▓ █ █ █ █ █ ▓ █ █ █ █ █ ▓ █ █ █ █ █ █ ▓ █ █ ▓ █ █ █ 
    │█ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ 
  0 ┼█ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ █ 
    └-------------------------------------------------------─
      4  2  1  4  5  6  2  4  1  2  4  5  6  2  1  4  5  6  2  4  2  1  4  2  1  2  4  5 
```

### Getting a simple string representation of the graph
````java
String result = graph.getVisualizer().simpleVisualization();
````
### Output
```
4 -> 2 -> 1 -> 4 -> 5 -> 6 -> 2 -> 4 -> 1 -> 2 -> 4 -> 5 -> 6 -> 2 -> 1 -> 4 -> 5 -> 6 -> 2 -> 4 -> 2 -> 1 -> 4 -> 2 -> 1 -> 2 -> 4 -> 5
```


