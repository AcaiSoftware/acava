package gg.acai.acava.collect.maps;

import gg.acai.acava.collect.pairs.Pairs;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 17.04.2023 01:06
 * © Acava - All Rights Reserved
 */
public interface BiMap<K, V, S> {

  /**
   * Get the size of this map
   *
   * @return Returns the size of this map
   */
  int size();

  /**
   * Checks if this map is empty
   *
   * @return Returns true if this map is empty
   */
  boolean isEmpty();

  /**
   * Checks if this map contains the specified key
   *
   * @param key The key to check
   * @return Returns true if this map contains the specified key
   */
  boolean containsKey(K key);

  /**
   * Checks if this map contains the specified value
   *
   * @param value The value to check
   * @return Returns true if this map contains the specified value
   */
  boolean containsValue(V value);

  /**
   * Checks if this map contains the specified second value
   *
   * @param secondValue The second value to check
   * @return Returns true if this map contains the specified second value
   */
  boolean containsSecondValue(S secondValue);

  /**
   * Gets a pair of V and S from the specified key
   *
   * @param key The key to get the pair from
   * @return Returns the pair of V and S from the specified key
   */
  Pairs<V, S> get(K key);

  /**
   * Gets the value from the specified key
   *
   * @param key The key to get the value from
   * @return Returns the value from the specified key
   */
  Pairs<V, S> put(K key, V value, S secondValue);

  /**
   * Removes the pair of V and S from the specified key
   *
   * @param key The key to remove the pair from
   * @return Returns the pair of V and S from the specified key
   */
  Pairs<V, S> remove(K key);

  /**
   * Puts all the entries from the specified map into this map
   *
   * @param m The map to put all the entries from
   */
  void putAll(BiMap<? extends K, ? extends V, ? extends S> m);

  /**
   * Removes all the entries from this map
   */
  void clear();

  /**
   * Gets the inverse of this map
   *
   * @return Returns the inverse of this map
   */
  BiMap<V, K, S> inverse();

  /**
   * Puts the specified pair of V and S into the specified key
   *
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> put(K key, Pairs<V, S> value) {
    return put(key, value.left(), value.right());
  }

  /**
   * Gets a set of all the keys in this map
   *
   * @return Returns a set of all the keys in this map
   */
  Set<K> keySet();

  /**
   * Gets a collection of all the pairs in this map
   *
   * @return Returns a collection of all the pairs in this map
   */
  Collection<Pairs<V, S>> values();

  /**
   * Gets a set of all the entries in this map
   *
   * @return Returns a set of all the entries in this map
   */
  Set<Map.Entry<K, Pairs<V, S>>> entrySet();

  /**
   * Gets the value from the specified key or the default value if the key is not present
   *
   * @param key The key to get the value from
   * @param defaultValue The default value to return if the key is not present
   * @return Returns the value from the specified key or the default value if the key is not present
   */
  default Pairs<V, S> getOrDefault(K key, Pairs<V, S> defaultValue) {
    Pairs<V, S> pairs = get(key);
    return pairs == null ? defaultValue : pairs;
  }

  /**
   * Performs the specified action for each entry in this map until all entries have been processed.
   *
   * @param action The action to be performed for each entry
   */
  default void forEach(TrioConsumer<? super K, ? super V, ? super S> action) {
    Objects.requireNonNull(action);
    for (Map.Entry<K, Pairs<V, S>> entry : entrySet()) {
      action.accept(entry.getKey(), entry.getValue().left(), entry.getValue().right());
    }
  }

  interface TrioConsumer<K, V, S> {
    void accept(K key, V value, S secondValue);
  }

  /**
   * Put the specified pair of V and S into the specified key if the key is not present
   *
   * @param key The key to put the pair into
   * @param value The pair of V and S to put into the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> putIfAbsent(K key, Pairs<V, S> value) {
    Pairs<V, S> pairs = get(key);
    if (pairs == null) {
      pairs = put(key, value);
    }
    return pairs;
  }

  /**
   * Removes the pair of V and S from the specified key if the key is present and the pair of V and S is equal to the specified pair of V and S
   *
   * @param key The key to remove the pair from
   * @param value The pair of V and S to remove from the specified key
   * @return Returns true if the pair of V and S was removed
   */
  default boolean remove(K key, Object value) {
    Objects.requireNonNull(value);
    Pairs<V, S> pairs = get(key);
    if (pairs != null && pairs.equals(value)) {
      remove(key);
      return true;
    }
    return false;
  }

