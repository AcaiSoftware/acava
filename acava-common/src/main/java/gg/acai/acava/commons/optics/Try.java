package gg.acai.acava.commons.optics;

import gg.acai.acava.function.Action;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 27.03.2023 16:29
 * © Acava - All Rights Reserved
 */
public class Try {

  public interface Prone {
    void onError(Action<Exception> action);
  }

  private static final Prone SUCCESS = action -> {};
  private static final Function<Exception, Prone> FAILURE = e -> (Prone) action -> action.accept(e);

  public static <T> T get(Supplier<T> supplier, T defaultValue) {
    try {
      return supplier.get();
    } catch (Exception e) {
      return defaultValue;
    }
  }

  public static Prone run(Runnable runnable) {
    try {
      runnable.run();
      return SUCCESS;
    } catch (Exception e) {
      return FAILURE.apply(e);
    }
  }

}
