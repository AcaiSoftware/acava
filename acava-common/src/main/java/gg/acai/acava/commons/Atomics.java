package gg.acai.acava.commons;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 30.03.2023 15:16
 * © Acava - All Rights Reserved
 */
public final class Atomics {

  private Atomics() {}

  public static AtomicInteger integer(Supplier<Integer> supplier) {
    return new AtomicInteger(supplier.get());
  }

  public static AtomicInteger asInt(int value) {
    return new AtomicInteger(value);
  }

  public static AtomicInteger asInt() {
    return new AtomicInteger();
  }

  public static AtomicBoolean asBoolean(Supplier<Boolean> supplier) {
    return new AtomicBoolean(supplier.get());
  }

  public static AtomicBoolean asBoolean(boolean value) {
    return new AtomicBoolean(value);
  }

  public static AtomicBoolean asBoolean() {
    return new AtomicBoolean();
  }

  public static <T> AtomicReference<T> asReference(Supplier<T> supplier) {
    return new AtomicReference<>(supplier.get());
  }

  public static <T> AtomicReference<T> asReference(T value) {
    return new AtomicReference<>(value);
  }

  public static <T> AtomicReference<T> asReference() {
    return new AtomicReference<>();
  }

  public static AtomicLong asLong(Supplier<Long> supplier) {
    return new AtomicLong(supplier.get());
  }

  public static AtomicLong asLong(long value) {
    return new AtomicLong(value);
  }

  public static AtomicLong asLong() {
    return new AtomicLong();
  }

}
