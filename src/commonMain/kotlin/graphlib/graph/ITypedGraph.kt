package graphlib.graph

import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex

interface ITypedGraph<V: IVertex, E: ITypedEdge<V>>: IGraph {

    override var vertices: Iterable<V>
    override var edges: Iterable<E>

    /**
     * Add a vertex to the graph
     *
     * @param vertex A vertext to be added to the graph
     */
    fun addVertex(vertex: V)

    /**
     * Add an edge to the graph. For directed vertices `vFrom -> vTo`. For the undirected graphs
     *
     * @param vFrom First vertex of the edge
     * @param vTo Second vertex of the edge
     */
    fun addEdge(edge: E)

}
