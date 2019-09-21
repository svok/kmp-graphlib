package graphlib.edge

import graphlib.vertex.IVertex

interface IEdge {

    /**
     * A helper property that signifies if the edge is directed or undirected
     */
    val isDirected: Boolean

    /**
     * Starting vertex of the edge. In case of undirected edge it is equivalent to the [[vertexTo]]
     */
    val vertexFrom: IVertex

    /**
     * Ending vertex of the edge. In case of undirected edge it is equivalent to the [[vertexFrom]]
     */
    val vertexTo: IVertex
}
