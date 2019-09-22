package graphlib.graph;

import graphlib.edge.SimpleDirectedEdge;
import graphlib.vertex.SimpleVertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static graphlib.graph.Functional.toKotlin;
import static org.assertj.core.api.Assertions.assertThat;

class UsageFromJavaTest {

    private SimpleVertex v1 = new SimpleVertex("v1");
    private SimpleVertex v2 = new SimpleVertex("v2");
    private SimpleVertex v3 = new SimpleVertex("v3");
    private SimpleVertex v4 = new SimpleVertex("v4");
    private SimpleDirectedEdge<SimpleVertex> e1 = new SimpleDirectedEdge<>(v1, v2);
    private SimpleDirectedEdge<SimpleVertex> e2 = new SimpleDirectedEdge<>(v2, v3);
    private SimpleDirectedEdge<SimpleVertex> e3 = new SimpleDirectedEdge<>(v1, v3);
    private SimpleDirectedEdge<SimpleVertex> e4 = new SimpleDirectedEdge<>(v1, v4);
    private SimpleDirectedEdge<SimpleVertex> e5 = new SimpleDirectedEdge<>(v2, v4);
    private SimpleDirectedEdge<SimpleVertex> e6 = new SimpleDirectedEdge<>(v3, v4);
    private GraphInMemory<SimpleVertex, SimpleDirectedEdge<SimpleVertex>> graph = new GraphInMemory<>();

    @BeforeEach
    void tearUp() {
        graph.setVertices(Arrays.asList(v1, v2, v3, v4));
        graph.setEdges(Arrays.asList(e1, e2, e3, e4, e5, e6));
    }

    @Test
    void constructor() {

        var edges = StreamSupport.stream(graph.getEdges().spliterator(), false).collect(Collectors.toList());
        var vertices = StreamSupport.stream(graph.getVertices().spliterator(), false).collect(Collectors.toList());

        assertThat(edges.size()).isEqualTo(6);
        assertThat(vertices.size()).isEqualTo(4);
    }

    @Test
    void path() {
        var path = graph.path(v1, v4);
        assertThat(path).containsExactly(e4);
    }

    @Test
    void pathWeighted() {
        var pathWeighted = graph.path(v1, v4, toKotlin(e -> 1.0));
        assertThat(pathWeighted).containsExactly(e4);
    }

}
