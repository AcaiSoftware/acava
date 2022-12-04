package gg.acai.acava;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.AsyncPlaceholderDef;
import gg.acai.acava.scheduler.Scheduler;
import gg.acai.acava.scheduler.Schedulers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Clouke
 * @since 04.12.2022 01:16
 * © Acava - All Rights Reserved
 */
public final class PostgreConnection implements PostgreComponent {

    private final transient String host;
    private final transient int port;
    private final transient String database;
    private final transient String username;
    private final transient String password;
    private Connection connection;

    public PostgreConnection(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    @Override
    public PostgreComponent connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database, username, password);
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return this;
    }

    @Override
    public AsyncPlaceholder<PostgreComponent> connectAsync() {
        Scheduler scheduler = Schedulers.async();
        return scheduler.supply(() -> new AsyncPlaceholderDef<>(connect(), scheduler));
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public AsyncPlaceholder<Connection> getConnectionAsync() {
        Scheduler scheduler = Schedulers.async();
        return scheduler.supply(() -> new AsyncPlaceholderDef<>(getConnection(), scheduler));
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Builder {

        private String host;
        private int port;
        private String database;
        private String username;
        private String password;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public PostgreConnection build() {
            return new PostgreConnection(host, port, database, username, password);
        }

    }

}
