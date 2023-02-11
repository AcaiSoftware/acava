package gg.acai.acava.function;

/**
 * An action is a function that takes a single argument and returns no result.
 *
 * @author Clouke
 * @since 02.12.2022 18:08
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface Action<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

    /**
     * Returns a composed action that performs, in sequence, this action followed by the after action.
     *
     * @param after the action to perform after this action
     * @return a composed action that performs in sequence this action followed by the after action
     */
    default Action<T> andThen(Action<? super T> after) {
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

}
