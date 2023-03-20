package gg.acai.acava.collect.maps;

import gg.acai.acava.collect.Mutable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A fixed size HashMap that evicts the oldest entry when the map is full.
 *
 * @author Clouke
 * @since 03.12.2022 19:48
 * © Acava - All Rights Reserved
 */
public class FixedSizeHashMap<K, V> extends LinkedHashMap<K, V> implements Mutable {

  /**
   * The maximum size of the map.
   */
  private final int maxSize;

  /**
   * Creates a new FixedSizeHashMap with the given maximum size.
   *
   * @param maxSize The maximum size of the map.
   */
  public FixedSizeHashMap(int maxSize) {
    this.maxSize = maxSize;
  }

  /**
   * Puts the specified value with the specified key in this map.
   * Removes the oldest entry if the map is full.
   *
   * @param key   key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return Returns the value that was put into the map.
   */
  @Override
  public V put(K key, V value) {
    if (this.size() >= this.maxSize) {
      this.remove(this.keySet().iterator().next());
    }
    return super.put(key, value);
  }

  /**
   * Puts all the mappings from the specified map to this map.
   *
   * @param m mappings to be stored in this map
   */
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

  /**
   * Puts the specified value if absent with the specified key in this map.
   *
   * @param key   key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return Returns the value that was put into the map.
   */
  @Override
  public V putIfAbsent(K key, V value) {
    if (this.size() >= this.maxSize) {
      this.remove(this.keySet().iterator().next());
    }
    return super.putIfAbsent(key, value);
  }

  /**
   * Computes if absent with the specified key in this map.
   *
   * @param key             key with which the specified value is to be associated
   * @param mappingFunction the function to compute a value
   * @return Returns the value that was put into the map.
   */
  @Override
  public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    if (this.size() >= this.maxSize) {
      this.remove(this.keySet().iterator().next());
    }
    return super.computeIfAbsent(key, mappingFunction);
  }

  /**
   * Computes if present with the specified key in this map.
   *
   * @param key               key with which the specified value is to be associated
   * @param remappingFunction the function to compute a value
   * @return Returns the value that was put into the map.
   */
  @Override
  public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    if (this.size() >= this.maxSize) {
      this.remove(this.keySet().iterator().next());
    }
    return super.computeIfPresent(key, remappingFunction);
  }

  /**
   * Computes with the specified key in this map.
   *
   * @param key               key with which the specified value is to be associated
   * @param remappingFunction the function to compute a value
   * @return Returns the value that was put into the map.
   */
  @Override
  public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    if (this.size() >= this.maxSize) {
      this.remove(this.keySet().iterator().next());
    }
    return super.compute(key, remappingFunction);
  }

  /**
   * Merges with the specified key in this map.
   *
   * @param key               key with which the specified value is to be associated
   * @param value             value to be associated with the specified key
   * @param remappingFunction the function to compute a value
   * @return Returns the value that was put into the map.
   */
  @Override
  public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    if (this.size() >= this.maxSize) {
      this.remove(this.keySet().iterator().next());
    }
    return super.merge(key, value, remappingFunction);
  }

}
