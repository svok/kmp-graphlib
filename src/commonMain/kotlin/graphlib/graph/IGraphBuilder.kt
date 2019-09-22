package graphlib.graph

import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

interface IGraphBuilder<V: IVertex, E: ITypedEdge<V>>: ITypedGraph<V, E> {
    operator fun V.unaryPlus() {
        addVertex(this)
    }

    operator fun E.unaryPlus() {
        addEdge(this)
    }

    infix fun init(block: IGraphBuilder<V, E>.() -> Unit): IGraphBuilder<V, E> = this.apply(block)
}
