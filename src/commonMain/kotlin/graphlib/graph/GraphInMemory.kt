package graphlib.graph

import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

class GraphInMemory<V: IVertex, E: ITypedEdge<V>>(vertices: Iterable<V> = listOf(), edges: Iterable<E> = listOf())
    : AbstractGraph<V, E>() {
    override val innerVertices: MutableMap<V, GraphVertex<V, E>> = hashMapOf()

    constructor(vararg vertices: V): this(vertices = vertices.asIterable())
    constructor(vararg edges: E): this(edges = edges.asIterable())

    init {
        vertices.forEach { addVertex(it) }
        edges.forEach { addEdge(it) }
    }

}
