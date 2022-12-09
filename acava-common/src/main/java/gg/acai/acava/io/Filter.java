package gg.acai.acava.io;

import java.util.function.Predicate;

/**
 * @author Clouke
 * @since 04.12.2022 23:13
 * © Acava - All Rights Reserved
 */
public interface Filter<T> extends Predicate<T> {

    void add(T t);

}
