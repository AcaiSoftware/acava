package gg.acai.acava.io.logging;

import java.awt.Color;

/**
 * @author Clouke
 * @since 04.12.2022 17:13
 * © Acava - All Rights Reserved
 */
public interface Logger {

  void log(String... messages);

  void log(Color color, String... messages);

  void logMultiLine(String... messages);

  void logMultiLine(Color color, String... messages);

}
