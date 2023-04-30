package gg.acai.acava.collect.maps;

import gg.acai.acava.collect.pairs.Pair;
import gg.acai.acava.function.TripleConsumer;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
  Pair<V, S> get(K key);

  /**
   * Gets the value from the specified key
   *
   * @param key The key to get the value from
   * @return Returns the value from the specified key
   */
  Pair<V, S> put(K key, V value, S secondValue);

  /**
   * Removes the pair of V and S from the specified key
   *
   * @param key The key to remove the pair from
   * @return Returns the pair of V and S from the specified key
   */
  Pair<V, S> remove(K key);

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
  default Pair<V, S> put(K key, Pair<V, S> value) {
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
  Collection<Pair<V, S>> values();

  /**
   * Gets a set of all the entries in this map
   *
   * @return Returns a set of all the entries in this map
   */
  Set<Map.Entry<K, Pair<V, S>>> entrySet();

  /**
   * Gets the value from the specified key or the default value if the key is not present
   *
   * @param key The key to get the value from
   * @param defaultValue The default value to return if the key is not present
   * @return Returns the value from the specified key or the default value if the key is not present
   */
  default Pair<V, S> getOrDefault(K key, Pair<V, S> defaultValue) {
    Pair<V, S> pair = get(key);
    return pair == null ? defaultValue : pair;
  }

  /**
   * Performs the specified action for each entry in this map until all entries have been processed.
   *
   * @param action The action to be performed for each entry
   */
  default void forEach(TripleConsumer<? super K, ? super V, ? super S> action) {
    Objects.requireNonNull(action);
    for (Map.Entry<K, Pair<V, S>> entry : entrySet()) {
      action.accept(entry.getKey(), entry.getValue().left(), entry.getValue().right());
    }
  }

  /**
   * Put the specified pair of V and S into the specified key if the key is not present
   *
   * @param key The key to put the pair into
   * @param value The pair of V and S to put into the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pair<V, S> putIfAbsent(K key, Pair<V, S> value) {
    Pair<V, S> pair = get(key);
    if (pair == null) {
      pair = put(key, value);
    }
    return pair;
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
    Pair<V, S> pair = get(key);
    if (pair != null && pair.equals(value)) {
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
  default boolean replace(K key, Pair<V, S> oldValue, Pair<V, S> newValue) {
    Objects.requireNonNull(oldValue);
    Objects.requireNonNull(newValue);
    Pair<V, S> pair = get(key);
    if (pair != null && pair.equals(oldValue)) {
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
  default Pair<V, S> replace(K key, Pair<V, S> value) {
    Objects.requireNonNull(value);
    Pair<V, S> pair = get(key);
    if (pair != null) {
      put(key, value);
    }
    return pair;
  }

  /**
   * Computes a pair of V and S for the specified key if the key is not present
   *
   * @param key The key to compute the pair for
   * @param mappingFunction The function to compute the pair for the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pair<V, S> computeIfAbsent(K key, Function<? super K, ? extends Pair<V, S>> mappingFunction) {
    Objects.requireNonNull(mappingFunction);
    Pair<V, S> pair = get(key);
    if (pair == null) {
      Pair<V, S> newValue = mappingFunction.apply(key);
      if (newValue != null) {
        put(key, newValue);
        return newValue;
      }
    }
    return pair;
  }

  /**
   * Computes a pair of V and S for the specified key if the key is present
   *
   * @param key The key to compute the pair for
   * @param remappingFunction The function to compute the pair for the specified key
   * @return Returns the pair of V and S from the specified key
   */
  default Pair<V, S> computeIfPresent(K key, BiFunction<? super K, ? super Pair<V, S>, ? extends Pair<V, S>> remappingFunction) {
    Objects.requireNonNull(remappingFunction);
    Pair<V, S> pair = get(key);
    if (pair != null) {
      Pair<V, S> newValue = remappingFunction.apply(key, pair);
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
  default Pair<V, S> compute(K key, BiFunction<? super K, ? super Pair<V, S>, ? extends Pair<V, S>> remappingFunction) {
    Objects.requireNonNull(remappingFunction);
    Pair<V, S> pair = get(key);
    Pair<V, S> newValue = remappingFunction.apply(key, pair);
    if (newValue == null) {
      // delete mapping
      if (pair != null || containsKey(key)) {
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
  default Pair<V, S> merge(K key, Pair<V, S> value, BiFunction<? super Pair<V, S>, ? super Pair<V, S>, ? extends Pair<V, S>> remappingFunction) {
    Objects.requireNonNull(value);
    Objects.requireNonNull(remappingFunction);
    Pair<V, S> pair = get(key);
    if (pair == null) {
      put(key, value);
      return value;
    } else {
      Pair<V, S> newValue = remappingFunction.apply(pair, value);
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
