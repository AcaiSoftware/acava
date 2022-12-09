package gg.acai.acava.cache;

import gg.acai.acava.collect.maps.EvictingMap;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 03.12.2022 19:47
 * © Acava - All Rights Reserved
 */
public class CacheEvicting<K, V> implements CacheDuplex<K, V> {

    private final Map<K, V> cache;

    public CacheEvicting(int maxSize) {
        this.cache = new EvictingMap<>(maxSize);
    }

    @Override
    public V get(K key) {
        return this.cache.get(key);
    }

    @Override
    public void set(K key, V value) {
        this.cache.put(key, value);
    }

    @Override
    public boolean exists(K key) {
        return this.cache.containsKey(key);
    }

    @Override
    public void delete(K key) {
        this.cache.remove(key);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public boolean isEmpty() {
        return this.cache.isEmpty();
    }

    @Override
    public void invalidate() {
        this.cache.clear();
    }

    @Override
    public void invalidateNulls() {
        this.cache.entrySet().removeIf(entry -> entry.getValue() == null);
    }

    @Override
    public Stream<V> stream() {
        return this.cache.values().stream();
    }

    @Override
    public Collection<V> values() {
        return this.cache.values();
    }

    @Override
    public CacheDuplex<K, V> delegate(CacheDuplex<K, V> other) {
        this.cache.forEach(other::set);
        return other;
    }

    @Override
    public Map<K, V> asMap() {
        return this.cache;
    }

}
