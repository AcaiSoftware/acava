package gg.acai.acava.commons.graph;

import gg.acai.acava.Requisites;
import gg.acai.acava.collect.Mutability;
import gg.acai.acava.collect.lists.ImmutableList;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A builder class for creating different types of graphs.
 * @param <N> the type of the nodes in the graph (extending Number)
 *
 * @author Clouke
 * @since 22.02.2023 07:18
 * © Acava - All Rights Reserved
 */
public class GraphBuilder<N extends Number> {

    /** The mutability of the graph being built. */
    private Mutability mutability = Mutability.MUTABLE;

    /** The list of immutable nodes in the graph being built. */
    private List<N> immutableNodes;

    /** The height of the graph being built. */
    private int height = 15;

    /** The maximum display value of the graph being built.*/
    private int maxValue = -1;

    /** The fixed size of the graph being built. */
    private int fixedSize = -1;

    /**
     * Sets the mutability of the graph being built.
     *
     * @param mutability the mutability to set
     * @return this GraphBuilder instance
     */
    public GraphBuilder<N> setMutability(Mutability mutability) {
        this.mutability = mutability;
        return this;
    }

    /**
     * Sets the mutability of the graph being built to immutable.
     *
     * @return this GraphBuilder instance
     */
    public GraphBuilder<N> setImmutable() {
        this.mutability = Mutability.IMMUTABLE;
        return this;
    }

    /**
     * Sets the visualization height of the graph.
     *
     * @param height the height to set
     * @return this GraphBuilder instance
     */
    public GraphBuilder<N> setHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the maximum visualization value of the graph.
     *
     * @param limiter the maximum display value to set
     * @return this GraphBuilder instance
     */
    public GraphBuilder<N> setMaxDisplayValue(int limiter) {
        this.maxValue = limiter;
        return this;
    }

    /**
     * Sets the fixed size of the graph.
     * Marks this graph as a {@link FixedSizeGraph}
     *
     * @param fixedSize the fixed size to set
     * @return this GraphBuilder instance
     */
    public GraphBuilder<N> setFixedSize(int fixedSize) {
        this.fixedSize = fixedSize;
        return this;
    }

    /**
     * Adds immutable nodes to the graph.
     *
     * @param nodes the nodes to add
     * @return this GraphBuilder instance
     */
    @SafeVarargs
    public final GraphBuilder<N> addImmutableNodes(N... nodes) {
        ensureImmutable();
        Collections.addAll(immutableNodes, nodes);
        return this;
    }

    /**
     * Adds a collection of immutable nodes to the graph.
     *
     * @param nodes the nodes to add
     * @return this GraphBuilder instance
     */
    public GraphBuilder<N> addImmutableNodes(Collection<? extends N> nodes) {
        ensureImmutable();
        immutableNodes.addAll(nodes);
        return this;
    }

    /**
     * Builds the graph with the current configuration.
     *
     * @throws IllegalArgumentException if trying to build an immutable graph without any nodes added
     * @return the built graph
     */
    public Graph<N> build() {
        if (fixedSize != -1)
            return new FixedSizeGraph<>(fixedSize, height, maxValue);

        if (mutability == Mutability.MUTABLE)
            return new MutableGraph<>(height, maxValue);

        Requisites.checkArgument(immutableNodes != null && !immutableNodes.isEmpty(),
                "Cannot build immutable graph without nodes. Use addImmutableNodes(...)"
        );

        return new ImmutableGraph<>(new ImmutableList<>(immutableNodes), height, maxValue);
    }

    /**
     * Builds the graph asynchronously with the current configuration.
     *
     * @throws IllegalArgumentException if trying to build an immutable graph without any nodes added
     * @return the built graph
     */
    public AsyncPlaceholder<Graph<N>> buildAsync() {
        return Schedulers.supplyAsync(this::build);
    }

    private void ensureImmutable() {
        immutableNodes = Requisites.applyIfNull(immutableNodes, ArrayList::new);
    }


}
