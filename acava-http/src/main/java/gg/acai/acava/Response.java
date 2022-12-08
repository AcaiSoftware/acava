package gg.acai.acava;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Clouke
 * @since 08.12.2022 23:32
 * © Acava - All Rights Reserved
 */
public class Response<T> implements HttpResponse<T> {

    private final HttpURLConnection connection;

    public Response(HttpURLConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getHeader(String key) {
        return connection.getHeaderField(key);
    }

    @Override
    public List<String> getHeaders() {
        return connection.getHeaderFields().keySet().stream()
                .map(key -> key + ": " + connection.getHeaderField(key))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getCookies() {
        return connection.getHeaderFields().get("Set-Cookie");
    }

    @Override
    public String getCookie(String key) {
        return connection.getHeaderField(key);
    }

    @Override
    public String getBody() {
        try {
            try {
                return connection.getInputStream().toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        finally {
            connection.disconnect();
        }
    }

    @Override
    public int getStatusCode() {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getStatusMessage() {
        try {
            return connection.getResponseMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getContentType() {
        return connection.getContentType();
    }

    @Override
    public String getAccept() {
        return connection.getRequestProperty("Accept");
    }
}
