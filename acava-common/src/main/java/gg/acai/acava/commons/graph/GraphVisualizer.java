package gg.acai.acava.commons.graph;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author Clouke
 * @since 23.02.2023 00:36
 * © Acava - All Rights Reserved
 */
public final class GraphVisualizer<N extends Number> {

    private static final char FULL_BLOCK = '█';
    private static final char DARK_SHADE = '▓';
    private static final char MEDIUM_SHADE = '▒';
    private static final char LIGHT_SHADE = '░';

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

    public GraphVisualizer(AbstractGraph<N> graph) {
        this.graph = graph;
    }

    public String visualize() {
        return VISUALIZER.apply(graph);
    }

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

    public void print() {
        System.out.println(visualize());
    }

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
