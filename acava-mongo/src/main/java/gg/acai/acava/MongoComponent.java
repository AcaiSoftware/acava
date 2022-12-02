package gg.acai.acava;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import gg.acai.acava.entity.Entity;
import gg.acai.acava.io.Closeable;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.concurrent.CompletableFuture;

/**
 * @author Clouke
 * @since 02.12.2022 21:24
 * © Acava - All Rights Reserved
 */
public interface MongoComponent extends Closeable {

    static MongoServer.Builder newBuilder() {
        return new MongoServer.Builder();
    }

    /**
     * Connect the component to the Mongo database
     *
     * @return The component
     */
    MongoComponent connect();

    /**
     * Gets the client of the component
     *
     * @return The client
     */
    MongoClient getClient();

    /**
     * Get a collection from the registry.
     *
     * @param collection the collection name
     * @return The collection
     */
    MongoCollection<Document> getCollection(String collection);

    /**
     * Generate an entity from a collection, skip serialization.
     *
     * @param collection The collection to generate the entity from.
     * @param filters The filters to apply to the collection.
     * @return The entity.
     */
    Entity generateEntity(MongoCollection<Document> collection, Bson filters);

    /**
     * Generate an entity asynchronously.
     *
     * @param collection The collection to generate the entity from.
     * @param filters The filters to apply to the collection.
     * @return A completable future containing the entity.
     */
    CompletableFuture<Entity> generateEntityAsync(MongoCollection<Document> collection, Bson filters);

    /**
     * Search for a specific value in a collection.
     *
     * @param collection The collection to search in.
     * @param key The key to search for.
     * @param rawType The raw type of the value.
     * @param <T> The type of the value.
     * @return The value of the key.
     */
    <T> CompletableFuture<T> search(String collection, String key, Class<T> rawType);


}
