package gg.acai.acava.io;

import java.util.function.Consumer;

/**
 * A simple signal event that can be triggered and listened to.
 *
 * @author Clouke
 * @since 27.02.2023 09:22
 * © Acava - All Rights Reserved
 */
public final class Signal<T> {

    /** The listener that will be called when the signal is triggered. */
    private Consumer<T> listener;
    /** Marker to check if the signal has been triggered. */
    private boolean triggered;
    /** The value that was passed to the trigger method. */
    private T value;

    /**
     * Constructs a new signal with a listener.
     *
     * @param listener The listener that will be called when the signal is triggered.
     */
    public Signal(Consumer<T> listener) {
        this.listener = listener;
    }

    /**
     * Constructs a new signal without a listener.
     */
    public Signal() {
        this(null);
    }

    /**
     * Triggers the signal with a value.
     *
     * @param t The value that will be passed to the listener.
     */
    public void trigger(T t) {
        triggered = true;
        value = t;
        if (listener != null)
            listener.accept(t);
    }

    /**
     * Gets if the signal has been triggered.
     *
     * @return True if the signal has been triggered.
     */
    public boolean triggered() {
        return triggered;
    }

    /**
     * Resets the signal.
     */
    public void reset() {
        triggered = false;
    }

    /**
     * Gets the value that was passed to the trigger method.
     *
     * @return The value that was passed to the trigger method.
     */
    public T value() {
        return value;
    }

    /**
     * Sets the listener that will be called when the signal is triggered.
     *
     * @param listener The listener that will be called when the signal is triggered.
     */
    public void onTrigger(Consumer<T> listener) {
        this.listener = listener;
    }


}
