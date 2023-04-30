package gg.acai.acava.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 27.04.2023 12:35
 * © Acava - All Rights Reserved
 */
@SuppressWarnings("unchecked")
public class AttributesMapper implements Attributes {

  private final Map<String, Object> attributes;

  public AttributesMapper(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public AttributesMapper() {
    this(new HashMap<>());
  }

  @Override
  public AttributesMapper set(String key, Object value) {
    attributes.put(key, value);
    return this;
  }

  @Override
  public <T> T get(String key) {
    return (T) attributes.get(key);
  }

  @Override
  public <T> T get(String key, T def) {
    return (T) attributes.getOrDefault(key, def);
  }

  @Override
  public void remove(String key) {
    attributes.remove(key);
  }

  @Override
  public Map<String, Object> asMap() {
    return attributes;
  }

  @Override
  public void close() {
    attributes.clear();
  }

  @Override
  public Attributes copy() {
    return new AttributesMapper(new HashMap<>(attributes));
  }

  @Override
  public String toString() {
    return "AttributesMapper{" +
        "attributes=" + attributes +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AttributesMapper)) return false;
    AttributesMapper that = (AttributesMapper) o;
    return attributes.equals(that.attributes);
  }

  @Override
  public int hashCode() {
    return attributes.hashCode();
  }
}