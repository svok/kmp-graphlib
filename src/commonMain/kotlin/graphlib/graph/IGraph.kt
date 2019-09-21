package graphlib.graph

import graphlib.edge.IEdge
import graphlib.vertex.IVertex

interface IGraph {
    val edges: Iterable<IEdge>
    val vertices: Iterable<IVertex>

    fun edgesFor(vertex: IVertex): Iterable<IEdge>
    fun hasVertex(vertex: IVertex): Boolean

    companion object EMPTY: IGraph {
        override val edges: Iterable<IEdge>
            get() = emptyList()
        override val vertices: Iterable<IVertex>
            get() = emptyList()

        override fun edgesFor(vertex: IVertex): Iterable<IEdge> = emptyList()
        override fun hasVertex(vertex: IVertex): Boolean = false
    }
}