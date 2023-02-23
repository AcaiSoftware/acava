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
 * @author Clouke
 * @since 22.02.2023 07:18
 * © Acava - All Rights Reserved
 */
public class GraphBuilder<N extends Number> {

    private Mutability mutability = Mutability.MUTABLE;
    private List<N> immutableNodes;
    private int height = 15;
    private int maxValue = -1;
    private int fixedSize = -1;

    public GraphBuilder<N> setMutability(Mutability mutability) {
        this.mutability = mutability;
        return this;
    }

    public GraphBuilder<N> setImmutable() {
        this.mutability = Mutability.IMMUTABLE;
        return this;
    }

    public GraphBuilder<N> setHeight(int height) {
        this.height = height;
        return this;
    }

    public GraphBuilder<N> setMaxDisplayValue(int limiter) {
        this.maxValue = limiter;
        return this;
    }

    public GraphBuilder<N> setFixedSize(int fixedSize) {
        this.fixedSize = fixedSize;
        return this;
    }

    @SafeVarargs
    public final GraphBuilder<N> addImmutableNodes(N... nodes) {
        ensureImmutable();
        Collections.addAll(immutableNodes, nodes);
        return this;
    }

    public GraphBuilder<N> addImmutableNodes(Collection<? extends N> nodes) {
        ensureImmutable();
        immutableNodes.addAll(nodes);
        return this;
    }

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

    public AsyncPlaceholder<Graph<N>> buildAsync() {
        return Schedulers.supplyAsync(this::build);
    }

    private void ensureImmutable() {
        immutableNodes = Requisites.applyIfNull(immutableNodes, ArrayList::new);
    }


}
