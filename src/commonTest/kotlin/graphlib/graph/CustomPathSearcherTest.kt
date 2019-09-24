package graphlib.graph

import graphlib.SimpleDirectedGraph
import graphlib.TDefaultWeightFunction
import graphlib.algorythms.IBuildAlgorithm
import graphlib.algorythms.path.ISearchPath
import graphlib.edge.IEdge
import graphlib.edge.SimpleDirectedEdge
import graphlib.vertex.IVertex
import graphlib.vertex.SimpleVertex
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomPathSearcherTest {

    @Test
    fun testCustomPathSearcher() {
        val graph = SimpleDirectedGraph(
            vertices = listOf(v1, v2),
            edges = listOf(e1),
            pathSearcherCreator = CustomsPathSearcherCreator()
        )

        val path = graph.path(v2, v1).toList()

        assertEquals(listOf(e1), path)
    }

    private class CustomPathSearcher(val graph: IGraph): ISearchPath {
        override fun search(from: IVertex, to: IVertex, weightFunction: TDefaultWeightFunction): Collection<IEdge>
        = graph.edges.toList()
    }

    private class CustomsPathSearcherCreator: IBuildAlgorithm<ISearchPath> {
        var graph: IGraph = IGraph.EMPTY
        override fun graph(graph: IGraph) { this.graph = graph}

        override fun create(): ISearchPath = CustomPathSearcher(graph)
    }

    companion object {
        val v1 = SimpleVertex("v1")
        val v2 = SimpleVertex("v2")
        val e1 = SimpleDirectedEdge(v1, v2)
    }
}
