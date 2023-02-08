package gg.acai.acava.function;

/**
 * @author Clouke
 * @since 08.02.2023 16:18
 * © Acava - All Rights Reserved
 */
public interface Fold<E, R> {

    /**
     * @param accumulator The accumulator
     * @param element The element
     * @return Returns the new accumulator
     */
    R fold(R accumulator, E element);

    /**
     * Folds the elements into the accumulator.
     *
     * @param accumulator The accumulator
     * @param elements The elements
     * @return Returns the new accumulator
     */
    default R fold(R accumulator, Iterable<E> elements) {
        for (E element : elements)
            accumulator = fold(accumulator, element);
        return accumulator;
    }
}
