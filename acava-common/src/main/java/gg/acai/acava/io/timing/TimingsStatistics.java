package gg.acai.acava.io.timing;

/**
 * @author Clouke
 * @since 05.12.2022 10:33
 * © Acava - All Rights Reserved
 */
public interface TimingsStatistics<T> {

    double getAverage();

    double getMedian();

    double getMode();

    double getStandardDeviation();

    double getVariance();

    double getMinimum();

}
