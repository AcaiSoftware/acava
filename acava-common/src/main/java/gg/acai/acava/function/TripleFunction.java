package gg.acai.acava.function;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 09.02.2023 17:34
 * © Acava - All Rights Reserved
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @param <R> The type of the result of the function
 */
@FunctionalInterface
public interface TripleFunction<T, U, V, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first function argument
     * @param u The second function argument
     * @param v The third function argument
     * @return The function result
     */
    R apply(T t, U u, V v);

    /**
     * Returns a composed function that first applies this function to its input, and then applies the {@code after} function to the result.
     *
     * @param after the function to apply after this function is applied
     * @param <W> the type of output of the {@code after} function, and of the composed function
     * @return a composed function that first applies this function and then applies the {@code after} function
     */
    default <W> TripleFunction<T, U, V, W> andThen(Function<? super R, ? extends W> after) {
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }

    /**
     * Returns a composed function that first applies the {@code before} function to its input, and then applies this function to the result.
     *
     * @param after the function to apply after this function is applied
     * @param <W> the type of output of the {@code after} function, and of the composed function
     * @return a composed function that first applies this function and then applies the {@code after} function
     */
    default <W> TripleFunction<T, U, V, W> andThen(TripleFunction<? super R, ? super U, ? super V, ? extends W> after) {
        return (T t, U u, V v) -> after.apply(apply(t, u, v), u, v);
    }

    /**
     * Returns a composed function that first applies the {@code before} function to its input, and then applies this function to the result.
     *
     * @param before the function to apply before this function is applied
     * @param <W> the type of input to the {@code before} function, and to the composed function
     * @return a composed function that first applies the {@code before} function and then applies this function
     */
    default <W> TripleFunction<W, U, V, R> compose(Function<? super W, ? extends T> before) {
        return (W w, U u, V v) -> apply(before.apply(w), u, v);
    }
}
