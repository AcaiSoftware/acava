package gg.acai.acava;

import java.util.List;

/**
 * @author Clouke
 * @since 08.12.2022 23:17
 * © Acava - All Rights Reserved
 */
public interface HttpResponse<T> {

    String getHeader(String key);

    List<String> getHeaders();

    List<String> getCookies();

    String getCookie(String key);

    String getBody();

    int getStatusCode();

    String getStatusMessage();

    String getContentType();

    String getAccept();

}
