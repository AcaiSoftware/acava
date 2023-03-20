package gg.acai.acava.commons.hash;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 20.03.2023 20:06
 * © Acava - All Rights Reserved
 */
public final class AsciiHash implements HashFunction<String> {

  private static final Function<String, Integer> HASH_ASCII =
    s -> {
      int hash = 0;
      for (int i = 0; i < s.length(); i++) {
        hash += s.charAt(i);
      }
      return hash;
    };


  @Override
  public Integer apply(String s) {
    return HASH_ASCII.apply(s).hashCode();
  }
}
