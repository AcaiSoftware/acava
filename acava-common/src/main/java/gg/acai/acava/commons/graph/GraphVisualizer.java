package gg.acai.acava.commons.graph;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * A utility class for visualizing graphs.
 * @param <N> a type parameter that extends Number
 *
 * @author Clouke
 * @since 23.02.2023 00:36
 * © Acava - All Rights Reserved
 */
public final class GraphVisualizer<N extends Number> {

    // Constants for creating the visualization
    private static final char FULL_BLOCK = '█';
    private static final char DARK_SHADE = '▓';
    private static final char MEDIUM_SHADE = '▒';
    private static final char LIGHT_SHADE = '░';

    /**
     * A function that takes an AbstractGraph object and returns a string
     * representing the visualization of the graph.
     */
    private static final Function<AbstractGraph<? extends Number>, String> VISUALIZER = (graph) -> {
        int[] data = graph.nodes.stream()
                .mapToInt(Number::intValue)
                .toArray();

        int limiter = graph.limiter;
        int height = graph.height;

        int maxVal = maxOf(data, limiter);
        StringBuilder buf = new StringBuilder();

        for (int i = height; i > 0; i--) {
            if (i == height) buf.append(String.format("%3d ┤", maxVal));
            else if (i == 1) buf.append(String.format("%3d ┼", 0));
            else buf.append("    │");
            for (int datum : data) {
                double val = datum * 1.0 / maxVal * height;
                if (val >= i) buf.append(FULL_BLOCK).append(" ");
                else if (val >= i - 0.5) buf.append(DARK_SHADE).append(" ");
                else if (val >= i - 1) buf.append(MEDIUM_SHADE).append(" ");
                else buf.append(LIGHT_SHADE).append(" ");
            }
            buf.append("\n");
        }

        String copies = String.join("", Collections.nCopies(data.length * 2 - 1, "-"));
        buf.append("    └").append(copies).append("─").append("\n     ");

        for (int datum : data)
            buf.append(String.format("%2d ", datum));
        return buf.toString();
    };

    private final AbstractGraph<N> graph;

    /**
     * Constructs a new GraphVisualizer with the specified graph.
     *
     * @param graph the graph to visualize
     */
    public GraphVisualizer(AbstractGraph<N> graph) {
        this.graph = graph;
    }

    /**
     * Returns a string representing the visualization of the graph.
     *
     * @return a string representing the visualization of the graph
     */
    public String visualize() {
        return VISUALIZER.apply(graph);
    }

    /**
     * Returns an AsyncPlaceholder representing the asynchronous computation
     * of the visualize method.
     *
     * @return an AsyncPlaceholder representing the asynchronous computation of the visualize method
     */
    public AsyncPlaceholder<String> visualizeAsync() {
        return Schedulers.supplyAsync(this::visualize);
    }

    /**
     * Returns a simple string representation of the graph.
     *
     * @return a simple string representation of the graph
     */
    public String simpleVisualization() {
        StringBuilder builder = new StringBuilder();
        List<N> nodes = graph.nodes;
        for (int i = 0; i < nodes.size(); i++) {
            N node = nodes.get(i);
            builder.append(node);
            if (i < nodes.size() - 1)
                builder.append(" -> ");
        }
        return builder.toString();
    }

    /**
     * Returns an AsyncPlaceholder representing the asynchronous computation
     * of the simpleVisualization() method.
     *
     * @return an AsyncPlaceholder representing the asynchronous computation
     * of the simpleVisualization() method
     */
    public AsyncPlaceholder<String> simpleVisualizationAsync() {
        return Schedulers.supplyAsync(this::simpleVisualization);
    }

    /**
     * Prints the visualization of the graph to the standard output.
     */
    public void print() {
        System.out.println(visualize());
    }

    /**
     * Returns an AsyncPlaceholder representing the asynchronous computation
     * of the print() method.
     *
     * @return an AsyncPlaceholder representing the asynchronous computation
     * of the print() method
     */
    public AsyncPlaceholder<GraphVisualizer<N>> printAsync() {
        return Schedulers.supplyAsync(() -> {
            print();
            return this;
        });
    }

    /**
     * Returns a string representing the visualization of the graph.
     *
     * @return a string representing the visualization of the graph
     */
    @Override
    public String toString() {
        return visualize();
    }

    private static int maxOf(int[] data, int limiter) {
        int max = Integer.MIN_VALUE;
        for (int value : data) {
            if (value > max)
                max = value;
            if (limiter != -1) {
                if (value > limiter) return limiter;
            }
        }
        return max;
    }

}
