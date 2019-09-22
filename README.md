# Kotlin Multiplatform Graph library

This is a simple multiplatform graph library. Among the platforms currently supported are
`JVM`, `JavaScript` and `Linux AMD64`. If you suppose to use other platforms feel free to
add it in this library through fork and pull request.


## Installation

### Requirements

For the linux installation the `libtinfo5` library is required. In Ubuntu you can install it as: 
```bash
sudo apt-get install libtinfo5
```

### On Linux

On any Linux system you can invoke:
```bash
gradlew build
```

### On Windows

On any Windows system you can invoke:
```bat
gradlew.bat build
```

## Usage

For the detailed examples of this library usage please see the tests. Here some of the cases
are briefly listed.

### Kotlin

```kotlin
val v1 = SimpleVertex("v1")
val v2 = SimpleVertex("v2")
val v3 = SimpleVertex("v3")
val v4 = SimpleVertex("v4")
val v5 = SimpleVertex("v5")
val v6 = SimpleVertex("v6")
val v7 = SimpleVertex("v7")

val e1 = SimpleDirectedEdge(v1, v2)
val e2 = SimpleDirectedEdge(v1, v3)
val e3 = SimpleDirectedEdge(v2, v4)
val e4 = SimpleDirectedEdge(v3, v5)
val e5 = SimpleDirectedEdge(v4, v6)
val e6 = SimpleDirectedEdge(v5, v7)
val e7 = SimpleDirectedEdge(v6, v7)

SimpleDirectedGraph() init {
    // Vertices
    +v1
    +v2
    +v3
    +v4
    +v5
    +v6
    +v7

    // Edges
    +e1
    +e2
    +e3
    +e4
    +e5
    +e6
    +e7
}

val path = graph.path(v1, v7) { edge ->
    1.0
}
```

### Java

```java
var v1 = new SimpleVertex("v1");
var v2 = new SimpleVertex("v2");
var v3 = new SimpleVertex("v3");
var v4 = new SimpleVertex("v4");
var e1 = new SimpleDirectedEdge<>(v1, v2);
var e2 = new SimpleDirectedEdge<>(v2, v3);
var e3 = new SimpleDirectedEdge<>(v1, v3);
var e4 = new SimpleDirectedEdge<>(v1, v4);
var e5 = new SimpleDirectedEdge<>(v2, v4);
var e6 = new SimpleDirectedEdge<>(v3, v4);
var graph = new GraphInMemory<>();

graph.setVertices(Arrays.asList(v1, v2, v3, v4));
graph.setEdges(Arrays.asList(e1, e2, e3, e4, e5, e6));

var edges = StreamSupport.stream(graph.getEdges().spliterator(), false).collect(Collectors.toList());
var vertices = StreamSupport.stream(graph.getVertices().spliterator(), false).collect(Collectors.toList());

var path = graph.path(v1, v4);
var pathWeighted = graph.path(v1, v4, toKotlin(e -> 1.0));
```

### JavaScript / TypeScript
TODO

### Linux AMD64 C/C++
TODO

### Custom vertices and edges

In order to implement custom logics you can create your own classes for vertices and edges. They must implement
respectively IVertex and ITypedEdge<IVertex> interfaces. To declare a class for vertices you do:
```kotlin
class MyVertex(): IVertex
```
Here you need to pay a special attention to the `hashCode` and `equal` methods since this class is used as a key
for HashMaps.

Thus, edges may be declared like that:
```kotlin
data class WeightedEdge(
    override val from: MyVertex,
    override val to: MyVertex,
    override val weight: Double
): ITypedEdge<MyVertex>
```

Then you need to declare a typealias for graph class:
```kotlin
typealias MyGraph = GraphInMemory<MyVertex, WeightedEdge>
```
Otherway you need to create your own realization for the graph. So, I recommend to inherit it from `AbstractGraph`.

Now you can fill your graph with data:
```kotlin
val myGraph = MyGraph() init {
    val v1 = MyVertex()
    val v2 = MyVertex()
    +v1
    +v2
    +WeightedEdge(v1, v2, 3.0)
}
```

... and compute the optimal path between vertices:
```kotlin
val path = graph.path(v1, v2) { edge ->
    // In this lambda you provide the weights to the path method
    edge.weight
}
```

### Concurrency

The default graph realization of `GraphInMemory` is not thread safe by default. It does not matter for JavaScript,
as it doesn't operate with threads, but that must be specially attentiond for JVM and Linux platforms.

For the JVM platform there is a decorator class implemented `ConcurrentGraph`. By default it just decorates the
`GraphInMemory` class. If you want to make your own realization concurrent you need to use it as follows:
```kotlin
val myConcurrentGraph = ConcurrentGraph(
    vertices = myVertices,
    edges = myEdges,
    instance = MyCustomGraph()
)
```

### Custom optimal path searchers

There may be different algorythms used for optimal path search. Right now there is only one implemented: [Dijkstra's 
algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm). If your graph allows 
[A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm) you better use that instead.
To do that you need your class to implement ISearchPath interface. Then you need to inject your searcher instance
to the graph class:
```kotlin
val graph = MyGraph(
    vertices = myVertices,
    edges = myEdges,
    pathSearcher = AStarPath()    
)
```
