package gg.acai.acava;

/**
 * @author Clouke
 * @since 06.01.2023 14:43
 * © Acai Software - All Rights Reserved
 */
public final class MongoCredentials {

    private String host;
    private int port;
    private String database;
    private boolean auth;
    private String user;
    private String applicationName;
    private String password;
    private String authDatabase;
    private boolean cacheCollections;

    /**
     * Applies the host to the credentials.
     *
     * @param host The host of the mongo server
     * @return The builder instance
     */
    public MongoCredentials host(String host) {
        this.host = host;
        return this;
    }

    /**
     * Applies the port to the credentials.
     *
     * @param port The port of the mongo server
     * @return The builder instance
     */
    public MongoCredentials port(int port) {
        this.port = port;
        return this;
    }

    /**
     * Applies the database to the credentials.
     *
     * @param database The database of the mongo server
     * @return The builder instance
     */
    public MongoCredentials database(String database) {
        this.database = database;
        return this;
    }

    /**
     * Applies the auth to the credentials.
     *
     * @param auth Whether the mongo server requires authentication
     * @return The builder instance
     */
    public MongoCredentials auth(boolean auth) {
        this.auth = auth;
        return this;
    }

    /**
     * Applies the application name to the credentials.
     *
     * @param applicationName The application name of the mongo server
     * @return The builder instance
     */
    public MongoCredentials applicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    /**
     * Applies the user to the credentials.
     *
     * @param user The user of the mongo server
     * @return The builder instance
     */
    public MongoCredentials user(String user) {
        this.user = user;
        return this;
    }

    /**
     * Applies the password to the credentials.
     *
     * @param password The password of the mongo server
     * @return The builder instance
     */
    public MongoCredentials password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Applies the auth database to the credentials.
     *
     * @param authDatabase The auth database of the mongo server
     * @return The builder instance
     */
    public MongoCredentials authDatabase(String authDatabase) {
        this.authDatabase = authDatabase;
        return this;
    }

    /**
     * Whether the collections should be cached.
     *
     * @return The builder instance
     */
    public MongoCredentials cacheCollections() {
        this.cacheCollections = true;
        return this;
    }

    /**
     * Builds the MongoServer instance.
     *
     * @return The MongoServer instance
     */
    public MongoServer build() {
        return new MongoServer(host, port, database, auth, user, applicationName, password, authDatabase, cacheCollections);
    }


}
