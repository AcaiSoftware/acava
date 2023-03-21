package gg.acai.acava.event;

import gg.acai.acava.function.Action;
import gg.acai.acava.scheduler.Scheduler;

import java.util.Map;

/**
 * @author Clouke
 * @since 03.12.2022 18:45
 * © Acava - All Rights Reserved
 */
public interface EventBus {

  /**
   * Posts an event to the event bus with the given scheduler.
   *
   * @param scheduler The scheduler to post the event with.
   * @param event     The event to post.
   */
  EventBus post(Scheduler scheduler, Event event);

  /**
   * Posts an event to the event bus.
   *
   * @param event The event to post.
   */
  EventBus post(Event event);

  /**
   * Posts an event to the event bus with the given scheduler and only to the given listener.
   *
   * @param scheduler The scheduler to post the event with.
   * @param event     The event to post.
   * @param listener  The listener to post the event to.
   */
  EventBus postAssigned(Scheduler scheduler, Event event, EventListener listener);

  /**
   * Listens to a direct event.
   *
   * @param rawClass The class of the event.
   * @param action   The action to execute when the event is fired.
   */
  EventBus listenDirect(Class<? extends Event> rawClass, Action<Event> action);

  /**
   * Registers a listener to the event bus.
   *
   * @param listenerClass The class of the listener.
   */
  EventBus register(Class<? extends EventListener> listenerClass);

  /**
   * Registers a listener to the event bus.
   *
   * @param listener The listener to register.
   */
  EventBus register(EventListener listener);

  /**
   * Unregisters a listener from the event bus.
   *
   * @param listenerClass The class of the listener.
   */
  EventBus unregister(Class<? extends EventListener> listenerClass);

  /**
   * Gets a listener from the event bus.
   *
   * @param listenerClass The class of the listener.
   */
  EventListener get(Class<? extends EventListener> listenerClass);

  /**
   * Gets all listeners from the event bus.
   */
  Map<Class<? extends EventListener>, EventListener> asMap();

}
