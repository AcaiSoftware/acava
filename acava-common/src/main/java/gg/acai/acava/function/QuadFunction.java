package gg.acai.acava.function;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 11.02.2023 20:47
 * © Acava - All Rights Reserved
 *
 * @param <A> The first argument
 * @param <B> The second argument
 * @param <C> The third argument
 * @param <D> The fourth argument
 * @param <R> The result
 */
public interface QuadFunction<A, B, C, D, R> {

  /**
   * Applies the function.
   *
   * @param a The first argument
   * @param b The second argument
   * @param c The third argument
   * @param d The fourth argument
   * @return Returns the result
   */
  R apply(A a, B b, C c, D d);

  /**
   * Returns a composed function that first applies this function to its input, and then applies the {@code after} function to the result.
   *
   * @param after the function to apply after this function is applied
   * @param <W>   the type of output of the {@code after} function, and of the composed function
   * @return a composed function that first applies this function and then applies the {@code after} function
   */
  default <W> QuadFunction<A, B, C, D, W> andThen(Function<? super R, ? extends W> after) {
    return (A a, B b, C c, D d) -> after.apply(apply(a, b, c, d));
  }

  /**
   * Returns a composed function that first applies the {@code before} function to its input, and then applies this function to the result.
   *
   * @param after the function to apply after this function is applied
   * @param <W>   the type of output of the {@code after} function, and of the composed function
   * @return a composed function that first applies this function and then applies the {@code after} function
   */
  default <W> QuadFunction<A, B, C, D, W> andThen(QuadFunction<? super R, ? super B, ? super C, ? super D, ? extends W> after) {
    return (A a, B b, C c, D d) -> after.apply(apply(a, b, c, d), b, c, d);
  }

  /**
   * Returns a composed function that first applies the {@code before} function to its input, and then applies this function to the result.
   *
   * @param after
   * @param <W>   the type of output of the {@code after} function, and of the composed function
   * @return a composed function that first applies this function and then applies the {@code after} function
   */
  default <W> QuadFunction<A, B, C, D, W> andThen(TripleFunction<? super R, ? super C, ? super D, ? extends W> after) {
    return (A a, B b, C c, D d) -> after.apply(apply(a, b, c, d), c, d);
  }

  /**
   * Returns a composed function that first applies the {@code before} function to its input, and then applies this function to the result.
   *
   * @param before the function to apply before this function is applied
   * @param <W>    the type of input to the {@code before} function, and to the composed function
   * @return a composed function that first applies the {@code before} function and then applies this function
   */
  default <W> QuadFunction<W, B, C, D, R> compose(Function<? super W, ? extends A> before) {
    return (W w, B b, C c, D d) -> apply(before.apply(w), b, c, d);
  }
}
