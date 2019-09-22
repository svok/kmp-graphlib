package graphlib.graph

import graphlib.algorythms.path.CirclePath
import graphlib.algorythms.path.ISearchPath
import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

class GraphInMemory<V: IVertex, E: ITypedEdge<V>>(
    vertices: Iterable<V> = listOf(),
    edges: Iterable<E> = listOf(),
    override val pathSearcher: ISearchPath = CirclePath()
)
    : AbstractGraph<V, E>() {
    override val innerVertices: MutableMap<V, GraphVertex<V, E>> = hashMapOf()
    init {
        pathSearcher.graph(this)
    }

    constructor(vararg vertices: V): this(vertices = vertices.asIterable())
    constructor(vararg edges: E): this(edges = edges.asIterable())

    init {
        vertices.forEach { addVertex(it) }
        edges.forEach { addEdge(it) }
    }

}
