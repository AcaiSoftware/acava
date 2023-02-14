package gg.acai.acava.io;

import java.util.Set;
import java.util.function.Predicate;

/**
 * A simple filter extension of {@link Predicate}.
 *
 * @author Clouke
 * @since 04.12.2022 23:13
 * © Acava - All Rights Reserved
 */
public interface Filter<T> extends Predicate<T> {

    /**
     * Adds the given object to the filter.
     *
     * @param t Object to add
     */
    void add(T t);

    /**
     * Gets the set of objects that have been seen.
     *
     * @return Set of objects that have been seen
     */
    Set<T> getSeen();

}
