package graphlib

import graphlib.edge.SimpleUndirectedEdge
import graphlib.graph.AbstractGraph
import graphlib.graph.IGraphBuilder
import graphlib.graph.ITypedGraph
import graphlib.vertex.SimpleVertex
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

// Should support 2 types of graphs - directed and undirected
class GraphUndirectedTest() {

    @Test
    fun constructorTest() {
        val graph = SimpleUndirectedGraph(SimpleVertex("id"))
        val vertices = graph.vertices.toList()
        assertEquals(1, vertices.size)
    }

    // addVertex - adds vertex to the graphlib.graph
    @Test
    fun addVertex() {
        val graph = SimpleUndirectedGraph()
        graph.addVertex(SimpleVertex("id"))
        val vertices = graph.vertices.toList()
        assertEquals(1, vertices.size)
    }

    // addEdge - adds edge to the graphlib.graph
    @Test
    fun addEdge() {
        val graph = SimpleUndirectedGraph()
        graph.addEdge(SimpleUndirectedEdge(SimpleVertex("1"), SimpleVertex("2")))
        val edges = graph.edges.toList()
        assertEquals(1, edges.size)
        val vertices = graph.vertices.toList()
        assertEquals(2, vertices.size)
    }

    @Test
    fun buildGraph() {
        val graph = createGraph()
        assertEquals(7, graph.vertices.count())
        assertEquals(7, graph.edges.count())
        assertEquals(2, graph.edgesFor(v1).count())
        assertEquals(2, graph.edgesFor(v5).count())
        assertEquals(2, graph.edgesFor(v7).count())
    }

    @Test
    fun pathTest() {
        val graph = createGraph()
        val way = graph.path(v1, v7).toList()
        assertEquals(
            listOf(
                SimpleUndirectedEdge(v1, v3),
                SimpleUndirectedEdge(v3, v5),
                SimpleUndirectedEdge(v5, v7)
            ),
            way
        )
    }

    private fun createGraph(): ITypedGraph<SimpleVertex, SimpleUndirectedEdge<SimpleVertex>> = SimpleUndirectedGraph() init {
        // Vertices
        +v1
        +v2
        +v3
        +v4
        +v5
        +v6
        +v7

        // Edges
        +SimpleUndirectedEdge(v1, v2)
        +SimpleUndirectedEdge(v1, v3)
        +SimpleUndirectedEdge(v2, v4)
        +SimpleUndirectedEdge(v5, v3)
        +SimpleUndirectedEdge(v4, v6)
        +SimpleUndirectedEdge(v5, v7)
        +SimpleUndirectedEdge(v6, v7)
    }

    companion object {
        val v1 = SimpleVertex("v1")
        val v2 = SimpleVertex("v2")
        val v3 = SimpleVertex("v3")
        val v4 = SimpleVertex("v4")
        val v5 = SimpleVertex("v5")
        val v6 = SimpleVertex("v6")
        val v7 = SimpleVertex("v7")
    }
}
