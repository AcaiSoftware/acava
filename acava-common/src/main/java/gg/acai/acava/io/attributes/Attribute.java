package gg.acai.acava.io.attributes;

/**
 * @author Clouke
 * @since 05.12.2022 16:39
 * © Acava - All Rights Reserved
 */
public interface Attribute<K, V> {

    K getKey();

    V getValue();

    void setValue(V value);

}
