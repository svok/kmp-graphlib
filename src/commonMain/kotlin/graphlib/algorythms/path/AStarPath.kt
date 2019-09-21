package graphlib.algorythms.path

import graphlib.*
import graphlib.GraphConstants.DEFAULT_WEIGHT
import graphlib.edge.IEdge
import graphlib.graph.IGraph
import graphlib.vertex.IVertex

class CirclePath(
    private val graph: IGraph,
    private val from: IVertex,
    private val to: IVertex,
    private val weightFunction: TDefaultWeightFunction = { DEFAULT_WEIGHT }
) {
    private val front: MutableMap<IVertex, OptimalPath> = hashMapOf(from to OptimalPath(from))
    private val outer: MutableSet<IVertex> = graph.vertices.toHashSet()
    private val resultantPath: MutableList<IEdge> = mutableListOf()

    fun search() {
        resultantPath.clear()
        var cnt = 0
        do {
            var done = false
            val removeFromFront = hashSetOf<IVertex>()
            for (v in front) {
                treatVertex(v.value)
                if (v.key == to) {
                    done = done || resultantPath.addAll(v.value.path)
                    return
                } else removeFromFront.add(v.key)
            }
//            removeFromFront.forEach { front.remove(it) }
        } while (done || cnt++ <50)
    }
    fun result() = resultantPath.toList()

    private fun treatVertex(v: OptimalPath) {
        var made = false
        val f = v.toVertex
        graph.edgesFor(f).forEach {
            // Here we handle reverse order of undirected edges
            val t = if (! it.isDirected && it.vertexFrom != f) it.vertexFrom else it.vertexTo
            val weight = weightFunction(it)
            when {
                outer.contains(t) -> {
                    front[t] = OptimalPath(
                        toVertex = t,
                        cost = weight,
                        vertices = v.vertices + t,
                        path = v.path + it
                    )
                    outer.remove(t)
                    made = true
                }
                front[t]?.cost?.takeIf { it < v.cost + weight } != null -> {
                    // If we found shorter path we update front value
                    front[t] = OptimalPath(
                        toVertex = t,
                        cost = weight,
                        vertices = v.vertices + t,
                        path = v.path + it
                    )
                    made = true
                }
            }
        }
    }

    fun printFront() {
        front.forEach {
            println(it)
        }
    }

    private data class OptimalPath(
        val toVertex: IVertex,
        val cost: TCost = DEFAULT_WEIGHT,
        val vertices: Set<IVertex> = setOf(),
        val path: List<IEdge> = emptyList()
    )
}