package gg.acai.acava.caches;

import gg.acai.acava.collect.maps.FixedSizeHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Clouke
 * @since 19.03.2023 14:50
 * © Acava - All Rights Reserved
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

  protected final Lock lock;
  protected final boolean useLock;
  protected final long expireAfterWrite;
  protected final TimeUnit unit;
  protected final CacheBootstrap bootstrap;
  protected final ParametricCacheBootstrap<?> pcb;
  protected final CacheReferenceType cacheReferenceType;
  protected final CacheStatistics statistics = new CacheStatistics();

  protected final Map<K, CacheElement<V>> cache;

  public static class DEFAULT_CACHE<K, V> extends AbstractCache<K, V> {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public DEFAULT_CACHE(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap,
      ParametricCacheBootstrap<?> pcb, CacheReferenceType cacheReferenceType, Lock lock) {
        super(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock);
      }
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  public AbstractCache(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap, ParametricCacheBootstrap<?> pcb, CacheReferenceType cacheReferenceType, Lock lock) {
    this.lock = lock;
    this.useLock = lock != null;
    this.expireAfterWrite = expireAfterWrite;
    this.unit = unit;
    this.bootstrap = bootstrap;
    this.pcb = pcb;
    this.cacheReferenceType = cacheReferenceType;
    switch (type) {
      case DEFAULT:
        if (size.isPresent()) {
          throw new IllegalArgumentException("Size must not be specified for default cache");
        }
        cache = new HashMap<>();
        break;
      case FIXED_SIZE:
        cache = new FixedSizeHashMap<>(size.orElseThrow(() -> new IllegalArgumentException("Size must be specified for fixed size cache")));
        break;
      case LRU:
        cache = new LinkedHashMap<>(size.orElseThrow(() -> new IllegalArgumentException("Size must be specified for fixed size cache")), 0.75f, true);
        break;
      default:
        throw new IllegalArgumentException("Unknown cache type: " + type);
    }
  }

  @Override
  public void put(K key, V value) {
    try {
      lock();
      CacheElement<V> reference = cacheReferenceType.createWith(value);
      cache.put(key, reference);
      statistics.use();
    } finally {
      unlock();
    }
  }

  @Override
  public Optional<V> get(K key) {
    try {
      lock();
      CacheElement<V> element = cache.get(key);
      if (element == null) {
        statistics.miss();
        return Optional.empty();
      }
      V value = element.get();
      if (value == null) {
        cache.remove(key);
        statistics.miss();
        return Optional.empty();
      }
      statistics.hit();
      return Optional.of(value);
    } finally {
      unlock();
    }
  }

  @Override
  public void remove(K key) {
    try {
      lock();
      cache.remove(key);
    } finally {
      unlock();
    }
  }

  @Override
  public void invalidate() {
    try {
      lock();
      cache.clear();
      statistics.clear();
    } finally {
      unlock();
    }
  }

  @Override
  public boolean containsKey(K key) {
    return cache.containsKey(key);
  }

  @Override
  public boolean containsValue(V value) {
    CacheElement<V> element;
    for (Map.Entry<K, CacheElement<V>> entry : cache.entrySet()) {
      if ((element = entry.getValue()) != null && element.get() == value) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int size() {
    return cache.size();
  }

  @Override
  public boolean isEmpty() {
    return cache.isEmpty();
  }

  @Override
  public Collection<V> values() {
    Collection<V> values = new ArrayList<>(cache.size());
    CacheElement<V> element;
    for (Map.Entry<K, CacheElement<V>> entry : cache.entrySet()) {
      if ((element = entry.getValue()) != null) {
        values.add(element.get());
      }
    }
    return values;
  }

  @Override
  public Map<K, V> asMap() {
    Map<K, V> map = new HashMap<>(cache.size());
    CacheElement<V> element;
    for (Map.Entry<K, CacheElement<V>> entry : cache.entrySet()) {
      if ((element = entry.getValue()) != null) {
        map.put(entry.getKey(), element.get());
      }
    }
    return map;
  }

  @Override
  public CacheStatistics statistics() {
    return statistics;
  }

  protected void lock() {
    if (useLock) lock.lock();
  }

  protected void unlock() {
    if (useLock) lock.unlock();
  }
}
