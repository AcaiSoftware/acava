package gg.acai.acava.io.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 02.12.2022 18:25
 * © Acava - All Rights Reserved
 */
public class JsonMapConstructor {

  private static final Type MAP = new TypeToken<Map<String, Object>>() {
  }
          .getType();

  private static final Gson GSON = new GsonBuilder()
          .setPrettyPrinting()
          .create();

  private final Map<String, Object> map;

  @SuppressWarnings("unchecked")
  public JsonMapConstructor(String path, Type type, File dataFolder) {
    File file = new File(dataFolder, path);
    Map<String, Object> constructor;
    try {
      constructor = GSON.fromJson(new FileReader(file), HashMap.class);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    String json = GSON.toJson(constructor, type);
    this.map = GSON.fromJson(json, type);
  }

  public JsonMapConstructor(String json) {
    this.map = GSON.fromJson(json, MAP);
  }

  /**
   * Get the map of the json file
   *
   * @return Returns the configuration as a map.
   */
  public Map<String, Object> asMap() {
    return this.map;
  }

  /**
   * Create a {@link JsonConfiguration} from the constructed map.
   *
   * @param <T> The type of the configuration.
   * @return Returns a new {@link JsonConfiguration} instance.
   */
  public <T> JsonConfiguration<T> asConfiguration() {
    return new StandardJsonConfiguration<>(map);
  }

}
