package gg.acai.acava.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Clouke
 * @since 03.12.2022 12:23
 * © Acava - All Rights Reserved
 */
public class AsyncSchedulerTask implements SchedulerTask {

    private final ScheduledExecutorService executorService;
    private Runnable runnable;
    private TimeUnit unit;
    private long interval;
    private Context context;

    public AsyncSchedulerTask(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    public AsyncSchedulerTask() {
        this(Executors.newScheduledThreadPool(1));
    }

    @Override
    public SchedulerTask later(TimeUnit unit, long delay) {
        this.context = Context.LATER;
        this.unit = unit;
        this.interval = delay;
        return this;
    }

    @Override
    public SchedulerTask every(TimeUnit unit, long interval) {
        this.context = Context.TIMER;
        this.unit = unit;
        this.interval = interval;
        return this;
    }

    @Override
    public SchedulerTask action(Runnable action) {
        this.runnable = action;
        return this;
    }

    @Override
    public SchedulerTask start() {
        switch (context) {
            case TIMER:
                executorService.scheduleAtFixedRate(() -> {
                    runnable.run();
                }, 0, interval, unit);
                break;
            case LATER:
                executorService.schedule(() -> {
                    runnable.run();
                }, interval, unit);
                break;
        }
        return this;
    }

    @Override
    public boolean isCancelled() {
        return executorService.isShutdown();
    }

    @Override
    public void cancel() {
        executorService.shutdown();
    }

    @Override
    public void close() {
        cancel();
    }

    private enum Context {
        LATER, TIMER
    }
}
