package gg.acai.acava.event;

import gg.acai.acava.function.Action;
import gg.acai.acava.scheduler.Scheduler;
import gg.acai.acava.scheduler.Schedulers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Clouke
 * @since 02.12.2022 18:45
 * © Acava - All Rights Reserved
 */
public final class CompositeEventBus implements EventBus {

    private final Map<Class<? extends EventListener>, EventListener> compositeSubscriptions;
    private final Map<Class<? extends Event>, Set<Action<Event>>> directSubscriptions;

    public CompositeEventBus() {
        this.compositeSubscriptions = new HashMap<>();
        this.directSubscriptions = new HashMap<>();
    }

    @Override
    public EventBus post(Scheduler scheduler, Event event) {
        scheduler.execute(() -> {
            compositeSubscriptions.values().forEach(listener -> listener.onEvent(event));
            directSubscriptions.values().forEach(actions -> actions.forEach(action -> action.accept(event)));
        });
        return this;
    }

    @Override
    public EventBus post(Event event) {
        return post(Schedulers.sync(), event);
    }

    @Override
    public EventBus postAssigned(Scheduler scheduler, Event event, EventListener listener) {
        scheduler.execute(() -> listener.onEvent(event));
        return this;
    }

    @Override
    public EventBus listenDirect(Class<? extends Event> rawClass, Action<Event> action) {
        directSubscriptions.computeIfAbsent(rawClass, klass -> new HashSet<>()).add(action);
        return this;
    }

    @Override
    public EventBus register(Class<? extends EventListener> listenerClass) {
        try {
            this.compositeSubscriptions.put(listenerClass, listenerClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public EventBus register(EventListener listener) {
        this.compositeSubscriptions.put(listener.getClass(), listener);
        return this;
    }

    @Override
    public EventBus unregister(Class<? extends EventListener> listenerClass) {
        this.compositeSubscriptions.remove(listenerClass);
        return this;
    }

    @Override
    public EventListener get(Class<? extends EventListener> listenerClass) {
        return this.compositeSubscriptions.get(listenerClass);
    }

    @Override
    public Map<Class<? extends EventListener>, EventListener> asMap() {
        return this.compositeSubscriptions;
    }
}
