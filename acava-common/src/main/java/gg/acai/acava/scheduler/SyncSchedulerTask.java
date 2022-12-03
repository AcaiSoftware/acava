package gg.acai.acava.scheduler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Clouke
 * @since 03.12.2022 12:43
 * © Acava - All Rights Reserved
 */
public class SyncSchedulerTask implements SchedulerTask {

    private final Timer timer;
    private TimeUnit unit;
    private long delay;
    private Context context;
    private Runnable action;

    public SyncSchedulerTask(Timer timer) {
        this.timer = timer;
    }

    public SyncSchedulerTask() {
        this(new Timer());
    }

    @Override
    public SchedulerTask later(TimeUnit unit, long delay) {
        this.unit = unit;
        this.delay = delay;
        this.context = Context.LATER;
        return this;
    }

    @Override
    public SchedulerTask every(TimeUnit unit, long interval) {
        this.unit = unit;
        this.delay = interval;
        this.context = Context.TIMER;
        return null;
    }

    @Override
    public SchedulerTask action(Runnable action) {
        this.action = action;
        return this;
    }

    @Override
    public SchedulerTask start() {
        switch (context) {
            case LATER:
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        action.run();
                    }
                }, unit.toMillis(delay));
                break;
            case TIMER:
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        action.run();
                    }
                }, 0, unit.toMillis(delay));
                break;
        }
        return this;
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

    @Override
    public void close() {
        cancel();
    }

    private enum Context {
        LATER, TIMER
    }
}
