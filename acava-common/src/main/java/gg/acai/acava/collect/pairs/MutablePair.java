package gg.acai.acava.collect.pairs;

import gg.acai.acava.collect.Mutable;

/**
 * Mutable implementation of {@link Pair}.
 *
 * @author Clouke
 * @since 02.12.2022 18:15
 * © Acava - All Rights Reserved
 */
public class MutablePair<L, R> implements Pair<L, R>, Mutable {

  private L left;
  private R right;

  public MutablePair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public MutablePair() {
  }

  @Override
  public L left() {
    return left;
  }

  @Override
  public R right() {
    return right;
  }

  public void setLeft(L left) {
    this.left = left;
  }

  public void setRight(R right) {
    this.right = right;
  }

}
