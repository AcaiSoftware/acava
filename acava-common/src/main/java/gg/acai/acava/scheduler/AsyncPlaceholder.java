package gg.acai.acava.scheduler;

import gg.acai.acava.io.Callback;

/**
 * @author Clouke
 * @since 04.12.2022 01:40
 * © Acava - All Rights Reserved
 */
public interface AsyncPlaceholder<T> {

    AsyncPlaceholder<T> whenComplete(Callback<T> callback);

    T get();

}
