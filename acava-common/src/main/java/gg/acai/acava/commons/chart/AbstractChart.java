package gg.acai.acava.commons.chart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Clouke
 * @since 03.03.2023 21:33
 * © Acava - All Rights Reserved
 */
public abstract class AbstractChart implements Chart {

  protected final List<Integer> nodes;
  protected final int width;
  protected final int height;
  protected final String title;
  protected final double maxX;
  protected final double minX;
  protected final double maxY;
  protected final double minY;

  public AbstractChart(ChartBuilder builder) {
    this.nodes = builder.nodes;
    this.width = builder.width;
    this.height = builder.height;
    this.title = builder.title;
    this.maxX = builder.maxX;
    this.minX = builder.minX;
    this.maxY = builder.maxY;
    this.minY = builder.minY;
  }

  @Override
  public Chart addNode(int data) {
    nodes.add(data);
    return this;
  }

  @Override
  public Chart addNodes(List<Integer> data) {
    nodes.addAll(data);
    return this;
  }

  @Override
  public Chart addNodes(int... data) {
    for (int i : data) nodes.add(i);
    return this;
  }

  @Override
  public List<Integer> nodes() {
    return nodes;
  }

  @Override
  public String buildUrl() {
    return "https://chart.googleapis.com/chart?" +
            "cht=lc&" +
            "chs=" + width + "x" + height + "&" +
            "chd=t:" + Charts.getChartData(nodes) + "&" +
            "chxt=x,y&" +
            "chxr=0," + minX + "," + maxX + "|1," + minY + "," + maxY + "&" +
            "chds=" + minY + "," + maxY + "&" +
            "chtt=" + title.replaceAll(" ", "+");
  }

  @Override
  @Deprecated
  public void save() {
    HttpURLConnection connection;
    try {
      connection = (HttpURLConnection) new URL(buildUrl()).openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("User-Agent", "Mozilla/5.0");
      connection.setDoOutput(true);
      connection.connect();
      InputStream inputStream = connection.getInputStream();
      OutputStream outputStream = Files.newOutputStream(Paths.get("chart.png"));
      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) != -1)
        outputStream.write(buffer, 0, length);
      inputStream.close();
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
