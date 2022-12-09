package gg.acai.acava.io.timing;

/**
 * @author Clouke
 * @since 05.12.2022 10:35
 * © Acava - All Rights Reserved
 */
public class CompositeTimings<T> implements Timings<T> {

    private final AggregatedTimings<T> houseHolder;

    public CompositeTimings(AggregatedTimings<T> houseHolder) {
        this.houseHolder = houseHolder;
    }

    private long start;

    @Override
    public void begin() {
        this.start = System.nanoTime();
    }

    @Override
    public void end() {
        double time = (System.nanoTime() - start) / 1e6;
        this.houseHolder.track(time);
    }
}
