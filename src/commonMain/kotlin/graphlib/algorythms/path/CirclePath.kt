package graphlib.algorythms.path

import graphlib.GraphConstants.DEFAULT_WEIGHT
import graphlib.GraphConstants.INFINITY_WEIGHT
import graphlib.GraphConstants.ZERRO_WEIGHT
import graphlib.TCost
import graphlib.TDefaultWeightFunction
import graphlib.edge.IEdge
import graphlib.graph.IGraph
import graphlib.vertex.IVertex

class CirclePath(
    private var graph: IGraph
) : ISearchPath {

    // Search for shortest path
    override fun search(
        from: IVertex,
        to: IVertex,
        weightFunction: TDefaultWeightFunction
    ): Collection<IEdge> {
        val outer: MutableMap<IVertex, PathInfo> = graph
            .vertices
            .map {
                it to if (it == from) {
                    PathInfo(it, cost = ZERRO_WEIGHT)
                } else {
                    PathInfo(it, cost = INFINITY_WEIGHT)
                }
            }
            .toMap()
            .toMutableMap()

        var queue = hashSetOf<IVertex>(from)
        var resultPathInfo = outer[to]

        do {
            val auxPipe = queue
                .asSequence()
                .flatMap {
                    val edges = graph.edgesFor(it)
                    val pathInfo = outer[it] ?: return@flatMap emptySequence<Pair<PathInfo, IEdge>>()
                    edges.asSequence().map { pathInfo to it }
                }
                .onEach {
                    val edge = it.second
                    val pathInfo = it.first
                    val vertexFrom = pathInfo.toVertex
                    val vertexTo = edge.nextTo(vertexFrom) ?: return@onEach
                    val oPathInfo = outer[vertexTo] ?: return@onEach
                    val newCost = pathInfo.cost + weightFunction(edge)
                    if (newCost < oPathInfo.cost) {
                        val newPathInfo = PathInfo(
                            toVertex = vertexTo,
                            cost = newCost,
                            vertexPath = pathInfo.vertexPath + vertexTo,
                            edgePath = pathInfo.edgePath + edge
                        )
                        outer[vertexTo] = newPathInfo
                        if (vertexTo == to) {
                            resultPathInfo = newPathInfo
                        }
                    }
                }
                .toList()
            queue = auxPipe
                .map { it.second.nextTo(it.first.toVertex) }
                .filterNotNull()
                .filter { outer.containsKey(it) }
                .toHashSet()
            auxPipe
                .map { it.first.toVertex }
                .distinct()
                .forEach { outer.remove(it) }
        } while (queue.isNotEmpty())

        return resultPathInfo?.edgePath ?: emptyList()
    }

    private data class PathInfo(
        val toVertex: IVertex,
        val cost: TCost = DEFAULT_WEIGHT,
        val vertexPath: Set<IVertex> = setOf(),
        val edgePath: List<IEdge> = emptyList()
    )

}
