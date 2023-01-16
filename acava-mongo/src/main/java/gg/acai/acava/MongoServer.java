package gg.acai.acava;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gg.acai.acava.entity.Entity;
import gg.acai.acava.entity.EntityGenerator;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.AsyncPlaceholderDef;
import gg.acai.acava.scheduler.Scheduler;
import gg.acai.acava.scheduler.Schedulers;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author Clouke
 * @since 02.12.2022 21:14
 * © Acava - All Rights Reserved
 */
public final class MongoServer implements MongoComponent {

    private final String host;
    private final int port;
    private final String database;
    private final boolean auth;
    private final String user;
    private final String applicationName;
    private final String password;
    private final String authDatabase;
    private final boolean cacheCollections;
    private final Map<String, MongoCollection<Document>> collections;

    private MongoClient client;
    private MongoDatabase mongoDatabase;

    public MongoServer(String host, int port, String database, boolean auth, String user, String applicationName, String password, String authDatabase, boolean cacheCollections) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.auth = auth;
        this.user = user;
        this.applicationName = applicationName;
        this.password = password;
        this.authDatabase = authDatabase;
        this.cacheCollections = cacheCollections;
        this.collections = new HashMap<>();
    }

    @Override
    public MongoComponent connect() {
        MongoClientSettings.Builder settings = MongoClientSettings.builder()
                .applicationName(applicationName)
                .applyToClusterSettings(builder -> {
                    List<ServerAddress> addresses = Collections.singletonList(new ServerAddress(host, port));
                    builder.hosts(addresses).build();
                });

        if (auth) {
            settings.credential(MongoCredential.createCredential(user, authDatabase, password.toCharArray()));
        }

        this.client = MongoClients.create(settings.build());
        this.mongoDatabase = client.getDatabase(database);
        if (cacheCollections) {
            this.mongoDatabase.listCollectionNames()
                    .forEach((Consumer<String>) name -> {
                        collections.put(name, mongoDatabase.getCollection(name));
                    });
        }
        return this;
    }

    @Override
    public MongoClient getClient() {
        return this.client;
    }

    @Override
    public MongoCollection<Document> getCollection(String collection) {
        return mongoDatabase.getCollection(collection);
    }

    @Override
    public <T> CompletableFuture<T> search(String collection, String key, Class<T> rawType) {
        return CompletableFuture.supplyAsync(() -> {
            T result = null;
            for (Document document : mongoDatabase.getCollection(collection).find()) {
                if (document.containsKey(key)) {
                    result = document.get(key, rawType);
                    break;
                }
            }

            return result;
        });
    }

    @Override
    public Entity generateEntity(MongoCollection<Document> collection, Bson filters) {
        return new EntityGenerator(collection, filters);
    }

    @Override
    public AsyncPlaceholder<Entity> generateEntityAsync(MongoCollection<Document> collection, Bson filters) {
        Scheduler scheduler = Schedulers.async();
        return scheduler.supply(() -> new AsyncPlaceholderDef<>(generateEntity(collection, filters), scheduler));
    }

    @Override
    public void close() {
        this.client.close();
    }

    public static class Builder {
        private String host;
        private int port;
        private String database;
        private boolean auth;
        private String user;
        private String password;
        private String authDatabase;
        private String applicationName;
        private boolean cacheCollections;

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

        public Builder auth(boolean auth) {
            this.auth = auth;
            return this;
        }

        public Builder applicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder authDatabase(String authDatabase) {
            this.authDatabase = authDatabase;
            return this;
        }

        public Builder cacheCollections() {
            this.cacheCollections = true;
            return this;
        }

        public MongoServer build() {
            return new MongoServer(host, port, database, auth, user, applicationName, password, authDatabase, cacheCollections);
        }
    }
}
