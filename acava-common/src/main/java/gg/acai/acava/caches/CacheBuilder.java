package gg.acai.acava.caches;

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

  public CacheBuilder<K, V> withSize(int size) {
    this.size = Optional.of(size);
    return this;
  }

  public CacheBuilder<K, V> withType(CacheType type) {
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

  public <U, O> Cache<U, O> build() {
    return new AbstractCache.DEFAULT_CACHE<>(type, size, expireAfterWrite, unit, bootstrap, pcb, cacheReferenceType, lock);
  }

}
