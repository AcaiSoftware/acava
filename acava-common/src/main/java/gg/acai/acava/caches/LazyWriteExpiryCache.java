package gg.acai.acava.caches;

import gg.acai.acava.Requisites;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Clouke
 * @since 19.03.2023 22:32
 * © Acava - All Rights Reserved
 */
public final class LazyWriteExpiryCache<K, V> extends AbstractCache<K, V> {

  private final Map<K, Long> writes = new HashMap<>();

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  public LazyWriteExpiryCache(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap, ParametricCacheBootstrap<?> pcb, CacheReferenceType cacheReferenceType, Lock lock, CacheObserver<K, V> observer) {
    super(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock, observer);
    Requisites.checkArgument(expireAfterWrite > 0, "Expire after write must be greater than 0");
  }

  @Override
  public void put(K key, V value) {
    try {
      lock();
      writes.forEach((k, v) -> {
        long now = System.currentTimeMillis();
        if (now - v >= unit.toMillis(expireAfterWrite)) {
          synchronized (writes) {
            cache.remove(k);
            writes.remove(k);
          }
        }
      });
      writes.put(key, System.currentTimeMillis());
      super.put(key, value);
    } finally {
      unlock();
    }
  }

  @Override
  public Optional<V> get(K key) {
    try {
      lock();
      writes.forEach((k, v) -> {
        long now = System.currentTimeMillis();
        if (now - v >= unit.toMillis(expireAfterWrite)) {
          synchronized (writes) {
            cache.remove(k);
            writes.remove(k);
          }
        }
      });
      return super.get(key);
    } finally {
      unlock();
    }
  }

}
