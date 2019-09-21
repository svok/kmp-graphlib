package graphlib

import graphlib.vertex.SimpleVertex
import graphlib.edge.IEdge
import graphlib.edge.SimpleDirectedEdge
import graphlib.edge.SimpleUndirectedEdge
import graphlib.graph.GraphInMemory

typealias TCost = Double

typealias TWeightFunction<E> = (E) -> TCost
typealias TDefaultWeightFunction = TWeightFunction<IEdge>

typealias SimpleDirectedGraph = GraphInMemory<SimpleVertex, SimpleDirectedEdge<SimpleVertex>>
typealias SimpleUndirectedGraph = GraphInMemory<SimpleVertex, SimpleUndirectedEdge<SimpleVertex>>

object GraphConstants {
    const val DEFAULT_WEIGHT: TCost = 1.0
    const val ZERRO_WEIGHT: TCost = 0.0
    val INFINITY_WEIGHT: TCost = Double.MAX_VALUE
}
