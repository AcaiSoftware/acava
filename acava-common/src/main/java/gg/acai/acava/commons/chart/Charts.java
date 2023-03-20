package gg.acai.acava.commons.chart;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;

/**
 * @author Clouke
 * @since 03.03.2023 21:34
 * © Acava - All Rights Reserved
 */
public class Charts {

    public static ChartBuilder builder() {
        return new ChartBuilder();
    }

    protected static JsonArray getColumnArray() {
        JsonArray cols = new JsonArray();
        JsonObject col1 = new JsonObject();
        col1.addProperty("label", "X");
        col1.addProperty("type", "string");
        JsonObject col2 = new JsonObject();
        col2.addProperty("label", "Y");
        col2.addProperty("type", "number");
        cols.add(col1);
        cols.add(col2);
        return cols;
    }

    protected static JsonArray getRowArray(List<Integer> data) {
        JsonArray rows = new JsonArray();
        for (int i = 0; i < data.size(); i++) {
            JsonArray row = new JsonArray();
            row.add(new JsonPrimitive("Point " + i));
            row.add(new JsonPrimitive(data.get(i)));
            rows.add(row);
        }
        return rows;
    }

    protected static String getChartData(List<Integer> data) {
        StringBuilder chartData = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            chartData.append(data.get(i));
            if (i != data.size() - 1)
                chartData.append(",");
        }
        return chartData.toString();
    }

}
