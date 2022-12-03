package gg.acai.acava.scheduler;

import gg.acai.acava.io.Closeable;

/**
 * @author Clouke
 * @since 03.12.2022 12:13
 * © Acava - All Rights Reserved
 */
public interface Scheduler extends Closeable {

    /**
     * Schedules a task to be executed on the scheduler.
     *
     * @param action The action to be executed.
     * @return The scheduler.
     */
    Scheduler execute(Runnable action);

    /**
     * Creates a new task.
     *
     * @return Returns a new task.
     */
    SchedulerTask createTask();

}
