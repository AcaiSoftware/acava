package gg.acai.acava.collect.pairs;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 02.12.2022 18:12
 * © Acava - All Rights Reserved
 */
public interface Pairs<L, R> {

    /**
     * Gets the left value of the pair.
     *
     * @return Returns the left value of the pair.
     */
    L left();

    /**
     * Gets the right value of the pair.
     *
     * @return Returns the right value of the pair.
     */
    R right();

    /**
     * Sets the left value of the pair.
     *
     * @param left The left value to set.
     */
    void setLeft(L left);

    /**
     * Sets the right value of the pair.
     *
     * @param right The right value to set.
     */
    void setRight(R right);

    default <T> T mapLeft(Function<? super L, ? extends T> leftMapper) {
        return leftMapper.apply(left());
    }

    default <T> T mapRight(Function<? super R, ? extends T> rightMapper) {
        return rightMapper.apply(right());
    }

}
