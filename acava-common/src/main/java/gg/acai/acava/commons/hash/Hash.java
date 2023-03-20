package gg.acai.acava.commons.hash;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 20.03.2023 19:46
 * © Acava - All Rights Reserved
 */
public final class Hash<T> implements Function<T, Integer> {

  private static final Function<String, Integer> HASH_ASCII =
    s -> {
      int hash = 0;
      for (int i = 0; i < s.length(); i++) {
        hash += s.charAt(i);
      }
      return hash;
  };

  private final int idx;

  public Hash(int idx) {
    this.idx = idx;
  }

  @Override
  public Integer apply(T t) {
    return HASH_ASCII.apply(t.toString()).hashCode() % idx;
  }
}
