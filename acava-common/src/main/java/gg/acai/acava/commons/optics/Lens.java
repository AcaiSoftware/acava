package gg.acai.acava.commons.optics;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 27.03.2023 01:48
 * © Acava - All Rights Reserved
 */
public class Lens<A, B> {

  private final Function<A, B> getter;
  private final BiFunction<A, B, A> setter;

  public Lens(Function<A, B> getter, BiFunction<A, B, A> setter) {
    this.getter = getter;
    this.setter = setter;
  }

  public B get(A a) {
    return getter.apply(a);
  }

  public A set(A a, B b) {
    return setter.apply(a, b);
  }

  public <C> Lens<A, C> then(Lens<B, C> other) {
    return compose(other);
  }

  public <C> Lens<A, C> compose(Lens<B, C> other) {
    return new Lens<>(
      a -> other.get(getter.apply(a)),
      (a, c) -> setter.apply(a, other.set(getter.apply(a), c))
    );
  }

  public A modify(A a, Function<B, B> f) {
    return setter.apply(a, f.apply(getter.apply(a)));
  }

}
