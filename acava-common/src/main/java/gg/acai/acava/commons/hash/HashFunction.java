package gg.acai.acava.commons.hash;

import java.util.function.Function;

/**
 * @author Clouke
 * @since 20.03.2023 20:07
 * © Acava - All Rights Reserved
 */
public interface HashFunction<T> extends Function<T, Integer> {

  @Override
  Integer apply(T t);

}
