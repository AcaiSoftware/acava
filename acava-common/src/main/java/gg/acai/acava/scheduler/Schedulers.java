package gg.acai.acava.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/**
 * @author Clouke
 * @since 03.12.2022 12:12
 * © Acava - All Rights Reserved
 */
public final class Schedulers {

    public static Scheduler async() {
        return new AsyncScheduler();
    }

    public static Scheduler async(ExecutorService executor) {
        return new AsyncScheduler(executor);
    }

    public static Scheduler sync() {
        return new SyncScheduler();
    }

    public static <T> AsyncPlaceholder<T> supplyAsync(Supplier<T> supplier) {
        Scheduler scheduler = async();
        return scheduler.supply(() -> new AsyncPlaceholderDef<>(supplier, scheduler));
    }

    public static <T> AsyncPlaceholder<T> supplyAsync(Supplier<T> supplier, ExecutorService executor) {
        Scheduler scheduler = async(executor);
        return scheduler.supply(() -> new AsyncPlaceholderDef<>(supplier, scheduler));
    }

    public static <T> AsyncPlaceholder<T> supplyAsync(Supplier<T> supplier, Scheduler scheduler) {
        return scheduler.supply(() -> new AsyncPlaceholderDef<>(supplier, scheduler));
    }

}
