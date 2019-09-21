package graphlib.graph

import graphlib.vertex.IVertex
import kotlin.test.Test
import kotlin.test.assertEquals

internal class IGraphTest {
    @Test
    fun EMPTYTest() {
        assertEquals(emptyList(), IGraph.EMPTY.edges.toList())
        assertEquals(emptyList(), IGraph.EMPTY.vertices.toList())
        assertEquals(emptyList(), IGraph.EMPTY.edgesFor(object: IVertex {}).toList())
    }
}