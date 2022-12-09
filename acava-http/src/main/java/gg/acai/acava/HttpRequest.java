package gg.acai.acava;

import gg.acai.acava.scheduler.AsyncPlaceholder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Clouke
 * @since 08.12.2022 23:17
 * © Acava - All Rights Reserved
 */
public interface HttpRequest<T> {

    /**
     * Adds a header to the request.
     *
     * @param key The key of the header.
     * @param value The value of the header.
     */
    HttpRequest<T> addHeader(String key, String value);

    /**
     * Adds a cookie to the request.
     *
     * @param key The key of the cookie.
     * @param value The value of the cookie.
     */
    HttpRequest<T> addCookie(String key, String value);

    /**
     * Adds a body to the request.
     *
     * @param body The body of the request.
     */
    HttpRequest<T> addBody(String body);

    /**
     * Adds a parameter to the request.
     *
     * @param body the type body of the request.
     * @param type the type of T.
     */
    HttpRequest<T> addBody(T body, Type type);

    /**
     * Applies the content type to the request.
     *
     * @param contentType The content type of the request.
     */
    HttpRequest<T> contentType(String contentType);

    /**
     * Applies the accept type to the request.
     *
     * @param accept The accept type of the request.
     */
    HttpRequest<T> accept(String accept);

    /**
     * Applies the user agent to the request.
     *
     * @param userAgent The user agent of the request.
     */
    HttpRequest<T> userAgent(String userAgent);

    /**
     * Adds a parameter to the request.
     *
     * @param key The key of the parameter.
     * @param value The value of the parameter.
     */
    HttpRequest<T> queryParameter(String key, String value);

    /**
     * Adds a parameter to the request with a delegated map.
     *
     * @param parameters The map of parameters.
     */
    HttpRequest<T> queryParameter(Map<String, String> parameters);

    /**
     * Executes the request.
     *
     * @return The response of the request.
     */
    HttpResponse<T> execute();

    /**
     * Executes the request asynchronously.
     *
     * @return Returns an async placeholder of the response which callbacks when the request is done.
     */
    AsyncPlaceholder<HttpResponse<T>> executeAsync();

}
