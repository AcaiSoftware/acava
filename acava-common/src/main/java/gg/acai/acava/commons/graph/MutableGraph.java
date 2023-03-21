package gg.acai.acava.commons.graph;

import gg.acai.acava.annotated.Use;
import gg.acai.acava.collect.Mutable;

import java.util.Collection;
import java.util.Collections;

/**
 * A mutable graph implementation.
 *
 * @author Clouke
 * @since 16.02.2023 07:42
 * © Acava - All Rights Reserved
 */
@Use("Use Graph#newBuilder() to create a new graph")
public final class MutableGraph<N extends Number> extends AbstractGraph<N> implements Graph<N>, Mutable {

  public MutableGraph(int height, int limiter) {
    super(height, limiter);
  }

  @Override
  public Graph<N> addNode(N node) {
    super.nodes.add(node);
    return this;
  }

  @Override
  public Graph<N> addNodes(Collection<N> nodes) {
    super.nodes.addAll(nodes);
    return this;
  }

  @Override
  @SafeVarargs
  public final Graph<N> addNodes(N... nodes) {
    Collections.addAll(super.nodes, nodes);
    return this;
  }

  @Override
  public Graph<N> delegate(Graph<N> other) {
    int nodes = other.getNodes();
    for (int i = 0; i < nodes; i++) {
      super.nodes.add(other.getNode(i));
    }
    return this;
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

  @Override
  public void clear() {
    super.nodes.clear();
  }

}
