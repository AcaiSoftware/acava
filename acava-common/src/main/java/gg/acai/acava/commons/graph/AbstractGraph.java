package gg.acai.acava.commons.graph;

import gg.acai.acava.collect.lists.FixedSizeQueue;
import gg.acai.acava.collect.lists.ImmutableList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * The AbstractGraph class provides a base implementation for various types of graphs.
 * @param <N> the type of node in the graph.
 *
 * @author Clouke
 * @since 22.02.2023 05:15
 * © Acava - All Rights Reserved
 */
public class AbstractGraph<N extends Number> {

  /**
   * A GraphVisualizer object used for visualizing the graph.
   */
  protected final GraphVisualizer<N> visualizer = new GraphVisualizer<>(this);

  /**
   * A list of nodes in the graph.
   */
  protected final List<N> nodes;

  /**
   * The maximum height of the graph.
   */
  protected final int height;

  /**
   * The maximum number of nodes allowed in the graph.
   */
  protected final int limiter;

  /**
   * Constructs an AbstractGraph with an empty list of nodes.
   *
   * @param height  the maximum height of the graph.
   * @param limiter the maximum number of nodes allowed in the graph.
   */
  public AbstractGraph(int height, int limiter) {
    this.nodes = new ArrayList<>();
    this.height = height;
    this.limiter = limiter;
  }

  /**
   * Constructs an AbstractGraph with a fixed size queue of nodes.
   *
   * @param size    the maximum number of nodes allowed in the queue.
   * @param height  the maximum height of the graph.
   * @param limiter the maximum number of nodes allowed in the graph.
   */
  public AbstractGraph(int size, int height, int limiter) {
    this.nodes = new FixedSizeQueue<>(size);
    this.height = height;
    this.limiter = limiter;
  }

  /**
   * Constructs an AbstractGraph with a given list of nodes.
   *
   * @param nodes   a list of nodes to be added to the graph.
   * @param height  the maximum height of the graph.
   * @param limiter the maximum number of nodes allowed in the graph.
   */
  public AbstractGraph(List<N> nodes, int height, int limiter) {
    this.nodes = nodes;
    this.height = height;
    this.limiter = limiter;
  }

  /**
   * Returns a list of all nodes in the graph.
   *
   * @return a list of nodes in the graph.
   */
  @Nonnull
  public List<N> nodes() {
    return nodes;
  }

  /**
   * Returns an immutable list of all nodes in the graph.
   *
   * @return an immutable list of nodes in the graph.
   */
  @Nonnull
  public List<N> unmodifiable() {
    return new ImmutableList<>(nodes);
  }

}
