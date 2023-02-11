package gg.acai.acava.collect.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * An extension of the HashMap class that allows for chaining of methods.
 *
 * @author Clouke
 * @since 05.12.2022 19:47
 * © Acava - All Rights Reserved
 */
public class ChainedHashMap<K, V> extends HashMap<K, V> {

    /**
     * Appends a key-value into the map.
     *
     * @param key The key to append.
     * @param value The value to append.
     */
    public ChainedHashMap<K, V> insert(K key, V value) {
        super.put(key, value);
        return this;
    }

    /**
     * Appends a map into the map.
     *
     * @param map The map to append.
     */
    public ChainedHashMap<K, V> insert(Map<K, V> map) {
        super.putAll(map);
        return this;
    }

    /**
     * Deletes an entry from the map.
     *
     * @param key The key to delete.
     */
    public ChainedHashMap<K, V> delete(K key) {
        super.remove(key);
        return this;
    }

    /**
     * Deletes a map from the map.
     *
     * @param map The map to delete.
     */
    public ChainedHashMap<K, V> delete(Map<K, V> map) {
        map.forEach((key, value) -> super.remove(key));
        return this;
    }

    /**
     * Updates an entry in the map.
     *
     * @param key The key to update.
     * @param value The value to update.
     */
    public ChainedHashMap<K, V> update(K key, V value) {
        super.replace(key, value);
        return this;
    }

    /**
     * Updates a map in the map.
     *
     * @param map The map to update.
     */
    public ChainedHashMap<K, V> update(Map<K, V> map) {
        map.forEach(super::replace);
        return this;
    }

    /**
     * Merges a map into the map.
     *
     * @param map The map to merge.
     */
    public ChainedHashMap<K, V> merge(Map<K, V> map) {
        super.putAll(map);
        return this;
    }

    /**
     * Merges a key-value into the map.
     *
     * @param key The key to merge.
     * @param value The value to merge.
     */
    public ChainedHashMap<K, V> merge(K key, V value) {
        super.put(key, value);
        return this;
    }

    /**
     * Merges two maps into the map.
     *
     * @param map The first map to merge.
     * @param map2 The second map to merge.
     */
    public ChainedHashMap<K, V> merge(Map<K, V> map, Map<K, V> map2) {
        super.putAll(map);
        super.putAll(map2);
        return this;
    }

}
