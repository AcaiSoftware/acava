package gg.acai.acava.commons.chart;

import gg.acai.acava.collect.Immutable;
import gg.acai.acava.collect.lists.ImmutableList;

import java.util.List;

/**
 * @author Clouke
 * @since 03.03.2023 22:03
 * © Acava - All Rights Reserved
 */
public class ImmutableChart extends AbstractChart implements Immutable {

  public ImmutableChart(ChartBuilder builder) {
    super(builder);
  }

  @Override
  public Chart addNodes(int... data) {
    throw new UnsupportedOperationException("This chart is immutable");
  }

  @Override
  public Chart addNode(int data) {
    throw new UnsupportedOperationException("This chart is immutable");
  }

  @Override
  public Chart addNodes(List<Integer> data) {
    throw new UnsupportedOperationException("This chart is immutable");
  }

  @Override
  public List<Integer> nodes() {
    return ImmutableList.copyOf(nodes);
  }
}
