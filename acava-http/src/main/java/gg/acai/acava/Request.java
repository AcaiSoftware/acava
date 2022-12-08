package gg.acai.acava;

import com.google.gson.Gson;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Clouke
 * @since 08.12.2022 23:22
 * © Acava - All Rights Reserved
 */
public class Request<T> implements HttpRequest<T> {

    private static final Gson GSON = GsonProvider.getGson();

    private final String url;
    private final RestMethod method;
    private final Map<String, String> headers;
    private final Map<String, String> cookies;
    private final Map<String, String> queryParameters;
    private final List<String> body;
    private String contentType = "application/json";
    private String accept = "*/*";
    private String userAgent = "Acava/1.0";

    public Request(String url, RestMethod method) {
        this.url = url;
        this.method = method;
        this.headers = new HashMap<>();
        this.cookies = new HashMap<>();
        this.queryParameters = new HashMap<>();
        this.body = new ArrayList<>();
    }

    @Override
    public HttpRequest<T> addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    @Override
    public HttpRequest<T> addCookie(String key, String value) {
        this.cookies.put(key, value);
        return this;
    }

    @Override
    public HttpRequest<T> addBody(String body) {
        this.body.add(body);
        return this;
    }

    @Override
    public HttpRequest<T> addBody(T body, Type typeOfSrc) {
        this.body.add(GSON.toJson(body, typeOfSrc));
        return this;
    }

    @Override
    public HttpRequest<T> contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public HttpRequest<T> accept(String accept) {
        this.accept = accept;
        return this;
    }

    @Override
    public HttpRequest<T> userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public HttpRequest<T> queryParameter(String key, String value) {
        this.queryParameters.put(key, value);
        return this;
    }

    @Override
    public HttpResponse<T> execute() {
        String header = this.headers.toString();
        String cookie = this.cookies.toString();
        String body = GSON.toJson(this.body);
        String parameter = buildParameters();

        System.out.println(cookie);

        HttpURLConnection connection = null;
        try {
            URL url = new URL(this.url + parameter);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.name());
            connection.setRequestProperty("Content-Type", this.contentType);
            connection.setRequestProperty("Accept", this.accept);
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("User-Agent", this.userAgent);
            this.headers.forEach(connection::setRequestProperty);
            connection.setDoOutput(true);
            connection.connect();
            try (OutputStream out = connection.getOutputStream()) {
                out.write(body.getBytes());
            }

            if (connection.getResponseCode() >= 400) {
                System.out.println("Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                System.out.println("Stream: " + connection.getErrorStream().toString());
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Response<>(connection);
    }

    @Override
    public AsyncPlaceholder<HttpResponse<T>> executeAsync() {
        return Schedulers.supplyAsync(this::execute);
    }

    private String buildParameters() {
        if (queryParameters.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("?");

        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        return builder.toString();
    }

}
