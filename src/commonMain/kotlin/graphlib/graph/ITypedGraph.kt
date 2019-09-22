package graphlib.graph

import graphlib.GraphConstants
import graphlib.TWeightFunction
import graphlib.edge.IEdge
import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

interface ITypedGraph<V: IVertex, E: ITypedEdge<V>>: IGraph {

    override var vertices: Iterable<V>
    override var edges: Iterable<E>

    /**
     * Add a vertex to the graphlib.graph
     *
     * @param vertex A vertext to be added to the graphlib.graph
     */
    fun addVertex(vertex: V)

    /**
     * Add an edge to the graphlib.graph. For directed vertices `vFrom -> vTo`. For the undirected graphs
     *
     * @param edge The edge to be added to the graph
     */
    fun addEdge(edge: E)

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
        weightBlock: TWeightFunction<E>
    ): Iterable<IEdge>

    fun path(
        from: IVertex,
        to: IVertex
    ): Iterable<IEdge> = path(from, to) { GraphConstants.DEFAULT_WEIGHT }

}
