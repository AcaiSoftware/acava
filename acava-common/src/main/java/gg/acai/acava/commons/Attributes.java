package gg.acai.acava.commons;

import gg.acai.acava.io.Closeable;

import java.util.Map;
import java.util.Optional;

/**
 * @author Clouke
 * @since 27.04.2023 12:36
 * © Acava - All Rights Reserved
 */
public interface Attributes extends Closeable {

  /**
   * Sets the given value to the given key.
   *
   * @param key The key to set the value to.
   * @param value The value to set to the key.
   * @return Returns this instance for chaining.
   */
  Attributes set(String key, Object value);

  /**
   * Gets the value of the given key.
   *
   * @param key The key to get the value of.
   * @param <T> The type of the value.
   * @return Returns the value of the given key.
   */
  <T> T get(String key);

  /**
   * Gets the value of the given key.
   *
   * @param key The key to get the value of.
   * @param def The default value to return if the value is null.
   * @param <T> The type of the value.
   * @return Returns the value of the given key.
   */
  <T> T get(String key, T def);

  /**
   * Folds the value of the given key into an optional.
   *
   * @param key The key to get the value of.
   * @param <T> The type of the value.
   * @return Returns an optional of the value.
   */
  default <T> Optional<T> fold(String key) {
    return Optional.ofNullable(get(key));
  }

  /**
   * Folds the value of the given key into an optional.
   *
   * @param key The key to get the value of.
   * @param def The default value to return if the value is null.
   * @param <T> The type of the value.
   * @return Returns an optional of the value or the default value.
   */
  default <T> Optional<T> fold(String key, T def) {
    return Optional.ofNullable(get(key, def));
  }

  /**
   * Removes the value of the given key.
   *
   * @param key The key to remove the value of.
   */
  void remove(String key);

  /**
   * Gets the attributes as a map.
   *
   * @return Returns the attributes as a map.
   */
  Map<String, Object> asMap();

  /**
   * Closes this Attributes instance.
   */
  @Override
  void close();

  /**
   * Copies this Attributes instance.
   *
   * @return Returns a copy of this Attributes instance.
   */
  Attributes copy();

}
