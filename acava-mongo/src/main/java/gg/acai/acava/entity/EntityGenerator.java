package gg.acai.acava.entity;

import com.mongodb.client.MongoCollection;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * EntityGenerator - A class that generates entities from a collection.
 *
 * @author Clouke
 * @since 02.12.2022 21:14
 * © Acava - All Rights Reserved
 */
public class EntityGenerator implements Entity {

    private final Map<String, Object> values;

    public EntityGenerator(MongoCollection<Document> collection, Bson filters) {
        this.values = new HashMap<>();
        collection.find(filters).forEach((Consumer<? super Document>) values::putAll);
    }

    @Override @SuppressWarnings("unchecked")
    public <V, T> V getValue(String key, Class<T> rawType) {
        synchronized (values) {
            V value = null;
            try {
                value = (V) values.get(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return value;
        }
    }

    @Override
    public <V, T> V getValue(String key, Class<T> rawType, V defaultValue) {
        V value = getValue(key, rawType);
        return value == null ? defaultValue : value;
    }

    @Override
    public <V, T> AsyncPlaceholder<V> getValueAsync(String key, Class<T> rawType) {
        return Schedulers.supplyAsync(() -> getValue(key, rawType));
    }

    @Override
    public <V, T> AsyncPlaceholder<V> getValueAsync(String key, Class<T> rawType, V defaultValue) {
        return Schedulers.supplyAsync(() -> getValue(key, rawType, defaultValue));
    }

    @Override
    public boolean containsKey(String key) {
        return values.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.containsValue(value);
    }

    @Override
    public Map<String, Object> asMap() {
        return this.values;
    }

    @Override
    public <T extends EntityType> T createType(Class<T> raw) {
        T type = null;
        try {
            type = raw.getConstructor(EntityGenerator.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

}
