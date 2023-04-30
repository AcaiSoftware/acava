package gg.acai.acava.collect.pairs;

/**
 * @author Clouke
 * @since 02.12.2022 18:12
 * © Acava - All Rights Reserved
 */
public interface Pair<L, R> {

  static <L, R> Pair<L, R> immutable(L left, R right) {
    return new ImmutablePair<>(left, right);
  }

  static <L, R> Pair<L, R> mutable(L left, R right) {
    return new MutablePair<>(left, right);
  }

  /**
   * Gets the left value of the pair.
   *
   * @return Returns the left value of the pair.
   */
  L left();

  /**
   * Gets the right value of the pair.
   *
   * @return Returns the right value of the pair.
   */
  R right();

  /**
   * Sets the left value of the pair.
   *
   * @param left The left value to set.
   */
  void setLeft(L left);

  /**
   * Sets the right value of the pair.
   *
   * @param right The right value to set.
   */
  void setRight(R right);

}
