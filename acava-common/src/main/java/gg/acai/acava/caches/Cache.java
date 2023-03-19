package gg.acai.acava.caches;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 26.02.2023 07:03
 * © Acava - All Rights Reserved
 */
public interface Cache<K, V> {

  void put(K key, V value);

  Optional<V> get(K key);

  CacheStatistics statistics();

  void remove(K key);

  void invalidate();

  boolean containsKey(K key);

  boolean containsValue(V value);

  int size();

  boolean isEmpty();

  Collection<V> values();

  Map<K, V> asMap();

  default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    Objects.requireNonNull(mappingFunction);
    Optional<V> value = get(key);
    if (value.isPresent()) {
      return value.get();
    }
    V newValue = mappingFunction.apply(key);
    put(key, newValue);
    return newValue;
  }

  default V computeIfPresent(K key, Function<? super K, ? extends V> mappingFunction) {
    Objects.requireNonNull(mappingFunction);
    Optional<V> value = get(key);
    if (value.isPresent()) {
      V newValue = mappingFunction.apply(key);
      put(key, newValue);
      return newValue;
    }
    return null;
  }

  default V compute(K key, Function<? super K, ? extends V> mapper) {
    Objects.requireNonNull(mapper);
    V newValue = mapper.apply(key);
    put(key, newValue);
    return newValue;
  }

  default V merge(K key, V value, Function<? super V, ? extends V> remapper) {
    Objects.requireNonNull(remapper);
    Optional<V> oldValue = get(key);
    if (oldValue.isPresent()) {
      V newValue = remapper.apply(oldValue.get());
      put(key, newValue);
      return newValue;
    }
    put(key, value);
    return value;
  }

  default void putAll(Map<? extends K, ? extends V> map) {
    map.forEach(this::put);
  }

  default void putIfAbsent(K key, V value) {
    if (!containsKey(key)) {
      put(key, value);
    }
  }

  default void forEach(BiConsumer<? super K, ? super V> action) {
    Objects.requireNonNull(action);
    asMap().forEach(action);
  }

}
