package gg.acai.acava.cache.element;

import gg.acai.acava.cache.CacheNode;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Clouke
 * @since 19.03.2023 18:15
 * © Acava - All Rights Reserved
 */
public class PhantomCacheNode<V> implements CacheNode<V> {

  private PhantomReference<V> reference;

  public PhantomCacheNode(V value, ReferenceQueue<V> queue) {
    this.reference = new PhantomReference<>(value, queue);
  }

  public PhantomCacheNode(V value) {
    this(value, null);
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
