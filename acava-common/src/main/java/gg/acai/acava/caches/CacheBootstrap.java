package gg.acai.acava.caches;

import gg.acai.acava.io.Closeable;

/**
 * @author Clouke
 * @since 23.02.2023 16:02
 * © Acava - All Rights Reserved
 */
public interface CacheBootstrap extends Closeable {

  void load();

  @Override
  void close();

}
