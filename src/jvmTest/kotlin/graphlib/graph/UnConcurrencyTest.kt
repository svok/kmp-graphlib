package graphlib.graph

import graphlib.SimpleDirectedGraph
import graphlib.edge.SimpleDirectedEdge
import graphlib.vertex.SimpleVertex
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import kotlin.test.assertEquals
import kotlin.test.assertFails

internal class UnConcurrencyTest {

        @Test
        fun concurrencyAccessMustFailTest() {
            val nThreads = 32
            val graph = createGraph()
            assertFails {
                runBlocking(Executors.newFixedThreadPool(nThreads).asCoroutineDispatcher()) {
                    repeat(nThreads * 1000) {
                        launch {
                            graph.edges = listOf(e1, e2, e3, e4, e5, e6, e7)
                            val edges = graph.edges.toList()
                            assertThat(edges).containsExactlyInAnyOrder(e1, e2, e3, e4, e5, e6, e7)
                        }
                    }
                }
            }
        }

        @Test
        fun concurrencyPathTest() {
            val nThreads = 32
            val graph = createGraph()
            runBlocking(Executors.newFixedThreadPool(nThreads).asCoroutineDispatcher()) {
                repeat(nThreads*1000) {
                    launch {
                        val path = graph.path(v1, v7)
                        assertThat(path).containsExactly(e2, e4, e6)
                    }
                }
            }
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
            +e1
            +e2
            +e3
            +e4
            +e5
            +e6
            +e7
        }

        companion object {
            val v1 = SimpleVertex("v1")
            val v2 = SimpleVertex("v2")
            val v3 = SimpleVertex("v3")
            val v4 = SimpleVertex("v4")
            val v5 = SimpleVertex("v5")
            val v6 = SimpleVertex("v6")
            val v7 = SimpleVertex("v7")

            val e1 = SimpleDirectedEdge(v1, v2)
            val e2 = SimpleDirectedEdge(v1, v3)
            val e3 = SimpleDirectedEdge(v2, v4)
            val e4 = SimpleDirectedEdge(v3, v5)
            val e5 = SimpleDirectedEdge(v4, v6)
            val e6 = SimpleDirectedEdge(v5, v7)
            val e7 = SimpleDirectedEdge(v6, v7)

        }
}
