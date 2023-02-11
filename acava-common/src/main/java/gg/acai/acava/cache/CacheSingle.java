package gg.acai.acava.cache;

import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 11.02.2023 17:54
 * © Acava - All Rights Reserved
 */
public interface CacheSingle<V> {

    /**
     * Gets the supplier of the value.
     *
     * @return the supplier of the value.
     */
    Supplier<V> supplier();

    /**
     * Gets the value.
     *
     * @return the value.
     */
    V get();

}
