package graphlib.graph.edge

import graphlib.edge.SimpleDirectedEdge
import graphlib.vertex.SimpleVertex
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleDirectedEdgeTest {
    @Test
    fun edgeNextTo() {
        val v1 = SimpleVertex("1")
        val v2 = SimpleVertex("2")
        val v3 = SimpleVertex("3")
        val edge = SimpleDirectedEdge(v1, v2)
        assertEquals(null, edge.nextTo(v2))
        assertEquals(v2, edge.nextTo(v1))
        assertEquals(null, edge.nextTo(v3))
    }

}
