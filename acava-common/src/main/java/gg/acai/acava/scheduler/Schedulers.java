package gg.acai.acava.scheduler;

import java.util.concurrent.ExecutorService;

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

}
