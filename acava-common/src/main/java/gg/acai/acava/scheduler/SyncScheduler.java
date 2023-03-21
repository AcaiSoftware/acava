package gg.acai.acava.scheduler;

import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 03.12.2022 12:39
 * © Acava - All Rights Reserved
 */
public class SyncScheduler implements Scheduler {

  @Override
  public Scheduler execute(Runnable action) {
    action.run();
    return this;
  }

  @Override
  public SchedulerTask createTask() {
    return new SyncSchedulerTask();
  }

  @Override
  public <T> T supply(Supplier<T> supplier) {
    return supplier.get();
  }

  @Override
  public void close() {
  }
}
