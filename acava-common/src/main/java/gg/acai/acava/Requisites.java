package gg.acai.acava;

import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 05.12.2022 01:11
 * © Acava - All Rights Reserved
 */
public final class Requisites {

  public static <T> T requireNonNull(T object) {
    if (object == null) {
      throw new NullPointerException();
    }
    return object;
  }

  public static <T> T requireNonNull(T object, String message) {
    if (object == null) {
      throw new NullPointerException(message);
    }
    return object;
  }

  public static <T> T applyIfNull(T value, T newValue) {
    return value == null ? newValue : value;
  }

  public static <T> T applyIfNull(T value, Supplier<T> newValue) {
    return value == null ? newValue.get() : value;
  }

  public static <T> T applyIfNull(T value, T newValue, String message) {
    if (value == null && newValue == null) {
      throw new NullPointerException(message);
    }
    return value == null ? newValue : value;
  }

  public static void checkArgument(boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkArgument(boolean expression) {
    checkArgument(expression, "Illegal argument");
  }

  public static <T> T createInstance(Class<T> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("Could not create instance of " + clazz.getName());
  }

}
