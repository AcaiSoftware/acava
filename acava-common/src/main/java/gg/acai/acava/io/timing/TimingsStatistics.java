package gg.acai.acava.io.timing;

/**
 * @author Clouke
 * @since 05.12.2022 10:33
 * © Acava - All Rights Reserved
 */
public interface TimingsStatistics<T> {

    double getAverage();

    double getAverage(long skip);

    double getAverageSeconds();

}
