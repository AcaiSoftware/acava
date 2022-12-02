package gg.acai.acava.event;

/**
 * @author Clouke
 * @since 02.12.2022 18:43
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface EventListener {

    /**
     * Called when the event is fired.
     * @param event The event that was fired.
     */
    void onEvent(Event event);

}
