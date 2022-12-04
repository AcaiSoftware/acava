package gg.acai.acava;

import java.sql.Connection;

/**
 * @author Clouke
 * @since 04.12.2022 01:20
 * © Acava - All Rights Reserved
 */
public interface PostgreComponent {

    static PostgreConnection.Builder newBuilder() {
        return new PostgreConnection.Builder();
    }

    /**
     * Connects to the database
     *
     * @return The component
     */
    PostgreComponent connect();

    /**
     * Gets the connection
     *
     * @return The connection
     */
    Connection getConnection();

}
