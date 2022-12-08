package gg.acai.acava;

/**
 * @author Clouke
 * @since 08.12.2022 23:38
 * © Acava - All Rights Reserved
 */
public final class Requests {

     public static <T> HttpRequest<T> newRequest(String url, RestMethod method) {
        return new Request<>(url, method);
    }

}
