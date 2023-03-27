package gg.acai.acava.cache;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Clouke
 * @since 20.03.2023 01:15
 * © Acava - All Rights Reserved
 */
public class RemovalAfterReadCache<K, V> extends AbstractCache<K, V> {

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  public RemovalAfterReadCache(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap,
                               ParametricCacheBootstrap<?> pcb, CacheValueType cacheValueType, Lock lock, CacheObserver<K, V> observer) {
      super(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheValueType, lock, observer);
  }

  @Override
  public Optional<V> get(K key) {
    Optional<V> value = super.get(key);
    return value.map(
      toReturn -> {
        cache.remove(key);
        return toReturn;
      });
  }

  @Override
  public String toString() {
    return "RemovalAfterReadCache{" +
            "cache=" + cache +
            '}';
  }
}
