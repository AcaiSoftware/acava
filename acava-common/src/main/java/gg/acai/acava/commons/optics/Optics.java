package gg.acai.acava.commons.optics;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 27.03.2023 02:32
 * © Acava - All Rights Reserved
 */
public final class Optics {

  public static <S, T, A, B> Prism<S, T, A, B> prism(Function<S, Either<A, T>> matcher, Function<B, T> constructor) {
    return new Prism<>(matcher, constructor);
  }

  public static <A> Lens<A, A> id() {
    return new Lens<>(Function.identity(), (a, b) -> b);
  }

  public static <A, B> Lens<A, B> lens(Function<A, B> getter, BiFunction<A, B, A> setter) {
    return new Lens<>(getter, setter);
  }

  public static <A, B> Lens<A, B> lens(Function<A, B> getter, Function<B, A> setter) {
    return new Lens<>(getter, (a, b) -> setter.apply(b));
  }

  public static <S, T, A, B> Traversal<S, T, A, B> traversal(Function<S, List<A>> getter, BiFunction<S, List<B>, T> setter) {
    return new Traversal<>(getter, setter);
  }

  public static <S, T, A, B> Traversal<S, T, A, B> traversal(Function<S, List<A>> getter, Function<List<B>, T> setter) {
    return new Traversal<>(getter, (s, b) -> setter.apply(b));
  }

}
