package gg.acai.acava.collect.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 03.12.2022 19:48
 * © Acava - All Rights Reserved
 */
public class EvictingMap<K, V> extends HashMap<K, V> {

    private final int maxSize;

    public EvictingMap(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public V put(K key, V value) {
        if (this.size() >= this.maxSize) {
            this.remove(this.keySet().iterator().next());
        }
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (this.size() + m.size() >= this.maxSize) {
            int toRemove = this.size() + m.size() - this.maxSize;
            for (int i = 0; i < toRemove; i++) {
                this.remove(this.keySet().iterator().next());
            }
        }
        super.putAll(m);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (this.size() >= this.maxSize) {
            this.remove(this.keySet().iterator().next());
        }
        return super.putIfAbsent(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        if (this.size() >= this.maxSize) {
            this.remove(this.keySet().iterator().next());
        }
        return super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (this.size() >= this.maxSize) {
            this.remove(this.keySet().iterator().next());
        }
        return super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (this.size() >= this.maxSize) {
            this.remove(this.keySet().iterator().next());
        }
        return super.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (this.size() >= this.maxSize) {
            this.remove(this.keySet().iterator().next());
        }
        return super.merge(key, value, remappingFunction);
    }

}
