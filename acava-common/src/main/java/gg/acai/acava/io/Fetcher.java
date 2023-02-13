package gg.acai.acava.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * @author Clouke
 * @since 04.12.2022 00:56
 * © Acava - All Rights Reserved
 */
public class Fetcher implements Callable<Fetcher>, Closeable {

    private static final Type TYPE = new TypeToken<Map<String, Object>>(){}
            .getType();

    private final Callback<Map<String, Object>> callback;
    private final String content;
    private final Map<String, Object> map;

    public static Builder newBuilder() {
        return new Builder();
    }

    private Fetcher(Callback<Map<String, Object>> callback, String content, Map<String, Object> map) {
        this.callback = callback;
        this.content = content;
        this.map = map;
    }

    @Override
    public Fetcher call() {
        Objects.requireNonNull(content, "Cannot convert to map, no data has been scanned");
        Objects.requireNonNull(callback, "Cannot call callback, no callback has been provided");
        this.callback.onCallback(map);
        return this;
    }

    @Override
    public void close() {
        if (map != null) {
            map.clear();
        }
    }

    public static class Builder {

        private String url;
        private Callback<Map<String, Object>> callback;
        private String scanned;
        private Map<String, Object> map;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder callback(Callback<Map<String, Object>> callback) {
            this.callback = callback;
            return this;
        }

        public Builder scan() {
            synchronized (this) {
                Scanner scanner = new Scanner(url);
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                this.scanned = builder.toString();
                return this;
            }
        }

        public Builder establishMap() {
            return establishMap(new GsonBuilder()
                    .setPrettyPrinting()
                    .create());
        }

        public Builder establishMap(Gson gson) {
            this.map = gson.fromJson(scanned, TYPE);
            return this;
        }

        public Fetcher build() {
            return new Fetcher(callback, scanned, map);
        }

    }

}
