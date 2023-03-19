package gg.acai.acava.caches;

import gg.acai.acava.caches.element.DefaultCacheNode;
import gg.acai.acava.caches.element.PhantomCacheNode;
import gg.acai.acava.caches.element.SoftCacheNode;
import gg.acai.acava.caches.element.WeakCacheNode;

/**
 * @author Clouke
 * @since 19.03.2023 14:43
 * © Acava - All Rights Reserved
 */
public enum CacheReferenceType {
  SOFT,
  WEAK,
  PHANTOM,
  DEFAULT;

  public <V> CacheNode<V> createWith(V value) {
    switch (this) {
      case SOFT:
        return new SoftCacheNode<>(value);
      case WEAK:
        return new WeakCacheNode<>(value);
      case DEFAULT:
        return new DefaultCacheNode<>(value);
      case PHANTOM:
        return new PhantomCacheNode<>(value);
      default:
        throw new IllegalStateException("Unknown reference type: " + this);
    }
  }
}
