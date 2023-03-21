package gg.acai.acava.io;

/**
 * A simple closeable interface.
 *
 * @author Clouke
 * @since 02.12.2022 21:24
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface Closeable {

  /**
   * Closes the closeable.
   */
  void close();

}
