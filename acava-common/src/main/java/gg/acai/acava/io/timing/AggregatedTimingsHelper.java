package gg.acai.acava.io.timing;

import gg.acai.acava.cache.CacheDuplex;
import gg.acai.acava.cache.CacheEvicting;
import gg.acai.acava.math.DoubleMath;
import gg.acai.acava.math.Maths;

import java.util.Map;

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
            public double getAverage(long skip) {
                Map<Integer, Double> map = this.timings.asMap();
                int size = map.size();
                if (size <= skip) return 0.0;
                return map.values()
                        .stream()
                        .skip(skip)
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0);
            }

            @Override
            public double getAverageSeconds() {
                return this.getAverage() / 1000;
            }

        };
    }

    @Override
    public CacheDuplex<Integer, Double> asCache() {
        return this.timings;
    }

}
