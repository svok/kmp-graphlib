package graphlib

import graphlib.edge.SimpleDirectedEdge
import graphlib.graph.AbstractGraph
import graphlib.graph.ITypedGraph
import graphlib.vertex.SimpleVertex
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

// Should support 2 types of graphs - directed and undirected
class GraphDirectedTest() {

    @Test
    fun constructorTest() {
        val graph = SimpleDirectedGraph(SimpleVertex("id"))
        val vertices = graph.vertices.toList()
        assertEquals(1, vertices.size)
    }

    // addVertex - adds vertex to the graphlib.graph
    @Test
    fun addVertex() {
        val graph = SimpleDirectedGraph()
        graph.addVertex(SimpleVertex("id"))
        val vertices = graph.vertices.toList()
        assertEquals(1, vertices.size)
    }

    // addEdge - adds edge to the graphlib.graph
    @Test
    fun addEdge() {
        val graph = SimpleDirectedGraph()
        graph.addEdge(SimpleDirectedEdge(SimpleVertex("1"), SimpleVertex("2")))
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
        assertEquals(1, graph.edgesFor(v5).count())
        assertEquals(0, graph.edgesFor(v7).count())
    }

    @Test
    fun pathTest() {
        val graph = createGraph()
        val way = graph.path(v1, v7).toList()
        assertEquals(
            listOf(
                SimpleDirectedEdge(v1, v3),
                SimpleDirectedEdge(v3, v5),
                SimpleDirectedEdge(v5, v7)
            ),
            way
        )
    }

    private fun createGraph(): ITypedGraph<SimpleVertex, SimpleDirectedEdge<SimpleVertex>> = SimpleDirectedGraph() init {
        // Vertices
        +v1
        +v2
        +v3
        +v4
        +v5
        +v6
        +v7

        // Edges
        +SimpleDirectedEdge(v1, v2)
        +SimpleDirectedEdge(v1, v3)
        +SimpleDirectedEdge(v2, v4)
        +SimpleDirectedEdge(v3, v5)
        +SimpleDirectedEdge(v4, v6)
        +SimpleDirectedEdge(v5, v7)
        +SimpleDirectedEdge(v6, v7)
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
