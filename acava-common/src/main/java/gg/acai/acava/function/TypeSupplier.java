package gg.acai.acava.function;

/**
 * @author Clouke
 * @since 02.12.2022 18:10
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface TypeSupplier<T, U> {

    T get(U u);

    default T getOrDefault(U u, T t) {
        T value = get(u);
        return value == null ? t : value;
    }

    default void getIfPresent(U u, Action<T> action) {
        T value = get(u);
        if (value != null) action.accept(value);
    }

}
