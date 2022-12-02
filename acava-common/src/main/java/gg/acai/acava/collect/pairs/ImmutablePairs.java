package gg.acai.acava.collect.pairs;

/**
 * @author Clouke
 * @since 02.12.2022 18:16
 * © Acava - All Rights Reserved
 */
public final class ImmutablePairs<L, R> implements Pairs<L, R> {

    private final L left;
    private final R right;

    public ImmutablePairs(L left, R right) {
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
}
