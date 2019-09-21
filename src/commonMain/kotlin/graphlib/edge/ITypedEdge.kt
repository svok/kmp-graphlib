package graphlib.edge

import graphlib.vertex.IVertex

interface ITypedEdge<V: IVertex>: IEdge {

    /**
     * Starting vertex of the edge. In case of undirected edge it is equivalent to the [[vertexTo]]
     */
    override val vertexFrom: V

    /**
     * Ending vertex of the edge. In case of undirected edge it is equivalent to the [[vertexFrom]]
     */
    override val vertexTo: V
}
