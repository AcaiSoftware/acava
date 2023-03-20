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
  protected final CacheObserver<K, V> observer;

  protected final Map<K, CacheNode<V>> cache;

  public static class DEFAULT_CACHE<K, V> extends AbstractCache<K, V> {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public DEFAULT_CACHE(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap,
      ParametricCacheBootstrap<?> pcb, CacheReferenceType cacheReferenceType, Lock lock, CacheObserver<K, V> observer) {
        super(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock, observer);
    }

    @Override
    public String toString() {
      return "DEFAULT_CACHE{" +
        "cache=" + cache +
        '}';
    }
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  public AbstractCache(CacheType type, Optional<Integer> size, long expireAfterWrite, TimeUnit unit, CacheBootstrap bootstrap, ParametricCacheBootstrap<?> pcb, CacheReferenceType cacheReferenceType, Lock lock, CacheObserver<K, V> observer) {
    this.lock = lock;
    this.useLock = lock != null;
    this.expireAfterWrite = expireAfterWrite;
    this.unit = unit;
    this.bootstrap = bootstrap;
    this.pcb = pcb;
    this.cacheReferenceType = cacheReferenceType;
    this.observer = observer;
    switch (type) {
      case DEFAULT:
      case REMOVAL_AFTER_READ:
        cache = new HashMap<>();
        break;
      case FIXED_SIZE:
        cache = new FixedSizeHashMap<>(size.orElseThrow(() -> new IllegalArgumentException("Size must be specified for fixed size cache")));
        break;
      case LRU:
        cache = new LinkedHashMap<>(size.orElseThrow(() -> new IllegalArgumentException("Size must be specified for LRU cache")), 0.75f, true);
        break;
      default:
        throw new IllegalArgumentException("Unknown cache type: " + type);
    }
  }

  @Override
  public abstract String toString();

  @Override
  public void put(K key, V value) {
    try {
      lock();
      CacheNode<V> reference = cacheReferenceType.createWith(value);
      cache.put(key, reference);
      notify(CacheContext.PUT, key, value);
      statistics.use();
    } finally {
      unlock();
    }
  }

  @Override
  public Optional<V> get(K key) {
    try {
      lock();
      CacheNode<V> node = cache.get(key);
      if (node == null) {
        statistics.miss();
        return Optional.empty();
      }
      V value = node.get();
      if (value == null) {
        cache.remove(key);
        statistics.miss();
        return Optional.empty();
      }
      notify(CacheContext.GET, key, value);
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
      CacheNode<V> node = cache.remove(key);
      notify(CacheContext.REMOVE, key, node.get());
      node.clear();
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
    CacheNode<V> node;
    for (Map.Entry<K, CacheNode<V>> entry : cache.entrySet()) {
      if ((node = entry.getValue()) != null && node.get() == value) {
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
    CacheNode<V> node;
    for (Map.Entry<K, CacheNode<V>> entry : cache.entrySet()) {
      if ((node = entry.getValue()) != null) {
        values.add(node.get());
      }
    }
    return values;
  }

  @Override
  public Map<K, V> asMap() {
    Map<K, V> map = new HashMap<>(cache.size());
    CacheNode<V> node;
    for (Map.Entry<K, CacheNode<V>> entry : cache.entrySet()) {
      if ((node = entry.getValue()) != null) {
        map.put(entry.getKey(), node.get());
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

  protected void notify(CacheContext ctx, K key, V value) {
    if (observer != null) observer.onUpdate(ctx, key, value);
  }
}
