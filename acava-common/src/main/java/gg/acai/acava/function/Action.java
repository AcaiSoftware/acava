package gg.acai.acava.function;

/**
 * @author Clouke
 * @since 02.12.2022 18:08
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface Action<T> {

    void accept(T t);

    default Action<T> andThen(Action<? super T> after) {
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

}
