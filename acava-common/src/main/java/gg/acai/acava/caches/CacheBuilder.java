package gg.acai.acava.caches;

import gg.acai.acava.annotated.ThrowsException;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Clouke
 * @since 23.02.2023 16:59
 * © Acava - All Rights Reserved
 */
public class CacheBuilder<K, V> {

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  private Optional<Integer> size = Optional.empty();
  private CacheType type = CacheType.DEFAULT;
  private long expireAfterWrite = -1L;
  private TimeUnit unit;
  private CacheBootstrap bootstrap;
  private ParametricCacheBootstrap<?> pcb;
  private CacheReferenceType cacheReferenceType = CacheReferenceType.DEFAULT;
  private Lock lock;
  private CacheObserver<?, ?> observer;

  public CacheBuilder<K, V> withSize(int size) {
    this.size = Optional.of(size);
    return this;
  }

  public CacheBuilder<K, V> asType(CacheType type) {
    this.type = type;
    return this;
  }

  public CacheBuilder<K, V> useLock(Lock lock) {
    this.lock = lock;
    return this;
  }

  public CacheBuilder<K, V> expireAfterWrite(long expireAfterWrite, TimeUnit unit) {
    this.expireAfterWrite = expireAfterWrite;
    this.unit = unit;
    return this;
  }

  public CacheBuilder<K, V> withBootstrapper(CacheBootstrap bootstrap) {
    this.bootstrap = bootstrap;
    return this;
  }

  public <U> CacheBuilder<K, V> withTypedBootstrapper(ParametricCacheBootstrap<U> pcb) {
    this.pcb = pcb;
    return this;
  }

  public CacheBuilder<K, V> withReferenceType(CacheReferenceType cacheReferenceType) {
    this.cacheReferenceType = cacheReferenceType;
    return this;
  }

  public <U, O> CacheBuilder<K, V> withObserver(CacheObserver<U, O> observer) {
    this.observer = observer;
    return this;
  }

  @SuppressWarnings("unchecked")
  @ThrowsException(IllegalArgumentException.class)
  public <U, O> Cache<U, O> build() {
    CacheObserver<U, O> observer = null;
    if (this.observer != null) {
      try {
        observer = (CacheObserver<U, O>) this.observer;
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("Generic mismatch between CacheBuilder and CacheObserver");
      }
    }
    if (expireAfterWrite != -1L) {
      Objects.requireNonNull(unit, "TimeUnit must be set if expireAfterWrite is set");
      return new LazyWriteExpiryCache<>(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock, observer);
    }
    switch (type) {
      case LRU:
        return new LRUCache<>(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock, observer);
      case REMOVAL_AFTER_READ:
        return new RemovalAfterReadCache<>(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock, observer);
      default:
        return new AbstractCache.DEFAULT_CACHE<>(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock, observer);
    }
  }

}
