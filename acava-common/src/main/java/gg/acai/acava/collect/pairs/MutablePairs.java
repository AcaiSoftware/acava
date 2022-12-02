package gg.acai.acava.collect.pairs;

/**
 * @author Clouke
 * @since 02.12.2022 18:15
 * © Acava - All Rights Reserved
 */
public class MutablePairs<L, R> implements Pairs<L, R> {

    private L left;
    private R right;

    public MutablePairs(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public MutablePairs() {}

    @Override
    public L left() {
        return left;
    }

    @Override
    public R right() {
        return right;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
    }

}
