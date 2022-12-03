package gg.acai.acava;

import gg.acai.acava.io.Closeable;

/**
 * @author Clouke
 * @since 03.12.2022 11:53
 * © Acava - All Rights Reserved
 */
public interface Bootstrapper extends Closeable {

    void start();

}
