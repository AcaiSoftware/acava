package gg.acai.acava.commons.graph;

import gg.acai.acava.annotated.Use;
import gg.acai.acava.collect.Immutable;
import gg.acai.acava.collect.lists.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * The Graph interface defines methods for building and manipulating graphs.
 * @param <N> the type of node in the graph.
 *
 * @author Clouke
 * @since 16.02.2023 07:41
 * © Acava - All Rights Reserved
 */
@Use("Use Graph#newBuilder() to create a new graph")
public interface Graph<N extends Number> {

    /**
     * Adds a single node to the graph.
     *
     * @param node the node to be added.
     * @return the modified graph.
     */
    Graph<N> addNode(N node);

    /**
     * Adds a collection of nodes to the graph.
     * @param nodes the collection of nodes to be added.
     *
     * @return the modified graph.
     */
    Graph<N> addNodes(Collection<N> nodes);

    /**
     * Adds an array of nodes to the graph.
     * @param nodes the array of nodes to be added.
     *
     * @return the modified graph.
     */
    Graph<N> addNodes(N... nodes);

    /**
     * Delegates to another graph instance to allow modification of a different graph instance
     * through the same interface.
     *
     * @param other the other graph instance.
     * @return the modified graph.
     */
    Graph<N> delegate(Graph<N> other);

    /**
     * Returns a GraphVisualizer used for visualizing the graph.
     *
     * @return the GraphVisualizer.
     */
    GraphVisualizer<N> getVisualizer();

    /**
     * Returns the maximum height of the graph.
     *
     * @return the maximum height of the graph.
     */
    int getHeight();

    /**
     * Returns the maximum number of nodes allowed in the graph.
     *
     * @return the maximum number of nodes allowed in the graph.
     */
    int getLimiter();

    /**
     * Returns a new GraphBuilder instance for building a new graph.
     *
     * @param <U> the type of node in the new graph.
     * @return a new GraphBuilder instance.
     */
    static <U extends Number> GraphBuilder<U> newBuilder() {
        return new GraphBuilder<>();
    }

    /**
     * Returns the node at the specified index in the graph.
     *
     * @param index the index of the node to retrieve.
     * @return the node at the specified index.
     */
    N getNode(int index);

    /**
     * Returns the number of nodes in the graph.
     *
     * @return the number of nodes in the graph.
     */
    int getNodes();

    /**
     * Returns an instance of AbstractGraph for the current graph, if applicable.
     *
     * @return an instance of AbstractGraph for the current graph.
     * @throws UnsupportedOperationException if the current graph is not an instance of AbstractGraph.
     */
    @SuppressWarnings("unchecked")
    default AbstractGraph<N> asAbstractGraph() {
        if (this instanceof AbstractGraph)
            return (AbstractGraph<N>) this;
        throw new UnsupportedOperationException("This graph is not an instance of AbstractGraph");
    }

    /**
     * Returns whether the graph is immutable or not.
     *
     * @return true if the graph is immutable, false otherwise.
     */
    default boolean isImmutable() {
        return this instanceof Immutable;
    }

    /**
     * Applies the specified mapper function to all nodes in the graph and returns a new graph with
     * the mapped nodes.
     *
     * @param <R> the type of node in the new graph.
     * @param mapper the function to apply to each node.
     * @return a new graph with the mapped nodes.
     */
    default <R extends Number> Graph<R> map(Function<? super N, ? extends R> mapper) {
        ArrayList<R> nodes = new ArrayList<>();
        for (int i = 0; i < getNodes(); i++)
            nodes.add(mapper.apply(getNode(i)));

        ImmutableList<R> immutableNodes = new ImmutableList<>(nodes);
        int height = getHeight();
        int limiter = getLimiter();
        return new ImmutableGraph<>(immutableNodes, height, limiter);
    }

}
