package gg.acai.acava.collect.maps;

import gg.acai.acava.collect.pairs.MutablePair;
import gg.acai.acava.collect.pairs.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of a BiMap using a HashMap.
 *
 * @author Clouke
 * @since 17.04.2023 01:13
 * © Acava - All Rights Reserved
 */
public class BiHashMap<K, V, S> implements BiMap<K, V, S> {

  private final Map<K, Pair<V, S>> map;

  public BiHashMap() {
    this.map = new HashMap<>();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public boolean containsKey(K key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    for (Pair<V, S> pair : map.values()) {
      if (pair.left().equals(value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsSecondValue(S secondValue) {
    for (Pair<V, S> pair : map.values()) {
      if (pair.right().equals(secondValue)) {
        return true;
      }
    }
    return false;
  }


  @Override
  public Pair<V, S> get(K key) {
    return map.get(key);
  }

  @Override
  public Pair<V, S> put(K key, V value, S secondValue) {
    return map.put(key, new MutablePair<>(value, secondValue));
  }

  @Override
  public Pair<V, S> remove(K key) {
    return map.remove(key);
  }

  @Override
  public void putAll(BiMap<? extends K, ? extends V, ? extends S> m) {
    for (Map.Entry<? extends K, ? extends Pair<? extends V, ? extends S>> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue().left(), entry.getValue().right());
    }
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public BiMap<V, K, S> inverse() {
    BiMap<V, K, S> inverse = new BiHashMap<>();
    for (Map.Entry<K, Pair<V, S>> entry : map.entrySet()) {
      K key = entry.getKey();
      Pair<V, S> value = entry.getValue();
      V left = value.left();
      S secondValue = value.right();
      inverse.put(left, key, secondValue);
    }
    return inverse;
  }

  @Override
  public Set<K> keySet() {
    return map.keySet();
  }

  @Override
  public Collection<Pair<V, S>> values() {
    return map.values();
  }

  @Override
  public Set<Map.Entry<K, Pair<V, S>>> entrySet() {
    return map.entrySet();
  }
}
