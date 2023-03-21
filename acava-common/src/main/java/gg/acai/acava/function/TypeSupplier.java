package gg.acai.acava.function;

/**
 * A type supplier is a functional interface that can be used to get the value of a type.
 *
 * @author Clouke
 * @since 02.12.2022 18:10
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface TypeSupplier<T, U> {

  /**
   * Gets the value of the type
   *
   * @param u the type
   * @return the value of the type
   */
  T get(U u);

  /**
   * Gets the value of the type, or the default value if the type is null.
   *
   * @param u the type
   * @param t the default value
   * @return the value of the type, or the default value if the type is null.
   */
  default T getOrDefault(U u, T t) {
    T value = get(u);
    return value == null ? t : value;
  }

  /**
   * Computes the value of the type through {@link Action<U>}
   *
   * @param u      the type
   * @param action the action to compute the value
   */
  default void getIfPresent(U u, Action<T> action) {
    T value = get(u);
    if (value != null) action.accept(value);
  }

}
