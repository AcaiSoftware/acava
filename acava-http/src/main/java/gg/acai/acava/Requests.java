package gg.acai.acava;

/**
 * @author Clouke
 * @since 08.12.2022 23:38
 * © Acava - All Rights Reserved
 */
public final class Requests {

    /**
     * Creates a new {@link HttpRequest<T>} instance.
     *
     * @param url The url of the request.
     * @param method The method of the request.
     * @param <T> The type of the request.
     * @return Returns a new {@link HttpRequest<T>} instance.
     */
     public static <T> HttpRequest<T> newRequest(String url, RestMethod method) {
        return new Request<>(url, method);
    }

}
