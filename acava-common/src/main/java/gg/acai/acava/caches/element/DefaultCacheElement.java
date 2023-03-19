package gg.acai.acava.caches.element;

import gg.acai.acava.caches.CacheElement;

/**
 * @author Clouke
 * @since 19.03.2023 15:21
 * © Acava - All Rights Reserved
 */
public final class DefaultCacheElement<V> implements CacheElement<V> {

  private V value;

  public DefaultCacheElement(V value) {
    this.value = value;
  }

  @Override
  public V get() {
    return value;
  }

  @Override
  public void clear() {
    value = null;
  }

}
