package gg.acai.acava.caches.element;

import gg.acai.acava.caches.CacheNode;

import java.lang.ref.SoftReference;

/**
 * @author Clouke
 * @since 19.03.2023 15:20
 * © Acava - All Rights Reserved
 */
public final class SoftCacheNode<V> implements CacheNode<V> {

  private SoftReference<V> reference;

  public SoftCacheNode(V value) {
    this.reference = new SoftReference<>(value);
  }

  @Override
  public V get() {
    return reference.get();
  }

  @Override
  public void clear() {
    reference.clear();
    reference = null;
  }

}
