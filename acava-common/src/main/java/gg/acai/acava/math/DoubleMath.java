package gg.acai.acava.math;

import gg.acai.acava.annotated.Use;

/**
 * @author Clouke
 * @since 06.12.2022 12:30
 * © Acava - All Rights Reserved
 */
@Use("Use Maths#doubleMath(...)")
public interface DoubleMath {

    double getAverage();

    double getMedian();

    double getMode();

    double getStandardDeviation();

    double getVariance();

    double getSum();

    double getMin();

    double getMax();

    double getRange();

    double getQuartile();

    double getPercentile();

    double getPercentile(long x);

    double getSkewness();

    double getKurtosis();

    double getZScore();

}
