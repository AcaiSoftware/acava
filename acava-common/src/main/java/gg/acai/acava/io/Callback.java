package gg.acai.acava.io;

/**
 * @author Clouke
 * @since 04.12.2022 00:56
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface Callback<T> {

    void onCallback(T t);

}
