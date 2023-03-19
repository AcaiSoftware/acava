package gg.acai.acava.caches.element;

import gg.acai.acava.caches.CacheElement;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Clouke
 * @since 19.03.2023 18:15
 * © Acava - All Rights Reserved
 */
public class PhantomCacheElement<V> implements CacheElement<V> {

  private PhantomReference<V> reference;

  public PhantomCacheElement(V value, ReferenceQueue<V> queue) {
    this.reference = new PhantomReference<>(value, queue);
  }

  public PhantomCacheElement(V value) {
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
