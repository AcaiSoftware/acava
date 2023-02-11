package gg.acai.acava.math;

import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 06.12.2022 12:31
 * © Acava - All Rights Reserved
 */
@Deprecated
public class DoubleMathStream implements DoubleMath {

    private final Stream<? extends Number> stream;

    public DoubleMathStream(Stream<? extends Number> stream) {
        this.stream = stream;
    }

    @Override
    public double getAverage() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0);
    }

    @Override
    public double getMedian() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .sorted()
                .skip(stream.count() / 2)
                .findFirst()
                .orElse(0);
    }

    @Override
    public double getMode() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(0);
    }

    @Override
    public double getStandardDeviation() {
        double average = this.getAverage();
        return Math.sqrt(stream
                .mapToDouble(Number::doubleValue)
                .map(value -> Math.pow(value - average, 2))
                .sum() / stream.count());
    }

    @Override
    public double getVariance() {
        double average = this.getAverage();
        return this.stream
                .mapToDouble(Number::doubleValue)
                .map(value -> Math.pow(value - average, 2))
                .sum() / stream.count();
    }

    @Override
    public double getSum() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    @Override
    public double getMin() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .min()
                .orElse(0);
    }

    @Override
    public double getMax() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(0);
    }

    @Override
    public double getRange() {
        return getMax() - getMin();
    }

    @Override
    public double getQuartile() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .sorted()
                .skip(stream.count() / 4)
                .findFirst()
                .orElse(0);
    }

    @Override
    public double getPercentile() {
        return this.stream
                .mapToDouble(Number::doubleValue)
                .sorted()
                .skip(stream.count() / 100)
                .findFirst()
                .orElse(0);
    }

    @Override
    public double getPercentile(long x) {
        long count = stream.count();
        return this.stream
                .mapToDouble(Number::doubleValue)
                .sorted()
                .skip(count * x / 100)
                .findFirst()
                .orElse(0);
    }

    @Override
    public double getSkewness() {
        double average = this.getAverage();
        double standardDeviation = this.getStandardDeviation();
        return this.stream
                .mapToDouble(Number::doubleValue)
                .map(value -> Math.pow(value - average, 3))
                .sum() / (stream.count() * Math.pow(standardDeviation, 3));
    }

    @Override
    public double getKurtosis() {
        double average = this.getAverage();
        double standardDeviation = this.getStandardDeviation();
        return this.stream
                .mapToDouble(Number::doubleValue)
                .map(value -> Math.pow(value - average, 4))
                .sum() / (stream.count() * Math.pow(standardDeviation, 4));
    }

    @Override
    public double getZScore() {
        double average = this.getAverage();
        double standardDeviation = this.getStandardDeviation();
        return this.stream
                .mapToDouble(Number::doubleValue)
                .map(value -> (value - average) / standardDeviation)
                .sum() / stream.count();
    }
}
