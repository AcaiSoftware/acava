package gg.acai.acava.commons.chart;

import java.util.List;

/**
 * @author Clouke
 * @since 03.03.2023 21:33
 * © Acava - All Rights Reserved
 */
public interface Chart {

    static ChartBuilder builder() {
        return new ChartBuilder();
    }

    Chart addNode(int data);

    Chart addNodes(List<Integer> data);

    Chart addNodes(int... data);

    List<Integer> nodes();

    String buildUrl();

    void save();

}
