package gg.acai.acava.event;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Clouke
 * @since 02.12.2022 18:45
 * © Acava - All Rights Reserved
 */
public final class EventBus {

    private final Map<Class<? extends EventListener>, EventListener> compositeSubscriptions;

    public EventBus() {
        this.compositeSubscriptions = new HashMap<>();
    }

    public void post(Event event) {
        compositeSubscriptions.values().forEach(listener -> listener.onEvent(event));
    }

    public void postAsync(Event event) {
        CompletableFuture.runAsync(() -> {
            post(event);
        });
    }

    public void postAssigned(Event event, EventListener listener) {
        listener.onEvent(event);
    }

    public void postAssigned(Event event, Class<? extends EventListener> listener) {
        EventListener eventListener = compositeSubscriptions.get(listener);
        if (eventListener != null)
            eventListener.onEvent(event);
    }

    public void postAssignedAsync(Event event, Class<? extends EventListener> listener) {
        CompletableFuture.runAsync(() -> {
            compositeSubscriptions.get(listener).onEvent(event);
        });
    }

    public void postAssignedAsync(Event event, EventListener listener) {
        CompletableFuture.runAsync(() -> {
            listener.onEvent(event);
        });
    }

    public void register(Class<? extends EventListener> listenerClass) {
        try {
            this.compositeSubscriptions.put(listenerClass, listenerClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void register(EventListener listener) {
        this.compositeSubscriptions.put(listener.getClass(), listener);
    }

    public void unregister(Class<? extends EventListener> listenerClass) {
        this.compositeSubscriptions.remove(listenerClass);
    }

    public EventListener get(Class<? extends EventListener> listenerClass) {
        return this.compositeSubscriptions.get(listenerClass);
    }

    public Map<Class<? extends EventListener>, EventListener> asMap() {
        return this.compositeSubscriptions;
    }
}
