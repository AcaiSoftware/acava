package gg.acai.acava.entity;

import gg.acai.acava.annotated.Use;

/**
 * @author Clouke
 * @since 02.12.2022 21:14
 * © Acava - All Rights Reserved
 *
 * <pre>
 *  {@code
 *  public class Class implements EntityType {
 *    private final String name;
 *    private final UUID uniqueId;
 *    private final int id;
 *
 *    public Class(EntityGenerator root) { // requires EntityGenerator as parameter
 *        this.name = root.getValue("name", String.class, "N/A");
 *        this.uniqueId = UUID.fromString(root.getValue("uniqueId", String.class, "00000000-0000-0000-0000-000000000000"));
 *        this.id = root.getValue("id", Integer.class, 0);
 *    }
 *
 *    public String getName() {
 *       return name;
 *    }
 *
 *    public UUID getUniqueId() {
 *       return uniqueId;
 *    }
 *  }
 *}
 * </pre>
 */
@Use("Implement this interface to create an entity.")
public interface EntityType {}
