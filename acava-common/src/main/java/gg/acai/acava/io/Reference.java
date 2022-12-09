package gg.acai.acava.io;

/**
 * @author Clouke
 * @since 05.12.2022 18:58
 * © Acava - All Rights Reserved
 */
public final class Reference<V> {

    private V value;

    public Reference(V value) {
        this.value = value;
    }

    public V get() {
        return this.value;
    }

    public void set(V value) {
        this.value = value;
    }

}
