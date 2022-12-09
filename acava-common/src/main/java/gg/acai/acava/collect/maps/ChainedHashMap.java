package gg.acai.acava.collect.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 05.12.2022 19:47
 * © Acava - All Rights Reserved
 */
public class ChainedHashMap<K, V> extends HashMap<K, V> {

    public ChainedHashMap<K, V> insert(K key, V value) {
        super.put(key, value);
        return this;
    }

    public ChainedHashMap<K, V> insert(Map<K, V> map) {
        super.putAll(map);
        return this;
    }

    public ChainedHashMap<K, V> delete(K key) {
        super.remove(key);
        return this;
    }

    public ChainedHashMap<K, V> delete(Map<K, V> map) {
        map.forEach((key, value) -> super.remove(key));
        return this;
    }

    public ChainedHashMap<K, V> update(K key, V value) {
        super.replace(key, value);
        return this;
    }

    public ChainedHashMap<K, V> update(Map<K, V> map) {
        map.forEach(super::replace);
        return this;
    }

    public ChainedHashMap<K, V> merge(Map<K, V> map) {
        super.putAll(map);
        return this;
    }

    public ChainedHashMap<K, V> merge(K key, V value) {
        super.put(key, value);
        return this;
    }

    public ChainedHashMap<K, V> merge(Map<K, V> map, Map<K, V> map2) {
        super.putAll(map);
        super.putAll(map2);
        return this;
    }

}
