package graphlib.algorythms.path

import graphlib.edge.IEdge

interface ISearchPath {
    fun search(): Collection<IEdge>
}
