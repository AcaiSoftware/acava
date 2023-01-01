package gg.acai.acava.scheduler;

import gg.acai.acava.io.Callback;

import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 04.12.2022 01:42
 * © Acava - All Rights Reserved
 */
public class AsyncPlaceholderDef<T> implements AsyncPlaceholder<T> {

    private final T value;
    private final Scheduler scheduler;

    public AsyncPlaceholderDef(T value, Scheduler scheduler) {
        this.value = value;
        this.scheduler = scheduler;
    }

    public AsyncPlaceholderDef(Supplier<T> supplier, Scheduler scheduler) {
        this.value = supplier.get();
        this.scheduler = scheduler;
    }

    @Override
    public AsyncPlaceholder<T> whenComplete(Callback<T> callback) {
        scheduler.execute(() -> callback.onCallback(value));
        return this;
    }

    @Override
    public T get() {
        return scheduler.supply(() -> value);
    }

    @Override
    public T join() throws TimeoutException {
        int spins = 0;
        int checks = 0;
        T value;
        while (true) {
            if (spins > 100) {
                spins = 0;
                Thread.yield();
                checks++;
            }
            value = get();
            if (value != null) break;
            if (checks > 100) throw new TimeoutException("The placeholder timed out");

            spins++;
        }

        return value;
    }

}
