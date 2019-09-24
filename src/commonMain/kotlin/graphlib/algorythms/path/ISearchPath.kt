package graphlib.algorythms.path

import graphlib.GraphConstants.DEFAULT_WEIGHT
import graphlib.TDefaultWeightFunction
import graphlib.edge.IEdge
import graphlib.graph.IGraph
import graphlib.vertex.IVertex

/**
 * Interface for shortest path search algorithms. There may be many realization of the search, so, we use this interface
 * for all of them
 */
interface ISearchPath {

    /**
     * Search function fo find out an optimal path between vertices [[from]] and [[to]] with the edge weight function
     * [[weightFunction]]
     *
     * @param from The starting vertex to search the most optimal path
     * @param to The ending vertex to search the most optimal path
     * @param weightFunction The function that is called to compute the weight of the edge. Normally it should extract
     * some parameters like distance from the edge object of class implementing [[IEdge]] interface
     *
     * @return A collection of edges those form a path
     */
    fun search(
        from: IVertex,
        to: IVertex,
        weightFunction: TDefaultWeightFunction = { DEFAULT_WEIGHT }
    ): Collection<IEdge>
}
