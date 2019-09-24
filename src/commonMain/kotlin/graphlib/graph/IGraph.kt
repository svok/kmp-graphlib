package graphlib.graph

import graphlib.edge.IEdge
import graphlib.vertex.IVertex

interface IGraph {
    /**
     * Return all edges of the graph
     */
    val edges: Iterable<IEdge>

    /**
     * Return all vertices of the graph
     */
    val vertices: Iterable<IVertex>

    /**
     * Return all edges of where [[vertex]] is starting vertex for directed edge or one of the vertices for undirected
     * edge
     */
    fun edgesFor(vertex: IVertex): Iterable<IEdge>

    /**
     * Checks if the [[vertex]] is registered in the graph
     */
    fun hasVertex(vertex: IVertex): Boolean

    /**
     * Performs some operation on the graph. It is required for concurrency safe access to the graph.
     * By default it is not thread safe since not all platforms support thread safety
     */
    fun <T> handle(type: GraphLockType = GraphLockType.READ, block: IGraph.() -> T) = this.block()

    companion object EMPTY: IGraph {
        override val edges: Iterable<IEdge>
            get() = emptyList()
        override val vertices: Iterable<IVertex>
            get() = emptyList()

        override fun edgesFor(vertex: IVertex): Iterable<IEdge> = emptyList()
        override fun hasVertex(vertex: IVertex): Boolean = false
    }
}
