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
}
