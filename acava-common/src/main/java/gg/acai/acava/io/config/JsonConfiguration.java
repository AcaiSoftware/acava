package gg.acai.acava.io.config;

import gg.acai.acava.function.Action;
import gg.acai.acava.scheduler.AsyncPlaceholder;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 02.12.2022 18:30
 * © Acava - All Rights Reserved
 */
public interface JsonConfiguration<T> {

  /**
   * Gets the value of the given key.
   *
   * @param key The key to get the value of.
   * @return The value of the given key.
   */
  T get(String key);

  /**
   * Gets the value of the given key asynchronously.
   *
   * @param key The key to get the value of.
   * @return The value of the given key.
   */
  AsyncPlaceholder<T> getAsync(String key);

  /**
   * Gets the value of the given key as an optional.
   *
   * @param key The key to get the value of.
   * @return The value of the given key.
   */
  Optional<T> getOptional(String key);

  /**
   * Gets the value of the given key if present.
   *
   * @param key    The key to get the value of.
   * @param action The action to perform if the value is present.
   */
  JsonConfiguration<T> getIfPresent(String key, Action<T> action);

  /**
   * Streams all values of the configuration.
   *
   * @return A stream of all values.
   */
  Stream<T> stream();

  /**
   * @return Returns a map of the configuration.
   */
  Map<String, Object> asMap();

}
