package gg.acai.acava.collect.meta;

import gg.acai.acava.collect.Mutable;

import java.util.HashMap;
import java.util.Map;

/**
 * MetadataMapper - A mapper extension for metadata
 *
 * @author Clouke
 * @since 02.12.2022 18:17
 * © Acava - All Rights Reserved
 */
public class MetadataMapper<K, V> implements Mutable {

    /**
     * The map that holds the metadata
     */
    private final Map<K, V> map;

    /**
     * @param map The map to use as the metadata
     */
    public MetadataMapper(Map<K, V> map) {
        this.map = map;
    }

    /**
     * Creates a new MetadataMapper with a new HashMap
     */
    public MetadataMapper() {
        this.map = new HashMap<>();
    }

    /**
     * @param entry The {@link MetadataEntry} to add to the map
     */
    public void add(MetadataEntry<K, V> entry) {
        this.map.put(entry.key(), entry.value());
    }

    /**
     * @param key The key to add to the map
     * @param value The value to add to the map
     */
    public void add(K key, V value) {
        this.map.put(key, value);
    }

    /**
     * @param key The key to get from the map
     * @return Returns the value of the key
     */
    public MetadataEntry<K, V> get(K key) {
        return new MetadataEntry<K, V>() {
            @Override
            public K key() {
                return key;
            }

            @Override
            public V value() {
                return map.get(key);
            }
        };
    }

    /**
     * @param key The key to remove from the map
     * @return Returns the value of the key
     */
    public V remove(K key) {
        return this.map.remove(key);
    }

    /**
     * @return Returns the map
     */
    public Map<K, V> asMap() {
        return this.map;
    }

}
