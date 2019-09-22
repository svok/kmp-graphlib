package graphlib.graph

import graphlib.TCost
import graphlib.edge.IEdge
import graphlib.edge.SimpleDirectedEdge
import graphlib.vertex.SimpleVertex

typealias SimpleConcurrentDirectedGraph = ConcurrentGraph<SimpleVertex, SimpleDirectedEdge<SimpleVertex>>

object Functional
{
    @JvmStatic
    fun <E: IEdge> toKotlin(function: Function1<E, TCost>): (E) -> TCost {
        return {e -> function.invoke(e)}
    }
}
