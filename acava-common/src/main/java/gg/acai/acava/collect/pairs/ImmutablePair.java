package gg.acai.acava.collect.pairs;

import gg.acai.acava.collect.Immutable;

/**
 * Immutable implementation of {@link Pairs}.
 *
 * @author Clouke
 * @since 02.12.2022 18:16
 * © Acava - All Rights Reserved
 */
public final class ImmutablePair<L, R> implements Pairs<L, R>, Immutable {

    private final L left;
    private final R right;

    public ImmutablePair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L left() {
        return this.left;
    }

    @Override
    public R right() {
        return this.right;
    }

    @Override @Deprecated
    public void setLeft(L left) {
        throw new UnsupportedOperationException("Cannot set left value of immutable pair.");
    }

    @Override @Deprecated
    public void setRight(R right) {
        throw new UnsupportedOperationException("Cannot set right value of immutable pair.");
    }
}
