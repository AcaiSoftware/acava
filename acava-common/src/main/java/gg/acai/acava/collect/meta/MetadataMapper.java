package gg.acai.acava.collect.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 02.12.2022 18:17
 * © Acava - All Rights Reserved
 *
 * MetadataMapper - A mapper extension for metadata
 */
public class MetadataMapper<K, V> {

    private final Map<K, V> map;

    public MetadataMapper(Map<K, V> map) {
        this.map = map;
    }

    public MetadataMapper() {
        this.map = new HashMap<>();
    }

    /**
     * @param entry The {@link MetadataEntry} to add to the map
     */
    public void add(MetadataEntry<K, V> entry) {
        this.map.put(entry.key(), entry.value());
    }

    public void add(K key, V value) {
        this.map.put(key, value);
    }

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

    public V remove(K key) {
        return this.map.remove(key);
    }

    public Map<K, V> asMap() {
        return this.map;
    }

}
