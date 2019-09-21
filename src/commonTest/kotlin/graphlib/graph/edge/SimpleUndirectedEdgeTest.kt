package graphlib.graph.edge

import graphlib.edge.SimpleUndirectedEdge
import graphlib.vertex.SimpleVertex
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleUndirectedEdgeTest {
    @Test
    fun edgeNextTo() {
        val v1 = SimpleVertex("1")
        val v2 = SimpleVertex("2")
        val v3 = SimpleVertex("3")
        val edge = SimpleUndirectedEdge(v1, v2)
        assertEquals(v1, edge.nextTo(v2))
        assertEquals(v2, edge.nextTo(v1))
        assertEquals(null, edge.nextTo(v3))
    }

}
