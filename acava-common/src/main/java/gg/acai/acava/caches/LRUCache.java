package gg.acai.acava.caches;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Clouke
 * @since 19.03.2023 15:30
 * © Acava - All Rights Reserved
 */
public class LRUCache<K, V> extends AbstractCache<K, V> {

  private final int maxSize;

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  public LRUCache(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap, ParametricCacheBootstrap<?> pcb, CacheReferenceType cacheReferenceType, Lock lock) {
    super(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock);
    this.maxSize = size.orElseThrow(() -> new IllegalArgumentException("Size must be set for LRUCache"));
  }

  @Override
  public Optional<V> get(K key) {
    Optional<V> value = super.get(key);
    if (value.isPresent()) {
      V v = value.get();
      super.remove(key);
      super.put(key, v);
      if (cache.size() > maxSize) {
        cache.remove(cache.keySet().iterator().next());
      }
      statistics.hit();
      return Optional.of(v);
    }
    statistics.miss();
    return Optional.empty();
  }
}
