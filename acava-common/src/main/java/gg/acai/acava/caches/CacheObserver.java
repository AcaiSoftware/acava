package gg.acai.acava.caches;

import javax.annotation.Nullable;

/**
 * @author Clouke
 * @since 20.03.2023 02:02
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface CacheObserver<K, V> {

  void onUpdate(CacheContext ctx, @Nullable K key, @Nullable V value);

}
