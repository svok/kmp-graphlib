package graphlib.algorythm.path

import graphlib.algorythms.path.CirclePath
import graphlib.algorythms.path.CirclePathCreator
import graphlib.graph.IGraph
import graphlib.vertex.SimpleVertex
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CirclePathCreatorTest {

    @Test
    fun createSearcherTest() {
        val creator = CirclePathCreator()
        creator.graph(IGraph.EMPTY)
        val searcher = creator.create()

        assertTrue {
            searcher is CirclePath
        }
        assertEquals(emptyList(), searcher.search(SimpleVertex(), SimpleVertex()))
    }

}
