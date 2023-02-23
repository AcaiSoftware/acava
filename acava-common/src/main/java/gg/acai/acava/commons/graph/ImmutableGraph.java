package gg.acai.acava.commons.graph;

import gg.acai.acava.annotated.Use;
import gg.acai.acava.collect.Immutable;
import gg.acai.acava.collect.lists.ImmutableList;

import java.util.Collection;

/**
 * An immutable graph implementation.
 *
 * @author Clouke
 * @since 22.02.2023 21:56
 * © Acava - All Rights Reserved
 */
@Use("Use Graph#newBuilder() to create a new graph")
public final class ImmutableGraph<N extends Number> extends AbstractGraph<N> implements Graph<N>, Immutable {

    public ImmutableGraph(ImmutableList<N> nodes, int height, int limiter) {
        super(nodes, height, limiter);
    }

    @Override @Deprecated
    public Graph<N> addNode(N node) {
        throw new UnsupportedOperationException("Cannot add nodes to an immutable graph");
    }

    @Override @Deprecated
    public Graph<N> addNodes(Collection<N> nodes) {
        throw new UnsupportedOperationException("Cannot add nodes to an immutable graph");
    }

    @Override @Deprecated
    public Graph<N> addNodes(N... nodes) {
        throw new UnsupportedOperationException("Cannot add nodes to an immutable graph");
    }

    @Override @Deprecated
    public Graph<N> delegate(Graph<N> other) {
        throw new UnsupportedOperationException("Cannot delegate immutable graph");
    }

    @Override
    public GraphVisualizer<N> getVisualizer() {
        return super.visualizer;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getLimiter() {
        return limiter;
    }

    @Override
    public N getNode(int index) {
        return super.nodes.get(index);
    }

    @Override
    public int getNodes() {
        return super.nodes.size();
    }
}
