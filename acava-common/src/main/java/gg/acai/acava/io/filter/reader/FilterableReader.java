package gg.acai.acava.io.filter.reader;

import java.io.Reader;
import java.util.function.Predicate;

/**
 * @author Clouke
 * @since 01.04.2023 23:49
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface FilterableReader {
  String find(Reader reader, Predicate<String> matcher);
}
