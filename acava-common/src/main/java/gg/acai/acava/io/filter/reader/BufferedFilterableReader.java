package gg.acai.acava.io.filter.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Predicate;

/**
 * @author Clouke
 * @since 01.04.2023 23:51
 * © Acava - All Rights Reserved
 */
public class BufferedFilterableReader implements FilterableReader {
  @Override
  public String find(Reader reader, Predicate<String> matcher) throws RuntimeException {
    String line;
    try (BufferedReader bufferedReader = new BufferedReader(reader)) {
      while ((line = bufferedReader.readLine()) != null)
        if (matcher.test(line))
          break;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return line;
  }
}
