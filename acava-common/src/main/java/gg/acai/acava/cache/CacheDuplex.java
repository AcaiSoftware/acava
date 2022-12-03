package gg.acai.acava.cache;

import gg.acai.acava.io.Closeable;

/**
 * @author Clouke
 * @since 03.12.2022 19:45
 * © Acava - All Rights Reserved
 */
public interface CacheDuplex<K, V> extends Closeable {

    /**
     * @param key The key to get the value from.
     * @return Returns the value from the key.
     */
    V get(K key);

    /**
     * @param key The key to set the value to.
     * @param value The value to set to the key.
     */
    void set(K key, V value);

    /**
     * @param key The key to check if it exists.
     * @return Returns true if the key exists.
     */
    boolean exists(K key);

    /**
     * @param key The key to delete.
     */
    void delete(K key);

    /**
     * @return Returns the size of the cache.
     */
    int size();

    /**
     * @return Returns true if the cache is empty.
     */
    boolean isEmpty();

    /**
     * Clears the cache.
     */
    void invalidate();

    @Override
    default void close() {
        this.invalidate();
    }

}
