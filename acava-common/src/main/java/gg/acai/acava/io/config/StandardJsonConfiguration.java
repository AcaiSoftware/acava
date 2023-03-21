package gg.acai.acava.io.config;

import gg.acai.acava.function.Action;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 02.12.2022 18:31
 * © Acava - All Rights Reserved
 */
public final class StandardJsonConfiguration<T> implements JsonConfiguration<T> {

  private final Map<String, Object> map;

  public StandardJsonConfiguration(Map<String, Object> map) {
    this.map = map;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T get(String key) {
    T value = null;
    try {
      value = (T) this.map.get(key);
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return value;
  }

  @Override
  public AsyncPlaceholder<T> getAsync(String key) {
    return Schedulers.supplyAsync(() -> this.get(key));
  }

  @Override
  public Optional<T> getOptional(String key) {
    return Optional.ofNullable(this.get(key));
  }

  @Override
  public JsonConfiguration<T> getIfPresent(String key, Action<T> action) {
    T value = this.get(key);
    if (value != null) action.accept(value);
    return this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Stream<T> stream() {
    try {
      return this.map.values()
              .stream()
              .map(value -> (T) value);
    } catch (ClassCastException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Map<String, Object> asMap() {
    return this.map;
  }

}
