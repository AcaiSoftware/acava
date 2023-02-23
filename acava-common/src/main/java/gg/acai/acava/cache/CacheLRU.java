package gg.acai.acava.cache;

import gg.acai.acava.collect.maps.FixedSizeHashMap;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 23.02.2023 13:38
 * © Acava - All Rights Reserved
 */
public class CacheLRU<K, V> implements CacheDuplex<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private final FixedSizeHashMap<K, V> cache;

    public CacheLRU(int size) {
        this.cache = new FixedSizeHashMap<>(size);
    }

    public CacheLRU() {
        this(DEFAULT_SIZE);
    }

    @Override
    public V get(K key) {
        synchronized (cache) {
            if (!cache.containsKey(key))
                return null;

            V value = cache.remove(key);
            invalidateNulls();
            cache.put(key, value); // move to front
            return value;
        }
    }

    @Override
    public void set(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public boolean exists(K key) {
        return cache.containsKey(key);
    }

    @Override
    public void delete(K key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public void invalidate() {
        cache.clear();
    }

    @Override
    public void invalidateNulls() {
        cache.entrySet().removeIf(entry -> entry.getValue() == null);
    }

    @Override
    public Stream<V> stream() {
        return cache.values().stream();
    }

    @Override
    public Collection<V> values() {
        return cache.values();
    }

    @Override
    public CacheDuplex<K, V> delegate(CacheDuplex<K, V> other) {
        this.cache.forEach(other::set);
        return other;
    }

    @Override
    public Map<K, V> asMap() {
        return cache;
    }
}
