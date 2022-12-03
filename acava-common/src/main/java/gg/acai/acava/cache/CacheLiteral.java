package gg.acai.acava.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 03.12.2022 19:52
 * © Acava - All Rights Reserved
 */
public class CacheLiteral<K, V> implements CacheDuplex<K, V> {

    private final Map<K, V> cache;

    public CacheLiteral(Map<K, V> map) {
        this.cache = map;
    }

    public CacheLiteral() {
        this(new HashMap<>());
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

}
