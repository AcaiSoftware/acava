package gg.acai.acava;

import com.google.gson.Gson;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
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
    private final Parameter parameter;
    private final List<String> body;
    private String userAgent = "Acava/1.0";

    public Request(String url, RestMethod method) {
        this.url = url;
        this.method = method;
        this.headers = new HashMap<>();
        this.cookies = new HashMap<>();
        this.parameter = new Parameter();
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
        this.headers.put("Content-Type", contentType);
        return this;
    }

    @Override
    public HttpRequest<T> accept(String accept) {
        this.headers.put("Accept", accept);
        return this;
    }

    @Override
    public HttpRequest<T> userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public HttpRequest<T> queryParameter(String key, String value) {
        this.parameter.add(key, value);
        return this;
    }

    @Override
    public HttpRequest<T> queryParameter(Map<String, String> parameters) {
        this.parameter.delegate(parameters);
        return this;
    }

    @Override
    public HttpResponse<T> execute() {
        String header = this.headers.toString();
        String cookie = this.cookies.toString();
        String body = GSON.toJson(this.body);

        System.out.println(cookie);

        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(this.url + this.parameter);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.name());
            connection.setRequestProperty("User-Agent", this.userAgent);
            headers.forEach(connection::setRequestProperty);
            connection.setRequestProperty("Cookie", cookie);
            connection.connect();

            inputStream = connection.getInputStream();
            String inBody = readFromInputStream(inputStream);

            System.out.println(inBody);

            /*
            switch (method) {
                case POST:
                case PUT: {
                    try (OutputStream out = connection.getOutputStream()) {
                        out.write(body.getBytes());
                    }
                    connection.setDoOutput(true);
                    break;
                }
            }
             */

            if (connection.getResponseCode() >= 400) {
                System.out.println("Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                InputStream is = connection.getErrorStream();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < is.available(); i++) {
                    builder.append((char) is.read());
                }
                System.out.println(builder);
            }

            //connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new Response<>(connection);
    }

    @Override
    public AsyncPlaceholder<HttpResponse<T>> executeAsync() {
        return Schedulers.supplyAsync(this::execute);
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        int c;
        while ((c = inputStream.read()) != -1) {
            resultStringBuilder.append((char) c);
        }
        return resultStringBuilder.toString();
    }

}
