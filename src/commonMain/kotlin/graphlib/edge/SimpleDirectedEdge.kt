package graphlib.edge

import graphlib.vertex.IVertex

/**
 * A simple directed edge
 */
data class SimpleDirectedEdge<V: IVertex>(
    override val vertexFrom: V,
    override val vertexTo: V
): ITypedEdge<V> {
    override val isDirected: Boolean = true

    override fun toString(): String = "$vertexFrom -> $vertexTo"
}
