package gg.acai.acava.commons.graph;

import gg.acai.acava.collect.lists.FixedSizeQueue;
import gg.acai.acava.collect.lists.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clouke
 * @since 22.02.2023 05:15
 * © Acava - All Rights Reserved
 */
public class AbstractGraph<N extends Number> {

    protected final GraphVisualizer<N> visualizer = new GraphVisualizer<>(this);
    protected final List<N> nodes;
    protected final int height;
    protected final int limiter;

    public AbstractGraph(int height, int limiter) {
        this.nodes = new ArrayList<>();
        this.height = height;
        this.limiter = limiter;
    }

    public AbstractGraph(int size, int height, int limiter) {
        this.nodes = new FixedSizeQueue<>(size);
        this.height = height;
        this.limiter = limiter;
    }

    public AbstractGraph(List<N> nodes, int height, int limiter) {
        this.nodes = nodes;
        this.height = height;
        this.limiter = limiter;
    }

    public List<N> nodes() {
        return nodes;
    }

    public List<N> unmodifiable() {
        return new ImmutableList<>(nodes);
    }

}
