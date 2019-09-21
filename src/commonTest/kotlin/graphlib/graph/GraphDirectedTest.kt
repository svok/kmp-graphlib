package graphlib

import graphlib.edge.ITypedEdge
import graphlib.edge.SimpleDirectedEdge
import graphlib.graph.AbstractGraph
import graphlib.graph.GraphInMemory
import graphlib.graph.IGraph
import graphlib.graph.ITypedGraph
import graphlib.vertex.SimpleVertex
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

typealias Graph = SimpleDirectedGraph

// Should support 2 types of graphs - directed and undirected
class DirectedGraphTest() {

    @Test
    fun constructorTest() {
        val graph = Graph(SimpleVertex("id"))
        val vertices = graph.vertices.toList()
        assertEquals(1, vertices.size)
    }

    // addVertex - adds vertex to the graph
    @Test
    fun addVertex() {
        val graph = Graph()
        graph.addVertex(SimpleVertex("id"))
        val vertices = graph.vertices.toList()
        assertEquals(1, vertices.size)
    }

    @Test
    fun addVertexSameIdThrowsException() {
        val graph = Graph()
        graph.addVertex(SimpleVertex("id"))
        assertFails {
            graph.addVertex(SimpleVertex("id"))
        }
    }

    // addEdge - adds edge to the graph
    @Test
    fun addEdge() {
        val graph = Graph()
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

    fun createGraph(): AbstractGraph<SimpleVertex, SimpleDirectedEdge<SimpleVertex>> = Graph() init {
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
