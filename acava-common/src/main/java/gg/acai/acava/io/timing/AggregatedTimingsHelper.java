package gg.acai.acava.io.timing;

import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheEvicting;
import gg.acai.acava.math.DoubleMath;
import gg.acai.acava.math.Maths;

/**
 * @author Clouke
 * @since 05.12.2022 10:39
 * © Acava - All Rights Reserved
 */
public class AggregatedTimingsHelper<T> implements AggregatedTimings<T> {

    private final CacheDuplex<Integer, Double> timings;

    public AggregatedTimingsHelper() {
        this.timings = new CacheEvicting<>(120);
    }

    @Override
    public void track(double time) {
        int index = this.timings.size() - 1;
        if (index < 0) index = 0;
        this.timings.set(index, time);
    }

    @Override
    public TimingsStatistics<T> getStatistics() {
        return new TimingsStatistics<T>() {

            private final CacheDuplex<Integer, Double> timings = AggregatedTimingsHelper.this.timings;
            private final DoubleMath math = Maths.doubleMath(timings.stream());

            @Override
            public double getAverage() {
                return this.math.getAverage();
            }

            @Override
            public double getMedian() {
                return this.math.getMedian();
            }

            @Override
            public double getMode() {
                return this.math.getMode();
            }

            @Override
            public double getStandardDeviation() {
                return this.math.getStandardDeviation();
            }

            @Override
            public double getVariance() {
                return this.math.getVariance();
            }

            @Override
            public double getMinimum() {
                return this.math.getMin();
            }
        };
    }

    @Override
    public CacheDuplex<Integer, Double> asCache() {
        return this.timings;
    }

}
