package gg.acai.acava;

import gg.acai.acava.scheduler.AsyncPlaceholder;

import java.lang.reflect.Type;

/**
 * @author Clouke
 * @since 08.12.2022 23:17
 * © Acava - All Rights Reserved
 */
public interface HttpRequest<T> {

    HttpRequest<T> addHeader(String key, String value);

    HttpRequest<T> addCookie(String key, String value);

    HttpRequest<T> addBody(String body);

    HttpRequest<T> addBody(T body, Type type);

    HttpRequest<T> contentType(String contentType);

    HttpRequest<T> accept(String accept);

    HttpRequest<T> userAgent(String userAgent);

    HttpRequest<T> queryParameter(String key, String value);

    HttpResponse<T> execute();

    AsyncPlaceholder<HttpResponse<T>> executeAsync();

}
