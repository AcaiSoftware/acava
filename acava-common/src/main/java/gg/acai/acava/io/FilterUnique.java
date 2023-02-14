package gg.acai.acava.io;

import java.util.HashSet;
import java.util.Set;

/**
 * Unique filter implementation.
 *
 * @author Clouke
 * @since 04.12.2022 23:13
 * © Acava - All Rights Reserved
 */
public class FilterUnique<T> implements Filter<T> {

    private final Set<T> seen;

    public FilterUnique() {
        this.seen = new HashSet<>();
    }

    @Override
    public void add(T t) {
        this.seen.add(t);
    }

    @Override
    public Set<T> getSeen() {
        return seen;
    }

    @Override
    public boolean test(T t) {
        return this.seen.contains(t);
    }
}
