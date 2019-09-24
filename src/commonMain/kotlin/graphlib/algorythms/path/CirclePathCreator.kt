package graphlib.algorythms.path

import graphlib.algorythms.IBuildAlgorithm
import graphlib.graph.IGraph

class CirclePathCreator: IBuildAlgorithm<ISearchPath> {
    private var graph: IGraph = IGraph.EMPTY
    override fun graph(graph: IGraph) {
        this.graph = graph
    }

    override fun create(): CirclePath = CirclePath(graph)
}
