package gg.acai.acava.function;

/**
 * A consumer that takes three arguments.
 *
 * @author Clouke
 * @since 17.04.2023 03:15
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface TripleConsumer<A, B, C> {
  /**
   * Performs this operation on the given arguments.
   *
   * @param a The first argument
   * @param b The second argument
   * @param c The third argument
   */
  void accept(A a, B b, C c);

  /**
   * Returns a composed action that performs, in sequence, this action followed by the after action.
   *
   * @param after the action to perform after this action
   * @return a composed action that performs in sequence this action followed by the after action
   */
  default TripleConsumer<A, B, C> andThen(TripleConsumer<? super A, ? super B, ? super C> after) {
    return (a, b, c) -> {
      accept(a, b, c);
      after.accept(a, b, c);
    };
  }
}
