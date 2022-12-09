package gg.acai.acava;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Scanner;
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
        try {
            return connection.getHeaderFields().get("Set-Cookie");
        } catch (NullPointerException e) {
            return null;
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public String getCookie(String key) {
        try {
            try {
                return connection.getHeaderFields().get("Set-Cookie").stream()
                        .filter(cookie -> cookie.contains(key))
                        .map(cookie -> cookie.split("=")[1])
                        .collect(Collectors.joining());
            } catch (NullPointerException e) {
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public String getBody() {
        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    public int getStatusCode() {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public String getStatusMessage() {
        try {
            return connection.getResponseMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public String getContentType() {
        try {
            return connection.getContentType();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public String getAccept() {
        try {
            return connection.getRequestProperty("Accept");
        } finally {
            connection.disconnect();
        }
    }
}
