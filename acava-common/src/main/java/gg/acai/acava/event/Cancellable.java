package gg.acai.acava.event;

/**
 * @author Clouke
 * @since 02.12.2022 18:46
 * © Acava - All Rights Reserved
 */
public interface Cancellable {

  /**
   * @return Returns true if the event is cancelled.
   */
  boolean isCancelled();

  /**
   * Cancels the event.
   */
  void cancel();

}
