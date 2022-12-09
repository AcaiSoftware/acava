package gg.acai.acava.json;

import com.google.gson.Gson;
import gg.acai.acava.GsonProvider;

import java.lang.reflect.Type;

/**
 * @author Clouke
 * @since 09.12.2022 13:37
 * © Acava - All Rights Reserved
 */
public class JsonObject<T> {

    private static final Gson GSON = GsonProvider
            .getGson();

    private String json;
    private T object;

    public JsonObject(String json) {
        this.json = json;
    }

    public JsonObject(T object) {
        this.object = object;
    }

    public JsonObject<T> setJson(String json) {
        this.json = json;
        return this;
    }

    public JsonObject<T> setObject(T object) {
        this.object = object;
        return this;
    }

    public T getAs(Class<T> clazz) {
        this.object = GSON.fromJson(json, clazz);
        return this.object;
    }

    public T getAs(Type type) {
        this.object = GSON.fromJson(json, type);
        return this.object;
    }

    public String asJson() {
        return json == null ? GSON.toJson(object) : json;
    }

    @Override
    public String toString() {
        return asJson();
    }
}
