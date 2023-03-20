package gg.acai.acava.caches;

import gg.acai.acava.io.Closeable;

/**
 * @author Clouke
 * @since 23.02.2023 16:03
 * © Acava - All Rights Reserved
 */
public interface ParametricCacheBootstrap<T> extends Closeable {

  void load(T t);

  @Override
  void close();

}
