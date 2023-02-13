package gg.acai.acava.io.entity;

import com.google.common.collect.HashBasedTable;
import gg.acai.acava.annotated.Required;
import gg.acai.acava.io.Closeable;
import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;

import javax.annotation.Nullable;

/**
 * <p> Entities - A class keeper for entities.
 * <pre>{@code
 *   public void example() {
 *     Entities entities = new Entities();
 *     entities.register(new UserEntity("Clouke", UUID.randomUUID())); // Register a new entity.
 *
 *     entities.get(UserEntity.class, "Clouke") // Get the user entity.
 *        .whenComplete(user -> {
 *            System.out.println(user.getUuid().toString());
 *        });
 *     }
 *  }</pre>
 *
 * @author Clouke
 * @since 08.02.2023 15:50
 * © Acava - All Rights Reserved
 */
public final class Entities implements Closeable {

    private final HashBasedTable<Class<? extends Entity>, String, Entity> entities;

    public static Entities create() {
        return new Entities();
    }

    public Entities() {
        this.entities = HashBasedTable.create();
    }

    /**
     * Gets an entity from the table asynchronously.
     *
     * @param type The type of entity.
     * @param identifier The identifier of the entity.
     * @return Returns a completed {@link AsyncPlaceholder<R>} of the entity. The entity will be null if it does not exist.
     */
    @SuppressWarnings("unchecked")
    public <R extends Entity> AsyncPlaceholder<R> get(@Required Class<R> type, String identifier) {
        synchronized (this.entities) {
            return Schedulers.supplyAsync(() -> {
                if (this.entities.contains(type, identifier)) {
                    return (R) this.entities.get(type, identifier);
                }
                return null;
            });
        }
    }

    /**
     * Gets an entity from the table synchronously.
     *
     * @param type The type of entity.
     * @param identifier The identifier of the entity.
     * @return Returns the entity. The entity will be null if it does not exist.
     */
    @SuppressWarnings("unchecked")
    public <R extends Entity> R getSync(Class<R> type, String identifier) {
        synchronized (this.entities) {
            if (this.entities.contains(type, identifier)) {
                return (R) this.entities.get(type, identifier);
            }
            return null;
        }
    }

    /**
     * Checks if the table contains an entity.
     *
     * @param type The type of entity.
     * @param identifier The identifier of the entity.
     * @return Returns true if the table contains the entity.
     */
    public boolean contains(Class<? extends Entity> type, String identifier) {
        synchronized (this.entities) {
            return this.entities.contains(type, identifier);
        }
    }

    /**
     * Registers an entity to the table.
     *
     * @param entity The entity to register.
     * @param id The identifier of the entity.
     * @return Returns the registered entity.
     */
    public Entity register(Entity entity, String id) {
        synchronized (this.entities) {
            return this.entities.put(entity.getClass(), id, entity);
        }
    }

    /**
     * Unregisters the entity from the table.
     *
     * @param entity The entity to unregister.
     * @param id The identifier of the entity.
     * @return Returns the entity that was removed, or null if it does not exist.
     */
    @Nullable
    public Entity remove(Entity entity, String id) {
        synchronized (this.entities) {
            return this.entities.remove(entity.getClass(), id);
        }
    }

    /**
     * Closes the entities table.
     */
    @Override
    public void close() {
        this.entities.clear();
    }
}
