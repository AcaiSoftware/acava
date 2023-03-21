package gg.acai.acava.commons.hash;

import gg.acai.acava.io.Closeable;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Clouke
 * @since 20.03.2023 19:46
 * © Acava - All Rights Reserved
 */
public final class BloomFilter<T> implements Predicate<T>, Closeable {

  private final int size;
  private final Hash<T>[] hashes;
  private final boolean[] bits;

  public static <U> BloomFilter<U> copyOf(BloomFilter<U> filter) {
    BloomFilter<U> copy = new BloomFilter<>(filter.size, filter.hashes.length);
    System.arraycopy(filter.bits, 0, copy.bits, 0, filter.bits.length);
    System.arraycopy(filter.hashes, 0, copy.hashes, 0, filter.hashes.length);
    return copy;
  }

  public static <U> BloomFilter<U> from(Collection<U> collection, int hashes) {
    BloomFilter<U> filter = new BloomFilter<>(collection.size(), hashes);
    collection.forEach(filter::add);
    return filter;
  }

  @SuppressWarnings("unchecked")
  public BloomFilter(int size, int hashes) {
    this.size = size;
    try {
      this.hashes = (Hash<T>[]) new Hash[hashes];
    } catch (ClassCastException e) {
      throw new IllegalArgumentException("Hashes must be of type Hash<T>");
    }

    this.bits = new boolean[size];
    for (int i = 0; i < hashes; i++)
      this.hashes[i] = new Hash<>(size);
  }

  public void add(T t) {
    Arrays.stream(hashes)
      .forEach(hash -> {
        int hashValue = hash.apply(t);
        bits[hashValue] = true;
      });
  }

  @Override
  public boolean test(T t) {
    return Arrays.stream(hashes)
      .allMatch(hash -> {
        int hashValue = hash.apply(t);
        return bits[hashValue];
      });
  }

  @Override
  public void close() {
    for (int i = 0; i < size; i++)
      bits[i] = false;
    Arrays.fill(hashes, null);
  }
}
