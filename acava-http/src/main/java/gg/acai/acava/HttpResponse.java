package gg.acai.acava;

import java.util.List;

/**
 * @author Clouke
 * @since 08.12.2022 23:17
 * © Acava - All Rights Reserved
 */
public interface HttpResponse<T> {

    /**
     * Gets the status code of the response.
     *
     * @param key The key of the header.
     * @return The value of the header.
     */
    String getHeader(String key);

    /**
     * Gets a list of all headers of the response.
     *
     * @return Returns a list of all headers.
     */
    List<String> getHeaders();

    /**
     * Gets a list of all cookies of the response.
     *
     * @return Returns a list of all cookies.
     */
    List<String> getCookies();

    /**
     * Gets a cookie of the response.
     *
     * @param key The key of the cookie.
     * @return The value of the cookie.
     */
    String getCookie(String key);

    /**
     * Gets the body of the response.
     *
     * @return The body of the response.
     */
    String getBody();

    /**
     * Gets the status code of the response.
     *
     * @return The status code of the response.
     */
    int getStatusCode();

    /**
     * Gets the status message of the response.
     *
     * @return The status message of the response.
     */
    String getStatusMessage();

    /**
     * Gets the content type of the response.
     *
     * @return The content type of the response.
     */
    String getContentType();

    /**
     * Gets the accept type of the response.
     *
     * @return The accept type of the response.
     */
    String getAccept();

}
