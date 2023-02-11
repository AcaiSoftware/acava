package gg.acai.acava.function;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 09.02.2023 17:34
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface TripleFunction<T, U, V, R> {

    R apply(T t, U u, V v);

    default <W> TripleFunction<T, U, V, W> andThen(Function<? super R, ? extends W> after) {
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }

    default <W> TripleFunction<T, U, V, W> andThen(TripleFunction<? super R, ? super U, ? super V, ? extends W> after) {
        return (T t, U u, V v) -> after.apply(apply(t, u, v), u, v);
    }

    default <W> TripleFunction<W, U, V, R> compose(Function<? super W, ? extends T> before) {
        return (W w, U u, V v) -> apply(before.apply(w), u, v);
    }
}
