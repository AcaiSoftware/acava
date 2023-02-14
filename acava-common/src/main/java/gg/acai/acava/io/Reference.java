package gg.acai.acava.io;

/**
 * A reference object that can be used to pass a value by reference.
 *
 * @author Clouke
 * @since 05.12.2022 18:58
 * © Acava - All Rights Reserved
 */
public final class Reference<V> {

    /**
     * The value of the reference.
     */
    private V value;

    /**
     * Creates a new reference with the given value.
     *
     * @param value The value of the reference
     */
    public Reference(V value) {
        this.value = value;
    }

    /**
     * Gets the value of the reference.
     *
     * @return The value of the reference
     */
    public V get() {
        return this.value;
    }

    /**
     * Sets the value of the reference.
     *
     * @param value The value of the reference
     */
    public void set(V value) {
        this.value = value;
    }

}
