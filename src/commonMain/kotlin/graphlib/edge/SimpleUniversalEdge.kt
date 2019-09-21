package graphlib.edge

import graphlib.vertex.IVertex

/**
 * A simple edge that can be utilized both as directed as undirected
 */
data class SimpleUniversalEdge<V: IVertex>(
    override val vertexFrom: V,
    override val vertexTo: V,
    override val isDirected: Boolean
): ITypedEdge<V>