  /**
   * Replaces the pair of V and S from the specified key if the key is present and the pair of V and S is equal to the specified pair of V and S
   *
   * @param key The key to replace the pair from
   * @param oldValue The old pair of V and S to replace
   * @param newValue The new pair of V and S to replace
   * @return Returns true if the pair of V and S was replaced
   */
  default boolean replace(K key, Pairs<V, S> oldValue, Pairs<V, S> newValue) {
    Objects.requireNonNull(oldValue);
    Objects.requireNonNull(newValue);
    Pairs<V, S> pairs = get(key);
    if (pairs != null && pairs.equals(oldValue)) {
      put(key, newValue);
      return true;
    }
    return false;
  }

  /**
   * Replaces the pair of V and S from the specified key if the key is present
   *
   * @param key The key to replace the pair from
   * @param value The new pair of V and S to replace
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> replace(K key, Pairs<V, S> value) {
    Objects.requireNonNull(value);
    Pairs<V, S> pairs = get(key);
    if (pairs != null) {
      put(key, value);
    }
    return pairs;
  }

  /**
   * Computes a pair of V and S for the specified key if the key is not present
   *
   * @param key The key to compute the pair for
   * @param mappingFunction The function to compute the pair for the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> computeIfAbsent(K key, Function<? super K, ? extends Pairs<V, S>> mappingFunction) {
    Objects.requireNonNull(mappingFunction);
    Pairs<V, S> pairs = get(key);
    if (pairs == null) {
      Pairs<V, S> newValue = mappingFunction.apply(key);
      if (newValue != null) {
        put(key, newValue);
        return newValue;
      }
    }
    return pairs;
  }

  /**
   * Computes a pair of V and S for the specified key if the key is present
   *
   * @param key The key to compute the pair for
   * @param remappingFunction The function to compute the pair for the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> computeIfPresent(K key, BiFunction<? super K, ? super Pairs<V, S>, ? extends Pairs<V, S>> remappingFunction) {
    Objects.requireNonNull(remappingFunction);
    Pairs<V, S> pairs = get(key);
    if (pairs != null) {
      Pairs<V, S> newValue = remappingFunction.apply(key, pairs);
      if (newValue != null) {
        put(key, newValue);
        return newValue;
      } else {
        remove(key);
        return null;
      }
    }
    return null;
  }

  /**
   * Computes a pair of V and S for the specified key
   *
   * @param key The key to compute the pair for
   * @param remappingFunction The function to compute the pair for the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> compute(K key, BiFunction<? super K, ? super Pairs<V, S>, ? extends Pairs<V, S>> remappingFunction) {
    Objects.requireNonNull(remappingFunction);
    Pairs<V, S> pairs = get(key);
    Pairs<V, S> newValue = remappingFunction.apply(key, pairs);
    if (newValue == null) {
      // delete mapping
      if (pairs != null || containsKey(key)) {
        // something to remove
        remove(key);
      }
      return null;
    } else {
      // add or replace old mapping
      put(key, newValue);
      return newValue;
    }
  }

  /**
   * Merges the pair of V and S from the specified key with the specified pair of V and S
   *
   * @param key The key to merge the pair from
   * @param value The pair of V and S to merge
   * @param remappingFunction The function to merge the pair of V and S from the specified key with the specified pair of V and S
   * @return Returns the pair of V and S from the specified key
   */
  default Pairs<V, S> merge(K key, Pairs<V, S> value, BiFunction<? super Pairs<V, S>, ? super Pairs<V, S>, ? extends Pairs<V, S>> remappingFunction) {
    Objects.requireNonNull(value);
    Objects.requireNonNull(remappingFunction);
    Pairs<V, S> pairs = get(key);
    if (pairs == null) {
      put(key, value);
      return value;
    } else {
      Pairs<V, S> newValue = remappingFunction.apply(pairs, value);
      if (newValue == null) {
        remove(key);
        return null;
      } else {
        put(key, newValue);
        return newValue;
      }
    }
  }

}
