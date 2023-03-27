package gg.acai.acava.commons.optics;

import gg.acai.acava.collect.Mutable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Clouke
 * @since 27.03.2023 02:04
 * © Acava - All Rights Reserved
 */
public class Traversal<S, T, A, B> implements Mutable {

  private final Function<S, List<A>> getter;
  private final BiFunction<S, List<B>, T> setter;

  public Traversal(Function<S, List<A>> getter, BiFunction<S, List<B>, T> setter) {
    this.getter = getter;
    this.setter = setter;
  }

  public List<A> get(S s) {
    return getter.apply(s);
  }

  public T set(S s, List<B> b) {
    return setter.apply(s, b);
  }

  public <C> Traversal<S, T, C, B> compose(Traversal<A, B, C, B> other) {
    return new Traversal<>(
      s -> getter.apply(s)
        .stream()
        .flatMap(
          a -> other.get(a)
            .stream())
            .collect(Collectors.toList()
          ),
      (s, b) -> setter.apply(s, getter
          .apply(s)
            .stream()
            .map(a -> other.set(a, b))
            .collect(Collectors.toList()))
    );
  }

}