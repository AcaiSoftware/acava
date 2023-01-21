package gg.acai.acava;

/**
 * @author Clouke
 * @since 17.01.2023 01:07
 * © Clouke Services - All Rights Reserved
 */
public class WrappedMongoCredentials {

    private final String host;
    private final int port;
    private final String database;
    private final boolean auth;
    private final String username;
    private final String password;
    private final String authDatabase;
    private final String applicationName;
    private final boolean cacheCollections;

    public WrappedMongoCredentials(String host, int port, String database, boolean auth, String username, String password, String authDatabase, String applicationName, boolean cacheCollections) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.auth = auth;
        this.username = username;
        this.password = password;
        this.authDatabase = authDatabase;
        this.applicationName = applicationName;
        this.cacheCollections = cacheCollections;
    }

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    public String database() {
        return database;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String authDatabase() {
        return authDatabase;
    }

    public String applicationName() {
        return applicationName;
    }

    public boolean cacheCollections() {
        return cacheCollections;
    }

    public boolean auth() {
        return auth;
    }

}
