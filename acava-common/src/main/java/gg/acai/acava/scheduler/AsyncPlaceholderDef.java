package gg.acai.acava.scheduler;

import gg.acai.acava.io.Callback;

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

    @Override
    public AsyncPlaceholder<T> whenComplete(Callback<T> callback) {
        scheduler.execute(() -> callback.onCallback(value));
        return this;
    }

    @Override
    public T get() {
        return scheduler.supply(() -> value);
    }
}
