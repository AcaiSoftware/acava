package gg.acai.acava.caches;

import gg.acai.acava.caches.element.DefaultCacheElement;
import gg.acai.acava.caches.element.PhantomCacheElement;
import gg.acai.acava.caches.element.SoftCacheElement;
import gg.acai.acava.caches.element.WeakCacheElement;

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

  public <V> CacheElement<V> createWith(V value) {
    switch (this) {
      case SOFT:
        return new SoftCacheElement<>(value);
      case WEAK:
        return new WeakCacheElement<>(value);
      case DEFAULT:
        return new DefaultCacheElement<>(value);
      case PHANTOM:
        return new PhantomCacheElement<>(value);
      default:
        throw new IllegalStateException("Unknown reference type: " + this);
    }
  }
}
