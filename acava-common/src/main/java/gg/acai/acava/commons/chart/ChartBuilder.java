package gg.acai.acava.commons.chart;

import gg.acai.acava.collect.Mutability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Clouke
 * @since 03.03.2023 21:32
 * © Acava - All Rights Reserved
 */
public class ChartBuilder {

  protected List<Integer> nodes;
  protected int width = 600;
  protected int height = 400;
  protected String title = "Chart";
  protected double maxX = 100;
  protected double minX = 0;
  protected double maxY = 100;
  protected double minY = 0;
  private Mutability mutability = Mutability.MUTABLE;

  public ChartBuilder width(int width) {
    this.width = width;
    return this;
  }

  public ChartBuilder height(int height) {
    this.height = height;
    return this;
  }

  public ChartBuilder title(String title) {
    this.title = title;
    return this;
  }

  public ChartBuilder maxX(double x) {
    this.maxX = x;
    return this;
  }

  public ChartBuilder minX(double x) {
    this.minX = x;
    return this;
  }

  public ChartBuilder maxY(double y) {
    this.maxY = y;
    return this;
  }

  public ChartBuilder minY(double y) {
    this.minY = y;
    return this;
  }

  public ChartBuilder immutable() {
    this.mutability = Mutability.IMMUTABLE;
    return this;
  }

  public ChartBuilder addNodes(int... data) {
    nodes = (nodes == null ? new ArrayList<>() : nodes);
    for (int i : data) nodes.add(i);
    return this;
  }

  public ChartBuilder addNodes(Collection<Integer> data) {
    nodes = (nodes == null ? new ArrayList<>() : nodes);
    nodes.addAll(data);
    return this;
  }

  public ChartBuilder addNode(int data) {
    nodes = (nodes == null ? new ArrayList<>() : nodes);
    nodes.add(data);
    return this;
  }

  public Chart build() {
    nodes = (nodes == null ? new ArrayList<>() : nodes);
    switch (mutability) {
      case MUTABLE:
        return new MutableChart(this);
      case IMMUTABLE: {
        if (nodes.isEmpty())
          throw new IllegalStateException("Cannot create immutable chart without nodes");
        return new ImmutableChart(this);
      }
      default:
        throw new IllegalStateException("Unknown mutability: " + mutability);
    }
  }

}
