package gg.acai.acava.scheduler;

import gg.acai.acava.event.Cancellable;
import gg.acai.acava.io.Closeable;

import java.util.concurrent.TimeUnit;

/**
 * @author Clouke
 * @since 03.12.2022 12:17
 * © Acava - All Rights Reserved
 */
public interface SchedulerTask extends Closeable, Cancellable {

    /**
     * Schedules a task to be executed after the given delay.
     *
     * @param unit The unit of time.
     * @param delay The delay in the specified unit.
     * @return The scheduler task.
     */
    SchedulerTask later(TimeUnit unit, long delay);

    /**
     * Schedules a task to be executed every given interval.
     *
     * @param unit The unit of time.
     * @param interval The interval in the specified unit.
     * @return The scheduler task.
     */
    SchedulerTask every(TimeUnit unit, long interval);

    /**
     * Sets the action to be executed.
     *
     * @param action The action to be executed.
     * @return The scheduler task.
     */
    SchedulerTask action(Runnable action);

    /**
     * Starts the task.
     *
     * @return The scheduler task.
     */
    SchedulerTask start();

    /**
     * Cancels the task.
     */
    void cancel();

}
