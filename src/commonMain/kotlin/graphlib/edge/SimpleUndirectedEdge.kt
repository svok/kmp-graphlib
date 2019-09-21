package graphlib.edge

import graphlib.vertex.IVertex

class SimpleUndirectedEdge<V: IVertex>(
    override val vertexFrom: V,
    override val vertexTo: V
): ITypedEdge<V> {
    override val isDirected: Boolean = false

    override fun nextTo(vertex: IVertex): V? = when (vertex) {
        vertexFrom -> vertexTo
        vertexTo -> vertexFrom
        else -> null
    }

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other == null -> false
        (other as? SimpleUndirectedEdge<*>) == null -> false
        other.vertexFrom == this.vertexFrom && other.vertexTo == this.vertexTo -> true
        other.vertexFrom == this.vertexTo && other.vertexTo == this.vertexFrom -> true
        else -> false
    }

    override fun hashCode(): Int {
        var result = vertexFrom.hashCode()
        result = 31 * result + vertexTo.hashCode()
        return result
    }

    override fun toString(): String = "$vertexFrom -> $vertexTo"
}
