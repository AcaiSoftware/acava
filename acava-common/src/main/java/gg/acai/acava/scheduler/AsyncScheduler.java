package gg.acai.acava.scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Clouke
 * @since 03.12.2022 12:15
 * © Acava - All Rights Reserved
 */
public class AsyncScheduler implements Scheduler {

    private static final ExecutorService CACHED_EXECUTOR = Executors.newCachedThreadPool();
    private final ExecutorService executor;

    public AsyncScheduler(ExecutorService executor) {
        this.executor = executor;
    }

    public AsyncScheduler() {
        this(null);
    }

    @Override
    public Scheduler execute(Runnable action) {
        CompletableFuture.runAsync(action, executor == null ? CACHED_EXECUTOR : executor);
        return this;
    }

    @Override
    public SchedulerTask createTask() {
        return new AsyncSchedulerTask();
    }

    @Override
    public void close() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}
