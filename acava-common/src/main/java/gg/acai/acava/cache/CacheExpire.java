package gg.acai.acava.cache;

import gg.acai.acava.scheduler.Scheduler;
import gg.acai.acava.scheduler.SchedulerTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 03.12.2022 19:55
 * © Acava - All Rights Reserved
 */
public class CacheExpire<K, V> implements CacheDuplex<K, V> {

    private final Map<K, V> cache;
    private final SchedulerTask scheduler;
    private final TimeUnit unit;
    private final long duration;

    public CacheExpire(Map<K, V> map, SchedulerTask task, TimeUnit unit, long duration) {
        this.cache = map;
        this.scheduler = task;
        this.unit = unit;
        this.duration = duration;
    }

    public CacheExpire(Scheduler scheduler, TimeUnit unit, long duration) {
        this(new HashMap<>(), scheduler.createTask(), unit, duration);
    }

    public CacheExpire(SchedulerTask task, TimeUnit unit, long duration) {
        this(new HashMap<>(), task, unit, duration);
    }

    @Override
    public V get(K key) {
        return this.cache.get(key);
    }

    @Override
    public void set(K key, V value) {
        this.cache.put(key, value);
        this.scheduler
                .later(unit, duration)
                .action(() -> {
                    this.cache.remove(key);
                });
    }

    @Override
    public boolean exists(K key) {
        return this.cache.containsKey(key);
    }

    @Override
    public void delete(K key) {
        this.cache.remove(key);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public boolean isEmpty() {
        return this.cache.isEmpty();
    }

    @Override
    public void invalidate() {
        this.cache.clear();
    }

    @Override
    public void invalidateNulls() {
        this.cache.entrySet().removeIf(entry -> entry.getValue() == null);
    }

    @Override
    public Stream<V> stream() {
        return this.cache.values().stream();
    }

    @Override
    public Collection<V> values() {
        return this.cache.values();
    }

    @Override
    public CacheDuplex<K, V> delegate(CacheDuplex<K, V> other) {
        this.cache.forEach(other::set);
        return other;
    }

    @Override
    public Map<K, V> asMap() {
        return this.cache;
    }

    @Override
    public void close() {
        this.scheduler.close();
        this.cache.clear();
    }

    @Override
    public String toString() {
        return "CacheExpire{" +
                "cache=" + cache +
                ", scheduler=" + scheduler +
                ", unit=" + unit +
                ", duration=" + duration +
                '}';
    }
}
