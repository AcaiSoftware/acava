package gg.acai.acava.cache;

import gg.acai.acava.cache.element.DefaultCacheNode;
import gg.acai.acava.cache.element.PhantomCacheNode;
import gg.acai.acava.cache.element.SoftCacheNode;
import gg.acai.acava.cache.element.WeakCacheNode;

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
