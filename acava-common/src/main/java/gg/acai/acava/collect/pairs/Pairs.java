package gg.acai.acava.collect.pairs;

import gg.acai.acava.collect.Mutability;

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

    /**
     * Creates a new pair based on the given mutability.
     *
     * @param m The mutability of the pair.
     * @param left The left value of the pair.
     * @param right The right value of the pair.
     * @return Returns a new pair based on the given mutability.
     */
    static <L, R> Pairs<L, R> of(Mutability m, L left, R right) {
        return m == Mutability.MUTABLE ? new MutablePair<>(left, right) : new ImmutablePair<>(left, right);
    }

}
