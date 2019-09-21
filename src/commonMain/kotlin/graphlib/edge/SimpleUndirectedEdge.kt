package graphlib.edge

import graphlib.vertex.IVertex

data class SimpleUndirectedEdge<V: IVertex>(
    override val vertexFrom: V,
    override val vertexTo: V
): ITypedEdge<V> {
    override val isDirected: Boolean = false
}
