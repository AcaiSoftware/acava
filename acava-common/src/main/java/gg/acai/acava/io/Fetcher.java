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
 * A simple fetcher class that fetches data from an url and converts it to a map.
 *
 * @author Clouke
 * @since 04.12.2022 00:56
 * © Acava - All Rights Reserved
 */
public class Fetcher implements Callable<Fetcher>, Closeable {

    /**
     * The type token for the map.
     */
    private static final Type TYPE = new TypeToken<Map<String, Object>>(){}
            .getType();

    private final Callback<Map<String, Object>> callback;
    private final String content;
    private final Map<String, Object> map;

    /**
     * Creates a new builder for the fetcher.
     *
     * @return Returns a new builder.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    private Fetcher(Callback<Map<String, Object>> callback, String content, Map<String, Object> map) {
        this.callback = callback;
        this.content = content;
        this.map = map;
    }

    /**
     * Calls the callback with the map.
     *
     * @return Returns this instance.
     * @throws NullPointerException If the callback or the map is null.
     */
    @Override
    public Fetcher call() {
        Objects.requireNonNull(content, "Cannot convert to map, no data has been scanned");
        Objects.requireNonNull(callback, "Cannot call callback, no callback has been provided");
        this.callback.onCallback(map);
        return this;
    }

    /**
     * Closes the fetcher and clears the map.
     */
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

        /**
         * Sets the url to fetch from.
         *
         * @param url The url to fetch from.
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets the callback to call when the map is ready.
         *
         * @param callback The callback to call.
         */
        public Builder callback(Callback<Map<String, Object>> callback) {
            this.callback = callback;
            return this;
        }

        /**
         * Scans the url and converts it to a string.
         */
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

        /**
         * Establishes the map from the scanned string.
         */
        public Builder establishMap() {
            return establishMap(new GsonBuilder()
                    .setPrettyPrinting()
                    .create());
        }

        /**
         * Establishes the map from the scanned string.
         *
         * @param gson The gson instance to use.
         */
        public Builder establishMap(Gson gson) {
            this.map = gson.fromJson(scanned, TYPE);
            return this;
        }

        /**
         * Builds the fetcher.
         *
         * @return Returns the fetcher.
         */
        public Fetcher build() {
            return new Fetcher(callback, scanned, map);
        }

    }

}
