package gg.acai.acava.collect.lists;

import gg.acai.acava.collect.Immutable;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * ImmutableHashSet - A {@link HashSet} extension that does not allow any modifications.
 * @param <E> the type of elements in this set.
 *
 * @author Clouke
 * @since 23.02.2023 13:33
 * © Acava - All Rights Reserved
 */
public class ImmutableHashSet<E> extends HashSet<E> implements Immutable {

    @Override @Deprecated
    public boolean add(E e) {
        throw new UnsupportedOperationException("Cannot add elements to an immutable set");
    }

    @Override @Deprecated
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove elements from an immutable set");
    }

    @Override @Deprecated
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Cannot add elements to an immutable set");
    }

    @Override @Deprecated
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove elements from an immutable set");
    }

    @Override @Deprecated
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove elements from an immutable set");
    }

    @Override @Deprecated
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("Cannot remove elements from an immutable set");
    }

    @Override @Deprecated
    public void clear() {
        throw new UnsupportedOperationException("Cannot remove elements from an immutable set");
    }

}
