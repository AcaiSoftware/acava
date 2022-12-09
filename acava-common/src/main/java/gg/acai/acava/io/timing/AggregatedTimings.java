package gg.acai.acava.io.timing;

import gg.acai.acava.cache.CacheDuplex;

/**
 * @author Clouke
 * @since 05.12.2022 10:31
 * © Acava - All Rights Reserved
 */
public interface AggregatedTimings<T> {

    void track(double time);

    TimingsStatistics<T> getStatistics();

    CacheDuplex<Integer, Double> asCache();

}
