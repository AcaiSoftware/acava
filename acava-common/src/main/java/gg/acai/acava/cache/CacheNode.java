package gg.acai.acava.cache;

/**
 * @author Clouke
 * @since 19.03.2023 15:20
 * © Acava - All Rights Reserved
 */
public interface CacheNode<V> {

  V get();

  void clear();

}
