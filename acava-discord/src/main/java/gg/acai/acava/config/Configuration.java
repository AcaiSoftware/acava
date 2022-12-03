package gg.acai.acava.config;

/**
 * @author Clouke
 * @since 03.12.2022 15:11
 * © Acava - All Rights Reserved
 */
public interface Configuration {

    static ConfigurationBuilder newBuilder() {
        return new StandardConfigurationBuilder();
    }

    /**
     * Gets the String value of the given key.
     *
     * @param key The key.
     * @return The value.
     */
    String asString(String key);

    /**
     * Gets the Number value of the given key.
     *
     * @param key The key.
     * @return Returns a new Number instance, containing any number type.
     */
    Number asNumber(String key);

    /**
     * Gets the Boolean value of the given key.
     *
     * @param key The key.
     * @return The value.
     */
    Boolean asBoolean(String key);

}
