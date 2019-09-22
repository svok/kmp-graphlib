package graphlib.graph

import graphlib.TWeightFunction
import graphlib.edge.IEdge
import graphlib.edge.ITypedEdge
import graphlib.vertex.IVertex
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * Thread safe decorator for the [[GraphInMemory]] class
 */
class ConcurrentGraph<V : IVertex, E : ITypedEdge<V>>(
    vertices: Iterable<V> = listOf(),
    edges: Iterable<E> = listOf(),
    private val instance: AbstractGraph<V, E> = GraphInMemory<V, E>(vertices, edges)
) : ITypedGraph<V, E>, IGraphBuilder<V, E> {
    private val lock = ReentrantReadWriteLock()

    constructor(vararg vertices: V) : this(vertices = vertices.asIterable())
    constructor(vararg edges: E) : this(edges = edges.asIterable())

    init {
        vertices.forEach { addVertex(it) }
        edges.forEach { addEdge(it) }
    }

    override var vertices: Iterable<V>
        get() = lock.read {
            instance.vertices
        }
        set(value) = lock.write {
            instance.vertices = value
        }

    override var edges: Iterable<E>
        get() = lock.read {
            instance.edges
        }
        set(value) = lock.write {
            instance.edges = value
        }

    override fun addVertex(vertex: V) = lock.write {
        instance.addVertex(vertex)
    }

    override fun addEdge(edge: E) = lock.write {
        instance.addEdge(edge)
    }

    override fun edgesFor(vertex: IVertex): Iterable<E> = lock.read {
        instance.edgesFor(vertex)
    }

    override fun hasVertex(vertex: IVertex): Boolean = lock.read {
        instance.hasVertex(vertex)
    }

    override fun path(from: IVertex, to: IVertex, weightBlock: TWeightFunction<E>): Iterable<IEdge> = instance.path(from, to, weightBlock)
}
