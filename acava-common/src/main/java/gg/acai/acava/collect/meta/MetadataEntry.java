package gg.acai.acava.collect.meta;

/**
 * @author Clouke
 * @since 02.12.2022 18:17
 * © Acava - All Rights Reserved
 */
public interface MetadataEntry<K, V> {

  /**
   * Gets the key of the entry.
   *
   * @return Returns the key of the entry
   */
  K key();

  /**
   * Gets the value of the entry.
   *
   * @return Returns the value of the entry
   */
  V value();

}
