package gg.acai.acava;

import gg.acai.acava.io.Closeable;
import gg.acai.acava.scheduler.AsyncPlaceholder;

import java.sql.Connection;

/**
 * @author Clouke
 * @since 04.12.2022 01:20
 * © Acava - All Rights Reserved
 */
public interface PostgreComponent extends Closeable {

    static PostgreConnection.Builder newBuilder() {
        return new PostgreConnection.Builder();
    }

    /**
     * Connects to the database
     *
     * @return Returns the component
     */
    PostgreComponent connect();

    /**
     * Connects to the database asynchronously
     *
     * @return Returns a {@link AsyncPlaceholder<Connection>} that will be completed when the connection is established
     */
    AsyncPlaceholder<PostgreComponent> connectAsync();

    /**
     * Gets the connection
     *
     * @return The connection
     */
    Connection getConnection();

    /**
     * Gets the connection asynchronously
     *
     * @return Returns a {@link AsyncPlaceholder<Connection>} that will be completed when the connection is established
     */
    AsyncPlaceholder<Connection> getConnectionAsync();

}
