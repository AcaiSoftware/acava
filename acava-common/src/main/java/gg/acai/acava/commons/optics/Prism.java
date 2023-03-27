package gg.acai.acava.commons.optics;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 27.03.2023 02:15
 * © Acava - All Rights Reserved
 */
public class Prism<S, T, A, B> {

  private final Function<S, Either<A, T>> matcher;
  private final Function<B, T> constructor;

  public Prism(Function<S, Either<A, T>> matcher, Function<B, T> constructor) {
    this.matcher = matcher;
    this.constructor = constructor;
  }

  public Either<A, T> match(S s) {
    return matcher.apply(s);
  }

  public T construct(B b) {
    return constructor.apply(b);
  }

  public <C> Prism<S, T, C, B> compose(Prism<A, B, C, B> other) {
    return new Prism<>(
      s -> matcher.apply(s).fold(a -> other.match(a)
        .fold(
          Either::left,
          b -> Either.right(constructor.apply(b))), Either::right
      ), constructor
    );
  }
}