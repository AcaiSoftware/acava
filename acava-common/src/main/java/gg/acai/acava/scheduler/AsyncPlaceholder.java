package gg.acai.acava.scheduler;

import gg.acai.acava.io.Callback;

import java.util.concurrent.TimeoutException;

/**
 * @author Clouke
 * @since 04.12.2022 01:40
 * © Acava - All Rights Reserved
 */
public interface AsyncPlaceholder<T> {

    /**
     * Executes the callback when the placeholder is completed
     * @param callback The callback
     * @return Returns the placeholder
     */
    AsyncPlaceholder<T> whenComplete(Callback<T> callback);

    /**
     * Gets the value
     * @return The value
     */
    T get();

    /**
     * Joins the placeholder until it is completed
     *
     * @return The value
     */
    T join() throws TimeoutException;

}
