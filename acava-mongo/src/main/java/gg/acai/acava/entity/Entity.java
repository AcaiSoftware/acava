package gg.acai.acava.entity;

import gg.acai.acava.annotated.Use;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Clouke
 * @since 02.12.2022 21:14
 * © Acava - All Rights Reserved
 */
@Use("Use MongoServer#generateEntity() to create an entity.")
public interface Entity {

    /**
     * Gets the entity type.
     *
     * @param key The key to get the value of.
     * @param rawType The raw type of the value.
     * @param <V> The type of the value.
     * @param <T> The raw type of the value.
     * @return The value of the given key.
     */
    <V, T> V getValue(String key, Class<T> rawType);

    /**
     * Gets the entity type, with a default value.
     *
     * @return The value of the given key, if it exists, otherwise we return the default value.
     */
    <V, T> V getValue(String key, Class<T> rawType, V defaultValue);

    /**
     * Gets the entity type asynchronously.
     *
     * @param key The key to get the value of.
     * @param rawType The raw type of the value.
     * @return The value of the given key.
     */
    <V, T> CompletableFuture<V> getValueAsync(String key, Class<T> rawType);

    /**
     * Gets the entity type asynchronously, with a default value.
     *
     * @param key The key to get the value of.
     * @param rawType The raw type of the value.
     * @param defaultValue The default value to return if the key doesn't exist.
     * @return The value of the given key.
     */
    <V, T> CompletableFuture<V> getValueAsync(String key, Class<T> rawType, V defaultValue);

    /**
     * Checks if the entity contains the given key.
     *
     * @param key The key to check for.
     * @return True if the entity contains the key, otherwise false.
     */
    boolean containsKey(String key);

    /**
     * Checks if the entity contains the given value.
     *
     * @param value The value to check for.
     * @return True if the entity contains the value, otherwise false.
     */
    boolean containsValue(Object value);

    /**
     * Gets the entity as a map.
     *
     * @return The entity as a map.
     */
    Map<String, Object> asMap();

    /**
     * Creates an entity type.
     *
     * @param raw The raw type of the entity type.
     * @param <T> The raw type of the entity type.
     * @return The entity type.
     */
    <T extends EntityType> T createType(Class<T> raw);

}
