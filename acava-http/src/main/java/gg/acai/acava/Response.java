package gg.acai.acava;

import gg.acai.acava.codec.Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Clouke
 * @since 08.12.2022 23:32
 * © Acava - All Rights Reserved
 */
public class Response<T> implements HttpResponse<T> {

    private static final Encoder<String, InputStream> ENCODER = payload -> {
        StringBuilder builder = new StringBuilder();
        int c;
        while (true) {
            try {
                if ((c = payload.read()) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            builder.append((char) c);
        }
        return builder.toString();
    };

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
        InputStream inputStream = null;
        String result;
        try {
            inputStream = connection.getInputStream();
            result = ENCODER.encode(inputStream);
        } catch (IOException e) {
            inputStream = connection.getErrorStream();
            result = ENCODER.encode(inputStream);
        } finally {
            connection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
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
