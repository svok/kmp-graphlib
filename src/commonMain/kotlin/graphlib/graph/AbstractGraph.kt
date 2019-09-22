package graphlib.graph

import graphlib.GraphConstants
import graphlib.TWeightFunction
import graphlib.algorythms.path.ISearchPath
import graphlib.edge.IEdge
import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

abstract class AbstractGraph<V: IVertex, E: ITypedEdge<V>>
    : ITypedGraph<V, E>, IGraphBuilder<V, E> {

    protected abstract val innerVertices: MutableMap<V, GraphVertex<V, E>>
    protected abstract val pathSearcher: ISearchPath

    override var vertices: Iterable<V>
        get() = innerVertices.keys.asIterable()
        set(value) {
            innerVertices.clear()
            value.forEach(this::addVertex)
        }

    override var edges: Iterable<E>
        get() = innerVertices.flatMap { it.value.edges.values }.distinctBy { it }.asIterable()
        set(value) {
            cleanUpEdges()
            value.forEach(this::addEdge)
        }

    override fun edgesFor(vertex: IVertex): Iterable<E> = innerVertices[vertex]?.edges?.values ?: emptyList()
    override fun hasVertex(vertex: IVertex): Boolean = innerVertices.containsKey(vertex)

    override fun addVertex(vertex: V) {
        val sameVertex = innerVertices[vertex]?.vertex
        when {
//            sameVertex != null && sameVertex !== vertex ->
//                throw ConflictingVertexException("You are trying to add a vertex with an ID [$vertex] that has been registered in the graphlib.graph")
            sameVertex == null -> innerVertices[vertex] = GraphVertex(vertex)
        }
    }

    override fun addEdge(edge: E) {
        addVertex(edge.vertexFrom)
        addVertex(edge.vertexTo)
        innerVertices[edge.vertexFrom]?.addEdge(edge) ?: throw RuntimeException("Unexpected: vertexFrom isn't registered in repository")

        // If this is undirected edge we add it to both starting and ending vertices
        if (! edge.isDirected) {
            innerVertices[edge.vertexTo]?.addEdge(edge) ?: throw RuntimeException("Unexpected: vertexTo isn't registered in repository")
        }

    }


    private fun cleanUpEdges() {
        innerVertices.forEach { it.value.edges.clear() }
    }


    /**
     * Compute the shortest path between vertices [[from]] and [[to]] with the edge weight computed in [[weightBlock]]
     *
     * @param from Starting vertex of the path
     * @param to Ending vertex of the path
     * @param weightBlock A lambda function that yields weights for the edges. By default all weights are 1.0
     */
    @Suppress("UNCHECKED_CAST")
    override fun path(from: IVertex, to: IVertex, weightBlock: TWeightFunction<E>): Collection<E> = pathSearcher.search(
            from = from,
            to = to,
            weightFunction = { edge -> weightBlock(edge as E) }
        ).map { it as E }

    override fun path(from: IVertex, to: IVertex): Iterable<IEdge> = path(from, to) { GraphConstants.DEFAULT_WEIGHT }

    private fun reconstructPath(): List<V> {
        val path = mutableListOf<V>()
        return path.toList()
    }

    protected data class GraphVertex<VI: IVertex, EI: ITypedEdge<VI>>(
        val vertex: VI,
        val edges: MutableMap<VI, EI> = mutableMapOf()
    ) {
        fun addEdge(edge: EI) {
            if (edge.isDirected) {
                edges[edge.vertexTo] = edge
            } else {
                edge.nextTo(vertex)?.also { edges[it] = edge }
            }
        }
    }
}
