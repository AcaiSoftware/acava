package gg.acai.acava.math;

import java.util.Arrays;

/**
 * @author Clouke
 * @since 08.12.2022 22:09
 * © Acava - All Rights Reserved
 */
public class DoubleMathArray implements DoubleMath {

    private final double[] array;

    public DoubleMathArray(double[] array) {
        this.array = array;
    }

    @Override
    public double getAverage() {
        return getSum() / array.length;
    }

    @Override
    public double getMedian() {
        double[] sorted = this.array.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length / 2];
    }

    @Override
    public double getMode() {
        double[] sorted = this.array.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length - 1];
    }

    @Override
    public double getStandardDeviation() {
        double average = this.getAverage();
        double sum = 0;
        for (double value : array) {
            sum += Math.pow(value - average, 2);
        }
        return Math.sqrt(sum / array.length);
    }

    @Override
    public double getVariance() {
        double average = this.getAverage();
        double sum = 0;
        for (double value : array) {
            sum += Math.pow(value - average, 2);
        }
        return sum / array.length;
    }

    @Override
    public double getSum() {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum;
    }

    @Override
    public double getMin() {
        double min = Double.MAX_VALUE;
        for (double value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    @Override
    public double getMax() {
        double max = Double.MIN_VALUE;
        for (double value : array) {
            if (value > max) max = value;
        }
        return max;
    }

    @Override
    public double getRange() {
        return this.getMax() - this.getMin();
    }

    @Override
    public double getQuartile() {
        double[] sorted = this.array.clone();
        Arrays.sort(sorted);
        return sorted[sorted.length / 4];
    }

    @Override
    public double getPercentile() {
        return this.getPercentile(50);
    }

    @Override
    public double getPercentile(long x) {
        double[] sorted = this.array.clone();
        Arrays.sort(sorted);
        return sorted[(int) (sorted.length * (x / 100.0))];
    }

    @Override
    public double getSkewness() {
        double average = this.getAverage();
        double sum = 0;
        for (double value : array) {
            sum += Math.pow(value - average, 3);
        }
        return sum / array.length;
    }

    @Override
    public double getKurtosis() {
        double average = this.getAverage();
        double sum = 0;
        for (double value : array) {
            sum += Math.pow(value - average, 4);
        }
        return sum / array.length;
    }

    @Override
    public double getZScore() {
        double average = this.getAverage();
        double standardDeviation = this.getStandardDeviation();
        double sum = 0;
        for (double value : array) {
            sum += Math.pow((value - average) / standardDeviation, 2);
        }
        return sum / array.length;
    }
}
