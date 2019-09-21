package graphlib.graph

import graphlib.GraphConstants
import graphlib.TDefaultWeightFunction
import graphlib.TWeightFunction
import graphlib.edge.IEdge
import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

interface IGraphOperational<V: IVertex, E: ITypedEdge<V>> {
    /**
     * Compute the shortest path between vertices [[from]] and [[to]] with the edge weight computed in [[weightBlock]]
     *
     * @param from Starting vertex of the path
     * @param to Ending vertex of the path
     * @param weightBlock A lambda function that yields weights for the edges. By default all weights are 1.0
     */
    fun path(
        from: IVertex,
        to: IVertex,
        weightBlock: TWeightFunction<E> = { GraphConstants.DEFAULT_WEIGHT }
    ): Iterable<IEdge>
}