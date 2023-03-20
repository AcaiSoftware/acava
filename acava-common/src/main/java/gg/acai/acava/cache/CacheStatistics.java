package gg.acai.acava.cache;

import gg.acai.acava.collect.Mutability;
import gg.acai.acava.commons.graph.Graph;
import gg.acai.acava.commons.graph.GraphBuilder;

/**
 * @author Clouke
 * @since 19.03.2023 17:52
 * © Acava - All Rights Reserved
 */
public class CacheStatistics {

  private final Graph<Double> hitRateGraph;
  private double hitMissRatio;
  private long hitCount;
  private long missCount;
  private long usageCount;
  private long clearCount;

  public CacheStatistics() {
    this.hitRateGraph = new GraphBuilder<>()
      .setMutability(Mutability.MUTABLE)
      .build();
  }

  public double hitMissRatio() {
    return hitMissRatio;
  }

  public long hitCount() {
    return hitCount;
  }

  public long missCount() {
    return missCount;
  }

  public long usageCount() {
    return usageCount;
  }

  public long clearCount() {
    return clearCount;
  }

  public Graph<Double> hitRateGraph() {
    return hitRateGraph;
  }

  protected void hit() {
    hitCount++;
    usageCount++;
    computeHitMissRatio();
  }

  protected void miss() {
    missCount++;
    usageCount++;
    computeHitMissRatio();
  }

  protected void use() {
    usageCount++;
  }

  protected void clear() {
    clearCount++;
  }

  protected void computeHitMissRatio() {
    if (hitCount == 0L || missCount == 0L) {
      hitMissRatio = 0.0D;
      return;
    }
    hitMissRatio = (double) hitCount / (double) missCount;
    double hm = hitMissRatio;
    hitRateGraph.addNode(hm);
  }

  @Override
  public String toString() {
    return "CacheStatistics{" +
      "hitMissRatio=" + hitMissRatio +
      ", hitCount=" + hitCount +
      ", missCount=" + missCount +
      ", usageCount=" + usageCount +
      ", clearCount=" + clearCount +
      '}';
  }
}
