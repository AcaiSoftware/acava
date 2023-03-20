package gg.acai.acava.cache.element;

import gg.acai.acava.cache.CacheNode;

import java.lang.ref.WeakReference;

/**
 * @author Clouke
 * @since 19.03.2023 15:21
 * © Acava - All Rights Reserved
 */
public final class WeakCacheNode<V> implements CacheNode<V> {

  private WeakReference<V> reference;

  public WeakCacheNode(V value) {
    this.reference = new WeakReference<>(value);
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
