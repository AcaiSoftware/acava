package gg.acai.acava.commons.graph;

import gg.acai.acava.annotated.Use;
import gg.acai.acava.collect.Immutable;
import gg.acai.acava.collect.lists.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 16.02.2023 07:41
 * © Acava - All Rights Reserved
 */
@Use("Use Graph#newBuilder() to create a new graph")
public interface Graph<N extends Number> {

    Graph<N> addNode(N node);

    Graph<N> addNodes(Collection<N> nodes);

    Graph<N> addNodes(N... nodes);

    Graph<N> delegate(Graph<N> other);

    GraphVisualizer<N> getVisualizer();

    int getHeight();

    int getLimiter();

    static <U extends Number> GraphBuilder<U> newBuilder() {
        return new GraphBuilder<>();
    }

    N getNode(int index);

    int getNodes();

    @SuppressWarnings("unchecked")
    default AbstractGraph<N> asAbstractGraph() {
        if (this instanceof AbstractGraph)
            return (AbstractGraph<N>) this;
        throw new UnsupportedOperationException("This graph is not an instance of AbstractGraph");
    }

    default boolean isImmutable() {
        return this instanceof Immutable;
    }

    default <R extends Number> Graph<R> map(Function<N, R> mapper) {
        ArrayList<R> nodes = new ArrayList<>();
        for (int i = 0; i < getNodes(); i++)
            nodes.add(mapper.apply(getNode(i)));

        ImmutableList<R> immutableNodes = new ImmutableList<>(nodes);
        int height = getHeight();
        int limiter = getLimiter();
        return new ImmutableGraph<>(immutableNodes, height, limiter);
    }

}
