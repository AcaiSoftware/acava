package gg.acai.acava.commons.optics;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 27.03.2023 02:00
 * © Acava - All Rights Reserved
 */
public class Either<A, B> {

  private final A left;
  private final B right;

  private Either(A left, B right) {
    this.left = left;
    this.right = right;
  }

  public static <A, B> Either<A, B> left(A left) {
    return new Either<>(left, null);
  }

  public static <A, B> Either<A, B> right(B right) {
    return new Either<>(null, right);
  }

  public <C> C fold(Function<A, C> f, Function<B, C> g) {
    return left != null ? f.apply(left) : g.apply(right);
  }

  public <C> Either<C, B> mapLeft(Function<A, C> f) {
    return left != null ? left(f.apply(left)) : null;
  }

  public <C> Either<A, C> mapRight(Function<B, C> f) {
    return right != null ? right(f.apply(right)) : null;
  }

  public <C> Either<C, B> flatMapLeft(Function<A, Either<C, B>> f) {
    return left != null ? f.apply(left) : null;
  }

  public <C> Either<A, C> flatMapRight(Function<B, Either<A, C>> f) {
    return right != null ? f.apply(right) : null;
  }

  public Optional<A> left() {
    return Optional.ofNullable(left);
  }

  public Optional<B> right() {
    return Optional.ofNullable(right);
  }

  public boolean isLeft() {
    return left != null;
  }

  public boolean isRight() {
    return right != null;
  }

}