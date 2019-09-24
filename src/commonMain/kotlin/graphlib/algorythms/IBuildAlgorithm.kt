package graphlib.algorythms

import graphlib.graph.IGraph

/**
 * Interface for specific operations objects creators. Those objects can be injected to the [[IGraph]] instances
 */
interface IBuildAlgorithm<T> {

    /**
     * Set the graph object to the built object
     * @param graph [[IGraph]] object where the optimal path will be searched out
     */
    fun graph(graph: IGraph)
    fun create(): T
}
